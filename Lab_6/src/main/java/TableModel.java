
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    Controller controller;

    ArrayList<Driver> d = null;
    public TableModel(Controller controller){this.controller = controller;
    d = HttpHelper.getDriverList();}
    @Override
    public int getRowCount(){
        return d.size();
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Лицензия";
            case 1:
                return "Имя";
            case 2:
                return "Фамилия";
            default:
                return "";
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Driver driver = d.get(rowIndex);
        switch (columnIndex){
            case 0:
                return driver.getLicense();
            case 1:
                return driver.getFirst_name();
            case 2:
                return driver.getLast_name();
            default:
                return "";
        }
    }
}
