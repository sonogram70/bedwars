package com.github.devil0414.bedwars.plugin

import com.github.devil0414.bedwars.process.CommandBW
import com.github.noonmaru.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class BedWarPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: BedWarPlugin
    }

    override fun onEnable() {
        instance = this
        kommand {
            register("bw") {
                CommandBW.register(this)
            }
        }
    }
}