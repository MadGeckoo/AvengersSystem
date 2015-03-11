import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class MissionManagerImplTest {
    MissionManagerImpl missionManager;

    @Before
    public void setUp() throws Exception {
        missionManager = new MissionManagerImpl();

    }
    @Test
    public void testCreateMissionWithNull() throws Exception {
        try {
            missionManager.createMission(null);
            fail("Nevyhodilo NPE pre prazdny vstup(null)")
        }catch (NullPointerException ex)
    }
    @Test
     public void testCreateMissionWithID() throws Exception {
        Mission mission = new Mission();

        mission.setLocation("Junkyard");

        Mission mission1 = missionManager.createMission(mission);
        assertThat((int)mission1.getId(),is(not(equalTo(01))));


    }
    @Test
    public void testCreateMissionCanBeRetrieved() throws Exception {
        Mission mission = new Mission();
        mission.setLocation("Junkyard");
        Mission mission1 = missionManager.createMission(mission);
        assertThat(missionManager.findAllMissions(),hasItem(mission1));

    }


    @Test
    public void testCreateMissionWithVillainNull()throws Exception {
        Mission mission=new Mission();
        try{
            mission.setVillan(null);
            fail("Nevyhodilo NPE pre pridanie null villaina");
        }
        catch (NullPointerException ex)

    }

    @Test
    public void testFindAllMissions() throws Exception {

    }

    @Test
    public void testFindMissionsByHero() throws Exception {

    }

    @Test
    public void testFindMissionsByVillain() throws Exception {

    }

    @Test
    public void testFindMissionsHeroVsVillain() throws Exception {

    }

    @Test
    public void testGetMissionByID() throws Exception {

    }

    @Test
    public void testUpdateMission() throws Exception {

    }
}