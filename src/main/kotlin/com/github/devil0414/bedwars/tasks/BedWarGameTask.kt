package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.listener.BedWarListener
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.BedWarGame.Companion.gameStatus
import com.github.devil0414.bedwars.listener.BedWarShopInteractEvent
import com.github.devil0414.bedwars.listener.BedWarShopListener
import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getPluginManager
import org.bukkit.Bukkit.getScheduler
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player

class BedWarGameTask : BedWarTask {
    private var ticks = -1
    override fun execute(): BedWarTask? {
        ++ticks
        if(ticks == 0) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.isInvisible = false
                player.gameMode = GameMode.SURVIVAL
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
            getPluginManager().registerEvents(BedWarShopListener(), instance)
            getPluginManager().registerEvents(BedWarListener(), instance)
            getScheduler().runTaskTimer(instance, IronScheduler(), 0L, 15L)
            getScheduler().runTaskTimer(instance, GoldScheduler(), 0L, 100L)
            getScheduler().runTaskTimer(instance, DiamondScheduler(), 0L, 1200L)
            getScheduler().runTaskTimer(instance, EmeraldScheduler(), 0L, 1800L)
        }
        if(CommandBW.red.count() == 0 && CommandBW.blue.count() == 0 && CommandBW.green.count() == 0 || CommandBW.blue.count() == 0 && CommandBW.green.count() == 0 && CommandBW.yellow.count() == 0 || CommandBW.red.count() == 0 && CommandBW.green.count() == 0 && CommandBW.yellow.count() == 0 || CommandBW.red.count() == 0 && CommandBW.blue.count() == 0 && CommandBW.yellow.count() == 0) {
            gameStatus = false
        }
        return if (gameStatus) this else BedWarResultTask()
    }
}