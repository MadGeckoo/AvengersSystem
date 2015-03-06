import java.util.List;

public interface MissionManager {

	/**
	 * 
	 * @param mission
	 */
	void createMission(Mission mission);

	/**
	 * 
	 * @param mission
	 */
	void updateMission(Mission mission);

	List<Mission> findAllMissions();

	/**
	 * 
	 * @param id
	 */
	Mission getMissionByID(long id);

	/**
	 * 
	 * @param superHero
	 */
	List<Mission> findMissionsByHero(SuperHero superHero);

	/**
	 * 
	 * @param villain
	 */
	List<Mission> findMissionsByVillain(Villain villain);

	/**
	 * 
	 * @param superHero
	 * @param villain
	 */
	List<Mission> findMissionsHeroVsVillain(SuperHero superHero, Villain villain);

}