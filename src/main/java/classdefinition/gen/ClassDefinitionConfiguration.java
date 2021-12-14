package classdefinition.gen;

public class ClassDefinitionConfiguration {
	protected IClassDefinitionDAO classDefinitionDAO;
	protected ClassDefinitionService classDefinitionService;
	protected ClassDefinitionController classDefinitionController;
	public IClassDefinitionDAO getClassDefinitionDAO() {
		return classDefinitionDAO;
	}
	public ClassDefinitionService getClassDefinitionService() {
		return classDefinitionService;
	}
	public ClassDefinitionController getClassDefinitionController() {
		return classDefinitionController;
	}
	public ClassDefinitionConfiguration init() {
		classDefinitionDAO = new ClassDefinitionCSVDAO().setFileName("classDefinition.csv");
		classDefinitionService = new ClassDefinitionService().setClassDefinitionDAO(getClassDefinitionDAO());
		classDefinitionController = new ClassDefinitionController().setClassDefinitionService(getClassDefinitionService());
		return this;
	}
}
