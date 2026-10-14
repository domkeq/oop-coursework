package ru.spbstu.coursework;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;

// общая часть всех панелей: область вывода, которую нельзя редактировать
public abstract class LabPanel extends JPanel {
    private final JTextArea output = new JTextArea();

    protected LabPanel() {
        super(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(output), BorderLayout.CENTER);
    }

    protected void print(String text) {
        output.append(text + "\n");
    }
}
