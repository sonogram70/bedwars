package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.plugin.BedWarPlugin
import com.github.devil0414.bedwars.process.BedWarProcess.startProcess
import com.github.devil0414.bedwars.process.BedWarProcess.stopProcess
import com.github.devil0414.bedwars.utils.ShopInventory
import com.github.noonmaru.invfx.openWindow
import com.github.noonmaru.kommand.KommandBuilder
import com.github.noonmaru.kommand.argument.PlayerArgument
import com.github.noonmaru.kommand.sendFeedback
import com.github.noonmaru.tap.fake.FakeEntity
import com.github.noonmaru.tap.fake.FakeEntityServer
import com.github.noonmaru.tap.fake.invisible
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.CommandSender
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

internal object CommandBW {
    fun register(builder: KommandBuilder) {
        builder.apply {
            then("team") {
                then("player" to PlayerArgument) {
                    then("join") {
                        then("red") {
                            executes {
                                joinred(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("blue") {
                            executes {
                                joinblue(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("green") {
                            executes {
                                joingreen(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("yellow") {
                            executes {
                                joinyellow(it.parseArgument("player"), it.sender)
                            }
                        }
                    }
                }
            }
            then("start") {
                executes {
                    startprocess(it.sender)
                }
            }
            then("stop") {
                executes {
                    stopprocess(it.sender)
                }
            }
            then("test") {
                executes {
                    ShopInventory.create()
                    (it.sender as Player).openInventory(ShopInventory.inv)
                }
            }
            then("testInv") {
                executes {
                    (it.sender as Player).openWindow(ShopInventory.createInv())
                }
            }
            then("shop") {
                executes {
                    val world = Bukkit.getServer().worlds[0]
                    val location = Location(world, 14.7, 4.0, -53.3)
                    val shopkeeper = world.spawnEntity(location, EntityType.VILLAGER)
                    shopkeeper.customName = "shopkeeper"
                }
            }
            then("timer") {
                then("clear") {
                    executes {
                        timerremove()
                    }
                }
                executes {
                    timer()
                }
            }
        }
    }
    val red = arrayListOf<Player>()
    val blue = arrayListOf<Player>()
    val green = arrayListOf<Player>()
    val yellow = arrayListOf<Player>()
    private var fakeEntityServer = FakeEntityServer.create(BedWarPlugin.instance).apply {
        BedWarPlugin.instance.server.pluginManager.registerEvents(object: Listener {
            @EventHandler
            fun onJoin(event: PlayerJoinEvent) {
                addPlayer(event.player)
            }
        }, BedWarPlugin.instance)
        BedWarPlugin.instance.server.scheduler.runTaskTimer(BedWarPlugin.instance, this::update, 0L, 1L)
        for(onlinePlayer in Bukkit.getOnlinePlayers()) {
            addPlayer(onlinePlayer)
        }
    }
    private val fakeEntityTimer = arrayListOf<FakeEntity>()
    lateinit var diamond1: FakeEntity
    lateinit var diamond2: FakeEntity
    lateinit var diamond3: FakeEntity
    lateinit var diamond4: FakeEntity
    private fun timer() {
        val world: World = Bukkit.getServer().worlds[0]
        val location = Location(world, -0.4, 4.0, 8.5)
        val location2 = Location(world, 17.5, 4.0, 8.5)
        val location3 = Location(world, -0.4, 4.3, 8.5)
        val location4 = Location(world, 17.5, 4.3, 8.5)
        diamond1 = fakeEntityServer.spawnEntity(location3, ArmorStand::class.java).apply {
            updateMetadata<ArmorStand> {
                invisible = true
                customName = "Diamond"
                isCustomNameVisible = true
            }
        }
        diamond2 = fakeEntityServer.spawnEntity(location, ArmorStand::class.java).apply {
            updateMetadata<ArmorStand> {
                invisible = true
                isCustomNameVisible = true
            }
        }
        diamond3 = fakeEntityServer.spawnEntity(location4, ArmorStand::class.java).apply {
            updateMetadata<ArmorStand> {
                invisible = true
                customName = "Diamond"
                isCustomNameVisible = true
            }
        }
        diamond4 = fakeEntityServer.spawnEntity(location2, ArmorStand::class.java).apply {
            updateMetadata<ArmorStand> {
                invisible = true
                isCustomNameVisible = true
            }
        }
        fakeEntityTimer.add(diamond1)
        fakeEntityTimer.add(diamond2)
        fakeEntityTimer.add(diamond3)
        fakeEntityTimer.add(diamond4)
    }
    private fun timerremove() {
        for(fakeEntities in fakeEntityTimer) {
            fakeEntities.remove()
            fakeEntityTimer.remove(fakeEntities)
        }
    }
    private fun startprocess(sender: CommandSender) {
        startProcess()
        sender.sendMessage("game start!")
    }
    private fun stopprocess(sender: CommandSender) {
        stopProcess()
        sender.sendMessage("game stop")
    }
    private fun joinred(player: Player, sender: CommandSender) {
        red.add(player)
        sender.sendFeedback("${player.name}님 레드 팀 참가")
    }
    private fun joinblue(player: Player, sender: CommandSender) {
        blue.add(player)
        sender.sendFeedback("${player.name}님 블루 팀 참가")
    }
    private fun joingreen(player: Player, sender: CommandSender) {
        green.add(player)
        sender.sendFeedback("${player.name}님 그린 팀 참가")
    }
    private fun joinyellow(player: Player, sender: CommandSender) {
        yellow.add(player)
        sender.sendFeedback("${player.name}님 옐로우 팀 참가")
    }
}