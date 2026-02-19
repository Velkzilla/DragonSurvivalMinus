package by.dragonsurvivalteam.dragonsurvival.registry.datagen.tags;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty.DragonPenalties;
import by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty.DragonPenalty;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * DSDragonPenaltyTags - Minus Version
 * 
 * [EN] All dragon penalties have been removed except for ITEM_BLACKLIST (which is empty).
 *      This tag file assigns only the empty item blacklist to all dragon species.
 * 
 * [中文] 所有龙的惩罚已被移除，仅保留物品黑名单（但黑名单本身为空）。
 *        此标签文件仅将空物品黑名单分配给所有龙种。
 * 
 * == ORIGINAL TAG ASSIGNMENTS / 原版标签分配 ==
 * 
 * Cave Dragon:
 *   - COLD_WEAKNESS (寒冷弱点)
 *   - WATER_WEAKNESS (水伤害)
 *   - ITEM_BLACKLIST (物品黑名单)
 *   - WATER_POTION_WEAKNESS (水药水弱点)
 *   - SNOWBALL_WEAKNESS (雪球弱点)
 *   - WATER_SPLASH_POTION_WEAKNESS (水喷溅药水弱点)
 *   - FEAR (动物恐惧)
 * 
 * Sea Dragon:
 *   - THIN_SKIN (脱水)
 *   - ITEM_BLACKLIST (物品黑名单)
 *   - FEAR (动物恐惧)
 * 
 * Forest Dragon:
 *   - FEAR_OF_DARKNESS (黑暗恐惧)
 *   - ITEM_BLACKLIST (物品黑名单)
 *   - FEAR (动物恐惧)
 * 
 * == NEW TAG ASSIGNMENTS / 新标签分配 ==
 * 
 * All Dragons:
 *   - ITEM_BLACKLIST only (but the blacklist is EMPTY)
 * 
 * == MERGE NOTES / 合并上游源码注意事项 ==
 * 
 * When merging upstream changes from DragonSurvivalTeam/DragonSurvival:
 * 
 * 1. If upstream adds new penalty tags, DO NOT merge them into the tag files.
 *    Keep only ITEM_BLACKLIST for all dragon species.
 * 
 * 2. If upstream modifies the penalty system structure, review carefully
 *    to ensure Minus behavior (no penalties) is preserved.
 * 
 * 3. The penalty JSON files in generated/resources have been deleted.
 *    If upstream adds new penalty JSON files, they should also be deleted.
 * 
 * == WHY THIS CHANGE / 为什么这样修改 ==
 * 
 * 1. Mod Compatibility: Penalties like FEAR break compatibility with animal
 *    mods. Environmental penalties break compatibility with biome mods.
 * 
 * 2. Player Freedom: Players should choose their challenges, not the mod.
 * 
 * 3. Simplicity: New players don't need to learn complex penalty mechanics.
 * 
 * == MAINTAINERS / 维护者 ==
 * 
 * Fork: DragonSurvival Minus
 * Repository: https://github.com/Velkzilla/DragonSurvivalMinus
 * Original: https://github.com/DragonSurvivalTeam/DragonSurvival
 */
public class DSDragonPenaltyTags extends TagsProvider<DragonPenalty> {
    
    @Translation(comments = "Cave Dragon Penalties")
    public static final TagKey<DragonPenalty> CAVE = key("cave_dragon");
    
    @Translation(comments = "Sea Dragon Penalties")
    public static final TagKey<DragonPenalty> SEA = key("sea_dragon");
    
    @Translation(comments = "Forest Dragon Penalties")
    public static final TagKey<DragonPenalty> FOREST = key("forest_dragon");

    public DSDragonPenaltyTags(final PackOutput output, final CompletableFuture<HolderLookup.Provider> provider, @Nullable final ExistingFileHelper helper) {
        super(output, DragonPenalty.REGISTRY, provider, DragonSurvival.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // [Minus] All penalties removed - only empty ITEM_BLACKLIST remains
        // This ensures dragons have no penalties while maintaining the penalty system structure
        tag(CAVE)
                .add(DragonPenalties.ITEM_BLACKLIST);

        tag(SEA)
                .add(DragonPenalties.ITEM_BLACKLIST);

        tag(FOREST)
                .add(DragonPenalties.ITEM_BLACKLIST);
    }

    public static TagKey<DragonPenalty> key(final String path) {
        return TagKey.create(DragonPenalty.REGISTRY, DragonSurvival.res(path));
    }
}
