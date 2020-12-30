package com.github.devil0414.bedwars.tasks

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class DiamondScheduler: Runnable {
    override fun run() {
        val world = Bukkit.getServer().worlds[0]
        val location = Location(world, 8.4, 3.0, -57.7)
        world.dropItem(location, ItemStack(Material.DIAMOND))
    }
}