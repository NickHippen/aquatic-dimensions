package net.infernalrealms.aquatic_dimensions.mobs.ai;

import net.minecraft.server.v1_14_R1.EntityCreature;
import net.minecraft.server.v1_14_R1.EntityLiving;
import net.minecraft.server.v1_14_R1.IRangedEntity;
import net.minecraft.server.v1_14_R1.ItemBow;
import net.minecraft.server.v1_14_R1.Items;
import net.minecraft.server.v1_14_R1.PathfinderGoal;
import net.minecraft.server.v1_14_R1.ProjectileHelper;

public class PathfinderGoalBowShoot<T extends EntityCreature & IRangedEntity> extends PathfinderGoal {

	private final T entity;
	private final double b;
	private int c;
	private final float d;
	private int e = -1;
	private int f;
	private boolean g;
	private boolean h;
	private int i = -1;
	
	public PathfinderGoalBowShoot(T entity, double v1, int v2, float v3) {
		this.entity = entity;
		this.b = v1;
		this.c = v2;
		this.d = v3 * v3;
	}

	/**
	 * isGoalReady()
	 */
	@Override
	public boolean a() {
		return entity.getGoalTarget() != null;
	}

	/**
	 * isGoalStillValid()
	 */
	@Override
	public boolean b() {
		// Second condition is "isAtEndOfPath()"
		return (a() || !entity.getNavigation().n());
	}
	
	
	/**
	 * startGoal()
	 */
	@Override
	public void c() {
		System.out.println("c()");
		super.c();
		entity.q(true);
	}
	
	/**
	 * endGoal()
	 */
	@Override
	public void d() {
		System.out.println("d()");
		super.d();
		entity.q(false);
		f = 0;
		e = -1;
		entity.dp();
	}
	
	/**
	 * Do kiting thing around target and calculate / shoot arrow 
	 */
	@Override
	public void e() {
		super.e();
		EntityLiving var0 = entity.getGoalTarget();
		if (var0 != null) {
			double var1 = entity.e(var0.locX, var0.getBoundingBox().minY, var0.locZ);
			boolean var3 = entity.getEntitySenses().a(var0);
			boolean var4 = this.f > 0;
			if (var3 != var4) {
				this.f = 0;
			}

			if (var3) {
				++this.f;
			} else {
				--this.f;
			}

			if (var1 <= (double) this.d && this.f >= 20) {
				entity.getNavigation().o();
				++this.i;
			} else {
				entity.getNavigation().a(var0, this.b);
				this.i = -1;
			}

			if (this.i >= 20) {
				if ((double) entity.getRandom().nextFloat() < 0.3D) {
					this.g = !this.g;
				}

				if ((double) entity.getRandom().nextFloat() < 0.3D) {
					this.h = !this.h;
				}

				this.i = 0;
			}

			if (this.i > -1) {
				if (var1 > (double) (this.d * 0.75F)) {
					this.h = false;
				} else if (var1 < (double) (this.d * 0.25F)) {
					this.h = true;
				}

				entity.getControllerMove().a(this.h ? -0.5F : 0.5F, this.g ? 0.5F : -0.5F);
				entity.a(var0, 30.0F, 30.0F);
			} else {
				entity.getControllerLook().a(var0, 30.0F, 30.0F);
			}

			if (entity.isHandRaised()) {
				if (!var3 && this.f < -60) {
					entity.dp();
				} else if (var3) {
					int var5 = entity.dn();
					if (var5 >= 20) {
						entity.dp();
						((IRangedEntity) entity).a(var0, ItemBow.a(var5));
						this.e = this.c;
					}
				}
			} else if (--this.e <= 0 && this.f >= -60) {
				entity.c(ProjectileHelper.a(entity, Items.BOW));
			}

		}
	}

}
