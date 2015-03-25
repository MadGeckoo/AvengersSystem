import java.util.List;

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
	SuperHero getHeroByID(Long id);

	/**
	 * 
	 * @param superHero
	 */
	void updateHero(SuperHero superHero);

    List<SuperHero> getAllSuperHeroes() throws SuperHeroException;

}