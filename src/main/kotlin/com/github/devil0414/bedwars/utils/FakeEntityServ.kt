package com.github.devil0414.bedwars.utils

import com.github.noonmaru.tap.fake.FakeEntityServer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

object FakeEntityServ {
    lateinit var fakeEntityServer: FakeEntityServer
    internal fun initialize(plugin: JavaPlugin) {
        fakeEntityServer = FakeEntityServer.create(plugin).apply {
            plugin.server.pluginManager.registerEvents(object: Listener {
                @EventHandler
                fun onJoin(event: PlayerJoinEvent) {
                    addPlayer(event.player)
                }
            }, plugin)
            plugin.server.scheduler.runTaskTimer(plugin, this::update, 0L, 1L)
            for(player in Bukkit.getOnlinePlayers()) {
                addPlayer(player)
            }
        }
    }
}