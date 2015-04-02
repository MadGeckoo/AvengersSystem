import java.util.List;

public interface VillainManager {

	/**
	 * 
	 * @param villain
	 */
	void addVillain(Villain villain);

	/**
	 * 
	 * @param villain
	 */
	void removeVillain(Villain villain);

	/**
	 * 
	 * @param id
	 */
	Villain getVillainByID(Long id);

	/**
	 * 
	 * @param villain
	 */
	void updateVillain(Villain villain);

    List<Villain> getAllVillains();

}