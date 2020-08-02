package com.tebreca.steammadness.helper.annonation;

import net.minecraft.block.Blocks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropertyOf {

    Class<?> clazz() default Blocks.class;

    String value();

}

