package com.github.devil0414.bedwars.listener

import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.*
import org.bukkit.inventory.ItemStack

class KillCountdown : Runnable {
    private var ticks = -1
    override fun run() {
        ticks++
        if(ticks == 0) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("${ChatColor.WHITE}5", null, 5, 20, 5)
                }
            }
        } else if(ticks == 20) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("${ChatColor.AQUA}4", null, 5, 20, 5)
                }
            }
        } else if(ticks == 40) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("${ChatColor.LIGHT_PURPLE}3", null, 5, 20, 5)
                }
            }
        } else if(ticks == 60) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("${ChatColor.YELLOW}2", null, 5, 20, 5)
                }
            }
        } else if(ticks == 80) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("${ChatColor.RED}1", null, 5, 20, 5)
                }
            }
        } else if(ticks == 100) {
            val world : World = Bukkit.getWorlds()[0]
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("Revive", null, 5, 20, 5)
                    player.gameMode = GameMode.SURVIVAL
                    player.isInvisible = false
                    player.allowFlight = false
                    player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
                }
            }
            for(red in CommandBW.red) {
                red.teleport(Location(world, 8.5, 4.0, -50.5))
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
        }
    }
}