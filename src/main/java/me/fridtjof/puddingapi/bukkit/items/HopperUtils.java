package me.fridtjof.puddingapi.bukkit.items;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HopperUtils {

    public static boolean rearrangeHopper(Inventory inventory, Material wantedMaterial) {

        //check for item
        if (!inventory.contains(wantedMaterial)) {
            return false;
        }

        //check where item is at
        int slot = 0;
        for (ItemStack itemStack : inventory.getContents()) {

            if ((itemStack != null) && (itemStack.getType() == wantedMaterial)) {
                break;
            }
            slot++;
        }

        //get itemstacks to rearrange
        ItemStack firstItemStack = inventory.getItem(0);
        ItemStack wantedItemStack = inventory.getItem(slot);

        //rearrange itemstacks
        inventory.setItem(0, wantedItemStack);
        inventory.setItem(slot, firstItemStack);

        return true;
    }
}
