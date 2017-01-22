package chatroom;

import static j2html.TagCreator.article;
import static j2html.TagCreator.b;
import static j2html.TagCreator.p;
import static j2html.TagCreator.span;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class Message {
	//Sends a message from one user to all users, along with a list of current usernames
	  public static void broadcastMessage(String sender, String message) {
	      Chat.users.keySet().stream().filter(Session::isOpen).forEach(session -> {
	          try {
	              session.getRemote().sendString(String.valueOf(new JSONObject()
	                  .put("userMessage", createHtmlMessageFromSender(sender, message))
	                  .put("userlist", Chat.users.values())
	              ));
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	      });
	  }

	  //Builds a HTML element with a sender-name, a message, and a timestamp,
	  private static String createHtmlMessageFromSender(String sender, String message) {
	      return article().with(
	              b(sender + " says:"),
	              p(message),
	              span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
	      ).render();
	  }
}
