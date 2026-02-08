// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.example.commontransports.vehicle.client.model;

import com.example.commontransports.GenericMod;
import com.example.commontransports.vehicle.client.render.MotorcycleRenderState;

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



public class HarleyMinestoneModel extends EntityModel<MotorcycleRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(GenericMod.MODID, "harley_minestone"),
			"main");
	private final ModelPart root;
	private final @Nullable ModelPart frontWheel;
	private final @Nullable ModelPart rearWheel;

	public HarleyMinestoneModel(ModelPart root) {
		super(root);
		this.root = root;
		ModelPart bone = root.hasChild("bone") ? root.getChild("bone") : null;
		this.frontWheel = bone != null && bone.hasChild("Tire1") ? bone.getChild("Tire1")
				: (root.hasChild("Tire1") ? root.getChild("Tire1") : null);
		this.rearWheel = bone != null && bone.hasChild("Tire2") ? bone.getChild("Tire2")
				: (root.hasChild("Tire2") ? root.getChild("Tire2") : null);
	}

	public @Nullable ModelPart getPart(String name) {
		return root.hasChild(name) ? root.getChild(name) : null;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition backlight = partdefinition.addOrReplaceChild("backlight", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.5395F, 14.5932F, 0.0F, 1.5708F, 0.0F));

		backlight.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 77).addBox(-14.9436F, -2.3806F, 12.271F, 0.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.2117F, 8.7256F, 0.1321F, 1.5708F, 0.0F, -0.4363F));

		PartDefinition disttire = partdefinition.addOrReplaceChild("disttire", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.268F, 16.9341F, 9.43F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Tiredisk2 = disttire.addOrReplaceChild("Tiredisk2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		Tiredisk2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(127, -1).addBox(3.1807F, -3.5363F, 16.2371F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.0982F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(127, -1).addBox(1.5407F, -3.5363F, 16.4571F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.1963F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(127, -1).addBox(-0.1129F, -3.5363F, 16.5152F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.2945F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(127, -1).addBox(-1.7642F, -3.5363F, 16.411F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.3927F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(127, -1).addBox(-3.3974F, -3.5363F, 16.1454F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.4909F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(127, -1).addBox(-4.9967F, -3.5363F, 15.721F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.589F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(127, 0).addBox(-6.5467F, -3.5363F, 15.1419F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.6872F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(125, 0).addBox(-8.0324F, -3.5363F, 14.4136F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.7854F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(127, 0).addBox(-9.4397F, -3.5363F, 13.5433F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.8836F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(127, -1).addBox(-10.7548F, -3.5363F, 12.5392F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.9817F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(127, 0).addBox(-11.9652F, -3.5363F, 11.411F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.0799F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(127, -1).addBox(-13.0592F, -3.5363F, 10.1696F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.1781F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(127, -1).addBox(-14.0262F, -3.5363F, 8.827F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.2763F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(126, -1).addBox(-14.857F, -3.5363F, 7.396F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.3744F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(127, 0).addBox(-15.5435F, -3.5363F, 5.8905F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.4726F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(127, 0).addBox(-16.0792F, -3.5363F, 4.325F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.5708F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(127, 0).addBox(-16.4588F, -3.5363F, 2.7145F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.669F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(127, 0).addBox(-16.6787F, -3.5363F, 1.0745F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.7671F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(125, -1).addBox(-16.7369F, -3.5363F, -0.5791F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.8653F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(127, -1).addBox(-16.6326F, -3.5363F, -2.2304F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 1.9635F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(127, 0).addBox(-16.367F, -3.5363F, -3.8636F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.0617F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(127, 0).addBox(-15.9426F, -3.5363F, -5.4629F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.1598F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(127, -1).addBox(-14.6353F, -3.5363F, -8.4986F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.3562F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(127, 0).addBox(-15.3635F, -3.5363F, -7.0129F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.258F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(127, -1).addBox(-13.7649F, -3.5363F, -9.9059F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.4544F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(127, 0).addBox(-12.7608F, -3.5363F, -11.221F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.5525F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(127, -1).addBox(-11.6327F, -3.5363F, -12.4314F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.6507F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(125, -1).addBox(-10.3913F, -3.5363F, -13.5254F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.7489F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(127, -1).addBox(-9.0487F, -3.5363F, -14.4924F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.8471F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(127, 0).addBox(-7.6177F, -3.5363F, -15.3232F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 2.9452F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(127, -1).addBox(-6.1122F, -3.5363F, -16.0097F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 3.0434F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(127, 0).addBox(-4.5467F, -3.5363F, -16.5454F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 3.1416F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(125, 0).addBox(2.0088F, -3.5363F, -17.0988F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.7489F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(126, 0).addBox(14.2707F, -3.5363F, -9.5148F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.8653F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(127, -1).addBox(-2.9362F, -3.5363F, -16.925F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -3.0434F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(127, 0).addBox(-1.2962F, -3.5363F, -17.1449F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.9452F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(127, -1).addBox(0.3574F, -3.5363F, -17.2031F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.8471F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(125, 0).addBox(3.6419F, -3.5363F, -16.8332F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.6507F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(125, -1).addBox(5.2412F, -3.5363F, -16.4088F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.5525F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(127, 0).addBox(6.7912F, -3.5363F, -15.8297F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.4544F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(127, -1).addBox(8.277F, -3.5363F, -15.1015F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.3562F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(127, -1).addBox(9.6842F, -3.5363F, -14.2311F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.258F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(127, 0).addBox(10.9993F, -3.5363F, -13.227F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.1598F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(126, 0).addBox(12.2097F, -3.5363F, -12.0989F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -2.0617F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(127, -1).addBox(13.3037F, -3.5363F, -10.8575F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.9635F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(127, -1).addBox(15.1015F, -3.5363F, -8.0839F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.7671F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(127, -1).addBox(15.788F, -3.5363F, -6.5784F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.669F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(127, -1).addBox(16.3237F, -3.5363F, -5.0129F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.5708F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(127, -1).addBox(14.8798F, -3.5363F, 7.8108F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.7854F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(127, -1).addBox(15.6081F, -3.5363F, 6.325F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.8836F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(127, -1).addBox(16.1872F, -3.5363F, 4.775F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.9817F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(127, 0).addBox(16.6116F, -3.5363F, 3.1757F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.0799F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(125, 0).addBox(16.8772F, -3.5363F, 1.5426F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.1781F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(127, 0).addBox(16.9814F, -3.5363F, -0.1088F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.2763F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(127, -1).addBox(16.9233F, -3.5363F, -1.7624F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.3744F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(126, 0).addBox(16.7033F, -3.5363F, -3.4024F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -1.4726F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(127, 0).addBox(10.6358F, -3.5363F, 12.8375F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.3927F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(127, 0).addBox(11.8772F, -3.5363F, 11.7435F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.4909F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(127, 0).addBox(13.0054F, -3.5363F, 10.5331F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.589F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(127, 0).addBox(14.0095F, -3.5363F, 9.218F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.6872F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(125, -1).addBox(7.8622F, -3.5363F, 14.6353F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.1963F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(127, -1).addBox(9.2932F, -3.5363F, 13.8046F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.2945F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(127, -1).addBox(6.3567F, -3.5363F, 15.3218F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, -0.0982F, 1.5708F));

		Tiredisk2.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(127, 0).addBox(4.7912F, -3.5363F, 15.8575F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4001F, -4.6689F, -9.0485F, 0.0F, 0.0F, 1.5708F));

		PartDefinition tiredisk = disttire.addOrReplaceChild("tiredisk", CubeListBuilder.create(), PartPose.offsetAndRotation(33.3615F, 0.0F, 1.3413F, 0.0F, -1.5708F, 0.0F));

		tiredisk.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(127, 0).addBox(6.4507F, -1.8776F, -16.9637F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.0982F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(127, 0).addBox(8.0492F, -1.8776F, -16.2634F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.1963F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(127, 0).addBox(9.5714F, -1.8776F, -15.4098F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.2945F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(125, 0).addBox(11.0027F, -1.8776F, -14.411F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.3927F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(127, 0).addBox(12.3291F, -1.8776F, -13.2768F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.4909F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(127, -1).addBox(13.5379F, -1.8776F, -12.0181F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.589F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(127, -1).addBox(14.6176F, -1.8776F, -10.6469F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.6872F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(125, -1).addBox(15.5577F, -1.8776F, -9.1765F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.7854F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(127, -1).addBox(16.3491F, -1.8776F, -7.621F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.8836F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(127, 0).addBox(16.9842F, -1.8776F, -5.9955F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.9817F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(125, -1).addBox(17.457F, -1.8776F, -4.3155F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.0799F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(127, 0).addBox(17.7628F, -1.8776F, -2.5973F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.1781F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(127, 0).addBox(17.8987F, -1.8776F, -0.8573F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.2763F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(127, 0).addBox(17.8634F, -1.8776F, 0.8875F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.3744F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(127, -1).addBox(17.6573F, -1.8776F, 2.6205F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.4726F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(127, -1).addBox(17.2823F, -1.8776F, 4.325F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.5708F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(127, -1).addBox(16.7421F, -1.8776F, 5.9845F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.669F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(125, -1).addBox(16.0417F, -1.8776F, 7.583F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.7671F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(125, -1).addBox(15.1881F, -1.8776F, 9.1052F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.8653F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(125, -1).addBox(14.1894F, -1.8776F, 10.5365F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 1.9635F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(127, -1).addBox(13.0552F, -1.8776F, 11.8629F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.0617F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(127, 0).addBox(11.7964F, -1.8776F, 13.0718F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.1598F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(127, 0).addBox(8.9548F, -1.8776F, 15.0915F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.3562F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(127, -1).addBox(10.4252F, -1.8776F, 14.1514F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.258F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(127, 0).addBox(7.3994F, -1.8776F, 15.8829F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.4544F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(127, -1).addBox(5.7738F, -1.8776F, 16.518F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.5525F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(127, 0).addBox(4.0938F, -1.8776F, 16.9908F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.6507F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(125, -1).addBox(2.3756F, -1.8776F, 17.2966F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.7489F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(127, 0).addBox(0.6357F, -1.8776F, 17.4325F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.8471F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(127, -1).addBox(-1.1092F, -1.8776F, 17.3973F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 2.9452F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(127, -1).addBox(-2.8422F, -1.8776F, 17.1911F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 3.0434F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(127, 0).addBox(-4.5467F, -1.8776F, 16.8161F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 3.1416F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(125, 0).addBox(-10.7581F, -1.8776F, 13.7232F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.7489F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(127, -1).addBox(-17.6542F, -1.8776F, 0.1695F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.8653F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(127, -1).addBox(-6.2062F, -1.8776F, 16.2759F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -3.0434F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(127, 0).addBox(-7.8047F, -1.8776F, 15.5755F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.9452F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(127, 0).addBox(-9.3269F, -1.8776F, 14.7219F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.8471F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(125, -1).addBox(-12.0846F, -1.8776F, 12.589F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.6507F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(125, 0).addBox(-13.2934F, -1.8776F, 11.3302F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.5525F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(127, -1).addBox(-14.3731F, -1.8776F, 9.959F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.4544F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(127, 0).addBox(-15.3132F, -1.8776F, 8.4886F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.3562F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(123, 0).addBox(-16.1046F, -1.8776F, 6.9332F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.258F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(127, -1).addBox(-16.7397F, -1.8776F, 5.3076F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.1598F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(127, 0).addBox(-17.2125F, -1.8776F, 3.6276F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -2.0617F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(127, 0).addBox(-17.5183F, -1.8776F, 1.9094F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.9635F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(127, -1).addBox(-17.6189F, -1.8776F, -1.5754F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.7671F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(127, -1).addBox(-17.4128F, -1.8776F, -3.3084F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.669F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(125, -1).addBox(-17.0378F, -1.8776F, -5.0129F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.5708F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(125, -1).addBox(-8.7103F, -1.8776F, -15.7794F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.7854F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(127, 0).addBox(-10.1807F, -1.8776F, -14.8393F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.8836F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(127, -1).addBox(-11.5519F, -1.8776F, -13.7596F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.9817F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(127, -1).addBox(-12.8106F, -1.8776F, -12.5508F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.0799F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(125, 0).addBox(-13.9448F, -1.8776F, -11.2243F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.1781F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(125, 0).addBox(-14.9436F, -1.8776F, -9.7931F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.2763F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(127, -1).addBox(-15.7972F, -1.8776F, -8.2709F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.3744F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(127, -1).addBox(-16.4975F, -1.8776F, -6.6724F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -1.4726F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(127, -1).addBox(-2.1311F, -1.8776F, -17.9845F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.3927F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(127, 0).addBox(-3.8493F, -1.8776F, -17.6787F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.4909F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(127, 0).addBox(-5.5293F, -1.8776F, -17.2059F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.589F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(125, 0).addBox(-7.1548F, -1.8776F, -16.5708F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.6872F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(125, -1).addBox(1.3537F, -1.8776F, -18.0851F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.1963F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(125, -1).addBox(-0.3911F, -1.8776F, -18.1204F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.2945F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(127, 0).addBox(3.0867F, -1.8776F, -17.879F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, -0.0982F, 1.5708F));

		tiredisk.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(127, -1).addBox(4.7912F, -1.8776F, -17.504F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0588F, -4.6689F, 24.313F, 0.0F, 0.0F, 1.5708F));

		PartDefinition bar6 = partdefinition.addOrReplaceChild("bar6", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0688F, 2.9514F, -14.6048F, 0.0F, 1.5708F, 0.0F));

		PartDefinition bar5 = bar6.addOrReplaceChild("bar5", CubeListBuilder.create().texOffs(95, 116).addBox(5.9943F, 3.916F, -8.9423F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(95, 116).addBox(5.9943F, 3.916F, -8.9423F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(95, 116).addBox(5.9943F, 4.6088F, -8.9423F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.5218F));

		bar5.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(95, 116).addBox(3.2412F, 11.5089F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(95, 116).addBox(3.2412F, 12.2017F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.6089F, 1.1244F, -6.9367F, 0.0F, 0.0F, 1.5708F));

		bar5.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(95, 116).addBox(-10.3327F, -6.4416F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(95, 116).addBox(-10.3327F, -5.7489F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.6089F, 1.1244F, -6.9367F, 0.0F, 0.0F, -0.7854F));

		bar5.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(95, 116).addBox(-5.9921F, 10.2271F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(95, 116).addBox(-5.9921F, 10.9198F, -2.0055F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.6089F, 1.1244F, -6.9367F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bar4 = bar6.addOrReplaceChild("bar4", CubeListBuilder.create().texOffs(78, 74).addBox(-5.9263F, -0.2391F, 1.0884F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-5.9263F, -0.2391F, 1.0884F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-5.9263F, 0.4536F, 1.0884F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1296F, -0.5176F, -3.5667F, 0.0F, -0.4363F, 0.0F));

		bar4.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(78, 74).addBox(-9.6209F, -8.162F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-9.6209F, -7.4693F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9827F, 9.8314F, 2.8019F, 0.0F, 0.0F, 1.5708F));

		bar4.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(78, 74).addBox(12.6717F, -1.6271F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(12.6717F, -0.9343F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9827F, 9.8314F, 2.8019F, 0.0F, 0.0F, -0.7854F));

		bar4.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(78, 74).addBox(-1.1775F, -12.7774F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-1.1775F, -12.0846F, -1.7134F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9827F, 9.8314F, 2.8019F, 0.0F, 0.0F, 0.7854F));

		PartDefinition Bar3 = bar6.addOrReplaceChild("Bar3", CubeListBuilder.create().texOffs(78, 74).addBox(-5.8098F, -0.2391F, -3.52F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-5.8098F, -0.2391F, -3.52F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-5.8098F, 0.4536F, -3.52F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9112F, -0.552F, -11.9005F, 0.0F, 0.4363F, 0.0F));

		Bar3.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(78, 74).addBox(-9.6553F, -8.2437F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-9.6553F, -7.5509F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9478F, 9.8658F, -1.0271F, 0.0F, 0.0F, 1.5708F));

		Bar3.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(78, 74).addBox(12.7538F, -1.5937F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(12.7538F, -0.9009F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9478F, 9.8658F, -1.0271F, 0.0F, 0.0F, -0.7854F));

		Bar3.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(78, 74).addBox(-1.1441F, -12.8594F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 74).addBox(-1.1441F, -12.1666F, -2.4929F, 0.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9478F, 9.8658F, -1.0271F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bar2 = bar6.addOrReplaceChild("bar2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.7074F, -0.4969F, -11.5399F, 1.5708F, -1.1345F, 0.0F));

		bar2.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(127, 0).addBox(-8.7993F, -1.2322F, 8.6355F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.0982F, 1.5708F));

		bar2.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(127, 0).addBox(-9.6365F, -1.2322F, 7.7178F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.1963F, 1.5708F));

		bar2.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(127, 0).addBox(-10.3797F, -1.2322F, 6.7224F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.2945F, 1.5708F));

		bar2.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(127, 0).addBox(-11.0217F, -1.2322F, 5.659F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.3927F, 1.5708F));

		bar2.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(127, -1).addBox(-11.5565F, -1.2322F, 4.5378F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.4909F, 1.5708F));

		bar2.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(127, 0).addBox(-11.9787F, -1.2322F, 3.3696F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.589F, 1.5708F));

		bar2.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(127, -1).addBox(-12.2844F, -1.2322F, 2.1656F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.6872F, 1.5708F));

		bar2.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(125, -1).addBox(-12.4707F, -1.2322F, 0.9375F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.7854F, 1.5708F));

		bar2.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(127, -1).addBox(-12.5356F, -1.2322F, -0.303F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.8836F, 1.5708F));

		bar2.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(127, 0).addBox(-12.4787F, -1.2322F, -1.5439F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.9817F, 1.5708F));

		bar2.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(127, -1).addBox(-12.3004F, -1.2322F, -2.7732F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.0799F, 1.5708F));

		bar2.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(127, 0).addBox(-12.0025F, -1.2322F, -3.9792F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.1781F, 1.5708F));

		bar2.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(127, 0).addBox(-11.5878F, -1.2322F, -5.1501F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.2763F, 1.5708F));

		bar2.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(127, 0).addBox(-11.0603F, -1.2322F, -6.2747F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.3744F, 1.5708F));

		bar2.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(127, -1).addBox(-10.4251F, -1.2322F, -7.3423F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.4726F, 1.5708F));

		bar2.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(127, -1).addBox(-9.6884F, -1.2322F, -8.3424F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.5708F, 1.5708F));

		bar2.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(127, -1).addBox(-8.8571F, -1.2322F, -9.2655F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.669F, 1.5708F));

		bar2.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(127, -1).addBox(-7.9394F, -1.2322F, -10.1027F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.7671F, 1.5708F));

		bar2.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(125, -1).addBox(-6.9441F, -1.2322F, -10.8459F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.8653F, 1.5708F));

		bar2.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(127, -1).addBox(-5.8807F, -1.2322F, -11.4879F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 1.9635F, 1.5708F));

		bar2.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(127, 0).addBox(-4.7595F, -1.2322F, -12.0226F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.0617F, 1.5708F));

		bar2.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(127, 0).addBox(-3.5913F, -1.2322F, -12.4449F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.1598F, 1.5708F));

		bar2.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(127, -1).addBox(-1.1591F, -1.2322F, -12.9369F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.3562F, 1.5708F));

		bar2.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(127, 0).addBox(-2.3873F, -1.2322F, -12.7506F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.258F, 1.5708F));

		bar2.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(127, 0).addBox(0.0813F, -1.2322F, -13.0018F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.4544F, 1.5708F));

		bar2.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(127, -1).addBox(1.3222F, -1.2322F, -12.9449F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.5525F, 1.5708F));

		bar2.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(127, 0).addBox(2.5516F, -1.2322F, -12.7666F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.6507F, 1.5708F));

		bar2.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(125, -1).addBox(3.7575F, -1.2322F, -12.4687F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.7489F, 1.5708F));

		bar2.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(127, 0).addBox(4.9284F, -1.2322F, -12.054F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.8471F, 1.5708F));

		bar2.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(127, -1).addBox(6.0531F, -1.2322F, -11.5265F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 2.9452F, 1.5708F));

		bar2.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(127, -1).addBox(7.1206F, -1.2322F, -10.8913F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 3.0434F, 1.5708F));

		bar2.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(127, 0).addBox(8.1207F, -1.2322F, -10.1546F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 3.1416F, 1.5708F));

		bar2.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(125, 0).addBox(11.2662F, -1.2322F, -6.3469F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.7489F, 1.5708F));

		bar2.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(127, -1).addBox(11.8323F, -1.2322F, 4.4622F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.8653F, 1.5708F));

		bar2.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(127, -1).addBox(9.0438F, -1.2322F, -9.3233F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -3.0434F, 1.5708F));

		bar2.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(127, 0).addBox(9.881F, -1.2322F, -8.4056F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.9452F, 1.5708F));

		bar2.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(127, 0).addBox(10.6242F, -1.2322F, -7.4103F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.8471F, 1.5708F));

		bar2.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(125, -1).addBox(11.801F, -1.2322F, -5.2257F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.6507F, 1.5708F));

		bar2.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(125, 0).addBox(12.2232F, -1.2322F, -4.0575F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.5525F, 1.5708F));

		bar2.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(127, -1).addBox(12.529F, -1.2322F, -2.8535F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.4544F, 1.5708F));

		bar2.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(127, 0).addBox(12.7152F, -1.2322F, -1.6253F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.3562F, 1.5708F));

		bar2.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(127, 0).addBox(12.7802F, -1.2322F, -0.3849F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.258F, 1.5708F));

		bar2.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(127, -1).addBox(12.7232F, -1.2322F, 0.856F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.1598F, 1.5708F));

		bar2.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(127, 0).addBox(12.5449F, -1.2322F, 2.0854F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -2.0617F, 1.5708F));

		bar2.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(127, -1).addBox(12.247F, -1.2322F, 3.2913F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.9635F, 1.5708F));

		bar2.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(127, -1).addBox(11.3048F, -1.2322F, 5.5869F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.7671F, 1.5708F));

		bar2.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(127, -1).addBox(10.6697F, -1.2322F, 6.6544F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.669F, 1.5708F));

		bar2.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(127, -1).addBox(9.9329F, -1.2322F, 7.6545F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.5708F, 1.5708F));

		bar2.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(127, -1).addBox(1.4037F, -1.2322F, 12.249F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.7854F, 1.5708F));

		bar2.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(127, -1).addBox(2.6318F, -1.2322F, 12.0628F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.8836F, 1.5708F));

		bar2.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(127, 0).addBox(3.8358F, -1.2322F, 11.757F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.9817F, 1.5708F));

		bar2.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(127, -1).addBox(5.004F, -1.2322F, 11.3348F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.0799F, 1.5708F));

		bar2.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(125, 0).addBox(6.1252F, -1.2322F, 10.8F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.1781F, 1.5708F));

		bar2.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(127, 0).addBox(7.1886F, -1.2322F, 10.158F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.2763F, 1.5708F));

		bar2.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(127, -1).addBox(8.184F, -1.2322F, 9.4148F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.3744F, 1.5708F));

		bar2.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(127, -1).addBox(9.1017F, -1.2322F, 8.5776F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -1.4726F, 1.5708F));

		bar2.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(127, 0).addBox(-3.513F, -1.2322F, 11.7808F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.3927F, 1.5708F));

		bar2.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(127, 0).addBox(-2.307F, -1.2322F, 12.0787F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.4909F, 1.5708F));

		bar2.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(127, 0).addBox(-1.0777F, -1.2322F, 12.257F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.589F, 1.5708F));

		bar2.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(127, 0).addBox(0.1632F, -1.2322F, 12.314F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.6872F, 1.5708F));

		bar2.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(125, -1).addBox(-5.8085F, -1.2322F, 10.8386F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.1963F, 1.5708F));

		bar2.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(127, -1).addBox(-4.6839F, -1.2322F, 11.3661F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.2945F, 1.5708F));

		bar2.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(127, 0).addBox(-6.8761F, -1.2322F, 10.2035F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, -0.0982F, 1.5708F));

		bar2.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(127, -1).addBox(-7.8762F, -1.2322F, 9.4667F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.44F, 13.9802F, -9.8106F, 0.0F, 0.0F, 1.5708F));

		PartDefinition bar = bar6.addOrReplaceChild("bar", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.62F, -0.4969F, -2.5557F, 1.5708F, -2.0071F, 0.0F));

		bar.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(127, 0).addBox(-8.6688F, -2.7223F, 8.6483F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.0982F, 1.5708F));

		bar.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(127, 0).addBox(-9.5078F, -2.7223F, 7.7434F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.1963F, 1.5708F));

		bar.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(127, 0).addBox(-10.2542F, -2.7223F, 6.7605F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.2945F, 1.5708F));

		bar.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(127, 0).addBox(-10.9006F, -2.7223F, 5.7092F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.3927F, 1.5708F));

		bar.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(127, -1).addBox(-11.4408F, -2.7223F, 4.5997F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.4909F, 1.5708F));

		bar.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(127, 0).addBox(-11.8697F, -2.7223F, 3.4425F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.589F, 1.5708F));

		bar.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(127, -1).addBox(-12.1831F, -2.7223F, 2.2488F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.6872F, 1.5708F));

		bar.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(125, -1).addBox(-12.3779F, -2.7223F, 1.0302F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.7854F, 1.5708F));

		bar.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(127, -1).addBox(-12.4524F, -2.7223F, -0.2016F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.8836F, 1.5708F));

		bar.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(127, 0).addBox(-12.4058F, -2.7223F, -1.4349F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.9817F, 1.5708F));

		bar.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(127, -1).addBox(-12.2386F, -2.7223F, -2.6576F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.0799F, 1.5708F));

		bar.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(127, 0).addBox(-11.9523F, -2.7223F, -3.858F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.1781F, 1.5708F));

		bar.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(127, 0).addBox(-11.5497F, -2.7223F, -5.0246F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.2763F, 1.5708F));

		bar.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(127, 0).addBox(-11.0347F, -2.7223F, -6.1461F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.3744F, 1.5708F));

		bar.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(127, -1).addBox(-10.4123F, -2.7223F, -7.2117F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.4726F, 1.5708F));

		bar.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(127, -1).addBox(-9.6884F, -2.7223F, -8.2112F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.5708F, 1.5708F));

		bar.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(127, -1).addBox(-8.87F, -2.7223F, -9.135F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.669F, 1.5708F));

		bar.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(127, -1).addBox(-7.965F, -2.7223F, -9.974F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.7671F, 1.5708F));

		bar.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(125, -1).addBox(-6.9822F, -2.7223F, -10.7204F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.8653F, 1.5708F));

		bar.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(127, -1).addBox(-5.9309F, -2.7223F, -11.3667F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 1.9635F, 1.5708F));

		bar.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(127, 0).addBox(-4.8213F, -2.7223F, -11.907F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.0617F, 1.5708F));

		bar.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(127, 0).addBox(-3.6641F, -2.7223F, -12.3359F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.1598F, 1.5708F));

		bar.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(127, -1).addBox(-1.2519F, -2.7223F, -12.8441F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.3562F, 1.5708F));

		bar.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(127, 0).addBox(-2.4705F, -2.7223F, -12.6492F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.258F, 1.5708F));

		bar.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(127, 0).addBox(-0.02F, -2.7223F, -12.9186F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.4544F, 1.5708F));

		bar.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(127, -1).addBox(1.2132F, -2.7223F, -12.872F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.5525F, 1.5708F));

		bar.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(127, 0).addBox(2.4359F, -2.7223F, -12.7048F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.6507F, 1.5708F));

		bar.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(125, -1).addBox(3.6363F, -2.7223F, -12.4185F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.7489F, 1.5708F));

		bar.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(127, 0).addBox(4.8029F, -2.7223F, -12.0159F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.8471F, 1.5708F));

		bar.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(127, -1).addBox(5.9244F, -2.7223F, -11.5009F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 2.9452F, 1.5708F));

		bar.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(127, -1).addBox(6.9901F, -2.7223F, -10.8785F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 3.0434F, 1.5708F));

		bar.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(127, 0).addBox(7.9896F, -2.7223F, -10.1546F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 3.1416F, 1.5708F));

		bar.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(125, 0).addBox(11.1451F, -2.7223F, -6.3971F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.7489F, 1.5708F));

		bar.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(127, -1).addBox(11.7942F, -2.7223F, 4.3367F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.8653F, 1.5708F));

		bar.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(127, -1).addBox(8.9133F, -2.7223F, -9.3362F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -3.0434F, 1.5708F));

		bar.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(127, 0).addBox(9.7524F, -2.7223F, -8.4312F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.9452F, 1.5708F));

		bar.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(127, 0).addBox(10.4987F, -2.7223F, -7.4484F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.8471F, 1.5708F));

		bar.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(125, -1).addBox(11.6853F, -2.7223F, -5.2875F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.6507F, 1.5708F));

		bar.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(125, 0).addBox(12.1142F, -2.7223F, -4.1303F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.5525F, 1.5708F));

		bar.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(127, -1).addBox(12.4276F, -2.7223F, -2.9367F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.4544F, 1.5708F));

		bar.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(127, 0).addBox(12.6225F, -2.7223F, -1.7181F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.3562F, 1.5708F));

		bar.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(127, 0).addBox(12.697F, -2.7223F, -0.4862F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.258F, 1.5708F));

		bar.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(127, -1).addBox(12.6504F, -2.7223F, 0.747F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.1598F, 1.5708F));

		bar.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(127, 0).addBox(12.4831F, -2.7223F, 1.9697F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -2.0617F, 1.5708F));

		bar.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(127, -1).addBox(12.1968F, -2.7223F, 3.1701F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.9635F, 1.5708F));

		bar.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(127, -1).addBox(11.2792F, -2.7223F, 5.4583F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.7671F, 1.5708F));

		bar.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(127, -1).addBox(10.6568F, -2.7223F, 6.5239F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.669F, 1.5708F));

		bar.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(127, -1).addBox(9.9329F, -2.7223F, 7.5234F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.5708F, 1.5708F));

		bar.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(127, -1).addBox(1.4964F, -2.7223F, 12.1563F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.7854F, 1.5708F));

		bar.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(127, -1).addBox(2.715F, -2.7223F, 11.9614F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.8836F, 1.5708F));

		bar.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(127, 0).addBox(3.9087F, -2.7223F, 11.648F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.9817F, 1.5708F));

		bar.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(127, -1).addBox(5.0659F, -2.7223F, 11.2191F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.0799F, 1.5708F));

		bar.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(125, 0).addBox(6.1754F, -2.7223F, 10.6789F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.1781F, 1.5708F));

		bar.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(127, 0).addBox(7.2267F, -2.7223F, 10.0325F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.2763F, 1.5708F));

		bar.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(127, -1).addBox(8.2096F, -2.7223F, 9.2862F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.3744F, 1.5708F));

		bar.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(127, -1).addBox(9.1145F, -2.7223F, 8.4471F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -1.4726F, 1.5708F));

		bar.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(127, 0).addBox(-3.3918F, -2.7223F, 11.7306F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.3927F, 1.5708F));

		bar.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(127, 0).addBox(-2.1914F, -2.7223F, 12.0169F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.4909F, 1.5708F));

		bar.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(127, 0).addBox(-0.9687F, -2.7223F, 12.1842F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.589F, 1.5708F));

		bar.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(127, 0).addBox(0.2646F, -2.7223F, 12.2308F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.6872F, 1.5708F));

		bar.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(125, -1).addBox(-5.6799F, -2.7223F, 10.813F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.1963F, 1.5708F));

		bar.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(127, -1).addBox(-4.5584F, -2.7223F, 11.328F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.2945F, 1.5708F));

		bar.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(127, 0).addBox(-6.7456F, -2.7223F, 10.1906F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, -0.0982F, 1.5708F));

		bar.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(127, -1).addBox(-7.7451F, -2.7223F, 9.4667F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.6783F, 13.9655F, -9.8106F, 0.0F, 0.0F, 1.5708F));

		PartDefinition SUSP5 = partdefinition.addOrReplaceChild("SUSP5", CubeListBuilder.create(), PartPose.offsetAndRotation(2.2537F, 9.6498F, -19.3687F, 0.0F, 1.5708F, 0.0F));

		PartDefinition susp2 = SUSP5.addOrReplaceChild("susp2", CubeListBuilder.create().texOffs(61, 105).addBox(6.17F, -0.2391F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(6.17F, -0.2391F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(6.17F, 0.4536F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 2.5654F));

		susp2.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(61, 105).addBox(2.3321F, 11.7106F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(2.3321F, 12.4033F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, -2.1217F, -8.5677F, 0.0F, 0.0F, 1.5708F));

		susp2.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(61, 105).addBox(-9.8324F, -7.227F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(-9.8324F, -6.5343F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, -2.1217F, -8.5677F, 0.0F, 0.0F, -0.7854F));

		susp2.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(61, 105).addBox(-6.7775F, 9.7268F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(-6.7775F, 10.4195F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, -2.1217F, -8.5677F, 0.0F, 0.0F, 0.7854F));

		PartDefinition susp = SUSP5.addOrReplaceChild("susp", CubeListBuilder.create().texOffs(61, 105).addBox(6.17F, -0.2391F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(6.17F, -0.2391F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(6.17F, 0.4536F, -12.8395F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.4711F, 1.5708F, 0.0F, 2.5654F));

		susp.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(61, 105).addBox(-2.139F, 11.7106F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(-2.139F, 12.4033F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, 2.3495F, -8.5677F, 0.0F, 0.0F, 1.5708F));

		susp.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(61, 105).addBox(-6.6708F, -10.3886F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(-6.6708F, -9.6958F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, 2.3495F, -8.5677F, 0.0F, 0.0F, -0.7854F));

		susp.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(61, 105).addBox(-9.9391F, 6.5652F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(61, 105).addBox(-9.939F, 7.258F, -4.2718F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.9862F, 2.3495F, -8.5677F, 0.0F, 0.0F, 0.7854F));

		PartDefinition Susp3 = SUSP5.addOrReplaceChild("Susp3", CubeListBuilder.create().texOffs(25, 106).addBox(0.1078F, -0.2391F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.1078F, -0.2391F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.1078F, 0.4537F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.2131F, 7.2867F, 1.3757F, 1.5708F, 0.0F, 1.5618F));

		Susp3.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(25, 106).addBox(3.7078F, -4.8805F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(3.7078F, -4.1878F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, -3.4974F, 0.5048F, 0.0F, 0.0F, 1.5708F));

		Susp3.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(25, 106).addBox(0.9265F, 5.4774F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.9265F, 6.1702F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, -3.4974F, 0.5048F, 0.0F, 0.0F, -0.7854F));

		Susp3.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(25, 106).addBox(5.927F, -1.0321F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(5.927F, -0.3394F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, -3.4974F, 0.5048F, 0.0F, 0.0F, 0.7854F));

		PartDefinition susp4 = SUSP5.addOrReplaceChild("susp4", CubeListBuilder.create().texOffs(25, 106).addBox(0.1078F, -0.2391F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.1078F, -0.2391F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.1078F, 0.4537F, -16.0949F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.2131F, 7.2867F, -5.8469F, 1.5708F, 0.0F, 1.5618F));

		susp4.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(25, 106).addBox(-3.5147F, -4.8805F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(-3.5147F, -4.1878F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, 3.7252F, 0.5048F, 0.0F, 0.0F, 1.5708F));

		susp4.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(25, 106).addBox(6.0336F, 0.3703F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(6.0336F, 1.063F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, 3.7252F, 0.5048F, 0.0F, 0.0F, -0.7854F));

		susp4.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(25, 106).addBox(0.8198F, -6.1393F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(25, 106).addBox(0.8198F, -5.4465F, -16.5997F, 0.0F, 0.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6671F, 3.7252F, 0.5048F, 0.0F, 0.0F, 0.7854F));

		PartDefinition Light = partdefinition.addOrReplaceChild("Light", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 7.9574F, -18.0565F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Tire5 = Light.addOrReplaceChild("Tire5", CubeListBuilder.create().texOffs(50, 30).addBox(0.498F, 6.153F, -0.3439F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F));

		Tire5.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(50, 56).addBox(-0.0557F, -12.285F, 3.9301F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.0982F, 0.0F));

		Tire5.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(68, 31).addBox(-0.4719F, -12.285F, 3.8552F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.1963F, 0.0F));

		Tire5.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(62, 31).addBox(-0.8789F, -12.285F, 3.74F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.2945F, 0.0F));

		Tire5.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(62, 31).addBox(-1.2725F, -12.285F, 3.5853F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.3927F, 0.0F));

		Tire5.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(60, 42).addBox(-1.6491F, -12.285F, 3.3929F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.4909F, 0.0F));

		Tire5.addOrReplaceChild("cube_r284", CubeListBuilder.create().texOffs(60, 39).addBox(-2.0051F, -12.285F, 3.1644F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.589F, 0.0F));

		Tire5.addOrReplaceChild("cube_r285", CubeListBuilder.create().texOffs(60, 36).addBox(-2.3369F, -12.285F, 2.9022F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.6872F, 0.0F));

		Tire5.addOrReplaceChild("cube_r286", CubeListBuilder.create().texOffs(56, 30).addBox(-2.6414F, -12.285F, 2.6087F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.7854F, 0.0F));

		Tire5.addOrReplaceChild("cube_r287", CubeListBuilder.create().texOffs(54, 48).addBox(-2.9157F, -12.285F, 2.2868F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.8836F, 0.0F));

		Tire5.addOrReplaceChild("cube_r288", CubeListBuilder.create().texOffs(54, 45).addBox(-3.1571F, -12.285F, 1.9395F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.9817F, 0.0F));

		Tire5.addOrReplaceChild("cube_r289", CubeListBuilder.create().texOffs(50, 30).addBox(-3.3633F, -12.285F, 1.5702F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.0799F, 0.0F));

		Tire5.addOrReplaceChild("cube_r290", CubeListBuilder.create().texOffs(56, 54).addBox(-3.5324F, -12.285F, 1.1825F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.1781F, 0.0F));

		Tire5.addOrReplaceChild("cube_r291", CubeListBuilder.create().texOffs(56, 29).addBox(-3.6626F, -12.285F, 0.7801F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.2763F, 0.0F));

		Tire5.addOrReplaceChild("cube_r292", CubeListBuilder.create().texOffs(50, 29).addBox(-3.7527F, -12.285F, 0.3669F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.3744F, 0.0F));

		Tire5.addOrReplaceChild("cube_r293", CubeListBuilder.create().texOffs(50, 53).addBox(-3.8019F, -12.285F, -0.0531F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.4726F, 0.0F));

		Tire5.addOrReplaceChild("cube_r294", CubeListBuilder.create().texOffs(68, 28).addBox(-3.8097F, -12.285F, -0.476F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.5708F, 0.0F));

		Tire5.addOrReplaceChild("cube_r295", CubeListBuilder.create().texOffs(62, 28).addBox(-3.776F, -12.285F, -0.8976F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.669F, 0.0F));

		Tire5.addOrReplaceChild("cube_r296", CubeListBuilder.create().texOffs(62, 28).addBox(-3.7012F, -12.285F, -1.3139F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.7671F, 0.0F));

		Tire5.addOrReplaceChild("cube_r297", CubeListBuilder.create().texOffs(56, 27).addBox(-3.5859F, -12.285F, -1.7208F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.8653F, 0.0F));

		Tire5.addOrReplaceChild("cube_r298", CubeListBuilder.create().texOffs(50, 27).addBox(-3.4313F, -12.285F, -2.1144F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.9635F, 0.0F));

		Tire5.addOrReplaceChild("cube_r299", CubeListBuilder.create().texOffs(56, 51).addBox(-3.2388F, -12.285F, -2.4911F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.0617F, 0.0F));

		Tire5.addOrReplaceChild("cube_r300", CubeListBuilder.create().texOffs(54, 36).addBox(-3.0104F, -12.285F, -2.847F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.1598F, 0.0F));

		Tire5.addOrReplaceChild("cube_r301", CubeListBuilder.create().texOffs(56, 48).addBox(-2.4546F, -12.285F, -3.4834F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.3562F, 0.0F));

		Tire5.addOrReplaceChild("cube_r302", CubeListBuilder.create().texOffs(56, 45).addBox(-2.7481F, -12.285F, -3.1788F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.258F, 0.0F));

		Tire5.addOrReplaceChild("cube_r303", CubeListBuilder.create().texOffs(56, 33).addBox(-2.1327F, -12.285F, -3.7576F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.4544F, 0.0F));

		Tire5.addOrReplaceChild("cube_r304", CubeListBuilder.create().texOffs(56, 30).addBox(-1.7854F, -12.285F, -3.9991F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.5525F, 0.0F));

		Tire5.addOrReplaceChild("cube_r305", CubeListBuilder.create().texOffs(53, 27).addBox(-1.4162F, -12.285F, -4.2053F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.6507F, 0.0F));

		Tire5.addOrReplaceChild("cube_r306", CubeListBuilder.create().texOffs(65, 40).addBox(-1.0285F, -12.285F, -4.3743F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.7489F, 0.0F));

		Tire5.addOrReplaceChild("cube_r307", CubeListBuilder.create().texOffs(56, 27).addBox(-0.6261F, -12.285F, -4.5045F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.8471F, 0.0F));

		Tire5.addOrReplaceChild("cube_r308", CubeListBuilder.create().texOffs(50, 42).addBox(-0.2129F, -12.285F, -4.5947F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.9452F, 0.0F));

		Tire5.addOrReplaceChild("cube_r309", CubeListBuilder.create().texOffs(50, 39).addBox(0.2072F, -12.285F, -4.6439F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 3.0434F, 0.0F));

		Tire5.addOrReplaceChild("cube_r310", CubeListBuilder.create().texOffs(50, 36).addBox(0.6301F, -12.285F, -4.6516F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 3.1416F, 0.0F));

		Tire5.addOrReplaceChild("cube_r311", CubeListBuilder.create().texOffs(59, 39).addBox(2.2685F, -12.285F, -4.2732F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.7489F, 0.0F));

		Tire5.addOrReplaceChild("cube_r312", CubeListBuilder.create().texOffs(50, 32).addBox(4.6586F, -12.285F, -1.468F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.8653F, 0.0F));

		Tire5.addOrReplaceChild("cube_r313", CubeListBuilder.create().texOffs(50, 33).addBox(1.0517F, -12.285F, -4.618F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -3.0434F, 0.0F));

		Tire5.addOrReplaceChild("cube_r314", CubeListBuilder.create().texOffs(54, 42).addBox(1.4679F, -12.285F, -4.5431F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.9452F, 0.0F));

		Tire5.addOrReplaceChild("cube_r315", CubeListBuilder.create().texOffs(50, 30).addBox(1.8748F, -12.285F, -4.4278F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.8471F, 0.0F));

		Tire5.addOrReplaceChild("cube_r316", CubeListBuilder.create().texOffs(65, 34).addBox(2.6451F, -12.285F, -4.0807F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.6507F, 0.0F));

		Tire5.addOrReplaceChild("cube_r317", CubeListBuilder.create().texOffs(65, 37).addBox(3.0011F, -12.285F, -3.8523F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.5525F, 0.0F));

		Tire5.addOrReplaceChild("cube_r318", CubeListBuilder.create().texOffs(47, 34).addBox(3.3329F, -12.285F, -3.59F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.4544F, 0.0F));

		Tire5.addOrReplaceChild("cube_r319", CubeListBuilder.create().texOffs(47, 37).addBox(3.6374F, -12.285F, -3.2966F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.3562F, 0.0F));

		Tire5.addOrReplaceChild("cube_r320", CubeListBuilder.create().texOffs(53, 39).addBox(3.9117F, -12.285F, -2.9746F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.258F, 0.0F));

		Tire5.addOrReplaceChild("cube_r321", CubeListBuilder.create().texOffs(50, 27).addBox(4.1531F, -12.285F, -2.6274F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.1598F, 0.0F));

		Tire5.addOrReplaceChild("cube_r322", CubeListBuilder.create().texOffs(48, 42).addBox(4.3593F, -12.285F, -2.2581F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.0617F, 0.0F));

		Tire5.addOrReplaceChild("cube_r323", CubeListBuilder.create().texOffs(50, 50).addBox(4.5283F, -12.285F, -1.8704F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.9635F, 0.0F));

		Tire5.addOrReplaceChild("cube_r324", CubeListBuilder.create().texOffs(67, 46).addBox(4.7487F, -12.285F, -1.0548F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.7671F, 0.0F));

		Tire5.addOrReplaceChild("cube_r325", CubeListBuilder.create().texOffs(61, 46).addBox(4.7979F, -12.285F, -0.6347F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.669F, 0.0F));

		Tire5.addOrReplaceChild("cube_r326", CubeListBuilder.create().texOffs(60, 41).addBox(4.8057F, -12.285F, -0.2119F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.5708F, 0.0F));

		Tire5.addOrReplaceChild("cube_r327", CubeListBuilder.create().texOffs(60, 35).addBox(3.4506F, -12.285F, 2.7955F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.7854F, 0.0F));

		Tire5.addOrReplaceChild("cube_r328", CubeListBuilder.create().texOffs(54, 42).addBox(3.7441F, -12.285F, 2.4909F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.8836F, 0.0F));

		Tire5.addOrReplaceChild("cube_r329", CubeListBuilder.create().texOffs(54, 39).addBox(4.0063F, -12.285F, 2.1591F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.9817F, 0.0F));

		Tire5.addOrReplaceChild("cube_r330", CubeListBuilder.create().texOffs(54, 36).addBox(4.2348F, -12.285F, 1.8032F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.0799F, 0.0F));

		Tire5.addOrReplaceChild("cube_r331", CubeListBuilder.create().texOffs(55, 45).addBox(4.4273F, -12.285F, 1.4266F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.1781F, 0.0F));

		Tire5.addOrReplaceChild("cube_r332", CubeListBuilder.create().texOffs(49, 45).addBox(4.5819F, -12.285F, 1.0329F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.2763F, 0.0F));

		Tire5.addOrReplaceChild("cube_r333", CubeListBuilder.create().texOffs(54, 39).addBox(4.6972F, -12.285F, 0.626F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.3744F, 0.0F));

		Tire5.addOrReplaceChild("cube_r334", CubeListBuilder.create().texOffs(48, 39).addBox(4.772F, -12.285F, 0.2097F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.4726F, 0.0F));

		Tire5.addOrReplaceChild("cube_r335", CubeListBuilder.create().texOffs(50, 47).addBox(2.0245F, -12.285F, 3.6864F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.3927F, 0.0F));

		Tire5.addOrReplaceChild("cube_r336", CubeListBuilder.create().texOffs(67, 43).addBox(2.4121F, -12.285F, 3.5174F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.4909F, 0.0F));

		Tire5.addOrReplaceChild("cube_r337", CubeListBuilder.create().texOffs(61, 43).addBox(2.7814F, -12.285F, 3.3112F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.589F, 0.0F));

		Tire5.addOrReplaceChild("cube_r338", CubeListBuilder.create().texOffs(60, 38).addBox(3.1287F, -12.285F, 3.0698F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.6872F, 0.0F));

		Tire5.addOrReplaceChild("cube_r339", CubeListBuilder.create().texOffs(55, 42).addBox(1.2088F, -12.285F, 3.9068F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.1963F, 0.0F));

		Tire5.addOrReplaceChild("cube_r340", CubeListBuilder.create().texOffs(49, 42).addBox(1.6221F, -12.285F, 3.8166F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.2945F, 0.0F));

		Tire5.addOrReplaceChild("cube_r341", CubeListBuilder.create().texOffs(50, 33).addBox(0.7888F, -12.285F, 3.956F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.0982F, 0.0F));

		PartDefinition Tire6 = Light.addOrReplaceChild("Tire6", CubeListBuilder.create().texOffs(81, 0).addBox(-1.502F, 6.153F, -0.3439F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F));

		Tire6.addOrReplaceChild("cube_r342", CubeListBuilder.create().texOffs(88, 0).addBox(-2.0557F, -12.285F, 3.9301F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.0982F, 0.0F));

		Tire6.addOrReplaceChild("cube_r343", CubeListBuilder.create().texOffs(82, 0).addBox(-2.4719F, -12.285F, 3.8552F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.1963F, 0.0F));

		Tire6.addOrReplaceChild("cube_r344", CubeListBuilder.create().texOffs(89, 1).addBox(-2.8789F, -12.285F, 3.74F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.2945F, 0.0F));

		Tire6.addOrReplaceChild("cube_r345", CubeListBuilder.create().texOffs(81, 0).addBox(-3.2725F, -12.285F, 3.5853F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.3927F, 0.0F));

		Tire6.addOrReplaceChild("cube_r346", CubeListBuilder.create().texOffs(90, 0).addBox(-3.6491F, -12.285F, 3.3929F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.4909F, 0.0F));

		Tire6.addOrReplaceChild("cube_r347", CubeListBuilder.create().texOffs(89, 3).addBox(-4.0051F, -12.285F, 3.1644F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.589F, 0.0F));

		Tire6.addOrReplaceChild("cube_r348", CubeListBuilder.create().texOffs(89, 0).addBox(-4.3369F, -12.285F, 2.9022F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.6872F, 0.0F));

		Tire6.addOrReplaceChild("cube_r349", CubeListBuilder.create().texOffs(83, 0).addBox(-4.6414F, -12.285F, 2.6087F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.7854F, 0.0F));

		Tire6.addOrReplaceChild("cube_r350", CubeListBuilder.create().texOffs(89, 3).addBox(-4.9157F, -12.285F, 2.2868F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.8836F, 0.0F));

		Tire6.addOrReplaceChild("cube_r351", CubeListBuilder.create().texOffs(89, 0).addBox(-5.1571F, -12.285F, 1.9395F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 0.9817F, 0.0F));

		Tire6.addOrReplaceChild("cube_r352", CubeListBuilder.create().texOffs(89, 5).addBox(-5.3633F, -12.285F, 1.5702F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.0799F, 0.0F));

		Tire6.addOrReplaceChild("cube_r353", CubeListBuilder.create().texOffs(84, 6).addBox(-5.5324F, -12.285F, 1.1825F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.1781F, 0.0F));

		Tire6.addOrReplaceChild("cube_r354", CubeListBuilder.create().texOffs(87, 5).addBox(-5.6626F, -12.285F, 0.7801F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.2763F, 0.0F));

		Tire6.addOrReplaceChild("cube_r355", CubeListBuilder.create().texOffs(81, 5).addBox(-5.7527F, -12.285F, 0.3669F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.3744F, 0.0F));

		Tire6.addOrReplaceChild("cube_r356", CubeListBuilder.create().texOffs(90, 3).addBox(-5.8019F, -12.285F, -0.0531F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.4726F, 0.0F));

		Tire6.addOrReplaceChild("cube_r357", CubeListBuilder.create().texOffs(84, 3).addBox(-5.8097F, -12.285F, -0.476F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.5708F, 0.0F));

		Tire6.addOrReplaceChild("cube_r358", CubeListBuilder.create().texOffs(88, 4).addBox(-5.776F, -12.285F, -0.8976F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.669F, 0.0F));

		Tire6.addOrReplaceChild("cube_r359", CubeListBuilder.create().texOffs(83, 3).addBox(-5.7012F, -12.285F, -1.3139F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.7671F, 0.0F));

		Tire6.addOrReplaceChild("cube_r360", CubeListBuilder.create().texOffs(82, 3).addBox(-5.5859F, -12.285F, -1.7208F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.8653F, 0.0F));

		Tire6.addOrReplaceChild("cube_r361", CubeListBuilder.create().texOffs(89, 2).addBox(-5.4313F, -12.285F, -2.1144F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 1.9635F, 0.0F));

		Tire6.addOrReplaceChild("cube_r362", CubeListBuilder.create().texOffs(84, 3).addBox(-5.2388F, -12.285F, -2.4911F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.0617F, 0.0F));

		Tire6.addOrReplaceChild("cube_r363", CubeListBuilder.create().texOffs(89, 2).addBox(-5.0104F, -12.285F, -2.847F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.1598F, 0.0F));

		Tire6.addOrReplaceChild("cube_r364", CubeListBuilder.create().texOffs(84, 0).addBox(-4.4546F, -12.285F, -3.4834F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.3562F, 0.0F));

		Tire6.addOrReplaceChild("cube_r365", CubeListBuilder.create().texOffs(86, 3).addBox(-4.7481F, -12.285F, -3.1788F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.258F, 0.0F));

		Tire6.addOrReplaceChild("cube_r366", CubeListBuilder.create().texOffs(87, 3).addBox(-4.1327F, -12.285F, -3.7576F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.4544F, 0.0F));

		Tire6.addOrReplaceChild("cube_r367", CubeListBuilder.create().texOffs(87, 0).addBox(-3.7854F, -12.285F, -3.9991F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.5525F, 0.0F));

		Tire6.addOrReplaceChild("cube_r368", CubeListBuilder.create().texOffs(86, 0).addBox(-3.4162F, -12.285F, -4.2053F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.6507F, 0.0F));

		Tire6.addOrReplaceChild("cube_r369", CubeListBuilder.create().texOffs(81, 0).addBox(-3.0285F, -12.285F, -4.3743F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.7489F, 0.0F));

		Tire6.addOrReplaceChild("cube_r370", CubeListBuilder.create().texOffs(85, 6).addBox(-2.6261F, -12.285F, -4.5045F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.8471F, 0.0F));

		Tire6.addOrReplaceChild("cube_r371", CubeListBuilder.create().texOffs(87, 3).addBox(-2.2129F, -12.285F, -4.5947F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 2.9452F, 0.0F));

		Tire6.addOrReplaceChild("cube_r372", CubeListBuilder.create().texOffs(87, 0).addBox(-1.7928F, -12.285F, -4.6439F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 3.0434F, 0.0F));

		Tire6.addOrReplaceChild("cube_r373", CubeListBuilder.create().texOffs(86, 3).addBox(-1.3699F, -12.285F, -4.6516F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, 3.1416F, 0.0F));

		Tire6.addOrReplaceChild("cube_r374", CubeListBuilder.create().texOffs(88, 0).addBox(0.2685F, -12.285F, -4.2732F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.7489F, 0.0F));

		Tire6.addOrReplaceChild("cube_r375", CubeListBuilder.create().texOffs(82, 2).addBox(2.6586F, -12.285F, -1.468F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.8653F, 0.0F));

		Tire6.addOrReplaceChild("cube_r376", CubeListBuilder.create().texOffs(86, 0).addBox(-0.9483F, -12.285F, -4.618F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -3.0434F, 0.0F));

		Tire6.addOrReplaceChild("cube_r377", CubeListBuilder.create().texOffs(87, 2).addBox(-0.5321F, -12.285F, -4.5431F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.9452F, 0.0F));

		Tire6.addOrReplaceChild("cube_r378", CubeListBuilder.create().texOffs(88, 3).addBox(-0.1252F, -12.285F, -4.4278F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.8471F, 0.0F));

		Tire6.addOrReplaceChild("cube_r379", CubeListBuilder.create().texOffs(83, 0).addBox(0.6451F, -12.285F, -4.0807F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.6507F, 0.0F));

		Tire6.addOrReplaceChild("cube_r380", CubeListBuilder.create().texOffs(83, 3).addBox(1.0011F, -12.285F, -3.8523F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.5525F, 0.0F));

		Tire6.addOrReplaceChild("cube_r381", CubeListBuilder.create().texOffs(88, 0).addBox(1.3329F, -12.285F, -3.59F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.4544F, 0.0F));

		Tire6.addOrReplaceChild("cube_r382", CubeListBuilder.create().texOffs(88, 3).addBox(1.6374F, -12.285F, -3.2966F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.3562F, 0.0F));

		Tire6.addOrReplaceChild("cube_r383", CubeListBuilder.create().texOffs(82, 0).addBox(1.9117F, -12.285F, -2.9746F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.258F, 0.0F));

		Tire6.addOrReplaceChild("cube_r384", CubeListBuilder.create().texOffs(88, 0).addBox(2.1531F, -12.285F, -2.6274F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.1598F, 0.0F));

		Tire6.addOrReplaceChild("cube_r385", CubeListBuilder.create().texOffs(81, 2).addBox(2.3593F, -12.285F, -2.2581F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -2.0617F, 0.0F));

		Tire6.addOrReplaceChild("cube_r386", CubeListBuilder.create().texOffs(90, 0).addBox(2.5283F, -12.285F, -1.8704F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.9635F, 0.0F));

		Tire6.addOrReplaceChild("cube_r387", CubeListBuilder.create().texOffs(84, 0).addBox(2.7487F, -12.285F, -1.0548F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.7671F, 0.0F));

		Tire6.addOrReplaceChild("cube_r388", CubeListBuilder.create().texOffs(88, 1).addBox(2.7979F, -12.285F, -0.6347F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.669F, 0.0F));

		Tire6.addOrReplaceChild("cube_r389", CubeListBuilder.create().texOffs(83, 0).addBox(2.8057F, -12.285F, -0.2119F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.5708F, 0.0F));

		Tire6.addOrReplaceChild("cube_r390", CubeListBuilder.create().texOffs(82, 0).addBox(1.4506F, -12.285F, 2.7955F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.7854F, 0.0F));

		Tire6.addOrReplaceChild("cube_r391", CubeListBuilder.create().texOffs(84, 0).addBox(1.7441F, -12.285F, 2.4909F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.8836F, 0.0F));

		Tire6.addOrReplaceChild("cube_r392", CubeListBuilder.create().texOffs(83, 3).addBox(2.0063F, -12.285F, 2.1591F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.9817F, 0.0F));

		Tire6.addOrReplaceChild("cube_r393", CubeListBuilder.create().texOffs(83, 0).addBox(2.2348F, -12.285F, 1.8032F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.0799F, 0.0F));

		Tire6.addOrReplaceChild("cube_r394", CubeListBuilder.create().texOffs(82, 0).addBox(2.4273F, -12.285F, 1.4266F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.1781F, 0.0F));

		Tire6.addOrReplaceChild("cube_r395", CubeListBuilder.create().texOffs(88, 5).addBox(2.5819F, -12.285F, 1.0329F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.2763F, 0.0F));

		Tire6.addOrReplaceChild("cube_r396", CubeListBuilder.create().texOffs(89, 5).addBox(2.6972F, -12.285F, 0.626F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.3744F, 0.0F));

		Tire6.addOrReplaceChild("cube_r397", CubeListBuilder.create().texOffs(83, 5).addBox(2.772F, -12.285F, 0.2097F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -1.4726F, 0.0F));

		Tire6.addOrReplaceChild("cube_r398", CubeListBuilder.create().texOffs(89, 3).addBox(0.0245F, -12.285F, 3.6864F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.3927F, 0.0F));

		Tire6.addOrReplaceChild("cube_r399", CubeListBuilder.create().texOffs(83, 3).addBox(0.4121F, -12.285F, 3.5174F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.4909F, 0.0F));

		Tire6.addOrReplaceChild("cube_r400", CubeListBuilder.create().texOffs(90, 4).addBox(0.7814F, -12.285F, 3.3112F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.589F, 0.0F));

		Tire6.addOrReplaceChild("cube_r401", CubeListBuilder.create().texOffs(82, 3).addBox(1.1287F, -12.285F, 3.0698F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.6872F, 0.0F));

		Tire6.addOrReplaceChild("cube_r402", CubeListBuilder.create().texOffs(84, 3).addBox(-0.7912F, -12.285F, 3.9068F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.1963F, 0.0F));

		Tire6.addOrReplaceChild("cube_r403", CubeListBuilder.create().texOffs(88, 2).addBox(-0.3779F, -12.285F, 3.8166F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.2945F, 0.0F));

		Tire6.addOrReplaceChild("cube_r404", CubeListBuilder.create().texOffs(81, 3).addBox(-1.2112F, -12.285F, 3.956F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 18.438F, -4.3077F, 0.0F, -0.0982F, 0.0F));

		PartDefinition Tire8 = Light.addOrReplaceChild("Tire8", CubeListBuilder.create(), PartPose.offsetAndRotation(5.5029F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F));

		Tire8.addOrReplaceChild("cube_r405", CubeListBuilder.create().texOffs(118, 22).addBox(-1.1685F, 4.9445F, 10.2039F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.0982F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r406", CubeListBuilder.create().texOffs(112, 22).addBox(-1.5886F, 4.9761F, 10.1662F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.1963F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r407", CubeListBuilder.create().texOffs(106, 22).addBox(-2.0018F, 5.034F, 10.0971F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.2945F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r408", CubeListBuilder.create().texOffs(104, 23).addBox(-2.4042F, 5.1177F, 9.9974F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.3927F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r409", CubeListBuilder.create().texOffs(114, 14).addBox(-2.7919F, 5.2264F, 9.8679F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.4909F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r410", CubeListBuilder.create().texOffs(114, 11).addBox(-3.1612F, 5.3589F, 9.7099F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.589F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r411", CubeListBuilder.create().texOffs(114, 8).addBox(-3.5084F, 5.5141F, 9.525F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.6872F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r412", CubeListBuilder.create().texOffs(100, 21).addBox(-3.8304F, 5.6904F, 9.3149F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.7854F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r413", CubeListBuilder.create().texOffs(109, 8).addBox(-4.1239F, 5.8862F, 9.0816F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.8836F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r414", CubeListBuilder.create().texOffs(113, 23).addBox(-4.3861F, 6.0995F, 8.8274F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.9817F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r415", CubeListBuilder.create().texOffs(110, 22).addBox(-4.6145F, 6.3283F, 8.5547F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.0799F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r416", CubeListBuilder.create().texOffs(118, 21).addBox(-4.807F, 6.5703F, 8.2662F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.1781F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r417", CubeListBuilder.create().texOffs(98, 21).addBox(-4.9616F, 6.8234F, 7.9647F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.2763F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r418", CubeListBuilder.create().texOffs(92, 21).addBox(-5.0769F, 7.0849F, 7.6529F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.3744F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r419", CubeListBuilder.create().texOffs(118, 19).addBox(-5.1518F, 7.3525F, 7.3341F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.4726F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r420", CubeListBuilder.create().texOffs(112, 19).addBox(-5.1855F, 7.6235F, 7.0111F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.5708F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r421", CubeListBuilder.create().texOffs(106, 19).addBox(-5.1777F, 7.8953F, 6.6872F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.669F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r422", CubeListBuilder.create().texOffs(104, 20).addBox(-5.1285F, 8.1653F, 6.3654F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.7671F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r423", CubeListBuilder.create().texOffs(100, 18).addBox(-5.0383F, 8.4309F, 6.0488F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.8653F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r424", CubeListBuilder.create().texOffs(110, 19).addBox(-4.9081F, 8.6896F, 5.7406F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 1.9635F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r425", CubeListBuilder.create().texOffs(118, 18).addBox(-4.7391F, 8.9388F, 5.4436F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.0617F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r426", CubeListBuilder.create().texOffs(98, 12).addBox(-4.5329F, 9.1762F, 5.1607F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.1598F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r427", CubeListBuilder.create().texOffs(118, 15).addBox(-4.0171F, 9.6063F, 4.6481F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.3562F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r428", CubeListBuilder.create().texOffs(118, 12).addBox(-4.2914F, 9.3994F, 4.8947F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.258F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r429", CubeListBuilder.create().texOffs(114, 23).addBox(-3.7126F, 9.795F, 4.4233F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.4544F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r430", CubeListBuilder.create().texOffs(108, 21).addBox(-3.3808F, 9.9635F, 4.2224F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.5525F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r431", CubeListBuilder.create().texOffs(114, 16).addBox(-3.0249F, 10.1104F, 4.0474F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.6507F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r432", CubeListBuilder.create().texOffs(112, 17).addBox(-2.6483F, 10.2341F, 3.8999F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.7489F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r433", CubeListBuilder.create().texOffs(105, 11).addBox(-2.2546F, 10.3335F, 3.7815F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.8471F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r434", CubeListBuilder.create().texOffs(109, 20).addBox(-1.8477F, 10.4076F, 3.6932F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 2.9452F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r435", CubeListBuilder.create().texOffs(109, 17).addBox(-1.4314F, 10.4557F, 3.6358F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 3.0434F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r436", CubeListBuilder.create().texOffs(109, 14).addBox(-1.0098F, 10.4774F, 3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 3.1416F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r437", CubeListBuilder.create().texOffs(106, 16).addBox(0.6487F, 10.2991F, 3.8225F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.7489F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r438", CubeListBuilder.create().texOffs(92, 24).addBox(3.2061F, 8.5934F, 5.8552F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.8653F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r439", CubeListBuilder.create().texOffs(109, 11).addBox(-0.587F, 10.4723F, 3.616F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -3.0434F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r440", CubeListBuilder.create().texOffs(98, 18).addBox(-0.1669F, 10.4407F, 3.6537F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.9452F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r441", CubeListBuilder.create().texOffs(109, 8).addBox(0.2463F, 10.3828F, 3.7227F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.8471F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r442", CubeListBuilder.create().texOffs(112, 11).addBox(1.0364F, 10.1904F, 3.952F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.6507F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r443", CubeListBuilder.create().texOffs(112, 14).addBox(1.4057F, 10.0579F, 4.1099F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.5525F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r444", CubeListBuilder.create().texOffs(94, 11).addBox(1.7529F, 9.9027F, 4.2949F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.4544F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r445", CubeListBuilder.create().texOffs(94, 14).addBox(2.0749F, 9.7264F, 4.505F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.3562F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r446", CubeListBuilder.create().texOffs(100, 16).addBox(2.3684F, 9.5306F, 4.7383F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.258F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r447", CubeListBuilder.create().texOffs(109, 5).addBox(2.6306F, 9.3173F, 4.9925F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.1598F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r448", CubeListBuilder.create().texOffs(92, 18).addBox(2.8591F, 9.0886F, 5.2651F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -2.0617F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r449", CubeListBuilder.create().texOffs(118, 16).addBox(3.0515F, 8.8465F, 5.5536F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.9635F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r450", CubeListBuilder.create().texOffs(112, 16).addBox(3.3214F, 8.3319F, 6.1669F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.7671F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r451", CubeListBuilder.create().texOffs(106, 16).addBox(3.3963F, 8.0643F, 6.4858F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.669F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r452", CubeListBuilder.create().texOffs(104, 17).addBox(3.43F, 7.7933F, 6.8088F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.5708F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r453", CubeListBuilder.create().texOffs(90, 17).addBox(2.2617F, 5.8105F, 9.1718F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.7854F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r454", CubeListBuilder.create().texOffs(108, 14).addBox(2.536F, 6.0174F, 8.9252F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.8836F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r455", CubeListBuilder.create().texOffs(108, 11).addBox(2.7774F, 6.2407F, 8.6591F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.9817F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r456", CubeListBuilder.create().texOffs(108, 8).addBox(2.9836F, 6.478F, 8.3763F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.0799F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r457", CubeListBuilder.create().texOffs(100, 15).addBox(3.1526F, 6.7272F, 8.0793F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.1781F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r458", CubeListBuilder.create().texOffs(110, 16).addBox(3.2828F, 6.9859F, 7.771F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.2763F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r459", CubeListBuilder.create().texOffs(98, 15).addBox(3.373F, 7.2515F, 7.4545F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.3744F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r460", CubeListBuilder.create().texOffs(92, 15).addBox(3.4222F, 7.5215F, 7.1327F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -1.4726F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r461", CubeListBuilder.create().texOffs(118, 13).addBox(0.8928F, 5.1827F, 9.9199F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.3927F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r462", CubeListBuilder.create().texOffs(112, 13).addBox(1.2694F, 5.3064F, 9.7725F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.4909F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r463", CubeListBuilder.create().texOffs(106, 13).addBox(1.6253F, 5.4533F, 9.5975F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.589F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r464", CubeListBuilder.create().texOffs(104, 14).addBox(1.9571F, 5.6218F, 9.3966F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.6872F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r465", CubeListBuilder.create().texOffs(100, 12).addBox(0.0922F, 5.0092F, 10.1267F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.1963F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r466", CubeListBuilder.create().texOffs(110, 13).addBox(0.4991F, 5.0833F, 10.0384F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.2945F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r467", CubeListBuilder.create().texOffs(108, 23).addBox(-0.3241F, 4.9611F, 10.184F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, -0.0982F, -3.1416F));

		Tire8.addOrReplaceChild("cube_r468", CubeListBuilder.create().texOffs(102, 21).addBox(-0.7457F, 4.9395F, 10.2098F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, 23.9409F, -4.3077F, -0.6981F, 0.0F, -3.1416F));

		PartDefinition DISK = partdefinition.addOrReplaceChild("DISK", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0318F, 24.0F, 12.7255F, 0.0F, 1.5708F, 0.0F));

		PartDefinition Disk2 = DISK.addOrReplaceChild("Disk2", CubeListBuilder.create().texOffs(1, 6).addBox(-1.9622F, -1.3757F, 6.7067F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		Disk2.addOrReplaceChild("cube_r469", CubeListBuilder.create().texOffs(-1, 4).addBox(-13.7691F, -0.476F, -6.5915F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.0982F, 0.0F));

		Disk2.addOrReplaceChild("cube_r470", CubeListBuilder.create().texOffs(11, 4).addBox(-13.0815F, -0.476F, -8.0947F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.1963F, 0.0F));

		Disk2.addOrReplaceChild("cube_r471", CubeListBuilder.create().texOffs(5, 4).addBox(-12.2497F, -0.476F, -9.5232F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.2945F, 0.0F));

		Disk2.addOrReplaceChild("cube_r472", CubeListBuilder.create().texOffs(11, 4).addBox(-11.282F, -0.476F, -10.8634F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.3927F, 0.0F));

		Disk2.addOrReplaceChild("cube_r473", CubeListBuilder.create().texOffs(11, 9).addBox(-10.1876F, -0.476F, -12.1022F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.4909F, 0.0F));

		Disk2.addOrReplaceChild("cube_r474", CubeListBuilder.create().texOffs(11, 6).addBox(-8.977F, -0.476F, -13.2278F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.589F, 0.0F));

		Disk2.addOrReplaceChild("cube_r475", CubeListBuilder.create().texOffs(11, 3).addBox(-7.6619F, -0.476F, -14.2293F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.6872F, 0.0F));

		Disk2.addOrReplaceChild("cube_r476", CubeListBuilder.create().texOffs(-1, 3).addBox(-6.2549F, -0.476F, -15.0971F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.7854F, 0.0F));

		Disk2.addOrReplaceChild("cube_r477", CubeListBuilder.create().texOffs(11, 0).addBox(-4.7697F, -0.476F, -15.8228F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.8836F, 0.0F));

		Disk2.addOrReplaceChild("cube_r478", CubeListBuilder.create().texOffs(9, 9).addBox(-3.2206F, -0.476F, -16.3994F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 0.9817F, 0.0F));

		Disk2.addOrReplaceChild("cube_r479", CubeListBuilder.create().texOffs(-1, 3).addBox(-1.6223F, -0.476F, -16.8215F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.0799F, 0.0F));

		Disk2.addOrReplaceChild("cube_r480", CubeListBuilder.create().texOffs(5, 2).addBox(0.0096F, -0.476F, -17.0848F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.1781F, 0.0F));

		Disk2.addOrReplaceChild("cube_r481", CubeListBuilder.create().texOffs(5, 2).addBox(1.6595F, -0.476F, -17.1869F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.2763F, 0.0F));

		Disk2.addOrReplaceChild("cube_r482", CubeListBuilder.create().texOffs(-1, 2).addBox(3.3114F, -0.476F, -17.1268F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.3744F, 0.0F));

		Disk2.addOrReplaceChild("cube_r483", CubeListBuilder.create().texOffs(-1, 1).addBox(4.9495F, -0.476F, -16.9051F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.4726F, 0.0F));

		Disk2.addOrReplaceChild("cube_r484", CubeListBuilder.create().texOffs(11, 1).addBox(6.558F, -0.476F, -16.5238F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.5708F, 0.0F));

		Disk2.addOrReplaceChild("cube_r485", CubeListBuilder.create().texOffs(5, 1).addBox(8.1213F, -0.476F, -15.9868F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.669F, 0.0F));

		Disk2.addOrReplaceChild("cube_r486", CubeListBuilder.create().texOffs(11, 1).addBox(9.6245F, -0.476F, -15.2991F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.7671F, 0.0F));

		Disk2.addOrReplaceChild("cube_r487", CubeListBuilder.create().texOffs(-1, 0).addBox(11.053F, -0.476F, -14.4674F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.8653F, 0.0F));

		Disk2.addOrReplaceChild("cube_r488", CubeListBuilder.create().texOffs(-1, 0).addBox(12.3932F, -0.476F, -13.4996F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 1.9635F, 0.0F));

		Disk2.addOrReplaceChild("cube_r489", CubeListBuilder.create().texOffs(5, 8).addBox(13.632F, -0.476F, -12.4052F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.0617F, 0.0F));

		Disk2.addOrReplaceChild("cube_r490", CubeListBuilder.create().texOffs(5, 2).addBox(14.7576F, -0.476F, -11.1946F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.1598F, 0.0F));

		Disk2.addOrReplaceChild("cube_r491", CubeListBuilder.create().texOffs(5, 5).addBox(16.6269F, -0.476F, -8.4726F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.3562F, 0.0F));

		Disk2.addOrReplaceChild("cube_r492", CubeListBuilder.create().texOffs(5, 2).addBox(15.7591F, -0.476F, -9.8795F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.258F, 0.0F));

		Disk2.addOrReplaceChild("cube_r493", CubeListBuilder.create().texOffs(7, 0).addBox(17.3526F, -0.476F, -6.9874F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.4544F, 0.0F));

		Disk2.addOrReplaceChild("cube_r494", CubeListBuilder.create().texOffs(7, 6).addBox(17.9293F, -0.476F, -5.4382F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.5525F, 0.0F));

		Disk2.addOrReplaceChild("cube_r495", CubeListBuilder.create().texOffs(5, 7).addBox(18.3513F, -0.476F, -3.84F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.6507F, 0.0F));

		Disk2.addOrReplaceChild("cube_r496", CubeListBuilder.create().texOffs(9, 1).addBox(18.6146F, -0.476F, -2.208F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.7489F, 0.0F));

		Disk2.addOrReplaceChild("cube_r497", CubeListBuilder.create().texOffs(7, 3).addBox(18.7167F, -0.476F, -0.5582F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.8471F, 0.0F));

		Disk2.addOrReplaceChild("cube_r498", CubeListBuilder.create().texOffs(5, 6).addBox(18.6566F, -0.476F, 1.0937F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 2.9452F, 0.0F));

		Disk2.addOrReplaceChild("cube_r499", CubeListBuilder.create().texOffs(5, 3).addBox(18.4348F, -0.476F, 2.7318F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 3.0434F, 0.0F));

		Disk2.addOrReplaceChild("cube_r500", CubeListBuilder.create().texOffs(5, 0).addBox(18.0536F, -0.476F, 4.3403F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, 3.1416F, 0.0F));

		Disk2.addOrReplaceChild("cube_r501", CubeListBuilder.create().texOffs(3, 0).addBox(15.0294F, -0.476F, 10.1755F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.7489F, 0.0F));

		Disk2.addOrReplaceChild("cube_r502", CubeListBuilder.create().texOffs(-1, 5).addBox(2.088F, -0.476F, 16.499F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.8653F, 0.0F));

		Disk2.addOrReplaceChild("cube_r503", CubeListBuilder.create().texOffs(5, 6).addBox(17.5166F, -0.476F, 5.9036F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -3.0434F, 0.0F));

		Disk2.addOrReplaceChild("cube_r504", CubeListBuilder.create().texOffs(5, 8).addBox(16.8289F, -0.476F, 7.4068F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.9452F, 0.0F));

		Disk2.addOrReplaceChild("cube_r505", CubeListBuilder.create().texOffs(5, 3).addBox(15.9972F, -0.476F, 8.8354F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.8471F, 0.0F));

		Disk2.addOrReplaceChild("cube_r506", CubeListBuilder.create().texOffs(-1, 5).addBox(13.935F, -0.476F, 11.4144F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.6507F, 0.0F));

		Disk2.addOrReplaceChild("cube_r507", CubeListBuilder.create().texOffs(-1, 8).addBox(12.7244F, -0.476F, 12.5399F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.5525F, 0.0F));

		Disk2.addOrReplaceChild("cube_r508", CubeListBuilder.create().texOffs(-1, 5).addBox(11.4093F, -0.476F, 13.5415F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.4544F, 0.0F));

		Disk2.addOrReplaceChild("cube_r509", CubeListBuilder.create().texOffs(-1, 8).addBox(10.0024F, -0.476F, 14.4092F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.3562F, 0.0F));

		Disk2.addOrReplaceChild("cube_r510", CubeListBuilder.create().texOffs(3, 0).addBox(8.5172F, -0.476F, 15.135F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.258F, 0.0F));

		Disk2.addOrReplaceChild("cube_r511", CubeListBuilder.create().texOffs(5, 0).addBox(6.968F, -0.476F, 15.7116F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.1598F, 0.0F));

		Disk2.addOrReplaceChild("cube_r512", CubeListBuilder.create().texOffs(-1, 8).addBox(5.3697F, -0.476F, 16.1336F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -2.0617F, 0.0F));

		Disk2.addOrReplaceChild("cube_r513", CubeListBuilder.create().texOffs(-1, 7).addBox(3.7378F, -0.476F, 16.3969F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.9635F, 0.0F));

		Disk2.addOrReplaceChild("cube_r514", CubeListBuilder.create().texOffs(11, 7).addBox(0.436F, -0.476F, 16.4389F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.7671F, 0.0F));

		Disk2.addOrReplaceChild("cube_r515", CubeListBuilder.create().texOffs(5, 7).addBox(-1.2021F, -0.476F, 16.2172F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.669F, 0.0F));

		Disk2.addOrReplaceChild("cube_r516", CubeListBuilder.create().texOffs(11, 7).addBox(-2.8105F, -0.476F, 15.836F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.5708F, 0.0F));

		Disk2.addOrReplaceChild("cube_r517", CubeListBuilder.create().texOffs(11, 1).addBox(-12.8795F, -0.476F, 7.7847F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.7854F, 0.0F));

		Disk2.addOrReplaceChild("cube_r518", CubeListBuilder.create().texOffs(5, 9).addBox(-12.0117F, -0.476F, 9.1917F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.8836F, 0.0F));

		Disk2.addOrReplaceChild("cube_r519", CubeListBuilder.create().texOffs(5, 6).addBox(-11.0102F, -0.476F, 10.5067F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.9817F, 0.0F));

		Disk2.addOrReplaceChild("cube_r520", CubeListBuilder.create().texOffs(5, 3).addBox(-9.8846F, -0.476F, 11.7173F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.0799F, 0.0F));

		Disk2.addOrReplaceChild("cube_r521", CubeListBuilder.create().texOffs(-1, 6).addBox(-8.6457F, -0.476F, 12.8118F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.1781F, 0.0F));

		Disk2.addOrReplaceChild("cube_r522", CubeListBuilder.create().texOffs(-1, 6).addBox(-7.3056F, -0.476F, 13.7795F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.2763F, 0.0F));

		Disk2.addOrReplaceChild("cube_r523", CubeListBuilder.create().texOffs(5, 5).addBox(-5.877F, -0.476F, 14.6112F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.3744F, 0.0F));

		Disk2.addOrReplaceChild("cube_r524", CubeListBuilder.create().texOffs(-1, 5).addBox(-4.3739F, -0.476F, 15.2989F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -1.4726F, 0.0F));

		Disk2.addOrReplaceChild("cube_r525", CubeListBuilder.create().texOffs(-1, 4).addBox(-14.8671F, -0.476F, 1.5202F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.3927F, 0.0F));

		Disk2.addOrReplaceChild("cube_r526", CubeListBuilder.create().texOffs(11, 4).addBox(-14.6038F, -0.476F, 3.1521F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.4909F, 0.0F));

		Disk2.addOrReplaceChild("cube_r527", CubeListBuilder.create().texOffs(5, 4).addBox(-14.1818F, -0.476F, 4.7503F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.589F, 0.0F));

		Disk2.addOrReplaceChild("cube_r528", CubeListBuilder.create().texOffs(11, 4).addBox(-13.6052F, -0.476F, 6.2995F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.6872F, 0.0F));

		Disk2.addOrReplaceChild("cube_r529", CubeListBuilder.create().texOffs(-1, 3).addBox(-14.9091F, -0.476F, -1.7816F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.1963F, 0.0F));

		Disk2.addOrReplaceChild("cube_r530", CubeListBuilder.create().texOffs(-1, 3).addBox(-14.9693F, -0.476F, -0.1297F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.2945F, 0.0F));

		Disk2.addOrReplaceChild("cube_r531", CubeListBuilder.create().texOffs(1, 0).addBox(-14.6874F, -0.476F, -3.4197F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.344F, -0.8997F, 11.7349F, 0.0F, -0.0982F, 0.0F));

		PartDefinition Disk1 = DISK.addOrReplaceChild("Disk1", CubeListBuilder.create().texOffs(1, 6).addBox(-1.9622F, -1.3757F, 6.7067F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(33.3615F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		Disk1.addOrReplaceChild("cube_r532", CubeListBuilder.create().texOffs(-1, 4).addBox(19.4317F, -0.476F, -3.3215F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.0982F, 0.0F));

		Disk1.addOrReplaceChild("cube_r533", CubeListBuilder.create().texOffs(11, 4).addBox(19.639F, -0.476F, -1.5862F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.1963F, 0.0F));

		Disk1.addOrReplaceChild("cube_r534", CubeListBuilder.create().texOffs(5, 4).addBox(19.6752F, -0.476F, 0.1611F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.2945F, 0.0F));

		Disk1.addOrReplaceChild("cube_r535", CubeListBuilder.create().texOffs(11, 4).addBox(19.54F, -0.476F, 1.9035F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.3927F, 0.0F));

		Disk1.addOrReplaceChild("cube_r536", CubeListBuilder.create().texOffs(11, 9).addBox(19.2346F, -0.476F, 3.6243F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.4909F, 0.0F));

		Disk1.addOrReplaceChild("cube_r537", CubeListBuilder.create().texOffs(11, 6).addBox(18.7621F, -0.476F, 5.3068F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.589F, 0.0F));

		Disk1.addOrReplaceChild("cube_r538", CubeListBuilder.create().texOffs(11, 3).addBox(18.1269F, -0.476F, 6.935F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.6872F, 0.0F));

		Disk1.addOrReplaceChild("cube_r539", CubeListBuilder.create().texOffs(-1, 3).addBox(17.3352F, -0.476F, 8.493F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.7854F, 0.0F));

		Disk1.addOrReplaceChild("cube_r540", CubeListBuilder.create().texOffs(11, 0).addBox(16.3946F, -0.476F, 9.9659F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.8836F, 0.0F));

		Disk1.addOrReplaceChild("cube_r541", CubeListBuilder.create().texOffs(9, 9).addBox(15.3141F, -0.476F, 11.3396F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 0.9817F, 0.0F));

		Disk1.addOrReplaceChild("cube_r542", CubeListBuilder.create().texOffs(-1, 3).addBox(14.1042F, -0.476F, 12.6007F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.0799F, 0.0F));

		Disk1.addOrReplaceChild("cube_r543", CubeListBuilder.create().texOffs(5, 2).addBox(12.7765F, -0.476F, 13.7372F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.1781F, 0.0F));

		Disk1.addOrReplaceChild("cube_r544", CubeListBuilder.create().texOffs(5, 2).addBox(11.3438F, -0.476F, 14.738F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.2763F, 0.0F));

		Disk1.addOrReplaceChild("cube_r545", CubeListBuilder.create().texOffs(-1, 2).addBox(9.8199F, -0.476F, 15.5936F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.3744F, 0.0F));

		Disk1.addOrReplaceChild("cube_r546", CubeListBuilder.create().texOffs(-1, 1).addBox(8.2195F, -0.476F, 16.2958F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.4726F, 0.0F));

		Disk1.addOrReplaceChild("cube_r547", CubeListBuilder.create().texOffs(11, 1).addBox(6.558F, -0.476F, 16.8376F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.5708F, 0.0F));

		Disk1.addOrReplaceChild("cube_r548", CubeListBuilder.create().texOffs(5, 1).addBox(4.8513F, -0.476F, 17.214F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.669F, 0.0F));

		Disk1.addOrReplaceChild("cube_r549", CubeListBuilder.create().texOffs(11, 1).addBox(3.116F, -0.476F, 17.4213F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.7671F, 0.0F));

		Disk1.addOrReplaceChild("cube_r550", CubeListBuilder.create().texOffs(-1, 0).addBox(1.3687F, -0.476F, 17.4576F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.8653F, 0.0F));

		Disk1.addOrReplaceChild("cube_r551", CubeListBuilder.create().texOffs(-1, 0).addBox(-0.3737F, -0.476F, 17.3224F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 1.9635F, 0.0F));

		Disk1.addOrReplaceChild("cube_r552", CubeListBuilder.create().texOffs(5, 8).addBox(-2.0945F, -0.476F, 17.017F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.0617F, 0.0F));

		Disk1.addOrReplaceChild("cube_r553", CubeListBuilder.create().texOffs(5, 2).addBox(-3.777F, -0.476F, 16.5445F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.1598F, 0.0F));

		Disk1.addOrReplaceChild("cube_r554", CubeListBuilder.create().texOffs(5, 5).addBox(-6.9632F, -0.476F, 15.1175F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.3562F, 0.0F));

		Disk1.addOrReplaceChild("cube_r555", CubeListBuilder.create().texOffs(5, 2).addBox(-5.4052F, -0.476F, 15.9093F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.258F, 0.0F));

		Disk1.addOrReplaceChild("cube_r556", CubeListBuilder.create().texOffs(7, 0).addBox(-8.4362F, -0.476F, 14.1769F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.4544F, 0.0F));

		Disk1.addOrReplaceChild("cube_r557", CubeListBuilder.create().texOffs(7, 6).addBox(-9.8098F, -0.476F, 13.0964F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.5525F, 0.0F));

		Disk1.addOrReplaceChild("cube_r558", CubeListBuilder.create().texOffs(5, 7).addBox(-11.0709F, -0.476F, 11.8865F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.6507F, 0.0F));

		Disk1.addOrReplaceChild("cube_r559", CubeListBuilder.create().texOffs(9, 1).addBox(-12.2074F, -0.476F, 10.5589F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.7489F, 0.0F));

		Disk1.addOrReplaceChild("cube_r560", CubeListBuilder.create().texOffs(7, 3).addBox(-13.2083F, -0.476F, 9.1261F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.8471F, 0.0F));

		Disk1.addOrReplaceChild("cube_r561", CubeListBuilder.create().texOffs(5, 6).addBox(-14.0639F, -0.476F, 7.6022F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 2.9452F, 0.0F));

		Disk1.addOrReplaceChild("cube_r562", CubeListBuilder.create().texOffs(5, 3).addBox(-14.766F, -0.476F, 6.0018F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 3.0434F, 0.0F));

		Disk1.addOrReplaceChild("cube_r563", CubeListBuilder.create().texOffs(5, 0).addBox(-15.3079F, -0.476F, 4.3403F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, 3.1416F, 0.0F));

		Disk1.addOrReplaceChild("cube_r564", CubeListBuilder.create().texOffs(3, 0).addBox(-15.7926F, -0.476F, -2.5914F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.7489F, 0.0F));

		Disk1.addOrReplaceChild("cube_r565", CubeListBuilder.create().texOffs(-1, 5).addBox(-7.5964F, -0.476F, -15.4259F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.8653F, 0.0F));

		Disk1.addOrReplaceChild("cube_r566", CubeListBuilder.create().texOffs(5, 6).addBox(-15.6843F, -0.476F, 2.6336F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -3.0434F, 0.0F));

		Disk1.addOrReplaceChild("cube_r567", CubeListBuilder.create().texOffs(5, 8).addBox(-15.8916F, -0.476F, 0.8983F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.9452F, 0.0F));

		Disk1.addOrReplaceChild("cube_r568", CubeListBuilder.create().texOffs(5, 3).addBox(-15.9278F, -0.476F, -0.8489F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.8471F, 0.0F));

		Disk1.addOrReplaceChild("cube_r569", CubeListBuilder.create().texOffs(-1, 5).addBox(-15.4872F, -0.476F, -4.3121F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.6507F, 0.0F));

		Disk1.addOrReplaceChild("cube_r570", CubeListBuilder.create().texOffs(-1, 8).addBox(-15.0147F, -0.476F, -5.9947F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.5525F, 0.0F));

		Disk1.addOrReplaceChild("cube_r571", CubeListBuilder.create().texOffs(-1, 5).addBox(-14.3795F, -0.476F, -7.6228F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.4544F, 0.0F));

		Disk1.addOrReplaceChild("cube_r572", CubeListBuilder.create().texOffs(-1, 8).addBox(-13.5878F, -0.476F, -9.1809F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.3562F, 0.0F));

		Disk1.addOrReplaceChild("cube_r573", CubeListBuilder.create().texOffs(3, 0).addBox(-12.6471F, -0.476F, -10.6538F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.258F, 0.0F));

		Disk1.addOrReplaceChild("cube_r574", CubeListBuilder.create().texOffs(5, 0).addBox(-11.5667F, -0.476F, -12.0275F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.1598F, 0.0F));

		Disk1.addOrReplaceChild("cube_r575", CubeListBuilder.create().texOffs(-1, 8).addBox(-10.3567F, -0.476F, -13.2886F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -2.0617F, 0.0F));

		Disk1.addOrReplaceChild("cube_r576", CubeListBuilder.create().texOffs(-1, 7).addBox(-9.0291F, -0.476F, -14.4251F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.9635F, 0.0F));

		Disk1.addOrReplaceChild("cube_r577", CubeListBuilder.create().texOffs(11, 7).addBox(-6.0725F, -0.476F, -16.2815F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.7671F, 0.0F));

		Disk1.addOrReplaceChild("cube_r578", CubeListBuilder.create().texOffs(5, 7).addBox(-4.4721F, -0.476F, -16.9836F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.669F, 0.0F));

		Disk1.addOrReplaceChild("cube_r579", CubeListBuilder.create().texOffs(11, 7).addBox(-2.8105F, -0.476F, -17.5255F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.5708F, 0.0F));

		Disk1.addOrReplaceChild("cube_r580", CubeListBuilder.create().texOffs(11, 1).addBox(10.7107F, -0.476F, -15.8054F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.7854F, 0.0F));

		Disk1.addOrReplaceChild("cube_r581", CubeListBuilder.create().texOffs(5, 9).addBox(9.1526F, -0.476F, -16.5971F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.8836F, 0.0F));

		Disk1.addOrReplaceChild("cube_r582", CubeListBuilder.create().texOffs(5, 6).addBox(7.5245F, -0.476F, -17.2323F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.9817F, 0.0F));

		Disk1.addOrReplaceChild("cube_r583", CubeListBuilder.create().texOffs(5, 3).addBox(5.8419F, -0.476F, -17.7049F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.0799F, 0.0F));

		Disk1.addOrReplaceChild("cube_r584", CubeListBuilder.create().texOffs(-1, 6).addBox(4.1211F, -0.476F, -18.0102F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.1781F, 0.0F));

		Disk1.addOrReplaceChild("cube_r585", CubeListBuilder.create().texOffs(-1, 6).addBox(2.3787F, -0.476F, -18.1454F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.2763F, 0.0F));

		Disk1.addOrReplaceChild("cube_r586", CubeListBuilder.create().texOffs(5, 5).addBox(0.6315F, -0.476F, -18.1092F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.3744F, 0.0F));

		Disk1.addOrReplaceChild("cube_r587", CubeListBuilder.create().texOffs(-1, 5).addBox(-1.1039F, -0.476F, -17.9019F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -1.4726F, 0.0F));

		Disk1.addOrReplaceChild("cube_r588", CubeListBuilder.create().texOffs(-1, 4).addBox(15.9548F, -0.476F, -11.2467F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.3927F, 0.0F));

		Disk1.addOrReplaceChild("cube_r589", CubeListBuilder.create().texOffs(11, 4).addBox(14.8184F, -0.476F, -12.5744F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.4909F, 0.0F));

		Disk1.addOrReplaceChild("cube_r590", CubeListBuilder.create().texOffs(5, 4).addBox(13.5572F, -0.476F, -13.7843F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.589F, 0.0F));

		Disk1.addOrReplaceChild("cube_r591", CubeListBuilder.create().texOffs(11, 4).addBox(12.1836F, -0.476F, -14.8648F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.6872F, 0.0F));

		Disk1.addOrReplaceChild("cube_r592", CubeListBuilder.create().texOffs(-1, 3).addBox(17.8113F, -0.476F, -8.2901F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.1963F, 0.0F));

		Disk1.addOrReplaceChild("cube_r593", CubeListBuilder.create().texOffs(-1, 3).addBox(16.9557F, -0.476F, -9.814F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.2945F, 0.0F));

		Disk1.addOrReplaceChild("cube_r594", CubeListBuilder.create().texOffs(1, 0).addBox(18.5134F, -0.476F, -6.6897F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0175F, -0.8997F, 11.7349F, 0.0F, -0.0982F, 0.0F));

		PartDefinition sadle = partdefinition.addOrReplaceChild("sadle", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 3.9815F, 6.3647F, 0.0F, 1.5708F, 0.0F));

		sadle.addOrReplaceChild("cube_r595", CubeListBuilder.create().texOffs(7, 69).addBox(-13.9118F, -2.3806F, 11.1865F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 77).addBox(-14.5997F, -2.3806F, 11.9271F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 70).addBox(-14.2557F, -2.3806F, 11.5831F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 69).addBox(-13.5679F, -2.3806F, 10.8082F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 69).addBox(-13.2239F, -2.3806F, 10.3955F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 68).addBox(-12.88F, -2.3806F, 10.0516F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.4363F));

		sadle.addOrReplaceChild("cube_r596", CubeListBuilder.create().texOffs(24, 77).addBox(-19.0584F, -2.3806F, -1.281F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.3054F));

		sadle.addOrReplaceChild("cube_r597", CubeListBuilder.create().texOffs(24, 77).addBox(-19.0582F, -2.3806F, 1.2832F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.1745F));

		sadle.addOrReplaceChild("cube_r598", CubeListBuilder.create().texOffs(24, 77).addBox(-19.0956F, -2.3806F, -0.4265F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(23, 76).addBox(-14.2641F, -2.3806F, -1.1448F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 71).addBox(-10.1869F, -1.8521F, 0.1269F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.2618F));

		sadle.addOrReplaceChild("cube_r599", CubeListBuilder.create().texOffs(24, 77).addBox(-19.0956F, -2.3806F, 0.4288F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.2182F));

		sadle.addOrReplaceChild("cube_r600", CubeListBuilder.create().texOffs(24, 77).addBox(-18.8723F, -2.3806F, -2.981F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.3927F));

		sadle.addOrReplaceChild("cube_r601", CubeListBuilder.create().texOffs(24, 77).addBox(-18.9839F, -2.3806F, -2.133F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.3491F));

		sadle.addOrReplaceChild("cube_r602", CubeListBuilder.create().texOffs(10, 72).addBox(-13.0718F, -2.3806F, 9.2897F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.3927F));

		sadle.addOrReplaceChild("cube_r603", CubeListBuilder.create().texOffs(21, 72).addBox(-12.9255F, -2.3806F, 6.2282F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 72).addBox(-14.3013F, -2.3806F, 6.2282F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(15, 72).addBox(-11.5498F, -2.3806F, 6.2282F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.1745F));

		sadle.addOrReplaceChild("cube_r604", CubeListBuilder.create().texOffs(40, 71).addBox(-8.7139F, -1.8585F, 2.5004F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.0F));

		sadle.addOrReplaceChild("cube_r605", CubeListBuilder.create().texOffs(42, 71).addBox(-4.1022F, -1.8789F, 2.9017F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.0873F));

		sadle.addOrReplaceChild("cube_r606", CubeListBuilder.create().texOffs(41, 71).addBox(-13.4242F, -1.8605F, -1.7092F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.4363F));

		sadle.addOrReplaceChild("cube_r607", CubeListBuilder.create().texOffs(41, 71).addBox(-13.2844F, -1.8568F, -5.0611F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, 0.6981F));

		sadle.addOrReplaceChild("cube_r608", CubeListBuilder.create().texOffs(28, 72).addBox(-13.2383F, -2.3806F, 8.4329F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.3491F));

		sadle.addOrReplaceChild("cube_r609", CubeListBuilder.create().texOffs(14, 68).addBox(-13.3794F, -2.3806F, 7.6727F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.9832F, 8.2837F, 0.1321F, 1.5708F, 0.0F, -0.3054F));

		PartDefinition Tank = partdefinition.addOrReplaceChild("Tank", CubeListBuilder.create(), PartPose.offsetAndRotation(1.3757F, 24.0F, 28.5464F, 0.0F, 1.5708F, 0.0F));

		Tank.addOrReplaceChild("cube_r610", CubeListBuilder.create().texOffs(60, 71).addBox(-4.3585F, -2.3806F, 4.8691F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 1.5708F));

		Tank.addOrReplaceChild("cube_r611", CubeListBuilder.create().texOffs(60, 71).addBox(-1.9013F, -2.3806F, 5.8628F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 1.2217F));

		Tank.addOrReplaceChild("cube_r612", CubeListBuilder.create().texOffs(60, 71).addBox(0.7476F, -2.3806F, 5.9562F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 0.8727F));

		Tank.addOrReplaceChild("cube_r613", CubeListBuilder.create().texOffs(60, 71).addBox(2.325F, -2.3806F, 5.4695F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 0.6109F));

		Tank.addOrReplaceChild("cube_r614", CubeListBuilder.create().texOffs(60, 71).addBox(3.7227F, -2.3806F, 4.5911F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 0.3491F));

		Tank.addOrReplaceChild("cube_r615", CubeListBuilder.create().texOffs(60, 71).addBox(-1.547F, -2.3806F, 1.0363F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(60, 71).addBox(2.0763F, -2.3806F, 1.3533F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.4363F));

		Tank.addOrReplaceChild("cube_r616", CubeListBuilder.create().texOffs(60, 71).addBox(0.4948F, -2.3806F, 1.0737F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.6109F));

		Tank.addOrReplaceChild("cube_r617", CubeListBuilder.create().texOffs(60, 71).addBox(4.049F, -2.3806F, 3.8565F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 0.1745F));

		Tank.addOrReplaceChild("cube_r618", CubeListBuilder.create().texOffs(60, 71).addBox(1.2916F, -2.3806F, 1.179F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.5236F));

		Tank.addOrReplaceChild("cube_r619", CubeListBuilder.create().texOffs(60, 71).addBox(4.2427F, -2.3806F, 3.0765F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, 0.0F));

		Tank.addOrReplaceChild("cube_r620", CubeListBuilder.create().texOffs(60, 71).addBox(4.298F, -2.3806F, 2.2746F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.1745F));

		Tank.addOrReplaceChild("cube_r621", CubeListBuilder.create().texOffs(60, 71).addBox(3.5852F, -2.3806F, 1.9032F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.2618F));

		Tank.addOrReplaceChild("cube_r622", CubeListBuilder.create().texOffs(60, 71).addBox(2.8427F, -2.3806F, 1.5953F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.1649F, -11.7349F, -1.2437F, 1.5708F, 0.0F, -0.3491F));

		PartDefinition Chasis = partdefinition.addOrReplaceChild("Chasis", CubeListBuilder.create(), PartPose.offsetAndRotation(2.7514F, 24.0397F, 28.5996F, 0.0F, 1.5708F, 0.0F));

		Chasis.addOrReplaceChild("cube_r623", CubeListBuilder.create().texOffs(42, 96).addBox(-5.9849F, -5.0049F, -9.7486F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 96).addBox(-6.0001F, -5.0049F, -9.587F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 96).addBox(-6.0001F, -5.0049F, -8.8991F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 89).addBox(-7.2692F, -5.0049F, -6.1709F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 96).addBox(-8.628F, -5.0049F, 1.9828F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.0F));

		Chasis.addOrReplaceChild("cube_r624", CubeListBuilder.create().texOffs(45, 96).addBox(-4.9503F, -5.0049F, -4.4758F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 89).addBox(-6.1632F, -5.0049F, -7.8469F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 96).addBox(-8.7704F, -5.0049F, -1.7309F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.1745F));

		Chasis.addOrReplaceChild("cube_r625", CubeListBuilder.create().texOffs(45, 96).addBox(-5.3786F, -5.0049F, -8.0861F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 96).addBox(-5.3786F, -5.0049F, -6.7104F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(45, 96).addBox(-5.3786F, -5.0049F, -5.3346F, 15.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 96).addBox(-8.7802F, -5.0049F, 0.1291F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.0873F));

		Chasis.addOrReplaceChild("cube_r626", CubeListBuilder.create().texOffs(37, 89).addBox(-10.296F, -5.0049F, -3.7207F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 89).addBox(-8.6534F, -5.0049F, -0.3705F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, -0.5236F));

		Chasis.addOrReplaceChild("cube_r627", CubeListBuilder.create().texOffs(37, 89).addBox(-10.5792F, -5.0049F, -4.1411F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, -0.6109F));

		Chasis.addOrReplaceChild("cube_r628", CubeListBuilder.create().texOffs(37, 89).addBox(-5.4749F, -5.0049F, -9.2941F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 96).addBox(-8.5984F, -5.0049F, -3.5828F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.2618F));

		Chasis.addOrReplaceChild("cube_r629", CubeListBuilder.create().texOffs(37, 89).addBox(-8.0674F, -5.0049F, -4.3282F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, -0.1745F));

		Chasis.addOrReplaceChild("cube_r630", CubeListBuilder.create().texOffs(37, 89).addBox(-8.5335F, -5.0049F, -2.375F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(37, 89).addBox(-8.8087F, -5.0049F, 0.7705F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, -0.3491F));

		Chasis.addOrReplaceChild("cube_r631", CubeListBuilder.create().texOffs(37, 89).addBox(-8.7061F, -5.0049F, 0.2034F, 5.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, -0.4363F));

		Chasis.addOrReplaceChild("cube_r632", CubeListBuilder.create().texOffs(42, 96).addBox(-8.7046F, -5.0049F, -2.6587F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.2182F));

		Chasis.addOrReplaceChild("cube_r633", CubeListBuilder.create().texOffs(42, 96).addBox(-8.7956F, -5.0049F, -0.801F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.1309F));

		Chasis.addOrReplaceChild("cube_r634", CubeListBuilder.create().texOffs(42, 96).addBox(-8.7243F, -5.0049F, 1.0576F, 19.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(28.2181F, -11.7745F, -2.6194F, 1.5708F, 0.0F, 0.0436F));

		
		PartDefinition Tire2 = partdefinition.addOrReplaceChild("Tire2", CubeListBuilder.create(), PartPose.offset(0.0F, 16.9494F, 17.3189F));

		Tire2.addOrReplaceChild("cube_r635", CubeListBuilder.create().texOffs(82, 94).addBox(6.0024F, -2.1165F, 16.971F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.0982F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r636", CubeListBuilder.create().texOffs(99, 99).addBox(4.2908F, -2.1165F, 17.1819F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.1963F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r637", CubeListBuilder.create().texOffs(99, 96).addBox(2.5668F, -2.1165F, 17.2239F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.2945F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r638", CubeListBuilder.create().texOffs(76, 94).addBox(0.8469F, -2.1165F, 17.0968F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.3927F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r639", CubeListBuilder.create().texOffs(97, 92).addBox(-0.8522F, -2.1165F, 16.8017F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.4909F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r640", CubeListBuilder.create().texOffs(83, 108).addBox(-2.5142F, -2.1165F, 16.3414F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.589F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r641", CubeListBuilder.create().texOffs(101, 100).addBox(-4.1231F, -2.1165F, 15.7205F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.6872F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r642", CubeListBuilder.create().texOffs(101, 97).addBox(-5.6634F, -2.1165F, 14.9449F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.7854F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r643", CubeListBuilder.create().texOffs(101, 94).addBox(-7.1202F, -2.1165F, 14.022F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.8836F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r644", CubeListBuilder.create().texOffs(76, 95).addBox(-8.4796F, -2.1165F, 12.9608F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.9817F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r645", CubeListBuilder.create().texOffs(76, 92).addBox(-9.7284F, -2.1165F, 11.7714F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.0799F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r646", CubeListBuilder.create().texOffs(76, 89).addBox(-10.8546F, -2.1165F, 10.4654F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.1781F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r647", CubeListBuilder.create().texOffs(93, 108).addBox(-11.8474F, -2.1165F, 9.0553F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.2763F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r648", CubeListBuilder.create().texOffs(77, 92).addBox(-12.6972F, -2.1165F, 7.5546F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.3744F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r649", CubeListBuilder.create().texOffs(93, 105).addBox(-13.3958F, -2.1165F, 5.9779F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.4726F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r650", CubeListBuilder.create().texOffs(93, 102).addBox(-13.9365F, -2.1165F, 4.3403F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.5708F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r651", CubeListBuilder.create().texOffs(84, 93).addBox(-14.3141F, -2.1165F, 2.6576F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.669F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r652", CubeListBuilder.create().texOffs(79, 96).addBox(-14.5249F, -2.1165F, 0.946F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.7671F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r653", CubeListBuilder.create().texOffs(87, 102).addBox(-14.5669F, -2.1165F, -0.7781F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.8653F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r654", CubeListBuilder.create().texOffs(81, 102).addBox(-14.4398F, -2.1165F, -2.4979F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 1.9635F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r655", CubeListBuilder.create().texOffs(83, 93).addBox(-14.1447F, -2.1165F, -4.197F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.0617F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r656", CubeListBuilder.create().texOffs(89, 102).addBox(-13.6844F, -2.1165F, -5.859F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.1598F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r657", CubeListBuilder.create().texOffs(81, 105).addBox(-12.2879F, -2.1165F, -9.0082F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.3562F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r658", CubeListBuilder.create().texOffs(81, 102).addBox(-13.0635F, -2.1165F, -7.4679F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.258F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r659", CubeListBuilder.create().texOffs(81, 99).addBox(-11.365F, -2.1165F, -10.4651F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.4544F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r660", CubeListBuilder.create().texOffs(82, 91).addBox(-10.3038F, -2.1165F, -11.8245F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.5525F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r661", CubeListBuilder.create().texOffs(79, 98).addBox(-9.1145F, -2.1165F, -13.0733F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.6507F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r662", CubeListBuilder.create().texOffs(79, 100).addBox(-7.8084F, -2.1165F, -14.1995F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.7489F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r663", CubeListBuilder.create().texOffs(83, 104).addBox(-6.3983F, -2.1165F, -15.1923F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.8471F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r664", CubeListBuilder.create().texOffs(74, 98).addBox(-4.8976F, -2.1165F, -16.0421F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 2.9452F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r665", CubeListBuilder.create().texOffs(76, 91).addBox(-3.3209F, -2.1165F, -16.7406F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 3.0434F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r666", CubeListBuilder.create().texOffs(82, 91).addBox(-1.6833F, -2.1165F, -17.2813F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 3.1416F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r667", CubeListBuilder.create().texOffs(79, 97).addBox(5.1549F, -2.1165F, -17.7846F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.7489F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r668", CubeListBuilder.create().texOffs(76, 91).addBox(17.8492F, -2.1165F, -9.7431F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.8653F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r669", CubeListBuilder.create().texOffs(77, 89).addBox(-0.0006F, -2.1165F, -17.6589F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -3.0434F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r670", CubeListBuilder.create().texOffs(84, 90).addBox(1.711F, -2.1165F, -17.8697F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.9452F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r671", CubeListBuilder.create().texOffs(83, 91).addBox(3.435F, -2.1165F, -17.9118F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.8471F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r672", CubeListBuilder.create().texOffs(80, 91).addBox(6.854F, -2.1165F, -17.4895F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.6507F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r673", CubeListBuilder.create().texOffs(76, 94).addBox(8.516F, -2.1165F, -17.0293F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.5525F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r674", CubeListBuilder.create().texOffs(74, 91).addBox(10.1249F, -2.1165F, -16.4084F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.4544F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r675", CubeListBuilder.create().texOffs(79, 97).addBox(11.6652F, -2.1165F, -15.6328F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.3562F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r676", CubeListBuilder.create().texOffs(83, 92).addBox(13.1221F, -2.1165F, -14.7099F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.258F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r677", CubeListBuilder.create().texOffs(87, 99).addBox(14.4814F, -2.1165F, -13.6487F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.1598F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r678", CubeListBuilder.create().texOffs(81, 99).addBox(15.7302F, -2.1165F, -12.4593F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -2.0617F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r679", CubeListBuilder.create().texOffs(83, 90).addBox(16.8564F, -2.1165F, -11.1533F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.9635F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r680", CubeListBuilder.create().texOffs(82, 88).addBox(18.699F, -2.1165F, -8.2425F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.7671F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r681", CubeListBuilder.create().texOffs(76, 88).addBox(19.3976F, -2.1165F, -6.6658F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.669F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r682", CubeListBuilder.create().texOffs(82, 88).addBox(19.9383F, -2.1165F, -5.0282F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.5708F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r683", CubeListBuilder.create().texOffs(79, 100).addBox(18.2897F, -2.1165F, 8.3203F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.7854F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r684", CubeListBuilder.create().texOffs(93, 99).addBox(19.0654F, -2.1165F, 6.7801F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.8836F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r685", CubeListBuilder.create().texOffs(93, 96).addBox(19.6863F, -2.1165F, 5.1712F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.9817F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r686", CubeListBuilder.create().texOffs(76, 88).addBox(20.1465F, -2.1165F, 3.5092F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.0799F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r687", CubeListBuilder.create().texOffs(91, 92).addBox(20.4416F, -2.1165F, 1.8101F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.1781F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r688", CubeListBuilder.create().texOffs(77, 108).addBox(20.5688F, -2.1165F, 0.0902F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.2763F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r689", CubeListBuilder.create().texOffs(95, 100).addBox(20.5267F, -2.1165F, -1.6338F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.3744F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r690", CubeListBuilder.create().texOffs(95, 97).addBox(20.3159F, -2.1165F, -3.3455F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -1.4726F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r691", CubeListBuilder.create().texOffs(95, 94).addBox(13.8103F, -2.1165F, 13.5116F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.3927F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r692", CubeListBuilder.create().texOffs(79, 98).addBox(15.1163F, -2.1165F, 12.3854F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.4909F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r693", CubeListBuilder.create().texOffs(83, 93).addBox(16.3056F, -2.1165F, 11.1366F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.589F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r694", CubeListBuilder.create().texOffs(83, 90).addBox(17.3669F, -2.1165F, 9.7772F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.6872F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r695", CubeListBuilder.create().texOffs(87, 108).addBox(10.8995F, -2.1165F, 15.3542F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.1963F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r696", CubeListBuilder.create().texOffs(94, 108).addBox(12.4001F, -2.1165F, 14.5044F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.2945F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r697", CubeListBuilder.create().texOffs(87, 105).addBox(9.3228F, -2.1165F, 16.0528F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, -0.0982F, 1.5708F));

		Tire2.addOrReplaceChild("cube_r698", CubeListBuilder.create().texOffs(87, 102).addBox(7.6852F, -2.1165F, 16.5935F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1321F, -4.6842F, -17.6949F, 0.0F, 0.0F, 1.5708F));

		PartDefinition Tire1 = partdefinition.addOrReplaceChild("Tire1", CubeListBuilder.create().texOffs(13, 99).addBox(4.7584F, -1.6243F, -0.3439F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 16.9494F, -16.0426F, 0.0F, 1.5708F, 1.5708F));

		Tire1.addOrReplaceChild("cube_r699", CubeListBuilder.create().texOffs(13, 106).addBox(20.6406F, -1.4922F, 6.0018F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.0982F, 0.0F));

		Tire1.addOrReplaceChild("cube_r700", CubeListBuilder.create().texOffs(20, 110).addBox(19.9385F, -1.4922F, 7.6023F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.1963F, 0.0F));

		Tire1.addOrReplaceChild("cube_r701", CubeListBuilder.create().texOffs(15, 110).addBox(19.0829F, -1.4922F, 9.1262F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.2945F, 0.0F));

		Tire1.addOrReplaceChild("cube_r702", CubeListBuilder.create().texOffs(20, 109).addBox(18.082F, -1.4922F, 10.5588F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.3927F, 0.0F));

		Tire1.addOrReplaceChild("cube_r703", CubeListBuilder.create().texOffs(29, 101).addBox(16.9456F, -1.4922F, 11.8865F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.4909F, 0.0F));

		Tire1.addOrReplaceChild("cube_r704", CubeListBuilder.create().texOffs(28, 98).addBox(15.6845F, -1.4922F, 13.0964F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.589F, 0.0F));

		Tire1.addOrReplaceChild("cube_r705", CubeListBuilder.create().texOffs(18, 109).addBox(14.3108F, -1.4922F, 14.1769F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.6872F, 0.0F));

		Tire1.addOrReplaceChild("cube_r706", CubeListBuilder.create().texOffs(13, 105).addBox(12.8379F, -1.4922F, 15.1175F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.7854F, 0.0F));

		Tire1.addOrReplaceChild("cube_r707", CubeListBuilder.create().texOffs(27, 104).addBox(11.2798F, -1.4922F, 15.9092F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.8836F, 0.0F));

		Tire1.addOrReplaceChild("cube_r708", CubeListBuilder.create().texOffs(29, 99).addBox(9.6517F, -1.4922F, 16.5445F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 0.9817F, 0.0F));

		Tire1.addOrReplaceChild("cube_r709", CubeListBuilder.create().texOffs(26, 108).addBox(7.9691F, -1.4922F, 17.017F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.0799F, 0.0F));

		Tire1.addOrReplaceChild("cube_r710", CubeListBuilder.create().texOffs(15, 108).addBox(6.2484F, -1.4922F, 17.3223F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.1781F, 0.0F));

		Tire1.addOrReplaceChild("cube_r711", CubeListBuilder.create().texOffs(15, 107).addBox(4.5059F, -1.4922F, 17.4576F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.2763F, 0.0F));

		Tire1.addOrReplaceChild("cube_r712", CubeListBuilder.create().texOffs(13, 103).addBox(2.7586F, -1.4922F, 17.4213F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.3744F, 0.0F));

		Tire1.addOrReplaceChild("cube_r713", CubeListBuilder.create().texOffs(13, 103).addBox(1.0233F, -1.4922F, 17.2141F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.4726F, 0.0F));

		Tire1.addOrReplaceChild("cube_r714", CubeListBuilder.create().texOffs(20, 107).addBox(-0.6833F, -1.4922F, 16.8376F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.5708F, 0.0F));

		Tire1.addOrReplaceChild("cube_r715", CubeListBuilder.create().texOffs(15, 107).addBox(-2.3448F, -1.4922F, 16.2958F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.669F, 0.0F));

		Tire1.addOrReplaceChild("cube_r716", CubeListBuilder.create().texOffs(20, 106).addBox(-3.9453F, -1.4922F, 15.5936F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.7671F, 0.0F));

		Tire1.addOrReplaceChild("cube_r717", CubeListBuilder.create().texOffs(13, 102).addBox(-5.4692F, -1.4922F, 14.738F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.8653F, 0.0F));

		Tire1.addOrReplaceChild("cube_r718", CubeListBuilder.create().texOffs(26, 105).addBox(-6.9019F, -1.4922F, 13.7372F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 1.9635F, 0.0F));

		Tire1.addOrReplaceChild("cube_r719", CubeListBuilder.create().texOffs(15, 105).addBox(-8.2296F, -1.4922F, 12.6007F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.0617F, 0.0F));

		Tire1.addOrReplaceChild("cube_r720", CubeListBuilder.create().texOffs(13, 97).addBox(-9.4395F, -1.4922F, 11.3396F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.1598F, 0.0F));

		Tire1.addOrReplaceChild("cube_r721", CubeListBuilder.create().texOffs(15, 102).addBox(-11.4606F, -1.4922F, 8.493F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.3562F, 0.0F));

		Tire1.addOrReplaceChild("cube_r722", CubeListBuilder.create().texOffs(12, 101).addBox(-10.5199F, -1.4922F, 9.966F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.258F, 0.0F));

		Tire1.addOrReplaceChild("cube_r723", CubeListBuilder.create().texOffs(15, 106).addBox(-12.2523F, -1.4922F, 6.935F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.4544F, 0.0F));

		Tire1.addOrReplaceChild("cube_r724", CubeListBuilder.create().texOffs(15, 103).addBox(-12.8875F, -1.4922F, 5.3068F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.5525F, 0.0F));

		Tire1.addOrReplaceChild("cube_r725", CubeListBuilder.create().texOffs(13, 105).addBox(-13.36F, -1.4922F, 3.6243F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.6507F, 0.0F));

		Tire1.addOrReplaceChild("cube_r726", CubeListBuilder.create().texOffs(12, 99).addBox(-13.6654F, -1.4922F, 1.9035F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.7489F, 0.0F));

		Tire1.addOrReplaceChild("cube_r727", CubeListBuilder.create().texOffs(12, 102).addBox(-13.8006F, -1.4922F, 0.1611F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.8471F, 0.0F));

		Tire1.addOrReplaceChild("cube_r728", CubeListBuilder.create().texOffs(13, 106).addBox(-13.7644F, -1.4922F, -1.5862F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 2.9452F, 0.0F));

		Tire1.addOrReplaceChild("cube_r729", CubeListBuilder.create().texOffs(13, 103).addBox(-13.5571F, -1.4922F, -3.3215F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 3.0434F, 0.0F));

		Tire1.addOrReplaceChild("cube_r730", CubeListBuilder.create().texOffs(13, 100).addBox(-13.1807F, -1.4922F, -5.0282F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, 3.1416F, 0.0F));

		Tire1.addOrReplaceChild("cube_r731", CubeListBuilder.create().texOffs(11, 96).addBox(-10.0802F, -1.4922F, -11.2467F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.7489F, 0.0F));

		Tire1.addOrReplaceChild("cube_r732", CubeListBuilder.create().texOffs(16, 88).addBox(3.4959F, -1.4922F, -18.1454F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.8653F, 0.0F));

		Tire1.addOrReplaceChild("cube_r733", CubeListBuilder.create().texOffs(13, 97).addBox(-12.6388F, -1.4922F, -6.6897F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -3.0434F, 0.0F));

		Tire1.addOrReplaceChild("cube_r734", CubeListBuilder.create().texOffs(15, 104).addBox(-11.9367F, -1.4922F, -8.2901F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.9452F, 0.0F));

		Tire1.addOrReplaceChild("cube_r735", CubeListBuilder.create().texOffs(13, 106).addBox(-11.0811F, -1.4922F, -9.814F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.8471F, 0.0F));

		Tire1.addOrReplaceChild("cube_r736", CubeListBuilder.create().texOffs(28, 97).addBox(-8.9437F, -1.4922F, -12.5744F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.6507F, 0.0F));

		Tire1.addOrReplaceChild("cube_r737", CubeListBuilder.create().texOffs(13, 94).addBox(-7.6826F, -1.4922F, -13.7843F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.5525F, 0.0F));

		Tire1.addOrReplaceChild("cube_r738", CubeListBuilder.create().texOffs(14, 90).addBox(-6.309F, -1.4922F, -14.8648F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.4544F, 0.0F));

		Tire1.addOrReplaceChild("cube_r739", CubeListBuilder.create().texOffs(12, 93).addBox(-4.8361F, -1.4922F, -15.8054F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.3562F, 0.0F));

		Tire1.addOrReplaceChild("cube_r740", CubeListBuilder.create().texOffs(13, 90).addBox(-3.278F, -1.4922F, -16.5971F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.258F, 0.0F));

		Tire1.addOrReplaceChild("cube_r741", CubeListBuilder.create().texOffs(13, 90).addBox(-1.6499F, -1.4922F, -17.2323F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.1598F, 0.0F));

		Tire1.addOrReplaceChild("cube_r742", CubeListBuilder.create().texOffs(19, 87).addBox(0.0327F, -1.4922F, -17.7049F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -2.0617F, 0.0F));

		Tire1.addOrReplaceChild("cube_r743", CubeListBuilder.create().texOffs(12, 90).addBox(1.7535F, -1.4922F, -18.0102F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.9635F, 0.0F));

		Tire1.addOrReplaceChild("cube_r744", CubeListBuilder.create().texOffs(15, 88).addBox(5.2432F, -1.4922F, -18.1092F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.7671F, 0.0F));

		Tire1.addOrReplaceChild("cube_r745", CubeListBuilder.create().texOffs(14, 89).addBox(6.9785F, -1.4922F, -17.9019F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.669F, 0.0F));

		Tire1.addOrReplaceChild("cube_r746", CubeListBuilder.create().texOffs(15, 87).addBox(8.6852F, -1.4922F, -17.5255F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.5708F, 0.0F));

		Tire1.addOrReplaceChild("cube_r747", CubeListBuilder.create().texOffs(25, 90).addBox(19.4624F, -1.4922F, -9.1809F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.7854F, 0.0F));

		Tire1.addOrReplaceChild("cube_r748", CubeListBuilder.create().texOffs(22, 87).addBox(18.5218F, -1.4922F, -10.6538F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.8836F, 0.0F));

		Tire1.addOrReplaceChild("cube_r749", CubeListBuilder.create().texOffs(19, 85).addBox(17.4413F, -1.4922F, -12.0275F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.9817F, 0.0F));

		Tire1.addOrReplaceChild("cube_r750", CubeListBuilder.create().texOffs(23, 88).addBox(16.2314F, -1.4922F, -13.2886F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.0799F, 0.0F));

		Tire1.addOrReplaceChild("cube_r751", CubeListBuilder.create().texOffs(25, 89).addBox(14.9037F, -1.4922F, -14.4251F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.1781F, 0.0F));

		Tire1.addOrReplaceChild("cube_r752", CubeListBuilder.create().texOffs(18, 87).addBox(13.471F, -1.4922F, -15.4259F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.2763F, 0.0F));

		Tire1.addOrReplaceChild("cube_r753", CubeListBuilder.create().texOffs(17, 87).addBox(11.9471F, -1.4922F, -16.2815F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.3744F, 0.0F));

		Tire1.addOrReplaceChild("cube_r754", CubeListBuilder.create().texOffs(23, 87).addBox(10.3467F, -1.4922F, -16.9836F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -1.4726F, 0.0F));

		Tire1.addOrReplaceChild("cube_r755", CubeListBuilder.create().texOffs(13, 97).addBox(21.6672F, -1.4922F, -2.5914F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.3927F, 0.0F));

		Tire1.addOrReplaceChild("cube_r756", CubeListBuilder.create().texOffs(27, 94).addBox(21.3618F, -1.4922F, -4.3121F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.4909F, 0.0F));

		Tire1.addOrReplaceChild("cube_r757", CubeListBuilder.create().texOffs(25, 90).addBox(20.8893F, -1.4922F, -5.9947F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.589F, 0.0F));

		Tire1.addOrReplaceChild("cube_r758", CubeListBuilder.create().texOffs(25, 92).addBox(20.2541F, -1.4922F, -7.6228F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.6872F, 0.0F));

		Tire1.addOrReplaceChild("cube_r759", CubeListBuilder.create().texOffs(28, 96).addBox(21.7662F, -1.4922F, 0.8983F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.1963F, 0.0F));

		Tire1.addOrReplaceChild("cube_r760", CubeListBuilder.create().texOffs(28, 94).addBox(21.8024F, -1.4922F, -0.8489F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.2945F, 0.0F));

		Tire1.addOrReplaceChild("cube_r761", CubeListBuilder.create().texOffs(13, 102).addBox(21.5589F, -1.4922F, 2.6337F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.4241F, -0.1321F, -4.6842F, 0.0F, -0.0982F, 0.0F));

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
