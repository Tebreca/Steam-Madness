package com.tebreca.steammadness.helper.reflect;

import com.tebreca.steammadness.helper.annonation.OfType;
import com.tebreca.steammadness.items.obj.BaseFactoryItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;

import static com.tebreca.steammadness.Application.LOGGER;
import static com.tebreca.steammadness.Application.MODID;

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

    private static class ItemFactory {

        public static Item from(Field field) {
            Item item = null;
            if (field.isAnnotationPresent(OfType.class)) {
                OfType type = field.getAnnotation(OfType.class);
                try {
                    item = type.item().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.warn("Caught exception trying to make block!! falling back to default!!", e);
                }
            }
            if (item == null && !field.getType().equals(Item.class)){
                try {
                    item = (Item) field.getType().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (item == null) {
                item = new BaseFactoryItem(new Item.Properties());
            }
            return item.setRegistryName(new ResourceLocation(MODID, field.getName()));
        }

    }

}
