package baguchi.twilight_crossbow.init;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.item.IceCrossBowItem;
import baguchi.twilight_crossbow.item.TripleCrossBowItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(TwilightCrossBow.MODID);

    public static final DeferredItem<Item> TRIPLE_CROSSBOW = ITEM_REGISTRY.register("triple_crossbow", () -> new TripleCrossBowItem((new Item.Properties().durability(412).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).stacksTo(1))));
    public static final DeferredItem<Item> ICE_CROSSBOW = ITEM_REGISTRY.register("ice_crossbow", () -> new IceCrossBowItem((new Item.Properties().durability(412).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).stacksTo(1))));

}