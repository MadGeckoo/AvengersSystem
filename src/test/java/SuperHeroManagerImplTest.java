import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuperHeroManagerImplTest {
    SuperHeroManagerImpl heroManager;

    @Before
    public void setUp() throws Exception {
        heroManager = new SuperHeroManagerImpl();

    }

    @Test
    public void testAddHero() throws Exception {
    try
    {
        heroManager.addHero(null);
        fail("Pri pridavani null hera malo vyhoditt NPE");

    }
     catch (NullPointerException ex)
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