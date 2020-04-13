package com.gildedrose;

/**
 * Class created to model gilded rose items
 */
public class GildedRoseItem extends Item {

  private ItemType itemType;

  // It quantifies how much an item should degrade its quality per update
  // A negative quality degradation means that the item gains quality over an update
  // This is only considered for items with type SIMPLE_ITEM
  private int qualityDegradation;

  public GildedRoseItem(String name, int sellIn, int quality, ItemType itemType, int qualityDegradation) {
    super(name, sellIn, quality);
    this.itemType = itemType;
    this.qualityDegradation = qualityDegradation;
  }

  public GildedRoseItem(String name, int sellIn, int quality, ItemType itemType) {
    super(name, sellIn, quality);
    this.itemType = itemType;
    this.qualityDegradation = 1;
  }

  // Builds a GildedRoseItem from an Item class
  public static GildedRoseItem gildedRoseItemBuilder(Item item) {
    String itemName = item.name;
    ItemType itemType = GildedRoseItem.getItemTypeByItemName(itemName);
    int qualityDegradation = GildedRoseItem.getQualityDegradationByItemName(itemName);
    return new GildedRoseItem(itemName, item.sellIn, item.quality, itemType, qualityDegradation);
  }

  private static ItemType getItemTypeByItemName(String itemName) {
    switch (itemName) {
      case "Sulfuras, Hand of Ragnaros":
        return ItemType.LEGENDARY_ITEM;
      case "Backstage passes to a TAFKAL80ETC concert":
        return ItemType.TICKETS;
      default:
        return ItemType.SIMPLE_ITEM;
    }
  }

  private static int getQualityDegradationByItemName(String itemName) {
    switch (itemName) {
      case "Aged Brie":
        return -1;
      case "Conjured Mana Cake":
        return 2;
      default:
        return 1;
    }
  }

  public ItemType getItemType() {
    return itemType;
  }

  public int getQualityDegradation() {
    return qualityDegradation;
  }

  public int getQuality() {
    return this.quality;
  }

  private void setQuality(int qualityValue) {
    if (qualityValue < 0) {
      quality = 0;
    } else if (qualityValue > 50) {
      quality = 50;
    } else {
      quality = qualityValue;
    }
  }

  public int getSellIn() {
    return this.sellIn;
  }

  private void setSellIn(int sellIn) {
    this.sellIn = sellIn;
  }

  public void updateSellInAndQuality() {
    this.updateSellIn();
    this.updateQuality();
  }

  private void updateQuality() {
    switch (getItemType()) {
      case LEGENDARY_ITEM:
        break;
      case SIMPLE_ITEM:
        int sellInFactor = getSellIn() >= 0 ? 1 : 2;
        setQuality(getQuality() - (getQualityDegradation() * sellInFactor));
        ;
        break;
      case TICKETS:
        if (getSellIn() >= 10) {
          setQuality(getQuality() + 1);
        } else if (getSellIn() >= 5) {
          setQuality(getQuality() + 2);
        } else if (getSellIn() >= 0) {
          setQuality(getQuality() + 3);
        } else {
          setQuality(0);
        }
        break;
    }
  }

  private void updateSellIn() {
    if (!getItemType().equals(ItemType.LEGENDARY_ITEM)) {
      setSellIn(getSellIn() - 1);
    }
  }

  @Override
  public String toString() {
    return this.name + ", " + this.sellIn + ", " + this.quality + ", " + this.itemType + ", " + this.qualityDegradation;
  }
}
