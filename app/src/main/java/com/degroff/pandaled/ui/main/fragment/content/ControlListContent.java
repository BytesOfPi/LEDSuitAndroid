package com.degroff.pandaled.ui.main.fragment.content;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlListContent
    {
    /**
     * An array of Pattern items.
     */
    public static final List<ListItem> OUTFIT_ITEMS = new ArrayList<>();
    public static final List<ListItem> SUIT_ITEMS = new ArrayList<>();
    public static final List<ListItem> MATRIX_ITEMS = new ArrayList<>();
    public static final List<ListItem> CAPE_ITEMS = new ArrayList<>();
    public static final List<ListItem> PAL_ITEMS = new ArrayList<>();
    public static final List<ListItem> COL_ITEMS = new ArrayList<>();

    /**
     * A map of Pattern items, by ID.
     */
    public static final Map<String, ListItem> ITEM_MAP = new HashMap<>();

    //------------------------------------------------------------
    // Load Pattern List
    static
        {
        final ListItem[] patternOutfitData = new ListItem[]
                {
                        new ListItem("0", "cycle", "Cycle", "Cycle All Patterns", R.drawable.ic_cycle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("0", "bpm", "BPM", "Beats Per minute pattern", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("0", "CCHS", "CCHS", "CCHS color pattern", R.drawable.ic_cchs, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("0", "juggle", "Juggle", "Juggling pattern", R.drawable.ic_juggle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("0", "bts", "BTS", "BTS name pattern", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        final ListItem[] patternSuitData = new ListItem[]
                {
                        new ListItem("1", "cycle", "cycle", "Cycle All Patterns", R.drawable.ic_cycle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "palette", "palette", "Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "paletteGlitter", "paletteGlitter", "Palette Glitter", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "twoSplit", "twoSplit", "Red Blue Two Split", R.drawable.ic_twosplit, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "juggle", "juggle", "Juggle (Rainbow Chaser)", R.drawable.ic_juggle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "confetti", "confetti", "Confetti", R.drawable.ic_sparkle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "bpm", "bpm", "BPM", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "solid", "solid", "Solid Color", R.drawable.ic_brush, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "theater", "theater", "Theater", R.drawable.ic_theater, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "mixins", "mixins", "Mixins", R.drawable.ic_run, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("1", "mixSpec", "mixSpec", "Mixins Sparkle", R.drawable.ic_sparkle, BLEDevice.BLE_SEND_CHARACTERISTIC)
                        // new ListItem("1", "paletteCCHS", "paletteCCHS", "Palette CCHS #1", R.drawable.ic_cchs, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteCCHS2", "paletteCCHS2", "Palette CCHS #w", R.drawable.ic_cchs, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "solidCCHS", "solidCCHS", "Solid CCHS", R.drawable.ic_cchs, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "palletteCCHSGlitter", "palletteCCHSGlitter", "CCHS Glitter", R.drawable.ic_cchs, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "twoSplit", "twoSplit", "Red Blue Two Split", R.drawable.ic_twosplit, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "juggle", "juggle", "Juggle (Rainbow Chaser)", R.drawable.ic_juggle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "confetti", "confetti", "Confetti", R.drawable.ic_confetti, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteRWB", "paletteRWB", "Red White Blue Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteCloud", "paletteCloud", "Cloud Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteLava", "paletteLava", "Lava Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteHeat", "paletteHeat", "Heat Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteOcean", "paletteOcean", "Ocean Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteForest", "paletteForest", "Forest Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "paletteChristmas", "paletteChristmas", "Christmas Palette", R.drawable.ic_palette, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "bpmParty", "bpmParty", "Party BPM", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "bpmRWB", "bpmRWB", "Red White Blue BPM", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "bpmChristmas", "bpmChristmas", "Christmas BPM", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "solidBlue", "solidBlue", "Solid Blue", R.drawable.ic_brush, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "solidRed", "solidRed", "Solid Red", R.drawable.ic_brush, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "solidGreen", "solidGreen", "Solid Green", R.drawable.ic_brush, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "theaterCCHS", "theaterCCHS", "CCHS Theater", R.drawable.ic_theater, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "theaterChristmas", "theaterChristmas", "Christmas Theater", R.drawable.ic_theater, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "mixinsCCHS", "mixinsCCHS", "Mixins CCHS", R.drawable.ic_run, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "mixinsLava", "mixinsLava", "Mixins Lava", R.drawable.ic_run, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "mixSpecLava", "mixSpecLava", "Mixins Sparkle Lava", R.drawable.ic_gear, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "mixSpecOcean", "mixSpecOcean", "Mixins Sparkle Ocean", R.drawable.ic_gear, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        // new ListItem("1", "mixSpecForest", "mixSpecForest", "Mixins Sparkle Forest", R.drawable.ic_gear, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        final ListItem[] patternMatrixData = new ListItem[]
                {
                        new ListItem("2", "cycle", "Cycle", "Cycle between patterns", R.drawable.ic_cycle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternFire", "Fire", "Raging fire on matrix", R.drawable.ic_fire, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternWave", "Wave", "Random wave patterns", R.drawable.ic_wave, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternSpiro", "Spiro", "Multiple spiraling light trails", R.drawable.ic_yinyang, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternPlasma", "Plasma", "Flowing Light square", R.drawable.ic_plasma, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternSwirl", "Swirl", "6 circles swirling center/corners", R.drawable.ic_drift, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternSpin", "Spin", "Two dots circling faster", R.drawable.ic_twosplit, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternPulse", "Pulse", "Firework Pattern", R.drawable.ic_pulse, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternDrift", "Drift", "Seven circles rotating at different speeds", R.drawable.ic_drift, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("2", "PatternBTS", "BTS", "BTS", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        final ListItem[] patternCapeData = new ListItem[]
                {
                        new ListItem("3", "cycle", "Cycle", "Cycle between patterns", R.drawable.ic_cycle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "lightsaber", "Lightsaber", "Green Lightsaber", R.drawable.ic_icons8_lightsaber, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "sparkle", "Sparkle", "Sparkle", R.drawable.ic_sparkle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "extend", "Extend", "Lights extending across cape", R.drawable.ic_meausre, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "juggle", "Juggle", "Juggle across cape", R.drawable.ic_juggle, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "bpm", "BPM", "Beats per minute", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("3", "fire", "Fire", "Fire", R.drawable.ic_fire, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        final ListItem[] paletteData = new ListItem[]
                {
                        new ListItem("4", "Party", "Party", "Party", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Cloud", "Cloud", "Cloud", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Lava", "Lava", "Lava", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Ocean", "Ocean", "Ocean", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Forest", "Forest", "Forest", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Rainbow", "Rainbow", "Rainbow", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Heat", "Heat", "Heat", R.drawable.ic_fire, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "CCHS", "CCHS", "CCHS", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "RWB", "RWB", "RWB", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Christmas", "Christmas", "Christmas", R.drawable.ic_bts, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        final ListItem[] colorData = new ListItem[]
                {
                        new ListItem("4", "Red", "Red", "Red", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Orange", "Orange", "Orange", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Yellow", "Yellow", "Yellow", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Green", "Green", "Green", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Blue", "Blue", "Blue", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Violet", "Violet", "Violet", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC),
                        new ListItem("4", "Ivory", "Ivory", "Ivory", R.drawable.ic_music_note, BLEDevice.BLE_SEND_CHARACTERISTIC)
                };
        for ( final ListItem pi : patternOutfitData )
            {
            OUTFIT_ITEMS.add(pi);
            ITEM_MAP.put(pi.id, pi);
            }
        for ( final ListItem pi : patternSuitData )
            {
            SUIT_ITEMS.add(pi);
            }
        for ( final ListItem pi : patternMatrixData )
            {
            MATRIX_ITEMS.add(pi);
            }
        for ( final ListItem pi : patternCapeData )
            {
            CAPE_ITEMS.add(pi);
            }
        for ( final ListItem pi : paletteData )
            {
            PAL_ITEMS.add(pi);
            }
        for ( final ListItem pi : colorData )
            {
            COL_ITEMS.add(pi);
            }

        }
    }
