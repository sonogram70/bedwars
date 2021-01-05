package com.github.devil0414.bedwars.utils

import com.github.noonmaru.invfx.InvFX
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ShopInventory {
    val inv = Bukkit.createInventory(null, 54, "Shop")
    fun create() {
    }
    fun createInv() = InvFX.scene(6, "Shop") {
        panel(0, 0, 9, 5) {

            button(2, 3) {
                onInit {
                    it.item = ItemStack(Material.WHITE_WOOL)
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.type == Material.WHITE_WOOL) {
                        if(event.whoClicked.inventory.containsAtLeast(ItemStack(Material.IRON_INGOT), 3)) {
                            event.whoClicked.inventory.removeItem(ItemStack(Material.IRON_INGOT, 3))
                            event.whoClicked.inventory.addItem(ItemStack(Material.WHITE_WOOL, 3))
                        }
                    }
                }
            }
            button(2, 4) {
                onInit {
                    it.item = ItemStack(Material.TNT)
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.type == Material.TNT) {
                        if(event.whoClicked.inventory.containsAtLeast(ItemStack(Material.GOLD_INGOT), 3)) {
                            event.whoClicked.inventory.removeItem(ItemStack(Material.GOLD_INGOT, 3))
                            event.whoClicked.inventory.addItem(ItemStack(Material.TNT, 1))
                            event.whoClicked.inventory.addItem(ItemStack(Material.REDSTONE_BLOCK, 1))
                        }
                    }
                }
            }
        }
    }
}