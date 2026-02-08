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
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import com.example.commontransports.client.gui.VehicleFuelHudOverlay;
import com.example.commontransports.client.screen.BasicFluidPipeScreen;
import com.example.commontransports.client.screen.CatalyticReformerScreen;
import com.example.commontransports.client.screen.DistillationTowerScreen;
import com.example.commontransports.client.screen.RefineryScreen;
import com.example.commontransports.fluid.ModFluids;
import com.example.commontransports.menu.ModMenuTypes;
import com.example.commontransports.vehicle.client.model.HarleyMinestoneModel;
import com.example.commontransports.vehicle.client.model.MotorcycleModel;
import com.example.commontransports.vehicle.client.render.ChopperRenderer;
import com.example.commontransports.vehicle.client.render.MotorcycleRenderer;
import com.example.commontransports.vehicle.client.sound.MotorcycleIdleSoundInstance;
import com.example.commontransports.vehicle.entity.AbstractStatsVehicleEntity;
import com.example.commontransports.vehicle.entity.MotorcycleEntity;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = GenericMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods
// in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = GenericMod.MODID, value = Dist.CLIENT)
public class GenericModClient {
    private static MotorcycleIdleSoundInstance idleSoundInstance;

    // Track passengers on motorcycles for third-person lean rendering
    // Raw lean from entity (updated each tick)
    private static final Map<Integer, Float> motorcyclePassengerLean = new ConcurrentHashMap<>();
    // Smoothed lean for rendering (lerped each tick to reduce twitching)
    private static final Map<Integer, Float> motorcyclePassengerSmoothedLean = new ConcurrentHashMap<>();
    private static final float LEAN_SMOOTH_FACTOR = 0.25f; // Lower = smoother but more lag

