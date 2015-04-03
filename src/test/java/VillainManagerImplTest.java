import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class VillainManagerImplTest {
    private VillainManagerImpl villainManager;
    private javax.sql.DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:derby:memory:missionTest;create=true");
        this.dataSource = bds;
        //create new empty table before every test
        try (Connection conn = bds.getConnection()) {
            conn.prepareStatement("CREATE TABLE VILLAINS ("
                    + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "villainname VARCHAR(30),"
                    + "realname VARCHAR(20),"
                    + "realsurname VARCHAR(40))").executeUpdate();

        }
        villainManager = new VillainManagerImpl(bds);
    }
    @After
    public void tearDown() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            con.prepareStatement("DROP TABLE VILLAINS").executeUpdate();
        }
    }

    @Test
    public void testAddVillain() throws ParseException {
        Villain villain = new Villain(null,"Spidervepr", "Peter", "Parek");

        villainManager.addVillain(villain);
        Long id = villain.getId();

        Villain found = villainManager.getVillainByID(id);

        assertNotNull(found);
        assertEquals(villain, found);

        /*try
        {
            villainManager.addVillain(null);
            fail("Pri pridavani null villaina malo vyhoditt NPE");

        }
        catch (NullPointerException ex)
        {


        }*/
    }


    @Test
    public void testGetVillainByID() throws ParseException {
        Villain villain = newVillain("Spidervepr", "Peter", "Parek");

        villainManager.addVillain(villain);
        Long id = villain.getId();

        Villain returned = villainManager.getVillainByID(id);

        assertNotNull(returned);
        assertEquals(returned.getVillainName(), "Spidervepr");

    }

    @Test
    public void testRemoveVillain() throws ParseException {
        Villain villain = newVillain("Spidervepr", "Peter", "Parek");

        villainManager.addVillain(villain);
        Long id = villain.getId();

        assertNotNull(villainManager.getVillainByID(id));

        villainManager.removeVillain(villain);
        assertNull(villainManager.getVillainByID(id));

    }

    @Test
    public void testUpdateVillain() throws ParseException {
        Villain villain = newVillain("Spidervepr", "Peter", "Parek");

        villainManager.addVillain(villain);
        Long id = villain.getId();

        villain.setVillainName("HarryPoser");

        assertEquals(villain.getVillainName(), "HarryPoser");

        villainManager.updateVillain(villain);
        villain = villainManager.getVillainByID(id);

        assertEquals(villain.getVillainName(), "HarryPoser");

    }
    public static Villain newVillain(String villainName, String realName, String realSurname) {
        Villain villain = new Villain();
        villain.setVillainName(villainName);
        villain.setRealName(realName);
        villain.setRealSurname(realSurname);
        return villain;
    }
}