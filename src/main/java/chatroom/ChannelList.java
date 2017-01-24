package chatroom;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.Session;
public class ChannelList {
	private ConcurrentLinkedQueue<String> channels;

	
	ChannelList() {
		this.channels = new ConcurrentLinkedQueue<String>();
		this.channels.add("chatbot");
	}
	
	public ConcurrentLinkedQueue<String> getChannels() {
		return channels;
	}

	public void setChannels(ConcurrentLinkedQueue<String> channels) {
		this.channels = channels;
	}

	public void joinOrCreateChannel(String channel) {
		if(this.channels.stream().noneMatch(x -> x.equals(channel)))
			this.channels.add(channel);
	}

	private void removeChannel(String channel) {
		this.channels.remove(channel);
	}

	public void refreshChannels(Map<Session, User> users) {
		for (String channel : this.channels)
			if (!channel.equals("chatbot"))
				if (users.values().stream().noneMatch(x -> channel.equals(x.getCurrentChannel())))
				removeChannel(channel);
	}
	
	
	

}
