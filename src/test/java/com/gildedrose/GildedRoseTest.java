package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    void updateQualityByNumberOfDays(GildedRose app, int numberOfDays) {
        for(int i = 0; i < numberOfDays; i++) {
            app.updateQuality();
        }
    }

    @Test
    void itemUpdate() {
        int numberOfDays = 5;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Item", 5, 5, ItemType.SIMPLE_ITEM) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void itemQualityUpdate_NonNegativeQuality() {
        int numberOfDays = 1;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Item", 0, 0, ItemType.SIMPLE_ITEM) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void itemQualityUpdate_NegativeSellIn() {
        int numberOfDays = 2;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Item", 0, 10, ItemType.SIMPLE_ITEM) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void agedBrieUpdate_SimpleUpdate() {
        int numberOfDays = 5;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Aged Brie", 5, 10, ItemType.SIMPLE_ITEM, -1) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void agedBrieUpdate_NegativeSellIn() {
        int numberOfDays = 7;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Aged Brie", 5, 10, ItemType.SIMPLE_ITEM, -1) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void agedBrieUpdate_QualityBelow50() {
        int numberOfDays = 3;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Aged Brie", 5, 49, ItemType.SIMPLE_ITEM, -1) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void legendaryGildedRoseItemUpdate_NoChanges() {
        int numberOfDays = 10;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Sulfuras, Hand of Ragnaros", 5, 80, ItemType.LEGENDARY_ITEM) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityUpdate_SellInGreaterThan10() {
        int numberOfDays = 5;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 30, 10, ItemType.TICKETS) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, 5);
        assertEquals(25, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityUpdate_SellInBetween10And5() {
        int numberOfDays = 3;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 11, 10, ItemType.TICKETS) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(8, app.items[0].sellIn);
        assertEquals(15, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityUpdate_SellInBelow5() {
        int numberOfDays = 3;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 6, 10, ItemType.TICKETS) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(3, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityUpdate_SellIn0() {
        int numberOfDays = 2;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 2, 10, ItemType.TICKETS) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    @Test
    void backstagePassesQualityUpdate_SellInNegative() {
        int numberOfDays = 4;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 2, 10, ItemType.TICKETS) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void insertAllGildedRoseItems() {
        int numberOfDays = 1;
        GildedRoseItem simpleGildedRoseItem = new GildedRoseItem("Simple GildedRoseItem", 30, 30, ItemType.SIMPLE_ITEM, 1);
        GildedRoseItem agedBrie = new GildedRoseItem("Aged Brie", 30, 30, ItemType.SIMPLE_ITEM, -1);
        GildedRoseItem legendaryGildedRoseItem = new GildedRoseItem("Sulfuras, Hand of Ragnaros", 30, 80, ItemType.LEGENDARY_ITEM);
        GildedRoseItem backstagePass = new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 30, 30, ItemType.TICKETS);
        GildedRoseItem conjured = new GildedRoseItem("Conjured Mana Cake", 30, 30, ItemType.SIMPLE_ITEM, 2);
        GildedRoseItem[] items = new GildedRoseItem[] {
          simpleGildedRoseItem,
          agedBrie,
          legendaryGildedRoseItem,
          backstagePass,
          conjured
        };

        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);

        // Simple GildedRoseItem
        assertEquals(29, app.items[0].sellIn);
        assertEquals(29, app.items[0].quality);

        // Aged brie
        assertEquals(29, app.items[1].sellIn);
        assertEquals(31, app.items[1].quality);

        // Legendary item
        assertEquals(30, app.items[2].sellIn);
        assertEquals(80, app.items[2].quality);

        // Backstage ticket
        assertEquals(29, app.items[3].sellIn);
        assertEquals(31, app.items[3].quality);

        // Conjured ticket
        assertEquals(29, app.items[4].sellIn);
        assertEquals(27, app.items[4].quality);
    }

    @Test
    void conjuredGildedRoseItemsQualityUpdate_SimpleUpdate() {
        int numberOfDays = 5;
        GildedRoseItem[] items = new GildedRoseItem[] { new GildedRoseItem("Conjured Mana Cake", 5, 11, ItemType.SIMPLE_ITEM, 1) };
        GildedRose app = new GildedRose(items);
        updateQualityByNumberOfDays(app, numberOfDays);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

}
