import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VillainManagerImplTest {
    VillainManagerImpl villainManager;

    @Before
    public void setUp() throws Exception {
        villainManager=new VillainManagerImpl();
    }

    @Test
    public void testAddVillain() throws Exception {
        try
        {
            villainManager.addVillain(null);
            fail("Pri pridavani null villaina malo vyhoditt NPE");

        }
        catch (NullPointerException ex)
        {


        }
    }


    @Test
    public void testGetVillainByID() throws Exception {

    }

    @Test
    public void testRemoveVillain() throws Exception {

    }

    @Test
    public void testUpdateVillain() throws Exception {

    }
}