package com.peaqock.aml.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.stream.Stream;

public class MyBeanUtils {

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    public static String[] getNullProperties(Object source) {
        final var wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> Objects.isNull(wrappedSource.getPropertyValue(propertyName)))
                .toArray(String[]::new);
    }
}
