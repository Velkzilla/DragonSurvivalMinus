package by.dragonsurvivalteam.dragonsurvival.registry.datagen.data_maps;

import by.dragonsurvivalteam.dragonsurvival.common.codecs.DietEntry;
import by.dragonsurvivalteam.dragonsurvival.registry.DSConditions;
import by.dragonsurvivalteam.dragonsurvival.registry.DSDataMaps;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.BuiltInDragonSpecies;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DataMapProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * DietEntryProvider - Minus Version
 * 
 * [EN] All dragons can eat ANY food. No dietary restrictions or hunger/poison penalties.
 *      This maximizes compatibility with other mods that add food items.
 * 
 * [中文] 所有龙可以吃任何食物。没有饮食限制或饥饿/中毒惩罚。
 *        这最大化了与其他添加食物 Mod 的兼容性。
 * 
 * == ORIGINAL BEHAVIOR / 原版行为 ==
 * 
 * Each dragon species had a specific diet:
 * - Cave Dragon: Coal, charred meat, fire-related items (~30 items)
 * - Forest Dragon: Raw meat, berries, mushrooms, poisonous foods (~100 items)
 * - Sea Dragon: Raw fish, kelp, sea-related items (~100 items)
 * 
 * Eating non-diet food caused:
 * 1. Hunger effect (饥饿效果)
 * 2. Poisoning effect (中毒效果)
 * 
 * == NEW BEHAVIOR / 新版本行为 ==
 * 
 * All dragon species: Can eat ALL items tagged as #c:foods
 * - No hunger penalty
 * - No poison penalty
 * - Full compatibility with modded foods
 * 
 * == MERGE NOTES / 合并上游源码注意事项 ==
 * 
 * When merging upstream changes from DragonSurvivalTeam/DragonSurvival:
 * 
 * 1. If upstream adds new diet entries (caveDiet/forestDiet/seaDiet methods),
 *    DO NOT merge them. Keep using the universal #c:foods tag.
 * 
 * 2. If upstream modifies the DietEntry codec or data map structure,
 *    review carefully to ensure the universal diet still works.
 * 
 * 3. The DSItemTags.tagDragonSpeciesFood() method was also simplified.
 *    When merging, ensure it still points all dragon foods to #c:foods.
 * 
 * == WHY THIS CHANGE / 为什么这样修改 ==
 * 
 * 1. Mod Compatibility: Many mods add custom foods. Players shouldn't need
 *    to configure diet lists for every new mod.
 * 
 * 2. Player Freedom: Players should decide what their dragon eats, not the mod.
 * 
 * 3. Reduced Configuration: No need to edit JSON files or datapacks to add
 *    modded foods to dragon diets.
 * 
 * 4. Consistency: All dragons play the same way regarding food, reducing
 *    confusion for new players.
 * 
 * == MAINTAINERS / 维护者 ==
 * 
 * Fork: DragonSurvival Minus
 * Repository: https://github.com/Velkzilla/DragonSurvivalMinus
 * Original: https://github.com/DragonSurvivalTeam/DragonSurvival
 */
public class DietEntryProvider extends DataMapProvider {
    
    public DietEntryProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        // [Minus] Universal diet for all dragons - any item tagged as #c:foods
        // This includes ALL vanilla and modded foods without manual configuration
        List<DietEntry> universalDiet = List.of(
                DietEntry.create(Tags.Items.FOODS).build()
        );

        builder(DSDataMaps.DIET_ENTRIES)
                .add(BuiltInDragonSpecies.CAVE_DRAGON, universalDiet, false, DSConditions.CAVE_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.FOREST_DRAGON, universalDiet, false, DSConditions.FOREST_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.SEA_DRAGON, universalDiet, false, DSConditions.SEA_DRAGON_LOADED);
    }
}
