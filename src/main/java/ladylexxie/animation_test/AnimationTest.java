package ladylexxie.animation_test;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod( AnimationTest.MODID )
public class AnimationTest {
	public static final String MODID = "animation_test";
	private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	public static final RegistryObject<Item> RED_SCYTHE = ITEMS.register("red_scythe", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).fireResistant().tab(AnimationTestTab.ANIMATION_TEST_TAB)));

	public AnimationTest() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::commonSetup);
		ITEMS.register(modEventBus);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void commonSetup( final FMLCommonSetupEvent event ) {
		LOGGER.info("HELLO FROM COMMON SETUP");
		LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
	}

	@SubscribeEvent
	public void onServerStarting( ServerStartingEvent event ) {
		LOGGER.info("HELLO from server starting");
	}

	@Mod.EventBusSubscriber( modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT )
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup( FMLClientSetupEvent event ) {
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		}
	}
}
