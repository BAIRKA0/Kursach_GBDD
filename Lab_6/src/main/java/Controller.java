import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Controller {
    ViewElement viewElement =null;
    ViewSortWin viewSortWin =null;
    public void addListenerTo_AddButton(JButton addButton, JTextField license_,JTextField firstname_,JTextField lastname_,TableModel tableModel){
        addButton.addActionListener(e -> {
            try{
                HttpHelper.setDriver(new Driver(license_.getText(),firstname_.getText(),lastname_.getText()));
                tableModel.d = HttpHelper.getDriverList();
                tableModel.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    public void addListenerTo_DeleteButton(JButton deleteButton,JTextField s,TableModel tableModel){
        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    HttpHelper.delDriver(s.getText());
                    tableModel.d = HttpHelper.getDriverList();
                    tableModel.fireTableDataChanged();
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(new JFrame(), "Произошла ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public void addListenerTo_AddButton2(JButton addButton, String license_,JTextField model,JTextField plate,JTextField color,JTextField insurance,TableModel2 tableModel2){
        addButton.addActionListener(e -> {
            try{
                HttpHelper.setCar(license_,model.getText(),plate.getText(),color.getText(),insurance.getText());
                tableModel2.cars = HttpHelper.getCarList(license_);
                tableModel2.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    public void addListenerTo_AddButton3(JButton addButton, String license_,JTextField description,JTextField size,TableModel3 tableModel3){
        addButton.addActionListener(e -> {
            try{
                HttpHelper.setFines(license_,description.getText(),size.getText());
                tableModel3.fines = HttpHelper.getFines(license_);
                tableModel3.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    public void addListenerTo_DelButton2(JButton addButton,String license_,JTextField plate,TableModel2 tableModel2){
        addButton.addActionListener(e -> {
            try{
                HttpHelper.delCar(plate.getText());
                tableModel2.cars = HttpHelper.getCarList(license_);
                tableModel2.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    public void addListenerTo_DelButton3(JButton addButton,JTextField fineId,String license,TableModel3 tableModel3){
        addButton.addActionListener(e -> {
            try{
                HttpHelper.delFines(fineId.getText());
                tableModel3.fines = HttpHelper.getFines(license);
                tableModel3.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void addListenerTo_SelectAllButton(JButton addButton,TableModel tableModel){
        addButton.addActionListener(e -> {
            try{
                ArrayList<Driver> drivers = HttpHelper.getDriverList();
                tableModel.d = drivers;
                tableModel.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void addListenerTo_SortButton(JButton addButton,JTextField num,TableModel tableModel){
        addButton.addActionListener(e -> {
            try{
                ArrayList<Driver> drivers = HttpHelper.getDriverSortList(num.getText());
                tableModel.d = drivers;
                tableModel.fireTableDataChanged();
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void addListenerTo_SumFinesButton(JButton addButton){
        addButton.addActionListener(e -> {
            try{
                JOptionPane.showMessageDialog(new JFrame(), HttpHelper.getSum(), "Сумма штрафов", JOptionPane.INFORMATION_MESSAGE);
            }catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящие значения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void addListenerTo_SearchButton(JButton searchButton,JTextField license,JTable table,TableModel tableModel){
        ListSelectionModel selectionModel = table.getSelectionModel();
        searchButton.addActionListener(e -> {
            try{
                if(viewElement!=null){
                    viewElement.dispose();
                    viewElement=null;
                }
                viewElement = new ViewElement(license.getText(),this);
            }
            catch (IndexOutOfBoundsException indexExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Нет записи с таким номером", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящее значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    public void addListenerTo_FinesButton(JButton sortButton, String license){
        sortButton.addActionListener(e -> {
            try{
                if(viewSortWin!=null){
                    viewSortWin.dispose();
                    viewSortWin=null;
                }
                viewSortWin = new ViewSortWin(license,this);
            }catch (IndexOutOfBoundsException indexExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Нет записи с таким номером", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException numberExc) {
                JOptionPane.showMessageDialog(new JFrame(), "Неподходящее значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
