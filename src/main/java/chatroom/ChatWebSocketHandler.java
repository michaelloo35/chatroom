package chatroom;

import java.net.HttpCookie;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocketHandler {

    private String sender, msg;

    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
    	Chat.users.put(user,this.getCookieByName(user, "username"));
        Message.broadcastMessage(sender = "Server", msg = (Chat.users.get(user) + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        String username = Chat.users.get(user);
        Chat.users.remove(user);
        Message.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        Message.broadcastMessage(sender = Chat.users.get(user), msg = message);
    }
    

    private String getCookieByName(Session user,String name) {
    	
    if (user.getUpgradeRequest().getCookies() == null) {
        return null;
    }
    for (HttpCookie cookie : user.getUpgradeRequest().getCookies()){
    	if (cookie.getName().equals(name)) {
            return cookie.getValue();
        }
    }
    return null;
}


}