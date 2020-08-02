package com.tebreca.steammadness.blocks.obj;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestBlock extends Block {


    public TestBlock() {
        super(Properties.from(Blocks.GRASS));
        System.out.println("Test block created!!");
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.setGlowing(true);
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
