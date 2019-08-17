package net.infernalrealms.aquatic_dimensions.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;

import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityCreature;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.EntityIronGolem;
import net.minecraft.server.v1_14_R1.EntityPigZombie;
import net.minecraft.server.v1_14_R1.EntityTurtle;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityVillagerAbstract;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_14_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_14_R1.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_14_R1.World;

public class ADMonster extends EntityCreature implements AquaticDimensionsEntity {
	
	private List<Entity> nameTagComponents;

	public ADMonster(EntityTypes<?> entitytypes, World world) {
        super(EntityTypes.RAVAGER, world);
    }
	
	@Override
	public void init(CustomMobData mobData) {
		AquaticDimensionsEntity.super.init(mobData);
		setupNametag();
	}
	
	private void setupNametag() {
		nameTagComponents = new ArrayList<>();
		Entity as = CustomEntityType.spawnEntitiy(CustomEntityType.NAME_TAG_AS, getBukkitEntity().getLocation());
		nameTagComponents.add(as);
		Entity slime = CustomEntityType.spawnEntitiy(CustomEntityType.NAME_TAG_SLIME, getBukkitEntity().getLocation());
		nameTagComponents.add(slime);
		getBukkitEntity().addPassenger(slime);
		slime.addPassenger(as);
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
		getAttributeMap().b(GenericAttributes.FLYING_SPEED).setValue(1D);
		getAttributeMap().b(GenericAttributes.LUCK).setValue(1D);
	}
	
	@Override
	protected void initPathfinder() {
//		this.goalSelector.a(4, new EntityZombie.a(this, 1.0D, 3));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.l();
	}
	
	protected void l() {
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
//        this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
//        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, true, 4, this::ed));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(EntityPigZombie.class));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, false));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
        this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntityTurtle.class, 10, true, false, EntityTurtle.bz));
    }
	
}
