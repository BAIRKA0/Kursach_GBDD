

import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
public class View extends JFrame{
    private Controller controller = new Controller();
    JButton b1 = new JButton("Добавить");
    JButton b2 = new JButton("Поиск");
    JButton b3 = new JButton("Удалить");
    JLabel license = new JLabel("Номер лицензии");
    JTextField license_ = new JTextField();
    JLabel first_name = new JLabel("Имя водителя");
    JTextField first_name_ = new JTextField();
    JLabel last_name = new JLabel("Фамилия водителя");
    JTextField last_name_ = new JTextField();
    JLabel licenseSearch = new JLabel("Номер лицензии");
    JTextField license_Search = new JTextField();
    TableModel tableModel = new TableModel(controller);
    JLabel sort = new JLabel("Если больше:");
    JTextField sort_ = new JTextField();
    JButton b4 = new JButton("Вывести");
    JButton b5 = new JButton("Вывести все");
    JButton b6 = new JButton("Вывести сумму штрафов");
    View(){
        super("Водители");
        setLayout(new FlowLayout());
        Container LeftContainer = new Container();
        Container RightContainer = new Container();
        Container AddContainer = new Container();
        Container SearchContainer = new Container();
        LeftContainer.setLayout(new BoxLayout(LeftContainer,BoxLayout.Y_AXIS));
        AddContainer.setLayout(new BoxLayout(AddContainer,BoxLayout.Y_AXIS));
        SearchContainer.setLayout(new BoxLayout(SearchContainer,BoxLayout.Y_AXIS));
        AddContainer.add(license);
        AddContainer.add(license_);
        AddContainer.add(first_name);
        AddContainer.add(first_name_);
        AddContainer.add(last_name);
        AddContainer.add(last_name_);
        AddContainer.add(b1);

        SearchContainer.add(licenseSearch);
        SearchContainer.add(license_Search);
        SearchContainer.add(b2);
        SearchContainer.add(b3);
        SearchContainer.add(sort);
        SearchContainer.add(sort_);
        SearchContainer.add(b4);
        SearchContainer.add(b5);
        SearchContainer.add(b6);

        LeftContainer.add(AddContainer);
        LeftContainer.add(SearchContainer);
        RightContainer.setLayout(new FlowLayout());
        JTable table = new JTable();
        table.setModel(tableModel);
        RightContainer.add(new JScrollPane(table));
        add(RightContainer);
        add(LeftContainer);
        controller.addListenerTo_AddButton(b1,license_,first_name_,last_name_,tableModel);
        controller.addListenerTo_SearchButton(b2,license_Search,table,tableModel);
        controller.addListenerTo_DeleteButton(b3,license_Search,tableModel);
        controller.addListenerTo_SortButton(b4,sort_,tableModel);
        controller.addListenerTo_SelectAllButton(b5,tableModel);
        controller.addListenerTo_SumFinesButton(b6);
        setMinimumSize(new Dimension(700,400));
        setLocation(900, 400);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
