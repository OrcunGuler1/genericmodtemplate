// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

package com.example.commontransports.client.model;

import com.example.commontransports.GenericMod;
import com.example.commontransports.client.render.MotorcycleRenderState;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class MotorcycleModel extends EntityModel<MotorcycleRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(GenericMod.MODID, "motorcycle"),
			"main");
	private final @Nullable ModelPart frontWheel;
	private final @Nullable ModelPart rearWheel;

	public MotorcycleModel(ModelPart root) {
		super(root);
		this.frontWheel = root.hasChild("wheelfront") ? root.getChild("wheelfront") : null;
		this.rearWheel = root.hasChild("wheelback") ? root.getChild("wheelback") : null;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("wheelfront",
				CubeListBuilder.create().texOffs(84, 19)
						.addBox(-3.2F, -7.0F, 1.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(84, 31).addBox(-3.2F, -5.0F, 3.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(84, 35).addBox(-3.2F, -3.0F, 5.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(86, 47).addBox(-3.2F, -1.0F, 5.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(86, 51).addBox(-3.2F, 1.0F, 5.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(86, 55).addBox(-3.2F, 3.0F, 3.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 83).addBox(-3.2F, 5.0F, 1.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(80, 79).addBox(-3.2F, 5.0F, -1.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(82, 3).addBox(-3.2F, 5.0F, -3.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(40, 83).addBox(-3.2F, 3.0F, -5.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(56, 83).addBox(-3.2F, 1.0F, -7.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(72, 83).addBox(-3.2F, -1.0F, -7.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(84, 15).addBox(-3.2F, -3.0F, -7.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(84, 27).addBox(-3.2F, -5.0F, -5.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(84, 23).addBox(-3.2F, -7.0F, -3.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(16, 84).addBox(-3.2F, -7.0F, -1.0F, 6.4F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 17.0F, -17.0F));

		partdefinition.addOrReplaceChild("wheelback",
				CubeListBuilder.create().texOffs(22, 56)
						.addBox(-4.6F, -3.0F, 5.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(44, 56).addBox(-4.6F, -1.0F, 5.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 57).addBox(-4.6F, 1.0F, 5.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(58, 8).addBox(-4.6F, 3.0F, 3.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(26, 44).addBox(-4.6F, 5.0F, 1.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(36, 8).addBox(-4.6F, 5.0F, -1.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(26, 40).addBox(-4.6F, 5.0F, -3.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 45).addBox(-4.6F, 3.0F, -5.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(22, 48).addBox(-4.6F, 1.0F, -7.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(48, 40).addBox(-4.6F, -1.0F, -7.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(44, 48).addBox(-4.6F, -3.0F, -7.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(44, 52).addBox(-4.6F, -5.0F, -5.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(22, 52).addBox(-4.6F, -7.0F, -3.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(48, 44).addBox(-4.6F, -7.0F, -1.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 49).addBox(-4.6F, -7.0F, 1.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 53).addBox(-4.6F, -5.0F, 3.0F, 9.2F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 17.0F, 17.0F));

		partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-4.0F, -15.0F, -0.2F, 8.0F, 2.0F, 10.2F, new CubeDeformation(0.0F))
				.texOffs(22, 60).addBox(-4.0F, -16.0F, 7.0F, 8.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(62, 0).addBox(-4.0F, -17.0F, -14.0F, 8.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(10, 89).addBox(3.8F, -17.75F, -14.0F, 0.2F, 0.75F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.75F, -11.9F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.55F, -11.9F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.35F, -11.9F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.35F, -11.2F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.55F, -11.2F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.75F, -11.2F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.9F, -11.35F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.8F, -11.4F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.6F, -11.4F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.4F, -11.4F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.2F, -11.35F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.2F, -11.55F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.4F, -11.6F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.8F, -11.6F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.9F, -11.55F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.9F, -11.75F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.8F, -11.8F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.6F, -11.8F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.4F, -11.8F, 12.0F, 0.2F, 0.2F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -16.2F, -11.75F, 12.0F, 0.1F, 0.1F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.25F, -11.0F, 8.0F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.625F, -11.0F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.5F, -11.0F, 8.0F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.375F, -10.75F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.25F, -10.75F, 8.0F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.125F, -10.5F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.5F, -10.75F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.375F, -10.625F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.125F, -10.375F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.25F, -10.5F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.75F, -11.0F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.625F, -10.875F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -17.25F, -10.375F, 8.0F, 0.125F, 0.125F, new CubeDeformation(0.0F))
				.texOffs(44, 60).addBox(-4.0F, -17.75F, -14.0F, 7.8F, 0.75F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(64, 12).addBox(-3.825F, -17.775F, -14.0F, 7.625F, 0.05F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(34, 22).addBox(-3.85F, -14.0F, 0.5F, 7.7F, 2.0F, 7.25F, new CubeDeformation(0.0F))
				.texOffs(0, 79).addBox(-3.85F, -10.0F, 0.5F, 7.7F, 2.0F, 1.75F, new CubeDeformation(0.0F))
				.texOffs(80, 8).addBox(-3.85F, -8.0F, 0.5F, 7.7F, 2.0F, 1.75F, new CubeDeformation(0.0F))
				.texOffs(20, 80).addBox(-3.85F, -6.0F, 0.5F, 7.7F, 2.0F, 1.75F, new CubeDeformation(0.0F))
				.texOffs(80, 76).addBox(-3.85F, -4.0F, 0.5F, 7.7F, 1.0F, 1.75F, new CubeDeformation(0.0F))
				.texOffs(64, 15).addBox(-3.85F, -14.0F, -1.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 19).addBox(-3.85F, -12.0F, -1.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 64).addBox(-3.85F, -10.0F, -1.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 23).addBox(-3.85F, -8.0F, -1.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 27).addBox(-3.85F, -6.0F, -1.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 80).addBox(-3.85F, -4.0F, -1.5F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(84, 0).addBox(-3.85F, -15.0F, -3.5F, 7.7F, 1.0F, 1.25F, new CubeDeformation(0.0F))
				.texOffs(64, 31).addBox(-3.85F, -14.0F, -3.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 35).addBox(-3.85F, -12.0F, -3.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 64).addBox(-3.85F, -10.0F, -3.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 64).addBox(-3.85F, -8.0F, -3.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(66, 48).addBox(-3.85F, -6.0F, -3.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 80).addBox(-3.85F, -4.0F, -3.5F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(80, 64).addBox(-3.85F, -4.0F, -5.5F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(80, 67).addBox(-3.85F, -4.0F, -7.5F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(80, 70).addBox(-3.85F, -4.0F, -9.5F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(80, 73).addBox(-3.85F, -3.5F, -10.25F, 7.7F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(66, 52).addBox(-3.85F, -6.0F, -5.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(66, 56).addBox(-3.85F, -8.0F, -5.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(66, 60).addBox(-3.85F, -10.0F, -5.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 67).addBox(-3.85F, -12.0F, -5.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 68).addBox(-3.85F, -14.0F, -5.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(62, 3).addBox(-3.85F, -17.0F, -5.5F, 7.7F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 68).addBox(-3.85F, -14.0F, -7.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 68).addBox(-3.85F, -12.0F, -7.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(70, 39).addBox(-3.85F, -10.0F, -7.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(70, 43).addBox(-3.85F, -8.0F, -7.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 71).addBox(-3.85F, -6.0F, -7.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 72).addBox(-3.85F, -6.0F, -9.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 72).addBox(-3.85F, -8.0F, -9.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(60, 72).addBox(-3.85F, -10.0F, -9.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 75).addBox(-3.85F, -12.0F, -9.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 76).addBox(-3.85F, -14.0F, -9.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 76).addBox(-3.85F, -14.0F, -11.5F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(82, 7).addBox(-3.85F, -11.0F, -9.75F, 7.7F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(84, 2).addBox(-3.85F, -12.0F, -9.75F, 7.7F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(86, 14).addBox(-3.85F, -12.0F, -10.0F, 7.7F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(88, 83).addBox(-3.85F, -12.0F, -10.25F, 7.7F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.25F, -10.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.25F, -10.75F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.0F, -10.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.0F, -10.25F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -10.75F, -10.25F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -12.0F, -11.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -12.0F, -11.25F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.75F, -11.25F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.5F, -11.0F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.75F, -11.0F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -12.0F, -11.0F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -12.0F, -10.75F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.75F, -10.75F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.5F, -10.75F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.5F, -10.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.75F, -10.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -12.0F, -10.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -11.0F, -10.0F, 7.7F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 87).addBox(-3.85F, -14.0F, -11.75F, 7.7F, 2.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(68, 87).addBox(-3.85F, -14.0F, -12.0F, 7.7F, 1.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(16, 88).addBox(-3.85F, -14.0F, -12.25F, 7.7F, 1.5F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(70, 47).addBox(-3.85F, -14.0F, -12.5F, 7.7F, 1.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(88, 84).addBox(-3.85F, -14.0F, -12.75F, 7.7F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(88, 85).addBox(-3.85F, -14.0F, -13.0F, 7.7F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(88, 85).addBox(-3.85F, -14.0F, -13.25F, 7.7F, 0.5F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(88, 85).addBox(-3.85F, -14.0F, -13.5F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -17.75F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(32, 12).addBox(-3.85F, -15.5F, -13.5F, 7.7F, 1.5F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 90).addBox(-2.6F, -14.75F, -18.75F, 5.2F, 0.5F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -14.25F, -13.75F, 7.7F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 61).addBox(-2.6F, -15.5F, -18.0F, 5.2F, 1.25F, 4.5F, new CubeDeformation(0.0F))
				.texOffs(60, 89).addBox(-2.6F, -15.25F, -18.25F, 5.2F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 90).addBox(-2.6F, -15.0F, -18.5F, 5.2F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 27).addBox(-3.85F, -17.0F, -14.25F, 7.7F, 1.5F, 8.75F, new CubeDeformation(0.0F))
				.texOffs(0, 89).addBox(-2.6F, -16.75F, -14.5F, 5.2F, 1.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(70, 89).addBox(-2.6F, -16.5F, -15.0F, 5.2F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(50, 89).addBox(-2.6F, -16.75F, -14.75F, 5.2F, 1.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(80, 89).addBox(-2.6F, -16.5F, -15.25F, 5.2F, 1.0F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -16.25F, -15.5F, 5.2F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -16.25F, -15.75F, 5.2F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -16.25F, -16.0F, 5.2F, 0.75F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -16.0F, -16.25F, 5.2F, 0.5F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -16.5F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -16.75F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -17.0F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -17.25F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.6F, -15.75F, -17.5F, 5.2F, 0.25F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-3.85F, -12.0F, 1.75F, 7.7F, 7.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(60, 76).addBox(-3.85F, -14.0F, 7.75F, 7.7F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(86, 59).addBox(-2.85F, -15.0F, 9.75F, 5.7F, 1.0F, 2.25F, new CubeDeformation(0.0F))
				.texOffs(36, 0).addBox(-2.85F, -15.0F, 12.0F, 5.7F, 0.5F, 7.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -14.0F, 11.75F, 7.7F, 0.5F, 0.25F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.85F, -14.0F, 11.25F, 7.7F, 0.75F, 0.5F, new CubeDeformation(0.0F))
				.texOffs(84, 87).addBox(-3.85F, -14.0F, 10.75F, 7.7F, 1.0F, 0.5F, new CubeDeformation(0.0F))
				.texOffs(50, 87).addBox(-3.85F, -14.0F, 10.25F, 7.7F, 1.25F, 0.5F, new CubeDeformation(0.0F))
				.texOffs(32, 87).addBox(-3.85F, -14.0F, 9.75F, 7.7F, 1.5F, 0.5F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-3.0F, -15.2F, 11.0F, 6.0F, 0.2F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(88, 86).addBox(-3.0F, -15.7F, 17.7F, 6.0F, 0.7F, 0.3F, new CubeDeformation(0.0F))
				.texOffs(86, 62).addBox(-3.85F, -15.0F, -2.25F, 7.7F, 1.0F, 0.75F, new CubeDeformation(0.0F))
				.texOffs(86, 12).addBox(-3.85F, -16.0F, -3.5F, 7.7F, 1.0F, 1.25F, new CubeDeformation(0.0F))
				.texOffs(34, 31).addBox(-3.85F, -12.0F, 0.5F, 7.7F, 2.0F, 7.25F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(MotorcycleRenderState renderState) {
		super.setupAnim(renderState);
		if (frontWheel != null) {
			frontWheel.xRot = renderState.wheelRotation;
		}
		if (rearWheel != null) {
			rearWheel.xRot = renderState.wheelRotation;
		}
	}

}