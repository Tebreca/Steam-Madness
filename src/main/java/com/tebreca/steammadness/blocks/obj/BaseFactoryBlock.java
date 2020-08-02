package com.tebreca.steammadness.blocks.obj;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BaseFactoryBlock extends Block {

    private final Class<? extends TileEntity> tileEntityClass;

    public BaseFactoryBlock(Properties properties, Class<? extends TileEntity> tileEntityClass) {
        super(properties);
        this.tileEntityClass = tileEntityClass;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return tileEntityClass != null;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        try {
            return tileEntityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
