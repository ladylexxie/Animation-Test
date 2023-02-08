package ladylexxie.animation_test;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber( modid = AnimationTest.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT )
public class PlayerAnimationTrigger {
	@SubscribeEvent
	public static void onAttackEntity( AttackEntityEvent event ) {
		var player = Minecraft.getInstance().level.getPlayerByUUID(event.getEntity().getUUID());
		if( player == null ) return;

		var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(new ResourceLocation(AnimationTest.MODID, "animation"));
		if( animation != null ) {
			//				animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation(AnimationTest.MODID, "waving"))));
			animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation(AnimationTest.MODID, "animation_template"))));
		}
	}

	@SubscribeEvent
	public static void onLivingAttack( PlayerInteractEvent.LeftClickEmpty event ) {
		var player = Minecraft.getInstance().level.getPlayerByUUID(event.getEntity().getUUID());

		if( player == null ) return;

		var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(new ResourceLocation(AnimationTest.MODID, "animation"));
		if( animation != null ) {
			animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation(AnimationTest.MODID, "two_handed_slash_vertical_left"))));
		}
	}
}