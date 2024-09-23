import org.junit.jupiter.api.*;
import org.collection_converter.Converter;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @DisplayName("Сompare Collection Implementation")
    @Test
    void compareCollectionImplementation() {
        List<Collection> collections = List.of(new ArrayList<>(), new LinkedList<>(),
                new HashSet<>(), new TreeSet<>(), new PriorityQueue<>(), new ArrayDeque<>());
        for (var obj : collections) {
            Collection<String> collection = obj;
            collection.add("1");
            collection.add("3");
            collection.add("5");
            Function function = s -> Integer.parseInt((String) s);
            isInstance(collection, function);
        }
    }

    @DisplayName("Compare Map Implementation")
    @Test
    void compareMapImplementation() {
        List<Map> collections = List.of(new HashMap(), new LinkedHashMap(), new TreeMap());
        for (var obj : collections) {
            Map<String, Integer> map = obj;
            obj.put("1", 5);
            obj.put("2", 7);
            obj.put("3", 9);
            Function function = s -> String.valueOf(s);
            isInstance(map, function);
        }
    }

    @DisplayName("Add Object Function")
    @Test
    void addObjectFunction() {
        List<Collection> collections = List.of(new ArrayList<>(), new LinkedList<>(),
                new HashSet<>(), new ArrayDeque<>());
        for (var obj : collections) {
            Collection<String> collection = obj;
            collection.add("1");
            Function function = s -> new Object();
            isInstance(collection, function);
        }
    }

    @DisplayName("Add Comparable Object Function")
    @Test
    void addComparableObjectFunction() {
        List<Collection> collections = List.of(new TreeSet<>(), new PriorityQueue<>());
        for (var obj : collections) {
            Collection<String> collection = obj;
            collection.add("1");
            class CompareObject implements Comparable {
                @Override
                public int compareTo(Object o) {
                    return 0;
                }
            }
            Function function = s -> new CompareObject();
            isInstance(collection, function);
        }
    }

    @DisplayName("Add Object Function to Map")
    @Test
    void addObjectFunctionMap() {
        List<Map> collections = List.of(new HashMap(), new LinkedHashMap(), new TreeMap());
        for (var obj : collections) {
            Map<String, Integer> map = obj;
            obj.put("1", 5);
            obj.put("2", 7);
            obj.put("3", 9);
            Function function = s -> new Object();
            isInstance(map, function);
        }
    }

    @DisplayName("Add Some Function to Map")
    @Test
    void someFunctionMap() {
        List<Map> collections = List.of(new HashMap(), new LinkedHashMap(), new TreeMap());
        for (var obj : collections) {
            Map<String, Integer> map = obj;
            obj.put("1", 5);
            obj.put("2", 7);
            obj.put("3", 9);
            Function function = s -> {
                int x = Integer.parseInt(String.valueOf(s)) + 100;
                return Integer.valueOf(x);
            };
            Converter converter = new Converter();
            Map result = converter.convert(map, function);
        }
    }

    @DisplayName("Add Null")
    @Test
    void addNull() {
        List<Collection> collections = List.of(new ArrayList<>(), new LinkedList<>(), new HashSet<>());
        for (var obj : collections) {
            Collection<String> collection = obj;
            collection.add("1");
            Function function = s -> null;
            isInstance(collection, function);
        }
    }

    private void isInstance(Collection collection, Function function) {
        Converter converter = new Converter();
        Collection result = converter.convert(collection, function);
//        System.out.println(nameCollectionRealization(collection) + " -> " + nameCollectionRealization(result));
        assertEquals(nameCollectionRealization(collection), nameCollectionRealization(result));
    }

    private void isInstance(Map collection, Function function) {
        Converter converter = new Converter();
        Map result = converter.convert(collection, function);
//        System.out.println(nameMapRealization(collection) + " -> " + nameMapRealization(result));
        assertEquals(nameMapRealization(collection), nameMapRealization(result));
    }

    private String nameCollectionRealization(Collection collection) {
        return collection.getClass().getName().split("\\.")[2];
    }

    private String nameMapRealization(Map collection) {
        return collection.getClass().getName().split("\\.")[2];
    }
}
