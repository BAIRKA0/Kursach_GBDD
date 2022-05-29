
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel2 extends AbstractTableModel {
    Controller controller;
    ArrayList<Car> cars = null;
    public TableModel2(Controller controller,String license){this.controller = controller;
    cars = HttpHelper.getCarList(license);}
    @Override
    public int getRowCount(){
        return cars.size();
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Модель";
            case 1:
                return "Номер";
            case 2:
                return "Цвет";
            case 3:
                return "Страховка";
            default:
                return "";
        }
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Driver driver = controller.d.get(rowIndex);
        Car car = cars.get(rowIndex);
        switch (columnIndex){
            case 0:
                return car.getModel();
            case 1:
                return car.getPlate();
            case 2:
                return car.getColor();
            case 3:
                return car.getInsurance();
            default:
                return "";
        }
    }
}
