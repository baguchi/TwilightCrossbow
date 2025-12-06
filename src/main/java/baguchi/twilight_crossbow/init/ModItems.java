package baguchi.twilight_crossbow.init;

import baguchi.twilight_crossbow.TwilightCrossBow;
import baguchi.twilight_crossbow.item.IceCrossBowItem;
import baguchi.twilight_crossbow.item.TripleCrossBowItem;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REGISTRY = DeferredRegister.createItems(TwilightCrossBow.MODID);

    public static final DeferredItem<Item> TRIPLE_CROSSBOW = ITEM_REGISTRY.register("triple_crossbow", () -> new TripleCrossBowItem((new Item.Properties().rarity(Rarity.UNCOMMON).durability(412).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).stacksTo(1))));
    public static final DeferredItem<Item> ICE_CROSSBOW = ITEM_REGISTRY.register("ice_crossbow", () -> new IceCrossBowItem((new Item.Properties().rarity(Rarity.UNCOMMON).durability(412).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).stacksTo(1))));


    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;

    private static final Component CROSSBOW_UPGRADE_APPLIES_TO = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "smithing_template.crossbow_upgrade.applies_to"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component CROSSBOW_UPGRADE_INGREDIENTS = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "smithing_template.crossbow_upgrade.ingredients"))
            )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component CROSSBOW_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "smithing_template.crossbow_upgrade.base_slot_description"))
    );
    private static final Component CROSSBOW_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "smithing_template.crossbow_upgrade.additions_slot_description"))
    );
    private static final Component CROSSBOW_UPGRADE = Component.translatable(
                    Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "crossbow_upgrade"))
            )
            .withStyle(TITLE_FORMAT);

    private static final ResourceLocation EMPTY_SLOT_BOW = ResourceLocation.fromNamespaceAndPath(TwilightCrossBow.MODID, "item/empty_slot_bow");

    public static final DeferredItem<Item> CROSSBOW_UPGRADE_SMITHING_TEMPLATE = ITEM_REGISTRY.register("crossbow_upgrade_smithing_template", ModItems::createCrossbowUpgradeTemplate);


    public static SmithingTemplateItem createCrossbowUpgradeTemplate() {
        return new SmithingTemplateItem(
                CROSSBOW_UPGRADE_APPLIES_TO,
                CROSSBOW_UPGRADE_INGREDIENTS,
                CROSSBOW_UPGRADE,
                CROSSBOW_UPGRADE_BASE_SLOT_DESCRIPTION,
                CROSSBOW_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createCrossbowUpgradeIconList(),
                createEmptyMaterialList()
        );
    }

    private static List<ResourceLocation> createCrossbowUpgradeIconList() {
        return List.of(
                EMPTY_SLOT_BOW
        );
    }

    private static List<ResourceLocation> createEmptyMaterialList() {
        return List.of();
    }

}