package warehouse_api.config;

//import org.dbunit.dataset.CompositeDataSet;
//import org.dbunit.dataset.DataSetException;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.ReplacementDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import javax.ejb.EJB;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BaseTest {

//    @EJB
//    private DataSource dataSource;
//
//    private String fixturesPath = "/fixtures";
//
//    protected void loacData() throws DataSetException, SQLException {
////        cleanDatabase();
//        System.out.println("DataSource is null = " + dataSource == null);
//
//        Connection connection = dataSource.getConnection();
//        IDataSet data = getDataSet();
//        ReplacementDataSet rDataSet = new ReplacementDataSet(data);
//
////        rDataSet.addReplacementObject("[create_date]", dateHelper.addDayCount(new Date(), 0));
//    }
//
//    IDataSet getDataSet() throws DataSetException {
//        List<String> items = Arrays.asList(getFixtures());
//        InputStream[] streams = new InputStream[getFixtures().length];
//
//        for (int i = 0; i < items.size(); i++) {
//            streams[i] = getFixtureStream(items.get(i));
//        }
//
//        IDataSet[] dataSets = new IDataSet[streams.length];
//
//        for (int i = 0; i < streams.length; i++) {
//            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
//            builder.setColumnSensing(true);
//            dataSets[i] = builder.build(streams[i]);
//        }
//
//        return new CompositeDataSet(dataSets);
//    }
//
//    protected String[] getFixtures() {
//        return new String[]{};
//    }
//
//    protected InputStream getFixtureStream(String fixture) {
//        return BaseTest.class.getResourceAsStream(fixturesPath + fixture);
//    }
}
