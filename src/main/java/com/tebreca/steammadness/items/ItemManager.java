package com.tebreca.steammadness.items;

import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ItemInjector;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

import static com.tebreca.steammadness.Application.MODID;

@ObjectHolder(MODID)
public class ItemManager implements EntryHolder<Item> {

    private static final ItemInjector injector = new ItemInjector();

    public Item steam_tank;

    @Override
    public void runInjector() {
        injector.Inject(this);
    }

    @Override
    public List<Item> all() {
        return ReflectionHelper.collectFields(Item.class, this);
    }
}
