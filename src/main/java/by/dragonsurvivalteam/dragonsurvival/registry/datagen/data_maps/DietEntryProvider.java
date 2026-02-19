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

public class DietEntryProvider extends DataMapProvider {
    public DietEntryProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        // All dragons can eat any food - no dietary restrictions
        List<DietEntry> universalDiet = List.of(
                DietEntry.create(Tags.Items.FOODS).build()
        );

        builder(DSDataMaps.DIET_ENTRIES)
                .add(BuiltInDragonSpecies.CAVE_DRAGON, universalDiet, false, DSConditions.CAVE_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.FOREST_DRAGON, universalDiet, false, DSConditions.FOREST_DRAGON_LOADED)
                .add(BuiltInDragonSpecies.SEA_DRAGON, universalDiet, false, DSConditions.SEA_DRAGON_LOADED);
    }
}
