package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public class HelicopterStrategy implements MoveStrategy {

    @Override
    public String getName() {
        return "на вертолёте";
    }

    @Override
    public String move(Point from, Point to) {
        return "летит на вертолёте из " + from + " в " + to;
    }
}
