package ladylexxie.animation_test;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AnimationTestTab {
	public static final CreativeModeTab ANIMATION_TEST_TAB = new CreativeModeTab("animation_test") {
		@Override
		public @NotNull ItemStack makeIcon() {
			return new ItemStack(AnimationTest.RED_SCYTHE.get());
		}
	};
}
