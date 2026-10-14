package ru.spbstu.lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;

public class MethodInvoker {
    private final ArgumentGenerator generator = new ArgumentGenerator();

    // вызывает аннотированные защищенные и приватные методы объекта столько раз, сколько указано в @Repeat
    public void invokeAnnotated(Object target, Consumer<String> output) {
        for (Method method : target.getClass().getDeclaredMethods()) {
            Repeat repeat = method.getAnnotation(Repeat.class);
            if (repeat == null) {
                continue;
            }
            int modifiers = method.getModifiers();
            if (!Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers)) {
                continue;
            }

            output.accept("Метод " + method.getName() + ", вызовов: " + repeat.value());
            if (!method.trySetAccessible()) {
                output.accept("Нет доступа к методу " + method.getName());
                continue;
            }
            for (int i = 0; i < repeat.value(); i++) {
                try {
                    method.invoke(target, generator.generate(method.getParameterTypes()));
                } catch (InvocationTargetException e) {
                    output.accept("Метод " + method.getName() + " выбросил исключение: " + e.getCause());
                } catch (ReflectiveOperationException e) {
                    output.accept("Не удалось вызвать метод " + method.getName() + ": " + e);
                }
            }
            output.accept("");
        }
    }
}
