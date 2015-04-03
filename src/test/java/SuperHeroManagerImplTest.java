import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class SuperHeroManagerImplTest {
    private SuperHeroManagerImpl heroManager;
    private javax.sql.DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:derby:memory:missionTest;create=true");
        this.dataSource = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE SUPERHEROES ("
                    + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "supername VARCHAR(30),"
                    + "realname VARCHAR(20),"
                    + "realsurname VARCHAR(40)").executeUpdate();
        }


        /*Properties myconf = new Properties();
        myconf.load(Main.class.getResourceAsStream("/myconf.properties"));

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(myconf.getProperty("jdbc.url"));
        ds.setUsername(myconf.getProperty("jdbc.user"));
        ds.setPassword(myconf.getProperty("jdbc.password"));*/

        heroManager = new SuperHeroManagerImpl(bds);

    }
    @After
    public void tearDown() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            //con.prepareStatement("DROP TABLE MISSIONS").executeUpdate();
            con.prepareStatement("DROP TABLE SUPERHEROES").executeUpdate();
            //con.prepareStatement("DROP TABLE VILLAINS").executeUpdate();
        }
    }

    @Test
    public void testAddHero() throws ParseException {

        SuperHero hero = newSuperHero("Superman", "Clark", "Kent");

        heroManager.addHero(hero);
        Long id = hero.getId();

        SuperHero found = heroManager.getHeroByID(id);

        assertNotNull(found);
        assertEquals(hero, found);
    /*try
    {
        heroManager.addHero(null);
        fail("Pri pridavani null hera malo vyhoditt NPE");

    }
     catch (NullPointerException ex)
     {


     }*/
    }

    @Test
    public void testGetHeroByID() throws Exception {
        SuperHero hero = newSuperHero("Superman", "Clark", "Kent");

        heroManager.addHero(hero);
        Long id = hero.getId();

        SuperHero returned = heroManager.getHeroByID(id);

        assertNotNull(returned);
        assertEquals(returned.getSuperName(), "Superman");

    }

    @Test
    public void testRemoveHero() throws ParseException {
        SuperHero hero = newSuperHero("Superman", "Clark", "Kent");

        heroManager.addHero(hero);
        Long id = hero.getId();

        assertNotNull(heroManager.getHeroByID(id));

        heroManager.removeHero(hero);
        assertNull(heroManager.getHeroByID(id));

    }

    @Test
    public void testUpdateHero() throws ParseException {

        SuperHero hero = newSuperHero("Superman", "Clark", "Kent");

        heroManager.addHero(hero);
        Long id = hero.getId();

        hero.setSuperName("ManOfSteel");

        assertEquals(hero.getSuperName(), "ManOfSteel");

        heroManager.updateHero(hero);
        hero = heroManager.getHeroByID(id);

        assertEquals(hero.getSuperName(), "ManOfSteel");
    }

    public static SuperHero newSuperHero(String heroName, String realName, String realSurname) {
        SuperHero hero = new SuperHero();
        hero.setSuperName(heroName);
        hero.setRealName(realName);
        hero.setRealSurname(realSurname);
        return hero;
    }


}