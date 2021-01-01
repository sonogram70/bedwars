package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.inventory.ItemStack

class EmeraldScheduler: Runnable {
    private val world: World = Bukkit.getServer().worlds[0]
    private val location = Location(world, -0.4, 4.0, 8.5)
    private val location2 = Location(world, 17.5, 4.0, 8.5)
    private var ticks = 0
    private var minute = 2
    override fun run() {
        if(ticks > 9) {
            CommandBW.emerald2.updateMetadata<ArmorStand> {
                customName = "$minute : $ticks"
                isCustomNameVisible = true
            }
            CommandBW.emerald4.updateMetadata<ArmorStand> {
                customName = "$minute : $ticks"
                isCustomNameVisible = true
            }
        }
        else if(ticks <= 9) {
            CommandBW.emerald2.updateMetadata<ArmorStand> {
                customName = "$minute : 0$ticks"
                isCustomNameVisible = true
            }
            CommandBW.emerald4.updateMetadata<ArmorStand> {
                customName = "$minute : 0$ticks"
                isCustomNameVisible = true
            }
        }
        --ticks
        if(ticks == -1 && minute == 1 || ticks == -1 && minute == 2) {
            --minute
            ticks = 59
        }
        if(ticks == -1 && minute == 0) {
            world.dropItem(location, ItemStack(Material.EMERALD))
            world.dropItem(location2, ItemStack(Material.EMERALD))
            ticks = 0
            minute = 2
        }
    }
}