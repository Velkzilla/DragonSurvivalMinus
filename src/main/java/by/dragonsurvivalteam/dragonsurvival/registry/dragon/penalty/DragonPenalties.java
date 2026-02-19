package by.dragonsurvivalteam.dragonsurvival.registry.dragon.penalty;

import by.dragonsurvivalteam.dragonsurvival.DragonSurvival;
import by.dragonsurvivalteam.dragonsurvival.registry.datagen.Translation;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Optional;

/**
 * DragonPenalties - Minus Version
 * 
 * [EN] All unnecessary restrictions and penalties have been removed to maximize compatibility with other mods.
 *      Users can configure their own gameplay experience without being forced into specific mechanics.
 * 
 * [中文] 为了最大化兼容其他 Mod，所有不必要的限制和惩罚机制已被移除。
 *        用户可以自由配置游戏体验，而不被强制绑定到特定机制上。
 * 
 * == REMOVED PENALTIES / 移除的惩罚 ==
 * 
 * 1. COLD_WEAKNESS (洞穴龙寒冷弱点)
 *    - Original: Cave dragons take damage in snow/rain
 *    - Reason: Environmental damage limits player choice and reduces mod compatibility
 * 
 * 2. WATER_WEAKNESS (洞穴龙水伤害)
 *    - Original: Cave dragons take damage from water, water potions, snowballs
 *    - Reason: Prevents normal gameplay in water biomes, conflicts with many mods
 * 
 * 3. THIN_SKIN (海洋龙脱水)
 *    - Original: Sea dragons dehydrate and take damage when out of water
 *    - Reason: Forces players to stay in water, limits exploration and cross-mod interaction
 * 
 * 4. FEAR_OF_DARKNESS (森林龙黑暗恐惧)
 *    - Original: Forest dragons get STRESS effect in low light
 *    - Reason: Artificial difficulty, conflicts with mods that change lighting
 * 
 * 5. WATER_POTION_WEAKNESS, WATER_SPLASH_POTION_WEAKNESS, SNOWBALL_WEAKNESS
 *    - Original: Various water-based damage triggers
 *    - Reason: Inconsistent mechanics, breaks potion/snowball mod compatibility
 * 
 * 6. FEAR (动物恐惧)
 *    - Original: Animals flee from dragons, requires Dragon Beacon to fix
 *    - Reason: Forces players to build specific structures, limits animal interaction mods
 * 
 * == RETAINED / 保留的机制 ==
 * 
 * 1. ITEM_BLACKLIST (物品黑名单)
 *    - Now EMPTY by default - dragons can use all items
 *    - Kept for potential future configuration options
 * 
 * 2. Flight Hunger (飞行饥饿)
 *    - Located in ServerFlightHandler.java
 *    - Reason: Reasonable gameplay mechanic, not an artificial restriction
 * 
 * == MERGE NOTES / 合并上游源码注意事项 ==
 * 
 * When merging upstream changes from DragonSurvivalTeam/DragonSurvival:
 * 
 * 1. If new penalties are added upstream, DO NOT merge them unless they are gameplay-critical.
 *    Add a comment explaining why each new penalty was rejected.
 * 
 * 2. If upstream modifies penalty-related code, review carefully to ensure Minus behavior is preserved.
 * 
 * 3. The DEFAULT_COMMON_BLACKLIST should remain empty unless there's a game-breaking item.
 *    Any additions must be justified with clear documentation.
 * 
 * 4. ServerFlightHandler.flightHungerThreshold is intentionally NOT modified.
 *    Do not change this unless players specifically request it.
 * 
 * == MAINTAINERS / 维护者 ==
 * 
 * Fork: DragonSurvival Minus
 * Repository: https://github.com/Velkzilla/DragonSurvivalMinus
 * Original: https://github.com/DragonSurvivalTeam/DragonSurvival
 */
public class DragonPenalties {
    
    /**
     * Item Blacklist penalty key.
     * The blacklist itself is EMPTY - dragons can use all items including shields, bows, crossbows, tridents, etc.
     */
    @Translation(type = Translation.Type.PENALTY_DESCRIPTION, comments = "■ No penalties.")
    @Translation(type = Translation.Type.PENALTY, comments = "Item Blacklist")
    public static final ResourceKey<DragonPenalty> ITEM_BLACKLIST = DragonPenalties.key("item_blacklist");

    /**
     * Register all dragon penalties.
     * 
     * [EN] Only ITEM_BLACKLIST is registered, but the blacklist is empty.
     *      All other penalties have been intentionally removed.
     * 
     * [中文] 仅注册物品黑名单惩罚，但黑名单本身为空。
     *        所有其他惩罚已被有意移除。
     */
    public static void registerPenalties(final BootstrapContext<DragonPenalty> context) {
        // [Minus] Only register empty item blacklist - all other penalties removed for mod compatibility
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

    /**
     * [EN] Empty item blacklist - dragons can use ALL items by default.
     *      This includes: shields, bows, crossbows, tridents, elytra, etc.
     * 
     * [中文] 空物品黑名单 - 龙默认可以使用所有物品。
     *        包括：盾牌、弓、弩、三叉戟、鞘翅等。
     * 
     * == DO NOT ADD ITEMS HERE UNLESS ABSOLUTELY NECESSARY ==
     * == 除非绝对必要，否则不要在此添加物品 ==
     * 
     * Format: "modid:item" or "#namespace:tag"
     * 
     * Examples of what NOT to add:
     * - "#forge:tools/shield" - Shields should be usable by dragons
     * - "#forge:tools/bow" - Bows should be usable by dragons
     * - "minecraft:trident" - Tridents should be usable by dragons
     * - ".*:.*?elytra.*" - Elytra should be usable by dragons
     */
    public static final List<String> DEFAULT_COMMON_BLACKLIST = List.of();
}
