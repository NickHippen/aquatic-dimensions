package net.infernalrealms.aquatic_dimensions.mobs.types;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterData;
import net.infernalrealms.aquatic_dimensions.mobs.MonsterEquipment;
import net.infernalrealms.aquatic_dimensions.mobs.ai.PathfinderGoalBowShoot;
import net.infernalrealms.aquatic_dimensions.util.ItemStackUtil;
import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityArrow;
import net.minecraft.server.v1_14_R1.EntityCreature;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityIronGolem;
import net.minecraft.server.v1_14_R1.EntityLiving;
import net.minecraft.server.v1_14_R1.EntityMonster;
import net.minecraft.server.v1_14_R1.EntitySnowball;
import net.minecraft.server.v1_14_R1.EntityTurtle;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityVillagerAbstract;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.IAttribute;
import net.minecraft.server.v1_14_R1.IRangedEntity;
import net.minecraft.server.v1_14_R1.ItemStack;
import net.minecraft.server.v1_14_R1.Items;
import net.minecraft.server.v1_14_R1.MathHelper;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.ProjectileHelper;
import net.minecraft.server.v1_14_R1.SoundEffects;
import net.minecraft.server.v1_14_R1.World;

public class ADMonster extends EntityCreature implements ADEntity, IRangedEntity {

	private MonsterData monsterData;
	private List<Entity> nameTagComponents;

	/**
	 * Creates an ADMonster without specifying the type. The type should be set using reflection afterwards (useful if the type is not an EntityCreature).
	 * @param world
	 */
	public ADMonster(World world) {
		this(EntityTypes.ZOMBIE, world);
	}
	
	public ADMonster(EntityTypes<?> entitytypes, World world) {
        super((EntityTypes<? extends EntityCreature>) entitytypes, world); // This will throw an exception if a non-EntityCreature type is passed. For now we'll just have to avoid doing that.
    }
	
	@Override
	public void init(MonsterData monsterData) {
		this.monsterData = monsterData;
		setupNametag();
		setupAttributes();
		setupEquipment();
		setupAI();
	}
	
	private void setupNametag() {
		nameTagComponents = new ArrayList<>();
		Entity as = ADEntityType.spawnEntity(ADEntityType.NAME_TAG_AS, getBukkitEntity().getLocation(), monsterData);
		nameTagComponents.add(as);
		Entity slime = ADEntityType.spawnEntity(ADEntityType.NAME_TAG_SLIME, getBukkitEntity().getLocation(), monsterData);
		nameTagComponents.add(slime);
		getBukkitEntity().addPassenger(slime);
		slime.addPassenger(as);
	}
	
	private void setupAttributes() {
		getAttributeMap().a(GenericAttributes.MAX_HEALTH).setValue(monsterData.getHealth());
		getAttributeMap().a(GenericAttributes.ATTACK_DAMAGE).setValue(monsterData.getDamage());
		getAttributeMap().a(GenericAttributes.MOVEMENT_SPEED).setValue(monsterData.getSpeed());
	}
	
	private void setupEquipment() {
		if (monsterData.getEquips() == null) {
			return;
		}
		EntityEquipment equips = ((LivingEntity) getBukkitEntity()).getEquipment();
		MonsterEquipment monsterEquips = monsterData.getEquips();
		equips.setHelmet(ItemStackUtil.generateFromString(monsterEquips.getHelmet()));
		equips.setChestplate(ItemStackUtil.generateFromString(monsterEquips.getChestplate()));
		equips.setLeggings(ItemStackUtil.generateFromString(monsterEquips.getLeggings()));
		equips.setBoots(ItemStackUtil.generateFromString(monsterEquips.getBoots()));
		equips.setItemInMainHand(ItemStackUtil.generateFromString(monsterEquips.getMainHand()));
		equips.setItemInOffHand(ItemStackUtil.generateFromString(monsterEquips.getOffHand()));
	}
	
	private void setupAI() {
		// Goal Selectors
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        if (monsterData.isArcher()) {
        	this.goalSelector.a(4, new PathfinderGoalBowShoot<ADMonster>(this, 1.0D, 20, 15.0F));
        } else {
        	this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        }
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        
        // Target selectors
        this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityMonster.class, true));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, false));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
        this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bz));
	}
	
	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		boolean val = super.damageEntity(damagesource, f);
		if (getHealth() <= 0) {
			nameTagComponents.forEach(Entity::remove);
		}
		return val;
	}
	
	@Override
	public void setOnFire(int i) {
		// Stop fire
	}
	
	public double getAttributeValue(IAttribute attr) {
		return getAttributeMap().a(attr).getValue();
	}
	
	public void setAttributeValue(IAttribute attr, double value) {
		getAttributeMap().a(attr).setValue(value);
	}
	
	/**
	 * The following attributes are set in the super classes
	 * GenericAttributes.FOLLOW_RANGE
	 * GenericAttributes.ATTACK_KNOCKBACK
	 * GenericAttributes.MAX_HEALTH
	 * GenericAttributes.KNOCKBACK_RESISTANCE
	 * GenericAttributes.MOVEMENT_SPEED
	 * GenericAttributes.ARMOR
	 * GenericAttributes.ARMOR_TOUGHNESS
	 */
	@Override
	protected void initAttributes() {
		super.initAttributes();
		// TODO Get values for these from config. These must be set for now because the server may crash if it tries to access them during a mob tick.
		getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE).setValue(1D);
		getAttributeMap().b(GenericAttributes.ATTACK_SPEED).setValue(1D);
		getAttributeMap().b(GenericAttributes.FLYING_SPEED).setValue(0.1D);
		getAttributeMap().b(GenericAttributes.LUCK).setValue(1D);
	}
	
	@Override
	protected void initPathfinder() {
	}
	
	/**
	 * Shoot arrow
	 * (basically just copied from EntitySkeletonAbstract NMS; craftbukkit event calling removed)
	 */
	@Override
	public void a(EntityLiving entityliving, float f) {
		ItemStack itemstack = this.f(this.b(ProjectileHelper.a(this, Items.BOW)));
        EntityArrow entityarrow = ProjectileHelper.a(this, itemstack, f);
        double d0 = entityliving.locX - this.locX;
        double d1 = entityliving.getBoundingBox().minY + (double) (entityliving.getHeight() / 3.0F) - entityarrow.locY;
        double d2 = entityliving.locZ - this.locZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);

        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().a() * 4));
        world.addEntity(entityarrow);
        this.a(SoundEffects.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}
	
}
