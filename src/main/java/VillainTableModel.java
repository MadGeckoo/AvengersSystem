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
public class VillainTableModel extends AbstractTableModel {
    private List<Villain> villains=new ArrayList<Villain>();
    private static Logger log = LoggerFactory.getLogger(VillainTableModel.class);
    private static ResourceBundle bundle = ResourceBundle.getBundle("labelsInApp", Locale.getDefault());
    private VillainManager villainManager;

    public void setVillainManager(VillainManager villainManager) {
        this.villainManager = villainManager;
    }

    @Override
    public int getRowCount()
    {
        return villains.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex,int columnIndex)
    {
        Villain villain=villains.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return villain.getId();
            case 1:
                return villain.getVillainName();
            case 2:
                return villain.getRealName();
            case 3:
                return villain.getRealSurname();
            default:
                throw new IllegalArgumentException("columnIndex / there is no such column");
        }
    }

    public void createVillain(Villain villain)
    {
        log.debug("createVillainTableModel({})");
        villains.add(villain);
        int lastRow=villains.size()-1;
        fireTableRowsInserted(lastRow,lastRow);
    }

    public void removeVillain(int row)
    {
        log.debug("removeVillainTableModel({})");
        villains.remove(row);
        fireTableRowsDeleted(row,row);
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        switch (columnIndex)
        {
            case 0:
                return bundle.getString("VillainID");
            case 1:
                return bundle.getString("VillainName");
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
        Villain villain=villains.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                villain.setId((Long) aValue);
                break;
            case 1:
                villain.setVillainName((String) aValue);
                break;
            case 2:
                villain.setRealName((String) aValue);
                break;
            case 3:
                villain.setRealSurname((String) aValue);
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
