package com.degroff.pandaled.ui.main.fragment.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PatternListContent
    {
    /**
     * An array of Pattern items.
     */
    public static final List<PatternItem> ITEMS = new ArrayList<>();

    /**
     * A map of Pattern items, by ID.
     */
    public static final Map<String, PatternItem> ITEM_MAP = new HashMap<>();

    //------------------------------------------------------------
    // Load Pattern List
    static
        {
        final PatternItem[] patternData = new PatternItem[]
                {
                        new PatternItem("cycle", "cycle", "Cycle All Patterns"),
                        new PatternItem("paletteCCHS", "paletteCCHS", "Palette CCHS #1"),
                        new PatternItem("paletteCCHS2", "paletteCCHS2", "Palette CCHS #w"),
                        new PatternItem("solidCCHS", "solidCCHS", "Solid CCHS"),
                        new PatternItem("palletteCCHSGlitter", "palletteCCHSGlitter", "CCHS Glitter"),
                        new PatternItem("twoSplit", "twoSplit", "Red Blue Two Split"),
                        new PatternItem("juggle", "juggle", "Juggle (Rainbow Chaser)"),
                        new PatternItem("confetti", "confetti", "Confetti"),
                        new PatternItem("paletteRWB", "paletteRWB", "Red White Blue Palette"),
                        new PatternItem("paletteCloud", "paletteCloud", "Cloud Palette"),
                        new PatternItem("paletteLava", "paletteLava", "Lava Palette"),
                        new PatternItem("paletteHeat", "paletteHeat", "Heat Palette"),
                        new PatternItem("paletteOcean", "paletteOcean", "Ocean Palette"),
                        new PatternItem("paletteForest", "paletteForest", "Forest Palette"),
                        new PatternItem("paletteChristmas", "paletteChristmas", "Christmas Palette"),
                        new PatternItem("bpmParty", "bpmParty", "Party BPM"),
                        new PatternItem("bpmRWB", "bpmRWB", "Red White Blue BPM"),
                        new PatternItem("bpmChristmas", "bpmChristmas", "Christmas BPM"),
                        new PatternItem("solidBlueGreen", "solidBlueGreen", "Solid Blue Green"),
                        new PatternItem("solidBlue", "solidBlue", "Solid Blue"),
                        new PatternItem("solidRed", "solidRed", "Solid Red"),
                        new PatternItem("solidGreen", "solidGreen", "Solid Green"),
                        new PatternItem("theaterCCHS", "theaterCCHS", "CCHS Theater"),
                        new PatternItem("theaterChristmas", "theaterChristmas", "Christmas Theater"),
                        new PatternItem("mixinsCCHS", "mixinsCCHS", "Mixins CCHS"),
                        new PatternItem("mixinsLava", "mixinsLava", "Mixins Lava"),
                        new PatternItem("mixSpecLava", "mixSpecLava", "Mixins Sparkle Lava"),
                        new PatternItem("mixSpecOcean", "mixSpecOcean", "Mixins Sparkle Ocean"),
                        new PatternItem("mixSpecForest", "mixSpecForest", "Mixins Sparkle Forest"),
                        new PatternItem("lightning", "lightning", "Lightning")
                };
        for ( final PatternItem pi : patternData )
            {
            ITEMS.add(pi);
            ITEM_MAP.put(pi.id, pi);
            }
        }

    /**
     * A Pattern item representing a LED Pattern.
     */
    public static class PatternItem
        {
        public final String id;
        public final String content;
        public final String details;

        public PatternItem(final String id, final String content, final String details)
            {
            this.id = id;
            this.content = content;
            this.details = details;
            }

        @Override
        public String toString()
            {
            return content;
            }
        }
    }
