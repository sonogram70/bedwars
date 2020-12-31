package com.github.devil0414.bedwars.tasks

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class IronScheduler : Runnable {
    override fun run() {
        val world = Bukkit.getServer().worlds[0]
        val location = Location(world, 8.4, 3.0, -57.7)
        world.dropItem(location, ItemStack(Material.IRON_INGOT))
        val location2 = Location(world, 74.5, 3.0, 8.5)
        world.dropItem(location2, ItemStack(Material.IRON_INGOT))
        val location3 = Location(world, 8.4, 3.0, 74.5)
        world.dropItem(location3, ItemStack(Material.IRON_INGOT))
        val location4 = Location(world, -57.5, 3.0, 8.5)
        world.dropItem(location4, ItemStack(Material.IRON_INGOT))
    }
}