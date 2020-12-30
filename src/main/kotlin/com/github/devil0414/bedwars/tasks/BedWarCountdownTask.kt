package com.github.devil0414.bedwars.tasks

import org.bukkit.Bukkit
import org.bukkit.ChatColor

class BedWarCountdownTask: BedWarTask {
    private var ticks = 100
    private val period = 20
    private var countdown = -1
    override fun execute(): BedWarTask? {
        --ticks
        if(ticks > 0) {
            val count = (ticks + period) / period
            if(countdown != count) try {
                for(player in Bukkit.getOnlinePlayers()) {
                    player.sendTitle(getColorByCount(count).toString() + count.toString(), null, 0, period + 20, 0)
                }
            } finally { countdown = count }
            return this
        }
        for(player in Bukkit.getOnlinePlayers()) {
            player.sendTitle("START", null, 0, 24, 6)
        }
        return BedWarGameTask()
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