    public GenericModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your
        // mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json
        // file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onComputeFov);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onClientTick);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onCameraSetup);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onRenderLivingPre);
        NeoForge.EVENT_BUS.addListener(GenericModClient::onRenderLivingPost);
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
        event.registerEntityRenderer(GenericMod.CHOPPER.get(), ChopperRenderer::new);
    }

    @SubscribeEvent
    static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MotorcycleModel.LAYER_LOCATION, MotorcycleModel::createBodyLayer);
        event.registerLayerDefinition(HarleyMinestoneModel.LAYER_LOCATION, HarleyMinestoneModel::createBodyLayer);
    }

    @SubscribeEvent
    static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.REFINERY.get(), RefineryScreen::new);
        event.register(ModMenuTypes.DISTILLATION_TOWER.get(), DistillationTowerScreen::new);
        event.register(ModMenuTypes.CATALYTIC_REFORMER.get(), CatalyticReformerScreen::new);
        event.register(ModMenuTypes.BASIC_FLUID_PIPE.get(), BasicFluidPipeScreen::new);
    }

    @SubscribeEvent
    static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(
                Identifier.fromNamespaceAndPath(GenericMod.MODID, "vehicle_fuel_hud"),
                VehicleFuelHudOverlay::render);
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

        // Naphtha - lighter hydrocarbon tint
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return 0xFF8C6B3A;
            }

            @Override
            public Identifier getStillTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
            }

            @Override
            public Identifier getFlowingTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");
            }
        }, ModFluids.NAPHTHA_TYPE.get());

        // Reformate - lighter amber intermediate from catalytic reforming
        event.registerFluidType(new IClientFluidTypeExtensions() {
            @Override
            public int getTintColor() {
                return 0xFFb1864a;
            }

            @Override
            public Identifier getStillTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_still");
            }

            @Override
            public Identifier getFlowingTexture() {
                return Identifier.fromNamespaceAndPath("minecraft", "block/water_flow");
            }
        }, ModFluids.REFORMATE_TYPE.get());

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
        if (!(event.getPlayer().getVehicle() instanceof AbstractStatsVehicleEntity vehicle)) {
            return;
        }
        float speed = (float) vehicle.getDeltaMovement().horizontalDistance();
        float ratio = Mth.clamp(speed / vehicle.getMaxSpeed(), 0.0f, 1.0f);
        event.setNewFovModifier(event.getNewFovModifier() * (1.0f + ratio * 0.25f));
    }

    private static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }
        if (!(minecraft.player.getVehicle() instanceof AbstractStatsVehicleEntity vehicle)) {
            return;
        }

        // Apply camera roll based on motorcycle lean
        float lean = vehicle.getCurrentLean();
        float cameraRoll = -(lean * 0.8f); // 40% of bike lean applied to camera
        event.setRoll(event.getRoll() + cameraRoll);
    }

    /** Lean + bike yaw so we roll the player around the bike's forward axis. */
    private static record RiderLeanState(float lean, float bikeYaw) {}

    /**
     * Find which motorcycle passenger (if any) this render state corresponds to.
     * Returns smoothed lean and interpolated bike yaw so we can use the bike as the lean axis.
     */
    private static RiderLeanState getRiderLeanState(
            net.minecraft.client.renderer.entity.state.LivingEntityRenderState renderState,
            float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || renderState == null) {
            return null;
        }

        double rx = renderState.x;
        double ry = renderState.y;
        double rz = renderState.z;

        Integer bestId = null;
        double bestDistSq = Double.MAX_VALUE;
        final double maxDistSq = 0.25 * 0.25;

        for (var entry : motorcyclePassengerLean.entrySet()) {
            Entity entity = minecraft.level.getEntity(entry.getKey());
            if (entity == null || !(entity instanceof LivingEntity)) {
                continue;
            }
            double ex = Mth.lerp(partialTick, entity.xOld, entity.getX());
            double ey = Mth.lerp(partialTick, entity.yOld, entity.getY());
            double ez = Mth.lerp(partialTick, entity.zOld, entity.getZ());
            double dx = rx - ex;
            double dy = ry - ey;
            double dz = rz - ez;
            double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq < bestDistSq && distSq <= maxDistSq) {
                bestDistSq = distSq;
                bestId = entry.getKey();
            }
        }

        if (bestId == null) {
            return null;
        }
        Float smoothed = motorcyclePassengerSmoothedLean.get(bestId);
        Float raw = motorcyclePassengerLean.get(bestId);
        float lean = smoothed != null ? smoothed : (raw != null ? raw : 0f);
        Entity entity = minecraft.level.getEntity(bestId);
        float bikeYaw = 0f;
        if (entity != null && entity.getVehicle() instanceof AbstractStatsVehicleEntity bike) {
            bikeYaw = Mth.lerp(partialTick, bike.yRotO, bike.getYRot());
        }
        return new RiderLeanState(lean, bikeYaw);
    }
    
    @SuppressWarnings("rawtypes")
    private static void onRenderLivingPre(RenderLivingEvent.Pre event) {
        RiderLeanState state = getRiderLeanState(event.getRenderState(), event.getPartialTick());
        if (state == null) {
            return;
        }

        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();

        // Pivot at hip, then use bike as lean axis: roll around bike's forward direction
        float pivotHeight = 0.9f;
        poseStack.translate(0, pivotHeight, 0);
        // Align so bike forward = local +Z, then roll around Z, then undo
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.bikeYaw()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-state.lean() * 0.7f));
        poseStack.mulPose(Axis.YP.rotationDegrees(state.bikeYaw()));
        poseStack.translate(0, -pivotHeight, 0);
    }
    
    @SuppressWarnings("rawtypes")
    private static void onRenderLivingPost(RenderLivingEvent.Post event) {
        RiderLeanState state = getRiderLeanState(event.getRenderState(), event.getPartialTick());
        if (state == null) {
            return;
        }
        event.getPoseStack().popPose();
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.player == null) {
            stopIdleSound();
            motorcyclePassengerLean.clear();
            motorcyclePassengerSmoothedLean.clear();
            return;
        }

        // Update raw lean for all passengers on motorcycles
        motorcyclePassengerLean.clear();
        for (Entity entity : minecraft.level.entitiesForRendering()) {
            if (entity.getVehicle() instanceof AbstractStatsVehicleEntity statsVehicle) {
                int id = entity.getId();
                float raw = statsVehicle.getCurrentLean();
                motorcyclePassengerLean.put(id, raw);
                // Lerp smoothed lean towards raw to reduce twitching
                float prev = motorcyclePassengerSmoothedLean.getOrDefault(id, raw);
                float smoothed = Mth.lerp(LEAN_SMOOTH_FACTOR, prev, raw);
                motorcyclePassengerSmoothedLean.put(id, smoothed);
            }
        }
        // Drop smoothed entries for entities no longer on a bike
        motorcyclePassengerSmoothedLean.keySet().removeIf(id -> !motorcyclePassengerLean.containsKey(id));

        // Handle idle sound for local player
        if (!(minecraft.player.getVehicle() instanceof MotorcycleEntity motorcycle)) {
            fadeOutIdleSound();
            return;
        }
        if (motorcycle.getDeltaMovement().horizontalDistanceSqr() > 0.001) {
            fadeOutIdleSound();
            return;
        }
        // Start a new instance if there is none, or the previous one finished/is fading,
        // or the player switched to a different motorcycle.
        if (idleSoundInstance == null || idleSoundInstance.isStopped()
                || idleSoundInstance.isFadingOut()
                || idleSoundInstance.getMotorcycleId() != motorcycle.getId()) {
            // Let any old instance finish its fade-out on its own (SoundManager still ticks it)
            idleSoundInstance = new MotorcycleIdleSoundInstance(motorcycle);
            minecraft.getSoundManager().play(idleSoundInstance);
        }
    }

    /**
     * Gracefully fade out the current idle sound instead of cutting it instantly.
     * The old instance keeps ticking inside SoundManager until its volume reaches zero.
     */
    private static void fadeOutIdleSound() {
        if (idleSoundInstance != null) {
            if (!idleSoundInstance.isStopped()) {
                idleSoundInstance.fadeOut();
            }
            idleSoundInstance = null; // release reference; SoundManager manages the fade
        }
    }

    /** Hard-stop with no fade (used when the world/player is null). */
    private static void stopIdleSound() {
        if (idleSoundInstance != null) {
            idleSoundInstance.stopSound();
            idleSoundInstance = null;
        }
    }

}
