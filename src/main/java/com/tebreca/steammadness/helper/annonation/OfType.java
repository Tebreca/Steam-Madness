package com.tebreca.steammadness.helper.annonation;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OfType {

    Class<? extends Block> block() default Block.class;

    Class<? extends Item> item() default Item.class;

}
