package ru.spbstu.lab4;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamMethods {

    public static double average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new NoSuchElementException("список пуст, среднее не определено"));
    }

    public static List<String> toUpperCaseWithPrefix(List<String> strings) {
        return strings.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .toList();
    }

    public static List<Integer> squaresOfUnique(List<Integer> numbers) {
        Map<Integer, Long> counts = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return numbers.stream()
                .filter(n -> counts.get(n) == 1)
                .map(n -> n * n)
                .toList();
    }

    public static <T> T lastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("коллекция пуста"));
    }

    public static int sumOfEven(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    // у пустой строки нет первого символа, такие пропускаем
    // если первый символ повторяется, остается первая строка
    public static Map<Character, String> toMap(List<String> strings) {
        return strings.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (first, second) -> first,
                        LinkedHashMap::new));
    }
}
