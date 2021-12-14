package lib.vertx;

import java.util.Map;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public interface IHandlerProvider {
	public Map<String, Handler<RoutingContext>> getHandlers();
}
