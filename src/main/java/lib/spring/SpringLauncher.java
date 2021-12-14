package lib.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import classdefinition.gen.ClassDefinitionConfiguration;
import lib.db.meta.MetaDataProcessor;
import lib.vertx.DefaultVertxServer;

public class SpringLauncher {
    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        final MetaDataProcessor mdp = (MetaDataProcessor)context.getBean("metaDataProcessor");
        mdp.printTables();
        mdp.printTest1();
        //runVertx(context);
        context.close();
   }

	private static void runVertx(final ClassPathXmlApplicationContext context) {
		final ClassDefinitionConfiguration cdc = (ClassDefinitionConfiguration)context.getBean("classDefinitionConfiguration");
        final DefaultVertxServer dvs = (DefaultVertxServer)context.getBean("webServer");
        dvs.setStaticLocation(null);
        cdc.getClassDefinitionController().getHandlers().forEach((k,v) -> dvs.addApiHandler(k, v));
        dvs.run(8080);
	}
}
