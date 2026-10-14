package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public class CarStrategy implements MoveStrategy {

    @Override
    public String getName() {
        return "на болиде";
    }

    @Override
    public String move(Point from, Point to) {
        return "проносится на болиде из " + from + " в " + to;
    }
}
