package ru.spbstu.lab2.team;

import ru.spbstu.lab2.Repeat;

import java.util.Arrays;
import java.util.function.Consumer;

public class RaceEngineer {
    private final String name;
    // куда выводить сообщения, в курсовой это текстовая область окна
    private final Consumer<String> output;

    public RaceEngineer(String name, Consumer<String> output) {
        this.name = name;
        this.output = output;
    }

    // публичные методы

    @Repeat(2)
    public void radio(Driver driver, String message) {
        output.accept(name + " по радио для " + driver + ": " + message);
    }

    public void boxBox(Car car) {
        output.accept(name + ": " + car + ", box box!");
    }

    public void lapTime(int lap, double seconds) {
        output.accept(String.format("%s: круг %d за %.3f с", name, lap, seconds));
    }

    // защищенные методы

    @Repeat(2)
    protected void pitStop(Car car, Tyre tyre, double seconds) {
        output.accept(String.format("%s: пит-стоп, %s, ставим %s, %.1f с", name, car, tyre, seconds));
    }

    @Repeat(1)
    protected void tyrePlan(Tyre[] tyres, int[] laps) {
        output.accept(name + ": план на гонку " + Arrays.toString(tyres) + ", круги " + Arrays.toString(laps));
    }

    protected void checkWeather(boolean rain, int temperature) {
        output.accept(name + ": дождь " + rain + ", температура трассы " + temperature);
    }

    // приватные методы

    @Repeat(3)
    private void gap(Driver ahead, long millis, char sector) {
        output.accept(name + ": отставание от " + ahead + " " + millis + " мс, сектор " + sector);
    }

    @Repeat(1)
    private void readTelemetry(Telemetry telemetry, boolean drs) {
        // объект абстрактного класса создать нельзя, поэтому сюда может прийти null
        if (telemetry == null) {
            output.accept(name + ": телеметрия недоступна, DRS " + drs);
            return;
        }
        output.accept(name + ": скорость " + telemetry.getSpeed() + ", DRS " + drs);
    }

    private void teamOrder(Driver first, Driver second) {
        output.accept(name + ": " + second + ", пропусти " + first);
    }
}
