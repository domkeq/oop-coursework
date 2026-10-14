package ru.spbstu.coursework;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

public class MainWindow extends JFrame {
    private static final String[] TASKS = {
            "1. Стратегия",
            "2. Аннотации и reflection",
            "3. Переводчик",
            "4. Stream API"
    };

    public MainWindow() {
        super("Курсовая работа, задания 1-4");

        // панели заданий лежат стопкой, видна только выбранная
        CardLayout cards = new CardLayout();
        JPanel taskPanels = new JPanel(cards);
        taskPanels.add(new Lab1Panel(), TASKS[0]);
        taskPanels.add(new Lab2Panel(), TASKS[1]);
        taskPanels.add(new Lab3Panel(), TASKS[2]);
        taskPanels.add(new Lab4Panel(), TASKS[3]);

        JList<String> taskList = new JList<>(TASKS);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setSelectedIndex(0);
        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cards.show(taskPanels, taskList.getSelectedValue());
            }
        });
        JScrollPane listScroll = new JScrollPane(taskList);
        listScroll.setPreferredSize(new Dimension(200, 0));

        add(listScroll, BorderLayout.WEST);
        add(taskPanels, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
    }
}
