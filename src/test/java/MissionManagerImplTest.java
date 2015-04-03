import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.xml.bind.ValidationException;

import org.apache.commons.dbcp2.BasicDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class MissionManagerImplTest {
    private MissionManagerImpl missionManager;
    private SuperHeroManagerImpl heroManager;
    private VillainManagerImpl villainManager;
    private DataSource ds;

    private LocalDate preparedDate1;
    private LocalDate preparedDate2;
    @Before
    public void setUp() throws SQLException,ParseException {
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

            conn.prepareStatement("CREATE TABLE VILLAINS ("
                    + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "villainname VARCHAR(30),"
                    + "realname VARCHAR(20),"
                    + "realsurname VARCHAR(40)").executeUpdate();

            conn.prepareStatement("CREATE TABLE MISSIONS ("
                    + "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
                    + "heroid INTEGER NOT NULL,"
                    + "villainid INTEGER NOT NULL,"
                    + "location VARCHAR(40),"
                    + "date DATE NOT NULL,"
                    + "herowon BOOLEAN"
                    + "FOREIGN KEY(heroid) references SUPERHEROES,"
                    + "FOREIGN KEY(villainid) references VILLAINS,").executeUpdate();

            missionManager = new MissionManagerImpl(bds);
            heroManager= new SuperHeroManagerImpl(bds);
            villainManager = new VillainManagerImpl(bds);
        }


        preparedDate1 = date("2015-12-24");
        preparedDate2 = date("2012-07-10");

    }
    @After
    public void tearDown() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            con.prepareStatement("DROP TABLE MISSIONS").executeUpdate();
            con.prepareStatement("DROP TABLE SUPERHEROES").executeUpdate();
            con.prepareStatement("DROP TABLE VILLAINS").executeUpdate();
        }
    }
    /*----------------------NULL TESTS-----------------------*/
    @Test(expected=ValidationException.class)
    public void testCreateMissionWithNullHero() {
        Mission mission = newSummerActionMission();
        mission.setHero(null);
        missionManager.createMission(mission);
    }
    @Test(expected=ValidationException.class)
    public void testCreateMissionWithNullVillain() {
        Mission mission = newSummerActionMission();
        mission.setVillain(null);
        missionManager.createMission(mission);
    }
    @Test(expected=ValidationException.class)
    public void testCreateMissionWithNullLocation() {
        Mission mission = newSummerActionMission();
        mission.setLocation(null);
        missionManager.createMission(mission);
    }
    @Test(expected=ValidationException.class)
    public void testCreateMissionWithNullDate() {
        Mission mission = newSummerActionMission();
        mission.setDate(null);
        missionManager.createMission(mission);
    }

    @Test(expected=ValidationException.class)
    public void testCreateMissionWithNull() {
        missionManager.createMission(null);
    }

    @Test
    public void testCreateMission() {
        Mission mission = newSummerActionMission();
        missionManager.createMission(mission);

        long id = mission.getId();

        assertNotNull(missionManager.getMissionByID(id));
    }


    @Test(expected=ValidationException.class)
     public void testCreateMissionWithID() {

            Mission mission = newWinterActionMission();
            mission.setId((long)1);
            missionManager.createMission(mission);


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
        Mission mission = newSpringActionMission();
        missionManager.createMission(mission);

        assertNull(mission.getDate());

        long id = mission.getId();

        Mission saved = missionManager.getMissionByID(id);
        assertNotNull(saved);

        saved.setDate(preparedDate2);

        missionManager.updateMission(saved);

        Mission updated = missionManager.getMissionByID(id);
        assertNotNull(updated);
        assertNotNull(updated.getDate());
        assertEquals(updated.getDate(), preparedDate2);

    }
    /*---------------------------DATA------------------------------------------------*/
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private LocalDate date(String dateString) throws ParseException {
        LocalDate producedDate = null;
        try{
            producedDate =  dateFormat.parse(dateString);
            return producedDate;
        }
        catch(ParseException ex){}

        return producedDate;
    }

    public static SuperHero newSuperHero(String heroName, String realName, String realSurname) {
        SuperHero hero = new SuperHero();
        hero.setSuperName(heroName);
        hero.setRealName(realName);
        hero.setRealSurname(realSurname);
        return hero;
    }
    private SuperHero newBatman(){
        SuperHero hero = new SuperHero();
        hero.setSuperName("Batman");
        hero.setRealName("Bruce");
        hero.setRealSurname("Wayne");
        heroManager.addHero(hero);
        return hero;
    }
    private SuperHero newSuperMan(){
        SuperHero hero = new SuperHero();
        hero.setSuperName("Superman");
        hero.setRealName("Clark");
        hero.setRealSurname("Kent");
        heroManager.addHero(hero);
        return hero;
    }
    private Villain newJoker(){
        Villain villain = new Villain();
        villain.setVillainName("Joker");
        villain.setRealName("Jack");
        villain.setRealSurname("Napier");
        villainManager.addVillain(villain);
        return villain;
    }
    private Villain newBane(){
        Villain villain = new Villain();
        villain.setVillainName("Bane");
        villain.setRealName("Tom");
        villain.setRealSurname("Hardy");
        villainManager.addVillain(villain);
        return villain;
    }

    private Mission newWinterActionMission(){
        SuperHero hero = newBatman();
        Villain villain = newJoker();
        Mission mission = new Mission();

        mission.setLocation("Arkham");
        mission.setDate(preparedDate1);
        mission.setHero(hero);
        mission.setVillain(villain);
        mission.setHeroWon(true);

        return mission;
    }
    private Mission newSpringActionMission(){
        SuperHero hero = newBatman();
        Villain villain = newJoker();
        Mission mission = new Mission();

        mission.setLocation("Arkham");
        mission.setDate(null);
        mission.setHero(hero);
        mission.setVillain(villain);
        mission.setHeroWon(true);

        return mission;
    }

    private Mission newSummerActionMission(){
        SuperHero hero = newSuperMan();
        Villain villain = newBane();
        Mission mission = new Mission();

        mission.setLocation("Gotham");
        mission.setDate(preparedDate2);
        mission.setHero(hero);
        mission.setVillain(villain);
        mission.setHeroWon(false);

        return mission;
    }

}