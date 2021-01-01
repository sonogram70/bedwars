package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack

class DiamondScheduler: Runnable {
    private var ticks = 0
    private var minute = 1
    override fun run() {
        if(ticks > 9) {
            CommandBW.diamond2.updateMetadata<ArmorStand> {
                customName = "$minute : $ticks"
                isCustomNameVisible = true
            }
            CommandBW.diamond4.updateMetadata<ArmorStand> {
                customName = "$minute : $ticks"
                isCustomNameVisible = true
            }
        }
        else if(ticks <= 9) {
            CommandBW.diamond2.updateMetadata<ArmorStand> {
                customName = "$minute : 0$ticks"
                isCustomNameVisible = true
            }
            CommandBW.diamond4.updateMetadata<ArmorStand> {
                customName = "$minute : 0$ticks"
                isCustomNameVisible = true
            }
        }
        --ticks
        if(ticks == -1 && minute == 1) {
            minute = 0
            ticks = 59
        }
        if(ticks == -1 && minute == 0) {
            val world = Bukkit.getServer().worlds[0]
            val location = Location(world, 41.5, 17.0, -29.5)
            world.dropItem(location, ItemStack(Material.DIAMOND))
            val location2 = Location(world, 58.5, 1.0, 53.5)
            world.dropItem(location2, ItemStack(Material.DIAMOND))
            val location3 = Location(world, -30.5, 13.0, 59.4)
            world.dropItem(location3, ItemStack(Material.DIAMOND))
            val location4 = Location(world, -33.5, 9.0, -32.3)
            world.dropItem(location4, ItemStack(Material.DIAMOND))
            ticks = 0
            minute = 1
        }
    }
}