package com.tebreca.steammadness.fluids;

import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.fluid.Fluid;

import java.util.List;

public class FluidManager implements EntryHolder<Fluid> {
    @Override
    public void runInjector() {
        
    }

    @Override
    public List<Fluid> all() {
        return ReflectionHelper.collectFields(Fluid.class, this);
    }
}
