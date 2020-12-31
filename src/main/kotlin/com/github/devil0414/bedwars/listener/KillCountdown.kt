package com.github.devil0414.bedwars.listener

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class KillCountdown : Runnable {
    private var ticks = -1
    override fun run() {
        ticks++
        if(ticks == 0) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("5", null, 5, 20, 5)
                }
            }
        } else if(ticks == 20) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("4", null, 5, 20, 5)
                }
            }
        } else if(ticks == 40) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("3", null, 5, 20, 5)
                }
            }
        } else if(ticks == 60) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("2", null, 5, 20, 5)
                }
            }
        } else if(ticks == 80) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("1", null, 5, 20, 5)
                }
            }
        } else if(ticks == 100) {
            for(player in Bukkit.getOnlinePlayers()) {
                if(player.gameMode == GameMode.ADVENTURE) {
                    player.sendTitle("Revive", null, 5, 20, 5)
                    player.gameMode = GameMode.SURVIVAL
                    player.isInvisible = false
                    player.allowFlight = false
                    player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
                }
            }
        }
    }
}