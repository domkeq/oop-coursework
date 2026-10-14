package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public class YachtStrategy implements MoveStrategy {

    @Override
    public String getName() {
        return "на яхте";
    }

    @Override
    public String move(Point from, Point to) {
        return "плывёт на яхте из " + from + " в " + to + ", сошёл в Монако, отдыхает";
    }
}
