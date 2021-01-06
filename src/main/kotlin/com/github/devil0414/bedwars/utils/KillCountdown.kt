package com.github.devil0414.bedwars.utils

import com.github.devil0414.bedwars.process.CommandBW.blue
import com.github.devil0414.bedwars.process.CommandBW.green
import com.github.devil0414.bedwars.process.CommandBW.red
import com.github.devil0414.bedwars.process.CommandBW.yellow
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KillCountdown(private val player: Player) : Runnable {
    private var ticks = -1
    override fun run() {
        ticks++
        if(ticks == 0) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("${ChatColor.WHITE}5", null, 5, 20, 5)
            }
        } else if(ticks == 20) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("${ChatColor.AQUA}4", null, 5, 20, 5)
            }
        } else if(ticks == 40) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("${ChatColor.LIGHT_PURPLE}3", null, 5, 20, 5)
            }
        } else if(ticks == 60) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("${ChatColor.YELLOW}2", null, 5, 20, 5)
            }
        } else if(ticks == 80) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("${ChatColor.RED}1", null, 5, 20, 5)
            }
        } else if(ticks == 100) {
            val world : World = Bukkit.getWorlds()[0]
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("Revive", null, 5, 20, 5)
                player.gameMode = GameMode.SURVIVAL
                player.isInvisible = false
                player.allowFlight = false
                player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
                when {
                    red.contains(player) -> {
                        player.teleport(Location(world, 8.5, 4.0, -50.5))
                    }
                    blue.contains(player) -> {
                        player.teleport(Location(player.world, -50.5, 4.0, 8.5))
                    }
                    green.contains(player) -> {
                        player.teleport(Location(player.world, 67.5, 4.0, 8.4))
                    }
                    yellow.contains(player) -> {
                        player.teleport(Location(player.world, 8.5, 4.0, 67.5))
                    }
                }
            }
        }
    }
}