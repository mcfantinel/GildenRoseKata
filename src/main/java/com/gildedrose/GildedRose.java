package com.gildedrose;

class GildedRose {

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  // Updates the quality of all gilded rose items on stock
  public void updateQuality() {
    for (Item item : items) {
      GildedRoseItem gildedRoseItem;

      // If goblin still uses previous item class, we need to keep backwards compatibility
      boolean isItemFromCurrentVersion = item instanceof GildedRoseItem;
      if (isItemFromCurrentVersion) {
        gildedRoseItem = (GildedRoseItem) item;
      } else {
        gildedRoseItem = GildedRoseItem.gildedRoseItemBuilder(item);
      }

      gildedRoseItem.updateSellInAndQuality();

      if (!isItemFromCurrentVersion) {
        item.sellIn = gildedRoseItem.sellIn;
        item.quality = gildedRoseItem.quality;
      }
    }
  }
}