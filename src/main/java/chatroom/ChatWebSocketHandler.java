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

	@SuppressWarnings("unused")
	private String sender, msg;
	private Chat chat;
	private ChatBot bot;
	
	
	public ChatWebSocketHandler (Chat chat){
		this.chat = chat;
		this.bot = new ChatBot();
	}
	@OnWebSocketConnect
	public void onConnect(Session user) throws Exception {
		String channel = this.getCookieByName(user, "currentChannel");
		chat.getUsers().put(user, new User(this.getCookieByName(user, "username"), channel));
		chat.getChannels().joinOrCreateChannel(channel);
		Message.broadcastMessage(sender = "Server",
				msg = (chat.getUsers().get(user).getUsername() + " joined the channel " + channel),channel,chat);
		if (channel.equals("chatbot"))
			Message.broadcastMessage("ChatBot", msg = bot.Response("greeting"), "chatbot", chat);
			
	}

	@OnWebSocketClose
	public void onClose(Session user, int statusCode, String reason) {
		String username = chat.getUsers().get(user).getUsername();
		String channel = chat.getUsers().get(user).getCurrentChannel();
		chat.getUsers().remove(user);
		Message.broadcastMessage(sender = "Server",
				msg = (username + " left the channel " + channel),channel,chat);
		chat.getChannels().refreshChannels(chat.getUsers());
	}

	@OnWebSocketMessage
	public void onMessage(Session user, String message) {
		String channel = this.getCookieByName(user, "currentChannel");
		Message.broadcastMessage(sender = chat.getUsers().get(user).getUsername(), msg = message,channel,chat);
		if (channel.equals("chatbot"))
			Message.broadcastMessage("ChatBot", msg = bot.Response(message), "chatbot", chat);
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