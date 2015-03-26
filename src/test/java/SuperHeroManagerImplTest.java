import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.activation.DataSource;

import java.util.Properties;

import static org.junit.Assert.*;

public class SuperHeroManagerImplTest {
    SuperHeroManagerImpl heroManager;

    @Before
    public void setUp() throws Exception {
        Properties myconf = new Properties();
        myconf.load(Main.class.getResourceAsStream("/myconf.properties"));

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(myconf.getProperty("jdbc.url"));
        ds.setUsername(myconf.getProperty("jdbc.user"));
        ds.setPassword(myconf.getProperty("jdbc.password"));

        heroManager = new SuperHeroManagerImpl(ds);

    }

    @Test
    public void testAddHero() throws Exception {
    try
    {
        heroManager.addHero(null);
        fail("Pri pridavani null hera malo vyhoditt NPE");

    }
     catch (NullPointerException ex)
     {


     }
    }

    @Test
    public void testGetHeroByID() throws Exception {

    }

    @Test
    public void testRemoveHero() throws Exception {

    }

    @Test
    public void testUpdateHero() throws Exception {

    }
}