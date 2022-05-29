import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

public class ViewSortWin extends JFrame{
    Controller controller;
    Container container = new Container();
    JLabel num = new JLabel("Номер");
    JTextField num_ = new JTextField();
    JLabel opisanie = new JLabel("Описание");
    JTextField opisanie_ = new JTextField();
    JLabel razmer = new JLabel("Размер");
    JTextField razmer_ = new JTextField();
    JLabel data = new JLabel("Дата");
    JTextField data_ = new JTextField();
    JButton b1 = new JButton("Добавить");
    JButton b2 = new JButton("Удалить");
    TableModel3 tableModel3;
    public ViewSortWin(String license1,Controller controller1){
        super("Штрафы водителя с лицензией "+license1);
        tableModel3 = new TableModel3(controller,license1);
        setLayout(new FlowLayout());
        Container LeftContainer = new Container();
        Container RightContainer = new Container();
        Container AddContainer = new Container();
        Container DelContainer = new Container();
        LeftContainer.setLayout(new BoxLayout(LeftContainer,BoxLayout.Y_AXIS));
        AddContainer.setLayout(new BoxLayout(AddContainer,BoxLayout.Y_AXIS));
        DelContainer.setLayout(new BoxLayout(DelContainer,BoxLayout.Y_AXIS));

        AddContainer.add(opisanie);
        AddContainer.add(opisanie_);
        AddContainer.add(razmer);
        AddContainer.add(razmer_);
        AddContainer.add(b1);

        DelContainer.add(num);
        DelContainer.add(num_);
        DelContainer.add(b2);

        LeftContainer.add(AddContainer);
        LeftContainer.add(DelContainer);

        RightContainer.setLayout(new FlowLayout());
        JTable table = new JTable();
        table.setModel(tableModel3);
        RightContainer.add(new JScrollPane(table));
        add(RightContainer);
        add(LeftContainer);
        controller1.addListenerTo_AddButton3(b1,license1,opisanie_,razmer_,tableModel3);
        controller1.addListenerTo_DelButton3(b2,num_,license1,tableModel3);
        setMinimumSize(new Dimension(700,400));
        setLocation(900, 400);
        pack();
        setResizable(false);
        setVisible(true);
    }

}
