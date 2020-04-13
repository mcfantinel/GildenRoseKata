package com.gildedrose;

public class GildedRoseItem extends Item {

  private ItemType itemType;

  private int qualityDegradation;

  public GildedRoseItem(String name, int sellIn, int quality, ItemType itemType, int qualityDegradation) {
    super(name, sellIn, quality);
    this.itemType = itemType;
    this.qualityDegradation = qualityDegradation;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public int getQualityDegradation() {
    return qualityDegradation;
  }
}
