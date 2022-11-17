package ru.cs.vsu.pertsev;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowApplication extends JFrame{
    private final String projectDir = System.getProperty("user.dir");
    private JButton addColumnButton;
    private JButton removeColumnButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel mainPanel;
    private JPanel columnsButtonsPanel;
    private JScrollPane scrollPane;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextField inputPathTextField;
    private JButton chooseOutputFileButton;
    private JTextField outputPathTextField;
    private JPanel mainButtonsPanel;
    private JPanel fileManagerPanel;
    private JPanel inputManagerPanel;
    private JPanel outputManagerPanel;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JButton readFromFileButton;
    private JButton writeIntoFileButton;
    private JButton startSolutionButton;
    private JButton chooseInputFileButton;

    public WindowApplication() throws HeadlessException {
        this.setTitle("BebraCompany: Task 9");
        this.setPreferredSize(new Dimension(1080, 768));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        InputArgs inputArgs = new InputArgs("", "");


        //Создание базовой таблицы
        tableModel = new DefaultTableModel(1, 10);
        table.setModel(tableModel);

        //Обработка событий кнопок
        addColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setColumnCount(tableModel.getColumnCount() + 1);
            }
        });

        removeColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setColumnCount(tableModel.getColumnCount() - 1);
            }
        });

        readFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inputArgs.setInputFilePath(inputPathTextField.getText());
                    List<Integer> list = FileHandler.readListFromFile(inputArgs.getInputFilePath());
                    fillJTableOfList(tableModel, list);
                } catch (FileNotFoundException ex) {
                    System.err.println("Что-то пошло не так");
                }
            }
        });

        writeIntoFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inputArgs.setOutputFilePath(outputPathTextField.getText());
                    FileHandler.writeListIntoFile(getListFromJTable(tableModel), inputArgs.getOutputFilePath());
                } catch (IOException ex) {
                    System.err.println("Что-то пошло не так");
                }
            }
        });

        startSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> primaryList = getListFromJTable(tableModel);

                List<Integer> finalList = Logic.solution(primaryList);

                fillJTableOfList(tableModel, finalList);
                try {
                    FileHandler.writeListIntoFile(finalList, inputArgs.getOutputFilePath());
                } catch (IOException ex) {
                    System.err.println("Что-то пошло не так");
                }
            }
        });

        chooseInputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(projectDir);
                fc.setDialogTitle("Выберите файл");

                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File inputFile = fc.getSelectedFile();
                    inputPathTextField.setText(inputFile.toString());
                }
            }
        });

        chooseOutputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(projectDir);
                fc.setDialogTitle("Выберите файл");

                int result = fc.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    outputPathTextField.setText(file.toString());
                }
            }
        });

        this.pack();
        this.setVisible(true);
    }
    public void fillJTableOfList(DefaultTableModel tableModel, List<Integer> list) {
        tableModel.setColumnCount(list.size());
        for(int i = 0; i < tableModel.getColumnCount(); i++) {
            tableModel.setValueAt(list.get(i), 0, i);
        }
    }
    public List<Integer> getListFromJTable(DefaultTableModel tableModel) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < tableModel.getColumnCount(); i++) {
            list.add(Integer.valueOf((tableModel.getValueAt(0, i)).toString()));
        }
        return list;
    }
}
