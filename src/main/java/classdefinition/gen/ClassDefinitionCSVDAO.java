package classdefinition.gen;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import classdefinition.ClassDefinition;

public class ClassDefinitionCSVDAO implements IClassDefinitionDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassDefinitionCSVDAO.class);
	protected static final String NAME = "Name";
	protected static final String DESCRIPTION = "Description";
	protected String fileName;
	@Override
	public List<ClassDefinition> getAll() {
		try {
			return getAllInternal();
		} catch (IOException e) {
			LOGGER.error("Problems getting all classdefinitions", e);
			return new ArrayList<ClassDefinition>();
		}
	}

	protected List<ClassDefinition> getAllInternal() throws IOException {
		final Reader in = new FileReader(fileName);
		final CSVFormat hdrfmt = CSVFormat.RFC4180
				.builder().setHeader()
                .setSkipHeaderRecord(true)
                .build();
		final Iterable<CSVRecord> records = hdrfmt.parse(in);
		
		final List<ClassDefinition> cds = new ArrayList<>();
		int counter = 0;
		for (final CSVRecord record : records) {
			counter++;
			final ClassDefinition cd = new ClassDefinition();
			if (record.isSet(NAME)) {
				cd.setName(record.get(NAME));
			} else {
				LOGGER.error("{} : {} not set", counter, NAME);
			}
			if (record.isSet(DESCRIPTION)) {
				cd.setDescription(record.get(DESCRIPTION));
			} else {
				LOGGER.error("{} : {} not set", counter, DESCRIPTION);
			}
			cds.add(cd);
		}
		return cds;
	}
	public String getFileName() {
		return fileName;
	}
	public ClassDefinitionCSVDAO setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
}
