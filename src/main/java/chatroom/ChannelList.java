package chatroom;
import java.util.concurrent.ConcurrentLinkedQueue;
public class ChannelList {
	private ConcurrentLinkedQueue<String> channels;

	public ConcurrentLinkedQueue<String> getChannels() {
		return channels;
	}

	public void setChannels(ConcurrentLinkedQueue<String> channels) {
		this.channels = channels;
	}

	ChannelList() {
		this.channels = new ConcurrentLinkedQueue<String>();
	}

	public void joinOrCreateChannel(String channel) {
		if(this.channels.stream().noneMatch(x -> x.equals(channel)))
			this.channels.add(channel);
	}

	private void removeChannel(String channel) {
		this.channels.remove(channel);
	}

	public void refreshChannels() {
		for (String channel : this.channels)
			if (Chat.users.values().stream().noneMatch(x -> channel.equals(x.getCurrentChannel())))
				removeChannel(channel);
	}
	
	
	

}
