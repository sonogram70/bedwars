package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.plugin.BedWarPlugin
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.noonmaru.tap.fake.FakeEntityServer
import com.github.noonmaru.tap.fake.invisible
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class EmeraldScheduler: Runnable {
    private val world: World = Bukkit.getServer().worlds[0]
    private val location = Location(world, -0.4, 4.0, 8.5)
    private val location2 = Location(world, 17.5, 4.0, 8.5)
    override fun run() {
        world.dropItem(location, ItemStack(Material.EMERALD))
        world.dropItem(location2, ItemStack(Material.EMERALD))
    }
}