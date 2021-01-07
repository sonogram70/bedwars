package com.github.devil0414.bedwars.plugin

import com.github.devil0414.bedwars.process.CommandBW
import com.github.devil0414.bedwars.utils.BedWar
import com.github.devil0414.bedwars.utils.FakeEntityServ
import com.github.noonmaru.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin
import com.github.noonmaru.tap.fake.FakeEntityServer
import org.bukkit.Bukkit

class BedWarPlugin : JavaPlugin() {
    companion object {
        lateinit var instance: BedWarPlugin
    }

    override fun onEnable() {
        instance = this
        setupCommands()
        FakeEntityServ.initialize(this)
        BedWar().initialize(this)
    }
    private fun setupCommands() {
        kommand {
            register("bw") {
                CommandBW.register(this)
            }
        }
    }
}