package com.github.devil0414.bedwars.utils

import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.noonmaru.tap.fake.FakeProjectileManager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin

class BedWar {
    private lateinit var projectileManager: FakeProjectileManager
    fun initialize(plugin: JavaPlugin) {
        plugin.server.scheduler.runTaskTimer(instance, projectileManager::update, 0L, 1L)
    }
    fun launchProjectile(location: Location, projectile: PluginProjectile) {
        projectile.bedwar = this
        projectileManager.launch(location, projectile)
    }
}