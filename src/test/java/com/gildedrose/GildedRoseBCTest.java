package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class created to keep previous testing scenarios that use deprecated classes from the application
 * This set of tests are executing to make sure we keep backwards compatibility with previous versions
 */
public class GildedRoseBCTest {
  void updateQualityByNumberOfDays(GildedRose app, int numberOfDays) {
    for(int i = 0; i < numberOfDays; i++) {
      app.updateQuality();
    }
  }

  @Test
  void itemUpdate() {
    int numberOfDays = 5;
    Item[] items = new Item[] { new Item("Item", 5, 5) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(0, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void itemQualityUpdate_NonNegativeQuality() {
    int numberOfDays = 1;
    Item[] items = new Item[] { new Item("Item", 0, 0) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void itemQualityUpdate_NegativeSellIn() {
    int numberOfDays = 2;
    Item[] items = new Item[] { new Item("Item", 0, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(-2, app.items[0].sellIn);
    assertEquals(6, app.items[0].quality);
  }

  @Test
  void agedBrieUpdate_SimpleUpdate() {
    int numberOfDays = 5;
    Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(0, app.items[0].sellIn);
    assertEquals(15, app.items[0].quality);
  }

  @Test
  void agedBrieUpdate_NegativeSellIn() {
    int numberOfDays = 7;
    Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(-2, app.items[0].sellIn);
    assertEquals(19, app.items[0].quality);
  }

  @Test
  void agedBrieUpdate_QualityBelow50() {
    int numberOfDays = 3;
    Item[] items = new Item[] { new Item("Aged Brie", 5, 49) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(50, app.items[0].quality);
  }

  @Test
  void legendaryItemUpdate_NoChanges() {
    int numberOfDays = 10;
    Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 5, 80) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(5, app.items[0].sellIn);
    assertEquals(80, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityUpdate_SellInGreaterThan10() {
    int numberOfDays = 5;
    Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 30, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, 5);
    assertEquals(25, app.items[0].sellIn);
    assertEquals(15, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityUpdate_SellInBetween10And5() {
    int numberOfDays = 3;
    Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(8, app.items[0].sellIn);
    assertEquals(15, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityUpdate_SellInBelow5() {
    int numberOfDays = 3;
    Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(3, app.items[0].sellIn);
    assertEquals(18, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityUpdate_SellIn0() {
    int numberOfDays = 2;
    Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 2, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(0, app.items[0].sellIn);
    assertEquals(16, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityUpdate_SellInNegative() {
    int numberOfDays = 4;
    Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 2, 10) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(-2, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void insertAllItems() {
    int numberOfDays = 1;
    Item simpleItem = new Item("Simple Item", 30, 30);
    Item agedBrie = new Item("Aged Brie", 30, 30);
    Item legendaryItem = new Item("Sulfuras, Hand of Ragnaros", 30, 80);
    Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 30, 30);
    Item conjured = new Item("Conjured", 30, 30);
    Item[] items = new Item[] {
      simpleItem,
      agedBrie,
      legendaryItem,
      backstagePass,
      conjured
    };

    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);

    // Simple Item
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
  void conjuredItemsQualityUpdate_SimpleUpdate() {
    int numberOfDays = 5;
    Item[] items = new Item[] { new Item("Conjured", 5, 11) };
    GildedRose app = new GildedRose(items);
    updateQualityByNumberOfDays(app, numberOfDays);
    assertEquals(0, app.items[0].sellIn);
    assertEquals(1, app.items[0].quality);
  }
}
