package com.tebreca.steammadness.helper.reflect;

import com.tebreca.steammadness.blocks.obj.BaseFactoryBlock;
import com.tebreca.steammadness.helper.reflect.Injector;
import com.tebreca.steammadness.helper.annonation.OfType;
import com.tebreca.steammadness.helper.annonation.PropertyOf;
import com.tebreca.steammadness.helper.annonation.WithTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static com.tebreca.steammadness.Application.*;

import java.lang.reflect.Field;

public class BlockInjector implements Injector<Block> {


    @Override
    public Block getFrom(Field field) {
        return BlockFactory.from(field);//todo: BlockConfigurator
    }

    @Override
    public Class<? extends Block> getTClass() {
        return Block.class;
    }

    private static class BlockFactory {

        public static Block from(Field field) {
            Block block = null;
            if (field.isAnnotationPresent(OfType.class)) {
                OfType type = field.getAnnotation(OfType.class);
                try {
                    block = type.block().newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    LOGGER.warn("Caught exception trying to make block!! falling back to default!!", e);
                }
            }
            if (block == null) {
                Class<? extends TileEntity> tileEntityClass = null;
                AbstractBlock.Properties properties = AbstractBlock.Properties.create(Material.ROCK);
                if (field.isAnnotationPresent(WithTileEntity.class)) {
                    WithTileEntity withTileEntity = field.getAnnotation(WithTileEntity.class);
                    tileEntityClass = withTileEntity.value();
                }
                if (field.isAnnotationPresent(PropertyOf.class)) {
                    PropertyOf propertyOf = field.getAnnotation(PropertyOf.class);
                    try {
                        properties = AbstractBlock.Properties.from((AbstractBlock) propertyOf.clazz().getDeclaredField(propertyOf.value()).get(null));
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                block = new BaseFactoryBlock(properties, tileEntityClass);
            }
            return block.setRegistryName(new ResourceLocation(MODID, field.getName()));
        }

    }
}
