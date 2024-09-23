package org.collection_converter;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Converter {
    public <T extends Collection, K, V> T convert(T collection, Function<K, V> function) {
        return (T) collection.parallelStream().map(function).collect(Collectors.toCollection(receivedСlass(collection)));
    }

    public <T extends Map, K> Map convert(T collection, Function<K, Object> function) {
        BiConsumer combiner = (map1, map2) -> ((Map) map1).putAll((Map) map2);
        return (Map) collection.entrySet().parallelStream()
                .collect(receivedСlass(collection), accumulator(function), combiner);
    }

    private <K, T, V> BiConsumer<Map<K, V>, T> accumulator(Function<V, ? extends Object> function) {
        return (map, element) -> {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) element;
            map.putIfAbsent(entry.getKey(), (V) function.apply(entry.getValue()));
        };
    }

    private <T> Supplier receivedСlass(T collection) {
        return () -> {
            try {
                return collection.getClass().getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
