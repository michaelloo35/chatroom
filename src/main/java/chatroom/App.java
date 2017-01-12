/*package chatroom;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*; // dzieki static nie musimy pisac spark.get tak by³o w tutorialu
								// source : https://sparktutorials.github.io/2015/08/04/spark-video-tutorials.html
public class App {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		staticFileLocation("/public");
		String layout = "templates/layout.vtl";
		
		get ("/", (request,response) ->{
			@SuppressWarnings("rawtypes")
			HashMap model = new HashMap();
			model.put("template", "templates/login.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());

	
	}
}
*/