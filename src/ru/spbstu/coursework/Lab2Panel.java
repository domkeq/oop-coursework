package ru.spbstu.coursework;

import ru.spbstu.lab2.MethodInvoker;
import ru.spbstu.lab2.team.RaceEngineer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

// задание 2: аннотации и reflection
public class Lab2Panel extends LabPanel {
    private final MethodInvoker invoker = new MethodInvoker();

    public Lab2Panel() {
        JButton runButton = new JButton("Вызвать аннотированные методы");
        runButton.addActionListener(e -> {
            RaceEngineer engineer = new RaceEngineer("Джанпьеро", this::print);
            invoker.invokeAnnotated(engineer, this::print);
        });

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(runButton);
        add(controls, BorderLayout.NORTH);
    }
}
