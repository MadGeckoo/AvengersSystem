import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by kost on 28. 5. 2015.
 */
public class MissionTableModel extends AbstractTableModel {
        private List<Mission> missions=new ArrayList<Mission>();
        private static Logger log = LoggerFactory.getLogger(MissionTableModel.class);
        private static ResourceBundle bundle = ResourceBundle.getBundle("labelsInApp", Locale.getDefault());



        @Override
        public int getRowCount()
        {
            return missions.size();
        }

        @Override
        public int getColumnCount()
        {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex,int columnIndex)
        {
            Mission mission=missions.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    return mission.getId();
                case 1:
                    return mission.getLocation();
                case 2:
                    return mission.getDate();
                case 3:
                    return mission.getHero();
                case 4:
                    return mission.getVillain();
                case 5:
                    return mission.getHeroWon();
                default:
                    throw new IllegalArgumentException("columnIndex / there is no such column");
            }
        }

        public void createMission(Mission mission)
        {
            log.debug("createMissionTableModel({})");
            missions.add(mission);
            int lastRow=missions.size()-1;
            fireTableRowsInserted(lastRow,lastRow);
        }

        public void removeMission(int row)
        {
            log.debug("removeMissionTableModel({})");
            missions.remove(row);
            fireTableRowsDeleted(row,row);
        }

        @Override
        public String getColumnName(int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    return bundle.getString("MissionID");
                case 1:
                    return bundle.getString("Place");
                case 2:
                    return bundle.getString("Date");
                case 3:
                    return bundle.getString("HeroID");
                case 4:
                    return bundle.getString("VillainID");
                case 5:
                    return bundle.getString("HeroWon");
                default:
                    throw new IllegalArgumentException("columnIndex / thre is no such column");
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    return Long.class;
                case 1:
                    return String.class;
                case 2:
                    return LocalDate.class;
                case 3:
                    return Long.class;
                case 4:
                    return Long.class;
                case 5:
                    return Boolean.class;
                default:
                    throw new IllegalArgumentException("columnIndex");

            }
        }

        @Override
        public void setValueAt(Object aValue,int rowIndex,int columnIndex)
        {
            Mission mission=missions.get(rowIndex);
            switch (columnIndex)
            {
                case 0:
                    mission.setId((Long) aValue);
                    break;
                case 1:
                    mission.setLocation((String) aValue);
                    break;
                case 2:
                    mission.setDate((LocalDate) aValue);
                    break;
                case 3:
                    mission.setHero((SuperHero) aValue);
                    break;
                case 4:
                    mission.setVillain((Villain) aValue);
                    break;
                case 5:
                    mission.setHeroWon((Boolean) aValue);
                default:
                    throw new IllegalArgumentException("columnIndex");

            }
            fireTableCellUpdated(rowIndex,columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex,int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return false;
                default:
                    throw new IllegalArgumentException("columnIndex");
            }
        }
    }


