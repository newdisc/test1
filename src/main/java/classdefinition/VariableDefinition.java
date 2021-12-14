package classdefinition;

import java.util.Arrays;

public class VariableDefinition {
	protected String description;
	protected String name;
	protected String type;
	protected int count;
	public String getDescription() {
		return description;
	}
	public VariableDefinition setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getName() {
		return name;
	}
	public VariableDefinition setName(String name) {
		this.name = name;
		return this;
	}
	public String getType() {
		return type;
	}
	public VariableDefinition setType(String type) {
		this.type = type;
		return this;
	}
	public int getCount() {
		return count;
	}
	public VariableDefinition setCount(int count) {
		this.count = count;
		return this;
	}
	@Override
	public String toString() {
		final Object[] printCols = {name, description, type, count};
		return Arrays.toString(printCols);
	}
}
