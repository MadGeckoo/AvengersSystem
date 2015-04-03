import java.util.List;

public interface MissionManager {

	/**
	 * 
	 * @param mission
	 */
	void createMission(Mission mission) throws IllegalEntityException;

	/**
	 * 
	 * @param mission
	 */
	void updateMission(Mission mission) throws IllegalEntityException;

	List<Mission> findAllMissions();

	/**
	 * 
	 * @param id
	 */
	Mission getMissionByID(Long id);

	/**
	 * 
	 * @param superHero
	 */
	List<Mission> findMissionsByHero(SuperHero superHero) throws IllegalEntityException;

	/**
	 * 
	 * @param villain
	 */
	List<Mission> findMissionsByVillain(Villain villain) throws IllegalEntityException;



}