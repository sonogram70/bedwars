package com.github.devil0414.bedwars.listener

import com.github.devil0414.bedwars.utils.ShopInventory
import com.github.noonmaru.invfx.openWindow
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.ItemStack

class BedWarShopInteractEvent : Listener {
    @EventHandler
    fun onPlayerInteractEntity(event: PlayerInteractEntityEvent) {
        if(event.rightClicked.customName == "shopkeeper") {
            event.player.openWindow(ShopInventory.createInv())
        }
    }
}