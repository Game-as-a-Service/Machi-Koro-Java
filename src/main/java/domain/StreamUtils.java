package domain;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@UtilityClass
public class StreamUtils {
    public static <T, R> List<T> mapToList(Collection<R> collection, Function<R, T> mapping) {
        Objects.requireNonNull(collection, "Collection must not be null");
        Objects.requireNonNull(mapping, "Mapping function must not be null");

        return collection.stream().map(mapping).collect(Collectors.toList());
    }

    public static <T, R> Map<String, List<T>> convertMapToAnotherMap(Map<String, List<R>> inputMap, Function<R, T> mapping) {
        Objects.requireNonNull(inputMap, "Input map must not be null");
        Objects.requireNonNull(mapping, "Mapping function must not be null");

        return inputMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> mapToList(entry.getValue(), mapping)));
    }
}
