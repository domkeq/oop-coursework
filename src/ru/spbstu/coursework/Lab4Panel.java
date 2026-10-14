package ru.spbstu.coursework;

import ru.spbstu.lab4.StreamMethods;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

// задание 4: методы на Stream API, значения вводятся через запятую
public class Lab4Panel extends LabPanel {

    public Lab4Panel() {
        JPanel rows = new JPanel(new GridLayout(0, 1, 0, 4));
        addRow(rows, "Среднее значение", "1, 1, 2, 1, 5",
                text -> String.valueOf(StreamMethods.average(parseInts(text))));
        addRow(rows, "Верхний регистр и префикс _new_", "verstappen, norris, leclerc",
                text -> String.valueOf(StreamMethods.toUpperCaseWithPrefix(parseStrings(text))));
        addRow(rows, "Квадраты встречающихся один раз", "1, 4, 16, 1, 44, 4, 81",
                text -> String.valueOf(StreamMethods.squaresOfUnique(parseInts(text))));
        addRow(rows, "Последний элемент коллекции", "Бахрейн, Джидда, Мельбурн, Сузука",
                text -> StreamMethods.lastElement(parseStrings(text)));
        addRow(rows, "Сумма чётных чисел массива", "1, 4, 16, 44, 63, 81",
                text -> String.valueOf(StreamMethods.sumOfEven(
                        parseInts(text).stream().mapToInt(Integer::intValue).toArray())));
        addRow(rows, "Строки в Map", "Verstappen, Norris, Leclerc, Hamilton, Hulkenberg",
                text -> String.valueOf(StreamMethods.toMap(parseStrings(text))));
        add(rows, BorderLayout.NORTH);
    }

    private void addRow(JPanel rows, String title, String example, Function<String, String> method) {
        JLabel label = new JLabel(title);
        label.setPreferredSize(new Dimension(230, label.getPreferredSize().height));
        JTextField field = new JTextField(example);
        JButton button = new JButton("Выполнить");
        button.addActionListener(e -> {
            String text = field.getText();
            try {
                print(title + " [" + text + "]: " + method.apply(text));
            } catch (NumberFormatException ex) {
                print(title + ": нужно ввести целые числа через запятую");
            } catch (NoSuchElementException ex) {
                print(title + ": " + ex.getMessage());
            }
        });

        JPanel row = new JPanel(new BorderLayout(6, 0));
        row.add(label, BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        row.add(button, BorderLayout.EAST);
        rows.add(row);
    }

    private static List<String> parseStrings(String text) {
        if (text.isBlank()) {
            return List.of();
        }
        return Arrays.stream(text.split(","))
                .map(String::trim)
                .toList();
    }

    private static List<Integer> parseInts(String text) {
        return parseStrings(text).stream()
                .map(Integer::parseInt)
                .toList();
    }
}
