package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.plugin.BedWarPlugin
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.BedWarProcess.startProcess
import com.github.devil0414.bedwars.process.BedWarProcess.stopProcess
import com.github.devil0414.bedwars.utils.FakeEntityServ.fakeEntityServer
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
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scoreboard.DisplaySlot

internal object CommandBW {
    fun register(builder: KommandBuilder) {
        builder.apply {
            then("team") {
                then("player" to PlayerArgument) {
                    then("leave") {
                        then("red") {
                            executes {
                                leavered(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("blue") {
                            executes {
                                leaveblue(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("green") {
                            executes {
                                leavegreen(it.parseArgument("player"), it.sender)
                            }
                        }
                        then("yellow") {
                            executes {
                                leaveyellow(it.parseArgument("player"), it.sender)
                            }
                        }
                    }
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
                    Bukkit.getScheduler().cancelTasks(instance)
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
                    shop()
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
    private fun shop() {
        val world = Bukkit.getServer().worlds[0]
        val location = Location(world, 14.7, 4.0, -53.3)
        val location2 = Location(world, 2.3, 4.0, -53.4)
        val shopkeeper = world.spawnEntity(location, EntityType.VILLAGER)
        shopkeeper.customName = "shopkeeper"
        val shopkeeper2 = world.spawnEntity(location2, EntityType.VILLAGER)
        shopkeeper2.customName = "Upgrade"
    }
    val red = arrayListOf<Player>()
    val blue = arrayListOf<Player>()
    val green = arrayListOf<Player>()
    val yellow = arrayListOf<Player>()
    lateinit var emerald1: FakeEntity
    lateinit var emerald2: FakeEntity
    lateinit var emerald3: FakeEntity
    lateinit var emerald4: FakeEntity
    lateinit var diamond1: FakeEntity
    lateinit var diamond2: FakeEntity
    lateinit var diamond3: FakeEntity
    lateinit var diamond4: FakeEntity
    lateinit var diamond5: FakeEntity
    lateinit var diamond6: FakeEntity
    lateinit var diamond7: FakeEntity
    lateinit var diamond8: FakeEntity
    private fun timer() {
        val world: World = Bukkit.getServer().worlds[0]
        val location = Location(world, -0.4, 4.0, 8.5)
        val location2 = Location(world, 17.5, 4.0, 8.5)
        val location3 = Location(world, -0.4, 4.3, 8.5)
        val location4 = Location(world, 17.5, 4.3, 8.5)
        val location5 = Location(world, -30.6, 8.0, 59.6)
        val location6 = Location(world, -33.5, 9.0, -32.6)
        val location7 = Location(world, 41.5, 12.0, -29.3)
        val location8 = Location(world, 58.5, 1.0, 53.4)
        val loc5 = Location(world, -30.6, 8.3, 59.6)
        val loc6 = Location(world, -33.5, 9.3, -32.6)
        val loc7 = Location(world, 41.5, 12.3, -29.3)
        val loc8 = Location(world, 58.5, 1.3, 53.4)
        emerald1 = fakeEntityServer.spawnEntity(location3, ArmorStand::class.java)
        emerald2 = fakeEntityServer.spawnEntity(location, ArmorStand::class.java)
        emerald3 = fakeEntityServer.spawnEntity(location4, ArmorStand::class.java)
        emerald4 = fakeEntityServer.spawnEntity(location2, ArmorStand::class.java)
        diamond1 = fakeEntityServer.spawnEntity(location5, ArmorStand::class.java)
        diamond2 = fakeEntityServer.spawnEntity(location6, ArmorStand::class.java)
        diamond3 = fakeEntityServer.spawnEntity(location7, ArmorStand::class.java)
        diamond4 = fakeEntityServer.spawnEntity(location8, ArmorStand::class.java)
        diamond5 = fakeEntityServer.spawnEntity(loc5, ArmorStand::class.java)
        diamond6 = fakeEntityServer.spawnEntity(loc6, ArmorStand::class.java)
        diamond7 = fakeEntityServer.spawnEntity(loc7, ArmorStand::class.java)
        diamond8 = fakeEntityServer.spawnEntity(loc8, ArmorStand::class.java)
        emerald1.updateMetadata<ArmorStand> {
            customName = "Emerald"
            isCustomNameVisible = true
            invisible = true
            setGravity(false)
        }
        emerald2.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        emerald3.updateMetadata<ArmorStand> {
            customName = "Emerald"
            isCustomNameVisible = true
            invisible = true
            setGravity(false)
        }
        emerald4.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        diamond1.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        diamond2.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        diamond3.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        diamond4.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
        }
        diamond5.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
            customName = "Diamond"
            isCustomNameVisible = true
        }
        diamond6.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
            customName = "Diamond"
            isCustomNameVisible = true
        }
        diamond7.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
            customName = "Diamond"
            isCustomNameVisible = true
        }
        diamond8.updateMetadata<ArmorStand> {
            invisible = true
            setGravity(false)
            customName = "Diamond"
            isCustomNameVisible = true
        }
    }
    private fun timerremove() {
        fakeEntityServer.entities.run {
            for(fakeEntity in this) {
                fakeEntity.remove()
            }
        }
        emerald1.remove()
        emerald2.remove()
        emerald3.remove()
        emerald4.remove()
        diamond1.remove()
        diamond2.remove()
        diamond3.remove()
        diamond4.remove()
        diamond5.remove()
        diamond6.remove()
        diamond7.remove()
        diamond8.remove()
    }
    private fun startprocess(sender: CommandSender) {
        if(Bukkit.getScoreboardManager().mainScoreboard.getObjective("BedWars") != null) {
            Bukkit.getScoreboardManager().mainScoreboard.getObjective("BedWars")?.unregister()
        }
        Bukkit.getScoreboardManager().mainScoreboard.registerNewObjective("BedWars", "dummy", "Bed-Wars")
        val objective = Bukkit.getScoreboardManager().mainScoreboard.getObjective("BedWars")
        objective?.displaySlot = DisplaySlot.SIDEBAR
        startProcess()
    }
    private fun stopprocess(sender: CommandSender) {
        fakeEntityServer.entities.run {
            for(fakeEntity in this) {
                fakeEntity.remove()
            }
        }
        stopProcess()
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
    private fun leavered(player: Player, sender: CommandSender) {
        red.remove(player)
        sender.sendFeedback("${player.name}님 레드 팀 참가")
    }
    private fun leaveblue(player: Player, sender: CommandSender) {
        blue.remove(player)
        sender.sendFeedback("${player.name}님 블루 팀 참가")
    }
    private fun leavegreen(player: Player, sender: CommandSender) {
        green.remove(player)
        sender.sendFeedback("${player.name}님 그린 팀 참가")
    }
    private fun leaveyellow(player: Player, sender: CommandSender) {
        yellow.remove(player)
        sender.sendFeedback("${player.name}님 옐로우 팀 참가")
    }
}