package com.example.commontransports.vehicle.client.render;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.client.model.HarleyMinestoneModel;
import com.example.commontransports.vehicle.entity.ChopperEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class ChopperRenderer extends EntityRenderer<ChopperEntity, MotorcycleRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            GenericMod.MODID,
            "textures/entity/chopper.png");

    private final HarleyMinestoneModel model;

    public ChopperRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new HarleyMinestoneModel(context.bakeLayer(HarleyMinestoneModel.LAYER_LOCATION));
        this.shadowRadius = 0.7f;
    }

    @Override
    public MotorcycleRenderState createRenderState() {
        return new MotorcycleRenderState();
    }

    @Override
    public void extractRenderState(ChopperEntity entity, MotorcycleRenderState renderState, float partialTick) {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.yRot = Mth.lerp(partialTick, entity.yRotO, entity.getYRot());
        renderState.xRot = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());
        renderState.lean = entity.getCurrentLean();
        renderState.wheelRotation = entity.getWheelRotation();
    }

    @Override
    public void submit(MotorcycleRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector,
                       CameraRenderState cameraRenderState) {
        super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.5F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - renderState.yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.lean));
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        nodeCollector.submitModel(
                this.model,
                renderState,
                poseStack,
                this.model.renderType(TEXTURE),
                renderState.lightCoords,
                OverlayTexture.NO_OVERLAY,
                renderState.outlineColor,
                null
        );
        poseStack.popPose();
    }
}
