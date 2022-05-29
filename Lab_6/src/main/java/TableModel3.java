
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel3 extends AbstractTableModel {
    Controller controller;
    ArrayList<Fine> fines = null;

    public TableModel3(Controller controller,String license){this.controller = controller;
        fines = HttpHelper.getFines(license);}
    @Override
    public int getRowCount(){
        return fines.size();
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Номер";
            case 1:
                return "Дата";
            case 2:
                return "Описание";
            case 3:
                return "Размер";
            default:
                return "";
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fine fine = fines.get(rowIndex);
        switch (columnIndex){
            case 0:
                return fine.getNum();
            case 1:
                return fine.getData();
            case 2:
                return fine.getOpisanie();
            case 3:
                return fine.getRazmer();
            default:
                return "";
        }
    }
}
