package com.peaqock.aml.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class ObjectUtils {

    public static final Function<OffsetDateTime, String> extractYearlyMonth = date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    /**
     * Helper method to apply a function on an object if it is not null or empty.
     * The object if not null is then passed as a parameter in the function.
     *
     * @param object   the object to check
     * @param function the function to apply
     * @param <T>      The type of the object
     */
    public static <T> void applyIfNonNull(T object, Consumer<T> function) {
        if (object == null) return;
        if (object instanceof Collection) {
            var collection = ((Collection<?>) object);
            if (collection.isEmpty()) return;
        }
        function.accept(object);
    }

    public static <T> void applyIfNonEmpty(Collection<T> object, Consumer<Collection<T>> function) {
        if (object == null) return;
        if (object.isEmpty()) return;
        function.accept(object);
    }

    /**
     * Utility method to remove null elements from map
     */
    public static <T> Map<String, Object> cleanMap(Map<String, T> map) {
        Map<String, Object> returnedMap = new ConcurrentHashMap<>();
        for (String key : map.keySet())
            if (map.get(key) != null)
                returnedMap.put(key, map.get(key));

        return returnedMap;
    }

    public static <T> HashMap<String, Object> toMap(T object) {
        var mapType = MAPPER.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
        return MAPPER.convertValue(object, mapType);
    }

    public static <T> List<HashMap<String, Object>> toMapList(T object) {
        var mapType = MAPPER.getTypeFactory().constructCollectionType(List.class, HashMap.class);
        return MAPPER.convertValue(object, mapType);
    }


    public static boolean validateUUID(String source) {
        try {
            var uuid = UUID.fromString(source);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    public static ResponseEntity<ByteArrayResource> fileResponseParser(byte[] source, String fileKey) {
        return ResponseEntity.ok()
                .contentLength(source.length)
                .headers(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileKey));
                    httpHeaders.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
                    httpHeaders.add(HttpHeaders.PRAGMA, "no-cache");
                    httpHeaders.add(HttpHeaders.EXPIRES, "0");
                })
                .body(new ByteArrayResource(source));
    }

    public static List<Map<String, Object>> convertTuplesToMap(List<Tuple> tuples) {
        if (CollectionUtils.isEmpty(tuples)) return Collections.emptyList();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple single : tuples) {
            Map<String, Object> tempMap = new HashMap<>();
            for (TupleElement<?> key : single.getElements()) {
                tempMap.put(key.getAlias(), single.get(key));
            }
            result.add(tempMap);
        }
        return result;
    }

    public static Map<String, Object> convertTupleToMap(Tuple tuple) {
        if (Objects.isNull(tuple)) return null;
        Map<String, Object> result = new HashMap<>();
        for (TupleElement<?> key : tuple.getElements()) {
            result.put(key.getAlias(), tuple.get(key));
        }
        return result;
    }
}
