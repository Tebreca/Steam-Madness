package com.tebreca.steammadness.helper.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Injector<T> {

    default void Inject(EntryHolder holder){
        Class<? extends EntryHolder> holderClass = holder.getClass();
        Class<? extends T> tClass = getTClass();
        Arrays.stream(holderClass.getDeclaredFields()).filter(f -> f.getType().equals(tClass)).filter(field -> {
            try {
                field.setAccessible(true);
                return field.get(holder) == null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).forEach(
                field -> {
                    try {
                        field.set(holder, getFrom(field));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    T getFrom(Field field);

    Class<? extends T> getTClass();
}
