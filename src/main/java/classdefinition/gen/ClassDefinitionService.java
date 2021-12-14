package classdefinition.gen;

import java.util.List;

import classdefinition.ClassDefinition;

public class ClassDefinitionService {
	IClassDefinitionDAO classDefinitionDAO;
	public ClassDefinitionService setClassDefinitionDAO(final IClassDefinitionDAO dao) {
		classDefinitionDAO = dao;
		return this;
	}
	public List<ClassDefinition> getAll(){
		return classDefinitionDAO.getAll();
	}
}
