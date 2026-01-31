package com.example.commontransports;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

import com.example.commontransports.client.model.MotorcycleModel;
import com.example.commontransports.client.render.MotorcycleRenderer;
import com.example.commontransports.client.screen.RefineryScreen;
import com.example.commontransports.client.sound.MotorcycleIdleSoundInstance;
import com.example.commontransports.entity.MotorcycleEntity;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.menu.ModMenuTypes;
// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = GenericMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = GenericMod.MODID, value = Dist.CLIENT)
public class GenericModClient {
    private static MotorcycleIdleSoundInstance idleSoundInstance;

    public GenericModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onComputeFov);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onClientTick);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onRecipesReceived);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        GenericMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        GenericMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GenericMod.MOTORCYCLE.get(), MotorcycleRenderer::new);
    }

    @SubscribeEvent
    static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MotorcycleModel.LAYER_LOCATION, MotorcycleModel::createBodyLayer);
    }
    
    @SubscribeEvent
    static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.REFINERY.get(), RefineryScreen::new);
    }

    @SubscribeEvent
    static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        // Crude Oil - dark black color, uses vanilla water textures with tint
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return 0xFF1a1a1a; // Very dark, almost black
            }

            @Override
            public Identifier getStillTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
            }

            @Override
            public Identifier getFlowingTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");
            }
        }, ModFluids.CRUDE_OIL_TYPE.get());

        // Petrol - amber/golden color, uses vanilla water textures with tint
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return 0xFFc4a000; // Amber/golden color
            }

            @Override
            public Identifier getStillTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
            }

            @Override
            public Identifier getFlowingTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");
            }
        }, ModFluids.PETROL_TYPE.get());
    }

    private static void onComputeFov(ComputeFovModifierEvent event) {
        if (!(event.getPlayer().getVehicle() instanceof MotorcycleEntity motorcycle)) {
            return;
        }
        float speed = (float) motorcycle.getDeltaMovement().horizontalDistance();
        float ratio = Mth.clamp(speed / MotorcycleEntity.getMaxSpeed(), 0.0f, 1.0f);
        event.setNewFovModifier(event.getNewFovModifier() * (1.0f + ratio * 0.25f));
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null) {
            stopIdleSound();
            return;
        }
        if (!(minecraft.player.getVehicle() instanceof MotorcycleEntity motorcycle)) {
            stopIdleSound();
            return;
        }
        if (motorcycle.getDeltaMovement().horizontalDistanceSqr() > 0.001) {
            stopIdleSound();
            return;
        }
        if (idleSoundInstance == null || idleSoundInstance.isStopped()
                || idleSoundInstance.getMotorcycleId() != motorcycle.getId()) {
            idleSoundInstance = new MotorcycleIdleSoundInstance(motorcycle);
            minecraft.getSoundManager().play(idleSoundInstance);
        }
    }

    private static void stopIdleSound() {
        if (idleSoundInstance != null) {
            idleSoundInstance.stopSound();
            idleSoundInstance = null;
        }
    }

    private static void onRecipesReceived(RecipesReceivedEvent event) {
        int totalCrafting = event.getRecipeMap().byType(RecipeType.CRAFTING).size();
        var commonTransportsCraftingRecipes = event.getRecipeMap()
                .byType(RecipeType.CRAFTING)
                .stream()
                .map(RecipeHolder::id)
                .filter(key -> key.identifier().getNamespace().equals(GenericMod.MODID))
                .map(key -> key.identifier().toString())
                .sorted()
                .toList();
        GenericMod.LOGGER.info("Recipes received: crafting total={}, {} crafting for {}",
                totalCrafting, commonTransportsCraftingRecipes.size(), GenericMod.MODID);
        commonTransportsCraftingRecipes.stream()
                .limit(20)
                .forEach(id -> GenericMod.LOGGER.info("Client crafting recipe: {}", id));
    }
}
