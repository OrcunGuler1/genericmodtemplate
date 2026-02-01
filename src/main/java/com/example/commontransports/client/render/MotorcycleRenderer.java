package com.example.commontransports.client.render;

import com.example.commontransports.GenericMod;
import com.example.commontransports.client.model.MotorcycleModel;
import com.example.commontransports.entity.MotorcycleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class MotorcycleRenderer extends EntityRenderer<MotorcycleEntity, MotorcycleRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            GenericMod.MODID,
            "textures/entity/motorcycle.png");

    private final MotorcycleModel model;

    public MotorcycleRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MotorcycleModel(context.bakeLayer(MotorcycleModel.LAYER_LOCATION));
        this.shadowRadius = 0.7f;
    }

    @Override
    public MotorcycleRenderState createRenderState() {
        return new MotorcycleRenderState();
    }

    @Override
    public void extractRenderState(MotorcycleEntity entity, MotorcycleRenderState renderState, float partialTick) {
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
        poseStack.translate(0.0F, 1.5F, 0.0F); // half block higher so wheels sit on ground visually
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
