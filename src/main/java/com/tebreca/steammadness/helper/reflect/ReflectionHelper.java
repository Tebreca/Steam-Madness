package com.tebreca.steammadness.helper.reflect;

import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReflectionHelper {

    public static <T extends IForgeRegistryEntry<T>> List<T> collectFields(Class<T> type, EntryHolder<T> entryHolder){
        Class<?> holderClass = entryHolder.getClass();
        return Arrays.stream(holderClass.getDeclaredFields()).filter(f->f.getType().equals(type)).map(field -> {
            try {
                return ((T) field.get(entryHolder));
            } catch (IllegalAccessException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
