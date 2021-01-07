package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.tasks.BedWarScheduler
import com.github.devil0414.bedwars.utils.BedWar
import com.github.devil0414.bedwars.utils.FakeEntityServ
import com.github.devil0414.bedwars.utils.FakeEntityServ.fakeEntityServer
import com.github.devil0414.bedwars.utils.Manager.blocks
import com.github.devil0414.bedwars.utils.Manager.blueBed
import com.github.devil0414.bedwars.utils.Manager.greenBed
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList.unregisterAll
import com.github.devil0414.bedwars.utils.Manager.redBed
import com.github.devil0414.bedwars.utils.Manager.yellowBed
import org.bukkit.Material

class BedWarGame() {
    companion object {
        var gameStatus = false
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
        for(block in blocks) {
            block.type = Material.AIR
        }
        blocks.clear()
        fakeEntityServer.run {
            for(entity in this.entities) {
                entity.remove()
            }
            clear()
        }
        unregisterAll(instance)
        Bukkit.getScheduler().cancelTasks(instance)
        FakeEntityServ.initialize(instance)
        BedWar().initialize(instance)
    }
}