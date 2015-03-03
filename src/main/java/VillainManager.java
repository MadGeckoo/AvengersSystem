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
	Villain getVillainByID(long id);

	/**
	 * 
	 * @param villain
	 */
	void updateVillain(Villain villain);

}