package org.collection_converter;

import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Converter converter = new Converter();
        Map<Integer, String> stringMap = new HashMap<>();
        stringMap.put(1, "1");
        stringMap.put(2, "5");
        stringMap.entrySet().forEach(System.out::println);
        Function function = s -> Integer.parseInt(String.valueOf(s));
        Map<Integer, Integer> integerMap = converter.convert(stringMap, function);
        integerMap.entrySet()
                .forEach(s -> System.out.println(s.getKey() + " " + (s.getValue() + 100)));
    }
}