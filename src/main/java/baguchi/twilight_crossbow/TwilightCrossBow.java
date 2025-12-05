package baguchi.twilight_crossbow;

import baguchi.twilight_crossbow.init.ModEntities;
import baguchi.twilight_crossbow.init.ModItems;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import twilightforest.init.TFCreativeTabs;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TwilightCrossBow.MODID)
public class TwilightCrossBow {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "twilight_crossbow";


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TwilightCrossBow(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.ITEM_REGISTRY.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TFCreativeTabs.EQUIPMENT.getKey()) {
            event.accept(ModItems.TRIPLE_CROSSBOW);
        }
    }

}
