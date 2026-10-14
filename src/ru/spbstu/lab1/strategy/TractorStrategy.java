package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public class TractorStrategy implements MoveStrategy {

    @Override
    public String getName() {
        return "на тракторе";
    }

    @Override
    public String move(Point from, Point to) {
        return "тарахтит на тракторе из " + from + " в " + to;
    }
}
