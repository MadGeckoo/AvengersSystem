import org.apache.commons.dbcp2.BasicDataSource;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by kost on 14. 5. 2015.
 */
public class AvengersForm extends javax.swing.JFrame  {
    BasicDataSource basicDataSource = new BasicDataSource();
    private static final Logger LOGGER = Logger.getLogger(AvengersForm.class.getName());
    MissionManager missionManager;
    SuperHeroManager heroManager;
    VillainManager villainManager;
    MissionTableModel rentsTableModel;
    HeroesTableModel heroesTableModel;
    VillainTableModel villainTableModel;
    private SuperheroesSwingWorker superheroesSwingWorker;


    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox comboBox1;
    private JButton aktualizovaťHrdinuButton;
    private JButton pridaťNovéhoHrdinuButton;
    private JButton odstrániťHrdinuButton;
    private JButton odstrániťZloduchaButton;
    private JButton pridaťZloduchaButton;
    private JButton aktualizovaťZloduchaButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox2;
    private JButton pridaťMisiuButton;
    private JButton vyhľadajMisiePreHrdinuButton;
    private JButton vyhľadajMisiePreZloduchaButton;
    private JButton aktualizovaťMisiuButton;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JComboBox comboBox3;
    private JTable table1;
    private JTable table2;
    private JTable jTableMissions;


    private class SuperheroesSwingWorker extends SwingWorker<Void, SuperHero>{
        @Override
        protected Void doInBackground() throws Exception {
            heroesTableModel = (HeroesTableModel) table2.getModel();
            heroesTableModel.setHeroManager(heroManager);
            for (SuperHero hero : heroManager.getAllSuperHeroes()) {
                publish(hero);
            }
            return null;
        }
        @Override
        protected void process(List<SuperHero> items) {
            for (SuperHero i : items) {
                heroesTableModel.createHero(i);
            }

        }
        @Override
        protected void done() {
            superheroesSwingWorker = null;
        }

    }

    private VillanSwingWorker villanSwingWorker;
    private class VillanSwingWorker extends SwingWorker<Void, Villain>{
        @Override
        protected Void doInBackground() throws Exception{
            villainTableModel = (VillainTableModel) table1.getModel();
            villainTableModel.setVillainManager(villainManager);
            for (Villain villain : villainManager.getAllVillains()) {
                publish(villain);
            }
            return null;
        }
        @Override
        protected void process(List<Villain> items) {
            for (Villain i : items) {
                villainTableModel.createVillain(i);
            }
        }

        @Override
        protected void done() {
            villanSwingWorker = null;
        }
    }

    private MissionSwingWorker missionSwingWorker;
    private class MissionSwingWorker extends SwingWorker<Void, Mission>{
        @Override
        protected Void doInBackground() throws Exception {
            rentsTableModel = (MissionTableModel) jTableMissions.getModel();
            rentsTableModel.setMissionManager(missionManager);
            for (Mission mission : missionManager.findAllMissions()) {
                publish(mission);
            }
            return null;
        }

        @Override
        protected void process(List<Mission> items) {
            for (Mission i : items) {
                rentsTableModel.createMission(i);
            }
        }

        @Override
        protected void done() {
            missionSwingWorker = null;
        }
    }

    private void setUp() throws Exception {
        Properties configFile = new Properties();
        configFile.load(new FileInputStream("src/main/java/db.properties"));
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(configFile.getProperty("jdbc.drivers"));
        bds.setUrl(configFile.getProperty("jdbc.url"));
        bds.setUsername(configFile.getProperty("jdbc.username"));
        bds.setPassword(configFile.getProperty("jdbc.password"));
        basicDataSource = bds;
    }

    public AvengersForm() {
        table2.setModel(new HeroesTableModel());


    }
    public static void main (String[] args){

    }
}
