# ⚠️ DragonSurvival Minus

> **EN**: To maximize compatibility with other mods, this fork simplifies the original content by removing certain restrictions and penalties.
> **中文**: 为了最大化兼容其他 Mod，此分支对原版内容做了一些精简，移除了部分限制和惩罚机制。

---

## 🔧 Changes / 主要更改

| Category / 类别 | Original / 原版 | Minus Version / Minus 版本 |
|----------------|-----------------|--------------------------|
| **Items / 物品使用** | Dragons cannot use shields, bows, crossbows, tridents | ✅ Dragons can use all items / 龙可以使用所有物品 |
| **Animal Fear / 动物恐惧** | Animals flee from dragons (requires Beacon to fix) | ✅ Animals no longer fear dragons / 动物不再害怕龙 |
| **Environmental Damage / 环境伤害** | Cave dragons take water damage, Sea dragons dehydrate, Forest dragons get stress in darkness | ✅ No environmental penalties / 无环境伤害惩罚 |
| **Diet Restrictions / 饮食限制** | Each dragon species has specific diet, wrong food causes hunger/poison effects | ✅ All dragons can eat any food / 所有龙可以吃任何食物 |
| **Flight Hunger / 飞行饥饿** | Flight requires minimum hunger level | ⚠️ Retained (reasonable gameplay mechanic) / 保留（合理的游戏机制） |

## 📦 Build Artifacts / 构建产物

- `DragonSurvival-*-all.jar` - Full version with all dependencies included / 包含所有依赖的完整版本
- `DragonSurvival-*.jar` - Main mod file (requires manual dependency installation) / 主 Mod 文件（需要手动安装依赖）

## 🔗 Links / 相关链接

* ■ Original DragonSurvival / 原版 - https://github.com/DragonSurvivalTeam/DragonSurvival
* ■ CurseForge (Original / 原版) - https://www.curseforge.com/minecraft/mc-mods/dragons-survival

---

## 🛠️ For Maintainers / 维护者指南

### Merging Upstream Changes / 合并上游源码

**⚠️ IMPORTANT / 重要：** After merging from upstream (DragonSurvivalTeam/DragonSurvival), you MUST regenerate data files:

**⚠️ 注意：** 从上游合并后，必须重新生成数据文件：

```bash
# 1. Merge upstream changes
git merge upstream/1.21.1

# 2. Fix any conflicts in these files:
#    - DragonPenalties.java
#    - DietEntryProvider.java
#    - DSDragonPenaltyTags.java
#    - DSItemTags.java (tagDragonSpeciesFood method)

# 3. Regenerate data files (CRITICAL!)
./gradlew runData

# 4. Verify generated files:
#    - src/generated/resources/data/dragonsurvival/data_maps/dragonsurvival/dragon_species/diet_entries.json
#      Should show: "items": "#c:foods" for all dragons
#    - src/generated/resources/data/dragonsurvival/tags/dragonsurvival/dragon_penalty/*.json
#      Should show: only "item_blacklist" for all dragons

# 5. Build and test
./gradlew build
```

### Common Pitfalls / 常见坑

1. **Diet entries not updating / 饮食条目未更新**
   - Symptom: Dragons still get hunger/poison from certain foods
   - Cause: Forgot to run `runData` after merging
   - Fix: Run `./gradlew runData` and check generated JSON files

2. **Penalties still active / 惩罚仍然生效**
   - Symptom: Animals still flee, dragons still take water damage
   - Cause: Old penalty JSON files not deleted or regenerated
   - Fix: Delete old penalty JSONs and run `./gradlew runData`

3. **Compilation errors after merge / 合并后编译错误**
   - Symptom: Cannot find symbols like COLD_WEAKNESS, FEAR, etc.
   - Cause: Upstream code references removed penalties
   - Fix: Check these files for penalty references:
     - `DSDragonPenaltyTags.java`
     - `DSItemTags.java`
     - Any new files added by upstream

4. **Server config overriding defaults / 服务器配置覆盖默认值**
   - Note: `dragonsurvival-server.toml` can override some behaviors
   - Players may need to delete old config files after updating

### Files Modified by Minus / Minus 修改的文件

**Core Logic / 核心逻辑:**
- `src/main/java/.../registry/dragon/penalty/DragonPenalties.java`
- `src/main/java/.../registry/datagen/data_maps/DietEntryProvider.java`
- `src/main/java/.../registry/datagen/tags/DSDragonPenaltyTags.java`
- `src/main/java/.../registry/datagen/tags/DSItemTags.java`

