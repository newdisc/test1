package classdefinition.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classdefinition.ClassDefinition;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import lib.vertx.IHandlerProvider;

public class ClassDefinitionController implements IHandlerProvider {
	protected ClassDefinitionService classDefinitionService;
	public class GetAllHandler implements Handler<RoutingContext> {
		@Override
		public void handle(RoutingContext ctx) {
			List<ClassDefinition> clds = classDefinitionService.getAll();
			HttpServerResponse response = ctx.response();
			final JsonArray ja = new JsonArray(clds);
			final String jos = ja.encodePrettily();
			response.headers()
		    	.add("Content-Length", String.valueOf(jos.length()))
		    	.add("Content-Type", "application/json");
			response.write(jos);
			ctx.end();
		}
	}
	@Override
	public Map<String, Handler<RoutingContext>> getHandlers() {
		final HashMap<String, Handler<RoutingContext>> handlers = new HashMap<>();
		handlers.put("/classdef/getAll", new GetAllHandler());
		return handlers;
	}
	public ClassDefinitionController setClassDefinitionService(ClassDefinitionService classDefinitionService) {
		this.classDefinitionService = classDefinitionService;
		return this;
	}
}
