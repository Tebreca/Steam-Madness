package com.tebreca.steammadness.blocks;

import com.tebreca.steammadness.blocks.obj.TestBlock;
import com.tebreca.steammadness.helper.annonation.OfType;
import com.tebreca.steammadness.helper.annonation.PropertyOf;
import com.tebreca.steammadness.helper.reflect.BlockInjector;
import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockManager implements EntryHolder<Block> {

    private final BlockItemManager itemManager = new BlockItemManager();

    //simple block with properties of oak planks
    @PropertyOf(value = "OAK_PLANKS")
    public Block block1;

    //simple block with default properties (material rock)
    public Block block2;

    //block of type TestBlock
    @OfType(block = TestBlock.class)
    public Block block3;

    //simple block with default properties (material rock)
    public Block block4;

    //simple block with default properties (material rock)
    public Block block5;

    private static final BlockInjector injector = new BlockInjector();


    @Override
    public void runInjector() {
        injector.Inject(this);
        manualInject();
    }

    private void manualInject() {

    }

    @Override
    public List<Block> all() {
        return ReflectionHelper.collectFields(Block.class, this);
    }

    public BlockItemManager getItemManager() {
        return itemManager;
    }

    public class BlockItemManager implements EntryHolder<BlockItem> {

        Map<Block, BlockItem> blockItemMap = new HashMap<>();

        @Override
        public void runInjector() {
            BlockManager.this.all().forEach(blockIn -> blockItemMap.put(blockIn, (BlockItem) new BlockItem(blockIn, new Item.Properties()).setRegistryName(blockIn.getRegistryName())));
        }

        @Override
        public List<BlockItem> all() {
            return new ArrayList<>(blockItemMap.values());
        }
    }

}
