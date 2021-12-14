package gen.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import classdefinition.ClassDefinition;
import classdefinition.gen.ClassDefinitionCSVDAO;
import classdefinition.gen.IClassDefinitionDAO;

public class ClassDefinitionDAOTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassDefinitionDAOTest.class);
	private static final String GOODFILE = "./src/test/resources/gen/dao/testClassDefinition.csv";
	@Test
	public void loadClassDefinitions() throws IOException {
		final IClassDefinitionDAO cdd = new ClassDefinitionCSVDAO().setFileName(GOODFILE);
		final List<ClassDefinition> classes = cdd.getAll();
		assertNotNull(classes);
		assertTrue(classes.size() > 0);
		LOGGER.info("Found ClassDefinitions: {}", classes);
	}
}