**Generated Data / 生成数据:**
- `src/generated/resources/data/dragonsurvival/data_maps/dragonsurvival/dragon_species/diet_entries.json`
- `src/generated/resources/data/dragonsurvival/tags/dragonsurvival/dragon_penalty/cave_dragon.json`
- `src/generated/resources/data/dragonsurvival/tags/dragonsurvival/dragon_penalty/forest_dragon.json`
- `src/generated/resources/data/dragonsurvival/tags/dragonsurvival/dragon_penalty/sea_dragon.json`
- `src/generated/resources/data/dragonsurvival/tags/item/cave_dragon_food.json`
- `src/generated/resources/data/dragonsurvival/tags/item/forest_dragon_food.json`
- `src/generated/resources/data/dragonsurvival/tags/item/sea_dragon_food.json`

### Testing Checklist / 测试清单

After any merge or change, verify:

- [ ] Dragons can eat bread without hunger/poison effects
- [ ] Dragons can equip shields, bows, crossbows, tridents
- [ ] Animals don't flee from dragons
- [ ] Cave dragons can swim in water without taking damage
- [ ] Sea dragons don't dehydrate on land
- [ ] Forest dragons don't get STRESS effect in darkness
- [ ] Flight still requires hunger threshold (intentional)

---

# DRAGONS SURVIVAL (Original Project / 原项目)

* ■ Patreon - https://www.patreon.com/blackaures
* ■ Сurseforge (download) - https://www.curseforge.com/minecraft/mc-mods/dragons-survival
* ■ Discord Server - http://discord.gg/8SsB8ar
* ■ Youtube - https://www.youtube.com/channel/UCasTlG0nSSQPPvk4mrmoWLw
* ■ TODO list Trello: https://trello.com/b/Zs7uCDpo/dragon-survival-board

# Wiki 

* ■ ENG: https://github.com/DragonSurvivalTeam/DragonSurvival/wiki
* ■ ZH:  https://dragons-survival.fandom.com/zh/wiki/龙之生存_Wiki

***

<p align="center">
  <img src="https://github.com/DragonSurvivalTeam/DragonSurvival/assets/43911230/d8ae8006-de1f-4efb-a8f8-18e3c9c3f615" />
</p>

***

# Project Description

■ This project is creating a global modification that adds the ability to turn into a dragon and all the content associated with it. Details about the project are in [Wiki](https://github.com/DragonSurvivalTeam/DragonSurvival/wiki). 

■ *I've always liked dragons and this wonderful game, but I've been playing alone and without modifications my whole life, until I accidentally came across this cultural phenomenon in 2020. I was fascinated by the amount of player-created content. There were so many mods created by them, many of which had to do with dragons. You could tame them, breed them, ride them, kill them, but in none of them could you **BE a dragon**. This really upset me. Being an artist very far from programming, modding and the minecraft community, I decided to take a risk and create my own mod. I had absolutely no knowledge or friends in this field. The only thing I had was a dream and the support of a community of dragon lovers. That's about how I became the developer of Dragons Survival. Join us to help develop this project together!*

(c) **Black Aures**

***

<p align="center">
  <img src="https://github.com/DragonSurvivalTeam/DragonSurvival/assets/43911230/850db1d4-2e20-4bf0-8357-6176654bd91b" />
</p>

***


# Contribution rules

■  I welcome any help and would be very happy if you decided to participate. A few **small rules** to make it easier for us to work and for you not to do the work for nothing. 

* Don't change anything globally and don't do meaningless formatting fixes. The mod works correctly and this is the most important thing. If you plan to add a lot of new content, consult with me before doing so.

* Test everything thoroughly. Separately on the LAN and separately on the Dedicated server. These are not the same thing.  

* Don't touch the skin system. Do not even think about changing it. This is an extremely important part of the mod, which I will not allow to break for the sake of dubious bonuses.

* Try to keep as much compatibility with other mods and flexibility in customization as possible. The more configs that are available, the better! 

* Preferably do not affect the gameplay of human players and do not break the balance. Dragons should be only slightly stronger, not all-powerful.

***

■  All other information is better seen on the [Wiki](https://github.com/DragonSurvivalTeam/DragonSurvival/wiki).

***


<p align="center">
  <img src="https://github.com/DragonSurvivalTeam/DragonSurvival/assets/43911230/9dd48f1c-3e42-435d-bbe3-67e05360d222" />
</p>

***
