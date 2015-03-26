import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;


import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by kost on 25. 3. 2015.
 */
public class Main {

    public static void main(String[] args) throws IOException,SuperHeroException
    {
        Properties myconf = new Properties();
        myconf.load(Main.class.getResourceAsStream("/myconf.properties"));

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(myconf.getProperty("jdbc.url"));
        ds.setUsername(myconf.getProperty("jdbc.user"));
        ds.setPassword(myconf.getProperty("jdbc.password"));

        System.out.println("Povedlo se");
        SuperHeroManager heroManager = new SuperHeroManagerImpl(ds);

        System.out.println("Povedlo se");
        List<SuperHero> allSuperHeroes = heroManager.getAllSuperHeroes();
        allSuperHeroes.forEach(System.out::println);

        System.out.println("Povedlo se");

        //SuperHero hero=new SuperHero(0l,"Petrik","Peter","Pavlovic");
        //heroManager.addHero(hero);
        //System.out.println("Povedlo se pridat hera");
        //System.out.println("hero="+hero);

        heroManager.getAllSuperHeroes().forEach(System.out::println);

        //SuperHero auxHero=heroManager.getHeroByID(1l);
        //auxHero.setSuperName("Petrixio");
        //heroManager.updateHero(auxHero);

        //heroManager.getAllSuperHeroes().forEach(System.out::println);

       // SuperHero auxHero=heroManager.getHeroByID(3l);
        //heroManager.removeHero(auxHero);

        //System.out.println("Povedlo se");

       // heroManager.getAllSuperHeroes().forEach(System.out::println);




    }
}
