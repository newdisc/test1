package classdefinition;

import java.util.Arrays;

public class ClassDefinition {
	protected String name;
	protected String description;
	public String getName() {
		return name;
	}
	public ClassDefinition setName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public ClassDefinition setDescription(String description) {
		this.description = description;
		return this;
	}
	@Override
	public String toString() {
		final Object[] printCols = {name, description};
		return Arrays.toString(printCols);
	}
}
