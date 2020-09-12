package com.tebreca.steammadness.helper.reflect;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.tebreca.steammadness.blocks.BlockManager;
import com.tebreca.steammadness.fluids.FluidManager;
import com.tebreca.steammadness.items.ItemManager;
import com.tebreca.steammadness.tiles.TileEntityTypeManager;

public class RegistryModule extends AbstractModule {

    @Provides
    public BlockManager blockManager() {
        return new BlockManager();
    }

    @Provides
    public ItemManager itemManager() {
        return new ItemManager();
    }

    @Provides
    public FluidManager fluidManager() {
        return new FluidManager();
    }

    @Provides
    public TileEntityTypeManager tileEntityManager() {
        return new TileEntityTypeManager();
    }

}
