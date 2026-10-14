package ru.spbstu.lab1;

import ru.spbstu.lab1.strategy.MoveStrategy;

public class Hero {
    private final String name;
    private Point position;
    private MoveStrategy moveStrategy;

    public Hero(String name, Point position, MoveStrategy moveStrategy) {
        this.name = name;
        this.position = position;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public String move(Point to) {
        if (position.equals(to)) {
            return name + " уже находится в точке " + to;
        }
        String result = name + " " + moveStrategy.move(position, to);
        position = to;
        return result;
    }
}
