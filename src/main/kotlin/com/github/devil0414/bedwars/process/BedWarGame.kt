package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.tasks.BedWarScheduler
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList.unregisterAll

class BedWarGame() {
    companion object {
        var gameStatus = false
        var redBed = true
        var blueBed = true
        var greenBed = true
        var yellowBed = true
    }
    init {
        gameStatus = true
        Bukkit.getScheduler().runTaskTimer(instance, BedWarScheduler(), 0, 1)
    }
    fun unregister() {
        redBed = true
        blueBed = true
        greenBed = true
        yellowBed = true
        gameStatus = false
        unregisterAll(instance)
        Bukkit.getScheduler().cancelTasks(instance)
    }
}