package com.tebreca.steammadness.items;

import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ItemInjector;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.item.Item;

import java.util.List;

public class ItemManager implements EntryHolder<Item> {

    private static final ItemInjector injector = new ItemInjector();

    @Override
    public void runInjector() {
        injector.Inject(this);
    }


    @Override
    public List<Item> all() {
        return ReflectionHelper.collectFields(Item.class, this);
    }
}
