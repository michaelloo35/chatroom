package chatroom;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import static spark.Spark.webSocket;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		Chat chat = new Chat();
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		webSocket("/chat", new ChatWebSocketHandler(chat));
		
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("username", request.cookie("username"));
			model.put("template", "templates/welcome.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		post("/welcome", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String inputtedUsername = request.queryParams("username");
			response.cookie("username", inputtedUsername, 3600);
			model.put("username", inputtedUsername);
			model.put("template", "templates/welcome.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		post("/chat", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("username", request.cookie("username"));
			model.put("channel", request.cookie("currentChannel"));
			return new ModelAndView(model, "templates/chat.vtl");
		}, new VelocityTemplateEngine());

		get("/change", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/changeUsername.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		get("/channels", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			chat.getChannels().refreshChannels(chat.getUsers());
			model.put("template", "templates/channels.vtl");
			model.put("channels", chat.getChannels());
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

		post("/channels", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String inputtedChannel = request.queryParams("channel");
			response.cookie("currentChannel", inputtedChannel, 3600);
			model.put("currentChannel", inputtedChannel);
			model.put("channels", chat.getChannels());
			model.put("template", "templates/channels.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

	}

}
