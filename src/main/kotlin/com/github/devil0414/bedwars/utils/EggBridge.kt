package com.github.devil0414.bedwars.utils

import com.github.noonmaru.tap.fake.FakeEntity
import com.github.noonmaru.tap.math.copy
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack

class EggBridge(internal val location: Location) {
    private val armorStands = arrayListOf<FakeEntity>()
    init {
        armorStands += FakeEntityServ.fakeEntityServer.spawnEntity(location, ArmorStand::class.java).apply {
            updateMetadata<ArmorStand> {
                isMarker = true
                isVisible = false
            }
            updateEquipment {
                helmet = ItemStack(Material.EGG)
            }
        }
    }
    fun updateLocation(offsetY: Double = 0.0, newLoc: Location = location) {
        val x = newLoc.x
        val y = newLoc.y
        val z = newLoc.z
        location.apply {
            world = newLoc.world
            this.x = x
            this.y = y
            this.z = z
        }
        val loc = newLoc.clone()
        armorStands.forEachIndexed { index, armorStand ->
            armorStand.moveTo(loc.apply {
                copy(newLoc)
                this.y += offsetY
                yaw = (360 * index).toFloat()
                pitch = 0.0F
            })
        }
    }
    fun remove() {
        armorStands.forEach { it.remove() }
        armorStands.clear()
    }
}