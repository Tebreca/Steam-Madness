package com.tebreca.steammadness.tiles;

import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

import static com.tebreca.steammadness.Application.MODID;
import static com.tebreca.steammadness.Application.getInstance;

@ObjectHolder(MODID)
public class TileEntityTypeManager implements EntryHolder<TileEntityType> {



    @Override
    public void runInjector() {
        manualInject();
    }

    private void manualInject() {

    }

    private static TileEntityType<?> create(String key, TileEntityType.Builder<?> builder) {
        return builder.build(null).setRegistryName(new ResourceLocation(MODID, key));
    }

    @Override
    public List<TileEntityType> all() {
        return ReflectionHelper.collectFields(TileEntityType.class, this);
    }
}
