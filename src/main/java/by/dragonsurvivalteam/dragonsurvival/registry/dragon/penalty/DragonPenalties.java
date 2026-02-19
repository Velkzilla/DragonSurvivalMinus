package by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

public class DragonPenalties {
    @Translation(type = Translation.Type.PENALTY_DESCRIPTION, comments = "■ No penalties.")
    @Translation(type = Translation.Type.PENALTY, comments = "Item Blacklist")
    public static final ResourceKey<DragonPenalty> ITEM_BLACKLIST = DragonPenalties.key("item_blacklist");

    public static void registerPenalties(final BootstrapContext<DragonPenalty> context) {
        // All penalties have been removed for a more flexible gameplay experience

        context.register(ITEM_BLACKLIST, new DragonPenalty(
                Optional.empty(),
                Optional.empty(),
                new ItemBlacklistPenalty(DEFAULT_COMMON_BLACKLIST),
                PenaltyTrigger.instant()
        ));
    }

    public static ResourceKey<DragonPenalty> key(final ResourceLocation location) {
        return ResourceKey.create(DragonPenalty.REGISTRY, location);
    }

    public static ResourceKey<DragonPenalty> key(final String path) {
        return key(DragonSurvival.res(path));
    }

    // Empty blacklist - dragons can use all items
    public static final List<String> DEFAULT_COMMON_BLACKLIST = List.of();
}
