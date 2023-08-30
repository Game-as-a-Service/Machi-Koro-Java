package domain;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.range;

@UtilityClass
public class StreamUtils {
    public static <T, R> List<T> mapToList(Collection<R> collection, Function<R, T> mapping) {
        Objects.requireNonNull(collection, "Collection must not be null");
        Objects.requireNonNull(mapping, "Mapping function must not be null");

        return collection.stream().map(mapping).collect(Collectors.toList());
    }
}
