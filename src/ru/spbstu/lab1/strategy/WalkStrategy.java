package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public class WalkStrategy implements MoveStrategy {

    @Override
    public String getName() {
        return "пешком";
    }

    @Override
    public String move(Point from, Point to) {
        return "идёт пешком из " + from + " в " + to;
    }
}
