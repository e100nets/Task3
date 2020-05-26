package ilya.ignatov;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;
import ru.vsu.cs.util.ArrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_LinkedList extends JFrame {
    private JTable table = new JTable();
    private JPanel panel = new JPanel();
    private JTextField value = new JTextField();
    private JButton loadFromFile = new JButton("Загрузить из файла");
    private JButton addElement = new JButton("Добавить элемент");
    private JButton deleteElement = new JButton("Удалить элемент ");
    private JButton deleteAll = new JButton("Удалить весь список");
    private JButton execute = new JButton("Выполнить программу");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel enterValue = new JLabel("Введите значение:");
    private JLabel label = new JLabel("Начальная очередь");

    public GUI_LinkedList () {
        super("Дублирование списка");
        Queue_based_on_List list = new Queue_based_on_List();

        loadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    list.clear();
                    table = null;
                    if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        String[] array = ArrayUtils.readLinesFromFile(fileChooser.getSelectedFile().getPath());
                        list.fromArray(array);
                        JTableUtils.writeArrayToJTable(table, array);
                        label.setText("Начальный список: ");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }

            }
        });

        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] array;
                list.add(value.getText());
                array = list.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                value.setText("");
                label.setText("Начальный список");
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.clear();
                table = null;
                label.setText("Начальный список");
            }
        });

        deleteElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] array;
                list.remove();
                array = list.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                label.setText("Начальный список");
            }
        });

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                list.duplicate();
                String[] array = list.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                label.setText("Конечный список");
            }
        });

        panel.setLayout(null);
        enterValue.setBounds(5, 5, 150, 20);
        value.setBounds(160, 5, 100, 20);
        label.setBounds(5, 35, 150, 20);
        table.setBounds(5, 65, 400, 20);
        loadFromFile.setBounds(5, 95, 200, 30);
        addElement.setBounds(5, 130 , 200, 30);
        deleteElement.setBounds(5, 165, 200, 30);
        deleteAll.setBounds(5, 200, 200, 30);
        execute.setBounds(5, 235, 200, 30);


        panel.add(enterValue);
        panel.add(value);
        panel.add(label);
        panel.add(table);
        panel.add(loadFromFile);
        panel.add(addElement);
        panel.add(deleteElement);
        panel.add(deleteAll);
        panel.add(execute);

        getContentPane().add(panel);
    }
    public static void main (String[] args){
        GUI_LinkedList app = new GUI_LinkedList();
        app.setBounds(100, 100, 500, 500);
        app.setVisible(true);
    }

}
