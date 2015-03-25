import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by kost on 25. 3. 2015.
 */
public class Main {

    public static void main(String[] args) throws IOException
    {
        Properties myconf = new Properties();
        myconf.load(Main.class.getResourceAsStream("/myconf.properties"));

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(myconf.getProperty("jdbc.url"));
        ds.setUsername(myconf.getProperty("jdbc.user"));
        ds.setPassword(myconf.getProperty("jdbc.password"));

        SuperHeroManager heroManager = new SuperHeroManagerImpl(ds);

        List<SuperHero> allSuperHeroes = heroManager.getAllSuperHeroes();
        //allSuperHeroes.forEach(System.out::println);

    }
}
