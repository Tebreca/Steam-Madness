package com.tebreca.steammadness;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.tebreca.steammadness.helper.reflect.RegistryModule;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("steammadness")
public class Application
{
    public static final String MODID = "steammadness";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    private static Application instance;

    @Inject
    private final SteamMaddnessRegistries registries;

    private final Injector injector;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static Application getInstance() {
        return instance;
    }

    public SteamMaddnessRegistries getRegistries() {
        return registries;
    }

    public Injector getInjector() {
        return injector;
    }

    public Application() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        injector = Guice.createInjector(new RegistryModule());

        registries = injector.getInstance(SteamMaddnessRegistries.class);
        instance = this;
        //inject registry entries
        registries.inject();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegister) {
            instance.getRegistries().getBlockManager().all().forEach(blockRegister.getRegistry()::register);
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegister) {
            instance.getRegistries().getItemManager().all().forEach(itemRegister.getRegistry()::register);
            instance.registries.getBlockItemManager().all().forEach(itemRegister.getRegistry()::register);
        }

        @SubscribeEvent
        public static void onFluidRegistry(final RegistryEvent.Register<Fluid> fluidRegister) {
            List<Fluid> fluids = instance.getRegistries().getFluidManager().all();
            fluids.forEach(fluidRegister.getRegistry()::register);
        }

        @SubscribeEvent
        public static void onTETypeRegistry(final RegistryEvent.Register<TileEntityType<?>> tileEntityTypeRegister) {
            instance.getRegistries().getTileEntityTypeManager().all().forEach(tileEntityTypeRegister.getRegistry()::register);
        }

    }

    //for test cases
    public static void main(String[] args) {
        Vector3i[] states = {
                new Vector3i(14,50,14),
                new Vector3i(15,50,14),
                new Vector3i(15,50,13),
                new Vector3i(16,50,14),
                new Vector3i(17,50,14),
                new Vector3i(17,50,15),
                new Vector3i(15,50,15)
        };
        List<Integer> xPositions = Arrays.stream(states).map(Vector3i::getX).collect(Collectors.toList());
        List<Integer> yPositions = Arrays.stream(states).map(Vector3i::getY).collect(Collectors.toList());
        List<Integer> zPositions = Arrays.stream(states).map(Vector3i::getZ).collect(Collectors.toList());
        System.out.println(new BlockPos(median(xPositions), median(yPositions), median(zPositions)));
    }

    public static int median(@Nonnull List<Integer> list){
        list.sort(Integer::compareTo);
        System.out.println(Arrays.toString(list.toArray()));
        return list.get(list.size()/2);
    }

}
