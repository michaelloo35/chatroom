package chatroom;

public class User {
	private String username;
	private String currentChannel;

	User(String username, String channel) {
		this.username = username;
		this.currentChannel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCurrentChannel() {
		return currentChannel;
	}

	public void setCurrentChannel(String currentChannel) {
		this.currentChannel = currentChannel;
	}
}
