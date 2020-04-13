package com.gildedrose;

public class TextTestGildedRoseItemsUpdate {

  public static void main(String[] args) {
    System.out.println("OMGHAI!");

    GildedRoseItem[] items = new GildedRoseItem[] {
      new GildedRoseItem("+5 Dexterity Vest", 10, 20, ItemType.SIMPLE_ITEM), //
      new GildedRoseItem("Aged Brie", 2, 0, ItemType.SIMPLE_ITEM, -1), //
      new GildedRoseItem("Elixir of the Mongoose", 5, 7, ItemType.SIMPLE_ITEM), //
      new GildedRoseItem("Sulfuras, Hand of Ragnaros", 0, 80, ItemType.LEGENDARY_ITEM), //
      new GildedRoseItem("Sulfuras, Hand of Ragnaros", -1, 80, ItemType.LEGENDARY_ITEM),
      new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 15, 20, ItemType.TICKETS),
      new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 10, 49, ItemType.TICKETS),
      new GildedRoseItem("Backstage passes to a TAFKAL80ETC concert", 5, 49, ItemType.TICKETS),
      // this conjured item does not work properly yet
      new GildedRoseItem("Conjured Mana Cake", 3, 6, ItemType.SIMPLE_ITEM, 2) };

    GildedRose app = new GildedRose(items);

    int days = 2;
    if (args.length > 0) {
      days = Integer.parseInt(args[0]) + 1;
    }

    for (int i = 0; i < days; i++) {
      System.out.println("-------- day " + i + " --------");
      System.out.println("name, sellIn, quality");
      for (Item item : items) {
        System.out.println(item);
      }
      System.out.println();
      app.updateQuality();
    }
  }

}
