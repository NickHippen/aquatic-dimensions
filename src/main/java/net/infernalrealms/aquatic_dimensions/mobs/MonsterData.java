package net.infernalrealms.aquatic_dimensions.mobs;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MonsterData {

	private String name;
	private String type;
	private double health;
	private double damage;
	private double speed;
	private boolean archer;
	private MonsterEquipment equips;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public boolean isArcher() {
		return archer;
	}

	public void setArcher(boolean archer) {
		this.archer = archer;
	}

	public MonsterEquipment getEquips() {
		return equips;
	}

	public void setEquips(MonsterEquipment equips) {
		this.equips = equips;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
