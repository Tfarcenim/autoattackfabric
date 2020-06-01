package tfar.autoattack;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class AutoAttack implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientTickCallback.EVENT.register(mc -> {
			if (mc.options.keyAttack.isPressed() && mc.player != null
							&& mc.player.getAttackCooldownProgress(0) >= 1) {
				if (mc.crosshairTarget != null && mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
					Entity entity = ((EntityHitResult)mc.crosshairTarget).getEntity();
					if (entity.isAlive() && entity.isAttackable()) {
						mc.interactionManager.attackEntity(mc.player, entity);
					}
				}
			}
		});
	}
}
