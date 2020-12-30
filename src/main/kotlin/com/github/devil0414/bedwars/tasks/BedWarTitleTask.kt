package com.github.devil0414.bedwars.tasks

import org.bukkit.Bukkit

class BedWarTitleTask: BedWarTask {
    private var ticks = -1
    override fun execute(): BedWarTask? {
        ticks++
        if(ticks == 0) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.sendTitle("Bed Wars", null, 5, 50, 5)
            }
        }
        return if(ticks >= 60) BedWarCountdownTask() else this
    }
}