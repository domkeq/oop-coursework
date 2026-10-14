package ru.spbstu.coursework;

import ru.spbstu.lab1.Hero;
import ru.spbstu.lab1.Point;
import ru.spbstu.lab1.strategy.CarStrategy;
import ru.spbstu.lab1.strategy.HelicopterStrategy;
import ru.spbstu.lab1.strategy.MoveStrategy;
import ru.spbstu.lab1.strategy.TractorStrategy;
import ru.spbstu.lab1.strategy.WalkStrategy;
import ru.spbstu.lab1.strategy.YachtStrategy;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Arrays;

// задание 1: паттерн стратегия
public class Lab1Panel extends LabPanel {
    private static final MoveStrategy[] STRATEGIES = {
            new CarStrategy(),
            new TractorStrategy(),
            new WalkStrategy(),
            new YachtStrategy(),
            new HelicopterStrategy()
    };

    private final Hero hero = new Hero("Ферстаппен", new Point(0, 0), STRATEGIES[1]);
    private final JTextField xField = new JTextField("3", 4);
    private final JTextField yField = new JTextField("4", 4);

    public Lab1Panel() {
        String[] names = Arrays.stream(STRATEGIES).map(MoveStrategy::getName).toArray(String[]::new);
        JComboBox<String> strategyBox = new JComboBox<>(names);
        // вместо болида выдали трактор
        strategyBox.setSelectedIndex(1);
        strategyBox.addActionListener(e -> {
            hero.setMoveStrategy(STRATEGIES[strategyBox.getSelectedIndex()]);
            print("Теперь " + hero.getName() + " перемещается " + hero.getMoveStrategy().getName());
        });

        JButton moveButton = new JButton("Переместиться");
        moveButton.addActionListener(e -> moveHero());

        JButton whereButton = new JButton("Где герой");
        whereButton.addActionListener(e -> print(hero.getName() + " находится в точке " + hero.getPosition()
                + ", перемещается " + hero.getMoveStrategy().getName()));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("Способ перемещения:"));
        controls.add(strategyBox);
        controls.add(new JLabel("x:"));
        controls.add(xField);
        controls.add(new JLabel("y:"));
        controls.add(yField);
        controls.add(moveButton);
        controls.add(whereButton);
        add(controls, BorderLayout.NORTH);
    }

    private void moveHero() {
        try {
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());
            print(hero.move(new Point(x, y)));
        } catch (NumberFormatException e) {
            print("x и y должны быть целыми числами");
        }
    }
}
