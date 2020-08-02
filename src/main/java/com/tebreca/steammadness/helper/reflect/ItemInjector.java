package com.tebreca.steammadness.helper.reflect;

import com.tebreca.steammadness.helper.reflect.Injector;
import net.minecraft.item.Item;

import java.lang.reflect.Field;

public class ItemInjector implements Injector<Item> {

    @Override
    public Item getFrom(Field field) {
        return standard();//todo: ItemConfigurator
    }

    private Item standard() {
        return new Item(new Item.Properties());
    }

    @Override
    public Class<? extends Item> getTClass() {
        return null;
    }
}
