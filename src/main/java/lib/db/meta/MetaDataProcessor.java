package lib.db.meta;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

public class MetaDataProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaDataProcessor.class);
    private static class DBMetaCallback implements DatabaseMetaDataCallback<List> {
        @Override
        public List processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
            final ArrayList<String> l = new ArrayList();
            ResultSet rs = dbmd.getCatalogs();
            rs = dbmd.getTypeInfo();
            while (rs.next()) {
                
                //LOGGER.info("DATA_TYPE: {}", JDBCType.valueOf(rs.getInt("DATA_TYPE")).getName());
                LOGGER.info("TYPE_NAME: {}, PRECISION {}, NULLABLE {}, AUTO_INCREMENT {}", 
                    rs.getString("TYPE_NAME"),
                    rs.getInt("PRECISION"), 
                    rs.getInt("NULLABLE"),
                    rs.getInt("AUTO_INCREMENT"));
                //l.add(rs.getString(3));
            }
            rs = dbmd.getTables(null, null, null, new String[]{"TABLE"});//dbmd.getUserName() "PUBLIC"
            while (rs.next()) {
                l.add(rs.getString(3));
            }
            return l;
        }
    }
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void printTables() {
        DBMetaCallback getTableNames = new DBMetaCallback();
        try {//TRACE
            List o = JdbcUtils.extractDatabaseMetaData(dataSource, getTableNames);
            LOGGER.info(o.toString());
        } catch (MetaDataAccessException e) {
            LOGGER.error("Error retrieving Tables",e);
        }
    }//C:\dev\progs\hsqldb-2.6.1\hsqldb>java -classpath lib\hsqldb.jar  org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb
    public class StringMapper implements RowMapper<String> {
       public String mapRow(ResultSet rs, int rowNum) throws SQLException {
          return ""
             + Integer.toString(rs.getInt("INT1"))
             + Double.toString(rs.getDouble("DOUBLE1"))
             + rs.getDate("DT1")
             + rs.getTime("T1")
             ;
       }
    }
    public void printTest1() {
        final String SQL = "select * from test1";
        List <String> students = jdbcTemplate.query(SQL, new StringMapper());
        students.forEach(s -> LOGGER.info("Row: {}", s));
    }
}
