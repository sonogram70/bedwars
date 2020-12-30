package com.github.devil0414.bedwars.tasks

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class KillCountdown : BedWarTask {
    private var ticks = 100
    private val period = 20
    private var countdown = -1
    override fun execute(): BedWarTask? {
        --ticks
        if(ticks > 0) {
            val count = (ticks + period) / period
            if(countdown != count) try {
                for(player in Bukkit.getOnlinePlayers()) {
                    if(player.gameMode == GameMode.ADVENTURE) {
                        player.sendTitle(getColorByCount(count).toString() + count.toString(), null, 0, period + 20, 0)
                    }
                }
            } finally { countdown = count }
            return this
        }
        for(player in Bukkit.getOnlinePlayers()) {
            if(player.gameMode == GameMode.ADVENTURE) {
                player.sendTitle("Revive", null, 0, 24, 6)
                player.gameMode == GameMode.SURVIVAL
                player.inventory.setItem(0, ItemStack((Material.WOODEN_SWORD)))
                player.isInvisible = false
                player.allowFlight = false
            }
        }
        return null
    }
    private fun getColorByCount(count: Int) = when(count) {
        1 -> ChatColor.RED
        2 -> ChatColor.YELLOW
        3 -> ChatColor.GREEN
        4 -> ChatColor.AQUA
        5 -> ChatColor.LIGHT_PURPLE
        else -> ChatColor.WHITE
    }
}