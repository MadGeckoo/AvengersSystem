public interface SuperHeroManager {

	/**
	 * 
	 * @param superHero
	 */
	void addHero(SuperHero superHero);

	/**
	 * 
	 * @param superHero
	 */
	void removeHero(SuperHero superHero);

	/**
	 * 
	 * @param id
	 */
	SuperHero getHeroByID(long id);

	/**
	 * 
	 * @param superHero
	 */
	void updateHero(SuperHero superHero);

}