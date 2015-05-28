import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
//import javax.swing.table.AbstractTableModel;

/**
 * Created by kost on 28. 5. 2015.
 */
public class HeroesTableModel extends AbstractTableModel {
    private List<SuperHero> heroes=new ArrayList<SuperHero>();
    private static Logger log = LoggerFactory.getLogger(HeroesTableModel.class);
    private static ResourceBundle bundle = ResourceBundle.getBundle("labelsInApp", Locale.getDefault());
    private SuperHeroManager heroManager;

    public void setHeroManager(SuperHeroManager heroManager) {
        this.heroManager = heroManager;
    }

    @Override
    public int getRowCount()
    {
        return heroes.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex,int columnIndex)
    {
        SuperHero hero=heroes.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return hero.getId();
            case 1:
                return hero.getSuperName();
            case 2:
                return hero.getRealName();
            case 3:
                return hero.getRealSurname();
            default:
                throw new IllegalArgumentException("columnIndex / there is no such column");
        }
    }

    public void createHero(SuperHero hero)
    {
        log.debug("createHeroTableModel({})");
        heroes.add(hero);
        int lastRow=heroes.size()-1;
        fireTableRowsInserted(lastRow,lastRow);
    }

    public void removeHero(int row)
    {
        log.debug("removeHeroTableModel({})");
        heroes.remove(row);
        fireTableRowsDeleted(row,row);
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return bundle.getString("HeroID");
            case 1:
                return bundle.getString("SuperName");
            case 2:
                return bundle.getString("RealName");
            case 3:
                return bundle.getString("RealSurname");
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
            case 2:
            case 3:
                return String.class;
            default:
                throw new IllegalArgumentException("columnIndex");

        }
    }

    @Override
    public void setValueAt(Object aValue,int rowIndex,int columnIndex)
    {
        SuperHero hero=heroes.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                hero.setId((Long) aValue);
                break;
            case 1:
                hero.setSuperName((String) aValue);
                break;
            case 2:
                hero.setRealName((String) aValue);
                break;
            case 3:
                hero.setRealSurname((String) aValue);
                break;
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
                return false;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
}
