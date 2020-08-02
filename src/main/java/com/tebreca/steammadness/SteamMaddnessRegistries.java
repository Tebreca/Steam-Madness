package com.tebreca.steammadness;

import com.google.inject.Inject;
import com.tebreca.steammadness.blocks.BlockManager;
import com.tebreca.steammadness.fluids.FluidManager;
import com.tebreca.steammadness.items.ItemManager;


public class SteamMaddnessRegistries {

    private final BlockManager blockManager;
    private final ItemManager itemManager;
    private final FluidManager fluidManager;
    private final BlockManager.BlockItemManager blockItemManager;

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public FluidManager getFluidManager() {
        return fluidManager;
    }

    public BlockManager.BlockItemManager getBlockItemManager() {
        return blockItemManager;
    }

    @Inject
    public SteamMaddnessRegistries(BlockManager blockManager, ItemManager itemManager, FluidManager fluidManager) {
        this.blockManager = blockManager;
        this.itemManager = itemManager;
        this.fluidManager = fluidManager;
        this.blockItemManager = blockManager.getItemManager();
    }

    public void inject() {
        this.blockManager.runInjector();
        this.itemManager.runInjector();
        this.fluidManager.runInjector();
        this.blockItemManager.runInjector();
    }
}
