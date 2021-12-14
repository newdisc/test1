package lib.vertx;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import classdefinition.gen.ClassDefinitionConfiguration;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class DefaultVertxServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultVertxServer.class);
	public static final String STATIC_ROOT = "/ui";
	public static final String API_ROOT = "/api";
	public static final String SHUTDOWN = "/shutdown";
	protected VertxOptions options;
	protected Vertx myVertx;
	protected HttpServer server;
	protected Router rootRouter;
	protected Router staticRouter;
	protected Router apiRouter;
	protected String rootPath;
	
	public Router getApiRouter() {
		return apiRouter;
	}

	public DefaultVertxServer setOptions(VertxOptions options) {
		this.options = options;
		options.setBlockedThreadCheckInterval(1000*60*60);
		myVertx = Vertx.vertx(options);
		LOGGER.info("Started router with options: {}", options);
		return this;
	}

	public DefaultVertxServer setRootPath(String rootPath) {
		this.rootPath = rootPath;
		server = myVertx.createHttpServer();
		rootRouter = Router.router(myVertx);
		apiRouter = Router.router(myVertx);
		rootRouter.mountSubRouter(rootPath + API_ROOT, apiRouter);
		rootRouter.route(SHUTDOWN).handler(ctx -> {
			LOGGER.info("Shutting down server");
			myVertx.close();
			//server.close();//This does not work - exception and java proc remains
		});
		return this;
	}
	
	private DefaultVertxServer setStaticHandler(final StaticHandler sh) {
		staticRouter = Router.router(myVertx);
		staticRouter.get("/*").handler(sh);
		rootRouter.mountSubRouter(rootPath + STATIC_ROOT, staticRouter);
		return this;
	}
	
	public DefaultVertxServer setStaticLocation(final String cpLoc) {
		final StaticHandler sh;
		if (null != cpLoc) {
			LOGGER.info("Serving static content from: {}", cpLoc);
			sh = StaticHandler.create(cpLoc);
		} else {
			sh = StaticHandler.create();
		}
		sh.setDirectoryListing(true);
		sh.setCachingEnabled(false);
		setStaticHandler(sh);
		return this;
	}

	public DefaultVertxServer addApiHandler(final String path, 
			final Handler<RoutingContext> handler) {
		apiRouter.route(path).handler(handler);
		return this;
	}
	
	public DefaultVertxServer run(final int port) {
		LOGGER.info("Listing Routes");
		for (final Route r : rootRouter.getRoutes()) {
			LOGGER.info("Handling {} with: {}", r.getPath(), r);
		}		
		server.requestHandler(rootRouter).listen(port);
		return this;
	}

	//
	public static void main(String[] args) {
		final DefaultVertxServer dvs = new DefaultVertxServer();
		dvs.setOptions(new VertxOptions());
		dvs.setRootPath("/test");// 
		if (args.length != 0) {
			LOGGER.info("Arguments: {}", Arrays.toString(args));
			dvs.setStaticLocation(args[0]);//"META-INF/resources"
		} else {
			dvs.setStaticLocation(null);
		}
		ClassDefinitionConfiguration cdc = new ClassDefinitionConfiguration();
		cdc.init();
		cdc.getClassDefinitionController().getHandlers().forEach((k,v) -> dvs.addApiHandler(k, v));

		dvs.run(8080);
	}
}
/*
		router.route().handler(ctx -> {

		  // This handler will be called for every request
		  HttpServerResponse response = ctx.response();
		  response.putHeader("content-type", "text/plain");

		  // Write to the response and end it
		  response.end("Hello World from Vert.x-Web!");
		});

		server.requestHandler(router).listen(8080);		

 * 
 * */




