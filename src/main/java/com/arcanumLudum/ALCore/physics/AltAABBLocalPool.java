package com.arcanumLudum.ALCore.physics;

import net.minecraft.util.AxisAlignedBB;

public final class AltAABBLocalPool extends ThreadLocal {
	protected AxisAlignedBB createNewDefaultPool() {
		return AxisAlignedBB.getBoundingBox(300, 2000, 0, 0, 0, 0);
	}

	protected Object initialValue() {
		return this.createNewDefaultPool();
	}
}