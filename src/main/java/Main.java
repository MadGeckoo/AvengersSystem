
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;


import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by kost on 25. 3. 2015.
 */
public class Main {

    public static void main(String[] args) throws IOException
    {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        VillainManager villainManager = ctx.getBean(VillainManager.class);
        SuperHeroManager heroManager = ctx.getBean(SuperHeroManager.class);
        MissionManager missionManager=ctx.getBean(MissionManager.class);

        villainManager.getAllVillains().forEach(System.out::println);
        heroManager.getAllSuperHeroes().forEach(System.out::println);

        Villain villain=new Villain(null,"Jurko","Juraj","Mikula");
        villainManager.addVillain(villain);

        SuperHero hero=new SuperHero(null,"Petrik","Peter","Pavlovic");
        heroManager.addHero(hero);

      /*  Mission mission=new Mission(null,"Hnojisko",)*/
        //Properties myconf = new Properties();
        //myconf.load(Main.class.getResourceAsStream("/myconf.properties"));

        //BasicDataSource ds = new BasicDataSource();
        //ds.setUrl(myconf.getProperty("jdbc.url"));
        //ds.setUsername(myconf.getProperty("jdbc.user"));
        //ds.setPassword(myconf.getProperty("jdbc.password"));

        //System.out.println("Povedlo se");
        //SuperHeroManager heroManager = new SuperHeroManagerImpl(ds);

        //System.out.println("Povedlo se");
        //List<SuperHero> allSuperHeroes = heroManager.getAllSuperHeroes();
        //allSuperHeroes.forEach(System.out::println);

        //System.out.println("Povedlo se");


        //System.out.println("Povedlo se pridat hera");
        //System.out.println("hero="+hero);

        //heroManager.getAllSuperHeroes().forEach(System.out::println);

        //SuperHero auxHero=heroManager.getHeroByID(1l);
        //auxHero.setSuperName("Petrixio");
        //heroManager.updateHero(auxHero);

        //heroManager.getAllSuperHeroes().forEach(System.out::println);

       // SuperHero auxHero=heroManager.getHeroByID(3l);
        //heroManager.removeHero(auxHero);

        //System.out.println("Povedlo se");

       // heroManager.getAllSuperHeroes().forEach(System.out::println);
    }

    @Configuration
    @EnableTransactionManagement
    @PropertySource("classpath:myconf.properties")
    public static class SpringConfig
    {
        @Autowired
        Environment env;

        @Bean
        public DataSource dataSource() {
            BasicDataSource bds = new BasicDataSource(); //Apache DBCP connection pooling DataSource
            bds.setDriverClassName(env.getProperty("jdbc.driver"));
            bds.setUrl(env.getProperty("jdbc.url"));
            bds.setUsername(env.getProperty("jdbc.user"));
            bds.setPassword(env.getProperty("jdbc.password"));
            return bds;
        }

        @Bean //potřeba pro @EnableTransactionManagement
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        public VillainManager villainManager()
        {
            return new VillainManagerImpl(dataSource());
        }

        @Bean
        public SuperHeroManager heroManager()
        {
            return new SuperHeroManagerImpl(new TransactionAwareDataSourceProxy(dataSource()));
        }

        @Bean
        public MissionManager missionManager()
        {
            MissionManagerImpl missionManager=new MissionManagerImpl(dataSource());
            missionManager.setSuperHeroManager(heroManager());
            missionManager.setVillainManager(villainManager());
            return missionManager;
        }
    }
}
