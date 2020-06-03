package com.degroff.pandaled.ui.main.fragment.content;

import com.degroff.pandaled.ble.BLEDevice;

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
    public static final List<ListItem> ITEMS = new ArrayList<>();

    /**
     * A map of Pattern items, by ID.
     */
    public static final Map<String, ListItem> ITEM_MAP = new HashMap<>();

    //------------------------------------------------------------
    // Load Pattern List
    static
        {
        final ListItem[] patternData = new ListItem[]
                {
                        new ListItem("1", "cycle", "cycle", "Cycle All Patterns", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteCCHS", "paletteCCHS", "Palette CCHS #1", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteCCHS2", "paletteCCHS2", "Palette CCHS #w", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "solidCCHS", "solidCCHS", "Solid CCHS", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "palletteCCHSGlitter", "palletteCCHSGlitter", "CCHS Glitter", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "twoSplit", "twoSplit", "Red Blue Two Split", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "juggle", "juggle", "Juggle (Rainbow Chaser)", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "confetti", "confetti", "Confetti", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteRWB", "paletteRWB", "Red White Blue Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteCloud", "paletteCloud", "Cloud Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteLava", "paletteLava", "Lava Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteHeat", "paletteHeat", "Heat Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteOcean", "paletteOcean", "Ocean Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteForest", "paletteForest", "Forest Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "paletteChristmas", "paletteChristmas", "Christmas Palette", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "bpmParty", "bpmParty", "Party BPM", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "bpmRWB", "bpmRWB", "Red White Blue BPM", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "bpmChristmas", "bpmChristmas", "Christmas BPM", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "solidBlueGreen", "solidBlueGreen", "Solid Blue Green", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "solidBlue", "solidBlue", "Solid Blue", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "solidRed", "solidRed", "Solid Red", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "solidGreen", "solidGreen", "Solid Green", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "theaterCCHS", "theaterCCHS", "CCHS Theater", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "theaterChristmas", "theaterChristmas", "Christmas Theater", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "mixinsCCHS", "mixinsCCHS", "Mixins CCHS", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "mixinsLava", "mixinsLava", "Mixins Lava", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "mixSpecLava", "mixSpecLava", "Mixins Sparkle Lava", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "mixSpecOcean", "mixSpecOcean", "Mixins Sparkle Ocean", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "mixSpecForest", "mixSpecForest", "Mixins Sparkle Forest", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC),
                        new ListItem("1", "lightning", "lightning", "Lightning", BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC)
                };
        for ( final ListItem pi : patternData )
            {
            ITEMS.add(pi);
            ITEM_MAP.put(pi.id, pi);
            }
        }

    }
