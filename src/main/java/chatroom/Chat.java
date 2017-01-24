package chatroom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;

public class Chat {

	private Map<Session, User> users;
	private ChannelList channels;
	
	public Chat (){
		this.users = new ConcurrentHashMap<>();
		this.channels = new ChannelList();
	}
	
	
	public Map<Session, User> getUsers() {
		return users;
	}
	public void setUsers(Map<Session, User> users) {
		this.users = users;
	}
	public ChannelList getChannels() {
		return channels;
	}
	public void setChannels(ChannelList channels) {
		this.channels = channels;
	}
	
	
	
}