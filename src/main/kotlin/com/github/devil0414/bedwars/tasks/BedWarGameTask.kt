package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.listener.BedWarListener
import com.github.devil0414.bedwars.listener.BedWarShopInteractEvent
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.BedWarGame.Companion.gameStatus
import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getPluginManager
import org.bukkit.Bukkit.getScheduler
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BedWarGameTask : BedWarTask {
    private var ticks = -1
    override fun execute(): BedWarTask? {
        ++ticks
        if(ticks == 0) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.isInvisible = false
                player.gameMode = GameMode.SURVIVAL
                player.inventory.clear()
                if(player.gameMode == GameMode.SURVIVAL) {
                    player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
                }
            }
            for(red in CommandBW.red) {
                red.teleport(Location(red.world, 8.5, 4.0, -50.5))
            }
            for(blue in CommandBW.blue) {
                blue.teleport(Location(blue.world, -50.5, 4.0, 8.5))
            }
            for(green in CommandBW.green) {
                green.teleport(Location(green.world, 67.5, 4.0, 8.4))
            }
            for(yellow in CommandBW.yellow) {
                yellow.teleport(Location(yellow.world, 8.5, 4.0, 67.5))
            }
            getPluginManager().registerEvents(BedWarShopInteractEvent(), instance)
            getPluginManager().registerEvents(BedWarListener(), instance)
            getScheduler().runTaskTimer(instance, IronScheduler(), 0L, 15L)
            getScheduler().runTaskTimer(instance, GoldScheduler(), 0L, 100L)
            getScheduler().runTaskTimer(instance, DiamondScheduler(), 0L, 20L)
            getScheduler().runTaskTimer(instance, EmeraldScheduler(), 0L, 20L)
        }
        return if (gameStatus) this else BedWarResultTask()
    }
}