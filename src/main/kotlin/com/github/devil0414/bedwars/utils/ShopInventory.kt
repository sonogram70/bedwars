package com.github.devil0414.bedwars.utils

import com.github.noonmaru.invfx.InvFX
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ShopInventory {
    val inv = Bukkit.createInventory(null, 54, "Shop")
    fun create() {
    }
    fun createInv() = InvFX.scene(8, "Shop") {
        panel(0, 0, 9, 7) {
            button(1, 8) {
                onInit {
                    it.item = ItemStack(Material.BRICKS).apply {
                        itemMeta.setDisplayName("블럭")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "블럭") {

                    }
                }
            }
            button(2, 8) {
                onInit {
                    it.item = ItemStack(Material.BOW).apply {
                        itemMeta.setDisplayName("원거리")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "원거리") {

                    }
                }
            }
            button(3, 8) {
                onInit {
                    it.item = ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                        itemMeta.setDisplayName("갑옷")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "갑옷") {

                    }
                }
            }
            button(4, 8) {
                onInit {
                    it.item = ItemStack(Material.DIAMOND_SWORD).apply {
                        itemMeta.setDisplayName("무기")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "무기") {

                    }
                }
            }
            button(5, 8) {
                onInit {
                    it.item = ItemStack(Material.DIAMOND_AXE).apply {
                        itemMeta.setDisplayName("도구")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "도구") {

                    }
                }
            }
            button(6, 8) {
                onInit {
                    it.item = ItemStack(Material.POTION).apply {
                        itemMeta.setDisplayName("물약")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "물약") {

                    }
                }
            }
            button(7, 8) {
                onInit {
                    it.item = ItemStack(Material.TNT).apply {
                        itemMeta.setDisplayName("유틸리티")
                    }
                }
                onClick { _, _, _, event ->
                    if(event.currentItem?.itemMeta?.displayName == "유틸리티") {

                    }
                }
            }
            button(1, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(2, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(3, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(4, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(5, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(6, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }
            button(7, 1) {
                onInit {
                    it.item = ItemStack(Material.BLACK_STAINED_GLASS)
                }
            }

        }
    }
}