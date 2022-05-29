import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

public class ViewElement extends JFrame{
    Controller controller;
    Container container = new Container();
    JLabel license = new JLabel("Номер лицензии");
    JTextField license_ = new JTextField();
    JLabel car_number = new JLabel("Номер машины");
    JTextField car_number_ = new JTextField();
    JLabel color = new JLabel("Цвет");
    JTextField color_ = new JTextField();
    JLabel straxovka = new JLabel("Страховка");
    JTextField straxovka_ = new JTextField();
    JLabel model = new JLabel("Модель");
    JTextField model_ = new JTextField();
    JButton b1 = new JButton("Добавить");
    JButton b2 = new JButton("Удалить");
    JButton b3 = new JButton("Вывести список штрафов");
    TableModel2 tableModel2;
    public ViewElement(String license1,Controller controller1){
        super("Водитель с лицензией "+license1);
        tableModel2 = new TableModel2(controller,license1);
        setLayout(new FlowLayout());
        Container LeftContainer = new Container();
        Container RightContainer = new Container();
        Container AddContainer = new Container();
        Container DelContainer = new Container();
        LeftContainer.setLayout(new BoxLayout(LeftContainer,BoxLayout.Y_AXIS));
        AddContainer.setLayout(new BoxLayout(AddContainer,BoxLayout.Y_AXIS));
        DelContainer.setLayout(new BoxLayout(DelContainer,BoxLayout.Y_AXIS));
        AddContainer.add(model);
        AddContainer.add(model_);
        AddContainer.add(car_number);
        AddContainer.add(car_number_);
        AddContainer.add(color);
        AddContainer.add(color_);
        AddContainer.add(straxovka);
        AddContainer.add(straxovka_);
        AddContainer.add(b1);

        DelContainer.add(license);
        DelContainer.add(license_);
        DelContainer.add(b2);

        LeftContainer.add(AddContainer);
        LeftContainer.add(DelContainer);
        LeftContainer.add(b3);

        RightContainer.setLayout(new FlowLayout());
        JTable table = new JTable();
        table.setModel(tableModel2);
        RightContainer.add(new JScrollPane(table));
        add(RightContainer);
        add(LeftContainer);
        controller1.addListenerTo_AddButton2(b1,license1,model_,car_number_,color_,straxovka_,tableModel2);
        controller1.addListenerTo_DelButton2(b2,license1,car_number_,tableModel2);
        controller1.addListenerTo_FinesButton(b3,license1);
        setMinimumSize(new Dimension(700,400));
        setLocation(900, 400);
        pack();
        setResizable(false);
        setVisible(true);
    }

}
