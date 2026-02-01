package com.example.commontransports;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.example.commontransports.block.ModBlocks;
import com.example.commontransports.block.entity.ModBlockEntities;
import com.example.commontransports.entity.MotorcycleEntity;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.item.GasCanItem;
import com.example.commontransports.item.MotorcycleItem;
import com.example.commontransports.menu.ModMenuTypes;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(GenericMod.MODID)
public class GenericMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "common_transports";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under
    // the "common_transports" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under
    // the "common_transports" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold EntityTypes which will all be registered
    // under the "common_transports" namespace
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE,
            MODID);
    // Create a Deferred Register to hold SoundEvents which will all be registered
    // under the "common_transports" namespace
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT,
            MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be
    // registered under the "common_transports" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> RUBBER = ITEMS.registerItem("rubber", Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_HANDLEBARS = ITEMS.registerItem("motorcycle_handlebars",
            Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_SEAT = ITEMS.registerItem("motorcycle_seat", Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_WHEEL = ITEMS.registerItem("motorcycle_wheel", Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_BODY = ITEMS.registerItem("motorcycle_body", Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_TANK = ITEMS.registerItem("motorcycle_tank", Item::new);
    public static final DeferredItem<Item> MOTORCYCLE_ITEM = ITEMS.registerItem("motorcycle", MotorcycleItem::new);
    public static final DeferredItem<Item> GAS_CAN = ITEMS.registerItem("gas_can", GasCanItem::new);

    public static final DeferredHolder<EntityType<?>, EntityType<MotorcycleEntity>> MOTORCYCLE = ENTITIES.register(
            "motorcycle",
            () -> EntityType.Builder.<MotorcycleEntity>of(MotorcycleEntity::new, MobCategory.MISC)
                    .sized(0.9f, 0.8f) // width x height - smaller hitbox
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(MODID, "motorcycle"))));
    public static final DeferredHolder<SoundEvent, SoundEvent> MOTORCYCLE_IDLE_SOUND = SOUND_EVENTS.register(
            "motorcycle_idle",
            () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(MODID, "motorcycle_idle")));

    // Creates a creative tab with the id "common_transports:example_tab" for the
    // example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
            .register("example_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.common_transports")) // The language key for the title of
                                                                                  // your CreativeModeTab
                    .withTabsBefore(CreativeModeTabs.TOOLS_AND_UTILITIES)
                    .icon(() -> MOTORCYCLE_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(RUBBER.get());
                        output.accept(MOTORCYCLE_HANDLEBARS.get());
                        output.accept(MOTORCYCLE_SEAT.get());
                        output.accept(MOTORCYCLE_WHEEL.get());
                        output.accept(MOTORCYCLE_BODY.get());
                        output.accept(MOTORCYCLE_TANK.get());
                        output.accept(MOTORCYCLE_ITEM.get());
                        output.accept(GAS_CAN.get());
                        // Fluid bucket (only crude oil - petrol requires refinery)
                        output.accept(ModFluids.CRUDE_OIL_BUCKET.get());
                        // Machines
                        output.accept(ModBlocks.REFINERY_ITEM.get());
                    }).build());

    // The constructor for the mod class is the first code that is run when your mod
    // is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and
    // pass them in automatically.
    public GenericMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so entities get
        // registered
        ENTITIES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so sounds get registered
        SOUND_EVENTS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register fluids
        ModFluids.register(modEventBus);
        // Register blocks and block entities
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        // Register menus
        ModMenuTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (GenericMod)
        // to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in
        // this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        
        // Register capabilities (for pipe/automation support)
        modEventBus.addListener(this::registerCapabilities);

        // Register our mod's ModConfigSpec so that FML can create and load the config
        // file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    
    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Register fluid handler capability for the refinery block entity
        // Using the new Capabilities.Fluid.BLOCK for modern pipe/automation support
        event.registerBlockEntity(
            Capabilities.Fluid.BLOCK,
            ModBlockEntities.REFINERY.get(),
            (refinery, side) -> refinery.getFluidHandler(side)
        );
        LOGGER.info("Registered fluid handler capability for Refinery");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // Add the example block item to the building blocks tab; add hat to combat tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(MOTORCYCLE_ITEM.get());
            event.accept(GAS_CAN.get());
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        var recipes = event.getServer().getRecipeManager().getRecipes();
        long commonTransportsRecipes = recipes.stream()
                .filter(holder -> holder.id().identifier().getNamespace().equals(MODID))
                .count();
        LOGGER.info("Server recipes loaded: total={}, {} for {}", recipes.size(), commonTransportsRecipes, MODID);
        recipes.stream()
                .filter(holder -> holder.id().identifier().getNamespace().equals(MODID))
                .map(holder -> holder.id().identifier().toString())
                .sorted()
                .limit(20)
                .forEach(id -> LOGGER.info("Recipe: {}", id));
    }

    @SubscribeEvent
    public void onDatapackSync(OnDatapackSyncEvent event) {
        event.sendRecipes(RecipeType.CRAFTING);
        LOGGER.info("Requested crafting recipe sync for {}", MODID);
    }
}
