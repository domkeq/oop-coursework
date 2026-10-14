package ru.spbstu.lab1.strategy;

import ru.spbstu.lab1.Point;

public interface MoveStrategy {

    String getName();

    // возвращает описание перемещения, печатает его вызывающий код
    String move(Point from, Point to);
}
