package ilya.ignatov;

import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;
import ru.vsu.cs.util.ArrayUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI_Queue extends JFrame {
    private JTable table = new JTable();
    private JPanel panel = new JPanel();
    private JTextField value = new JTextField();
    private JButton loadFromFile = new JButton("Загрузить из файла");
    private JButton addElement = new JButton("Добавить элемент");
    private JButton deleteElement = new JButton("Удалить элемент ");
    private JButton deleteAll = new JButton("Удалить всю очередь");
    private JButton execute = new JButton("Выполнить программу");
    private JFileChooser fileChooser = new JFileChooser();
    private JLabel enterValue = new JLabel("Введите значение:");
    private JLabel label = new JLabel("Начальная очередь");


    public GUI_Queue () {
        super("Дублирование очереди");
        Queue_ queue = new Queue_();
        fileChooser.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
        fileChooser.addChoosableFileFilter(filter);

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.clear();
                table = null;
                label.setText("Начальная очередь");
            }
        });

        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] array;
                queue.addElement(value.getText());
                array = queue.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                value.setText("");
                label.setText("Начальная очередь");
            }
        });

        deleteElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String[] array;
                queue.deleteElement();
                array = queue.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                label.setText("Начальная очередь");
            }
        });

        deleteAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.clear();
                table = null;
                label.setText("Начальная очередь");
            }
        });

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                queue.duplicate();
                String[] array = queue.toArray();
                JTableUtils.writeArrayToJTable(table, array);
                label.setText("Конечная очередь");
            }
        });

        loadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    queue.clear();
                    table = null;
                    if (fileChooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        String[] array = ArrayUtils.readLinesFromFile(fileChooser.getSelectedFile().getPath());
                        queue.fromArray(array);
                        JTableUtils.writeArrayToJTable(table, array);
                        label.setText("Начальная очередь");
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
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
        GUI_Queue app = new GUI_Queue();
        app.setBounds(100, 100, 500, 500);
        app.setVisible(true);
    }
}
