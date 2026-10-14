package ru.spbstu.coursework;

import ru.spbstu.lab3.Dictionary;
import ru.spbstu.lab3.FileReadException;
import ru.spbstu.lab3.InvalidFileFormatException;
import ru.spbstu.lab3.Translator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

// задание 3: переводчик
public class Lab3Panel extends LabPanel {
    private final JLabel dictionaryLabel = new JLabel("Словарь не выбран");
    private final JTextArea input = new JTextArea("dog look to the window, dog look forward", 4, 40);
    private Translator translator;

    public Lab3Panel() {
        JButton dictionaryButton = new JButton("Выбрать словарь...");
        dictionaryButton.addActionListener(e -> {
            File file = chooseFile("Файл словаря");
            if (file != null) {
                loadDictionary(file.getPath());
            }
        });

        JButton textButton = new JButton("Текст из файла...");
        textButton.addActionListener(e -> {
            File file = chooseFile("Файл с текстом");
            if (file != null) {
                loadText(file);
            }
        });

        JButton translateButton = new JButton("Перевести");
        translateButton.addActionListener(e -> translate());

        JPanel files = new JPanel(new FlowLayout(FlowLayout.LEFT));
        files.add(dictionaryButton);
        files.add(dictionaryLabel);

        JPanel inputLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputLabel.add(new JLabel("Текст для перевода (можно ввести вручную):"));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(textButton);
        buttons.add(translateButton);

        input.setLineWrap(true);
        input.setWrapStyleWord(true);

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(files);
        top.add(inputLabel);
        top.add(new JScrollPane(input));
        top.add(buttons);
        add(top, BorderLayout.NORTH);

        // словарь по умолчанию из папки проекта, как в консольной версии
        if (new File("dictionary.txt").exists()) {
            loadDictionary("dictionary.txt");
        }
    }

    private File chooseFile(String title) {
        JFileChooser chooser = new JFileChooser(new File("."));
        chooser.setDialogTitle(title);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private void loadDictionary(String fileName) {
        try {
            translator = new Translator(new Dictionary(fileName));
            dictionaryLabel.setText("Словарь: " + fileName);
            print("Словарь " + fileName + " загружен");
        } catch (FileReadException e) {
            print("Ошибка чтения словаря: " + e.getMessage());
        } catch (InvalidFileFormatException e) {
            print("Неверный формат словаря, " + e.getMessage());
        }
    }

    private void loadText(File file) {
        try {
            input.setText(Files.readString(file.toPath(), StandardCharsets.UTF_8));
            print("Текст загружен из " + file.getName());
        } catch (IOException e) {
            print("Не удалось прочитать файл " + file.getName());
        }
    }

    private void translate() {
        if (translator == null) {
            print("Сначала выберите словарь");
            return;
        }
        print(translator.translate(input.getText()));
    }
}
