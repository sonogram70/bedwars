package com.github.devil0414.bedwars.listener

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class BedWarShopListener : Listener {
    @EventHandler
    fun onPlayerClickedInventory(event: InventoryClickEvent) {
        if(event.currentItem?.type == Material.WHITE_WOOL) {
            event.isCancelled = true
            if(event.whoClicked.inventory.contains(ItemStack(Material.IRON_INGOT, 3))) {
                event.whoClicked.inventory.remove(ItemStack(Material.IRON_INGOT, 3))
                event.whoClicked.inventory.addItem(ItemStack(Material.WHITE_WOOL, 16))
            }
        }
    }
}