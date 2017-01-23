package chatroom;

import java.net.HttpCookie;
import java.util.List;

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
		String channel = this.getCookieByName(user, "currentChannel");
		Chat.users.put(user, new User(this.getCookieByName(user, "username"), channel));
		Chat.channels.joinOrCreateChannel(channel);
		Message.broadcastMessage(sender = "Server",
				msg = (Chat.users.get(user).getUsername() + " joined the channel " + channel),channel);
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		String username = Chat.users.get(user).getUsername();
		String channel = Chat.users.get(user).getCurrentChannel();
		Chat.users.remove(user);
		Message.broadcastMessage(sender = "Server",
				msg = (username + " left the channel " + channel),channel);
		Chat.channels.refreshChannels();
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		String channel = this.getCookieByName(user, "currentChannel");
		Message.broadcastMessage(sender = Chat.users.get(user).getUsername(), msg = message,channel);
	}

	private String getCookieByName(Session user, String name) {
		List<HttpCookie> userCookies = user.getUpgradeRequest().getCookies();
		if (userCookies == null)
			return null;
		
		for (HttpCookie cookie : userCookies)
			if (cookie.getName().equals(name))
				return cookie.getValue();
				
		return null;
	}


}