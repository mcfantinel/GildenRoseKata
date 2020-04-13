package com.gildedrose;

public class GildedRoseItem extends Item {

  private ItemType itemType;

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

  public ItemType getItemType() {
    return itemType;
  }

  public int getQualityDegradation() {
    return qualityDegradation;
  }

  @Override
  public String toString() {
    return this.name + ", " + this.sellIn + ", " + this.quality + ", " + this.itemType + ", " + this.qualityDegradation;
  }
}
