package com.tebreca.steammadness.fluids;

import com.tebreca.steammadness.helper.reflect.EntryHolder;
import com.tebreca.steammadness.helper.reflect.ReflectionHelper;
import net.minecraft.fluid.Fluid;

import java.util.List;

import static com.tebreca.steammadness.Application.MODID;

public class FluidManager implements EntryHolder<Fluid> {

    public Fluid steam;

    @Override
    public void runInjector() {
         steam = new SteamFluid().setRegistryName(MODID, "steam");
    }

    @Override
    public List<Fluid> all() {
        return ReflectionHelper.collectFields(Fluid.class, this);
    }
}
