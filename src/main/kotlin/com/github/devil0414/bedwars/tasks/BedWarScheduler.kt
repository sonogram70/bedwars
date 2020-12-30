package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.process.BedWarProcess.stopProcess
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getOnlinePlayers
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class BedWarScheduler : Runnable {
    private var bedwarTask: BedWarTask? = null
    init {
        bedwarTask = BedWarTitleTask()
    }
    override fun run() {
        bedwarTask = bedwarTask?.execute()
        for(player in Bukkit.getOnlinePlayers()) {
            if(player.gameMode != GameMode.SURVIVAL) return
            player.inventory.setItem(0, ItemStack(Material.WOODEN_SWORD))
        }
        if(bedwarTask == null) {
            stopProcess()
            getOnlinePlayers().forEach {
                it?: return@forEach
                it.gameMode = if(it.isOp) GameMode.CREATIVE else GameMode.ADVENTURE
            }
        }
    }
}