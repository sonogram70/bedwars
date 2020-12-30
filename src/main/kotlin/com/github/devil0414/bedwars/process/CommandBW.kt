package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.process.BedWarProcess.startProcess
import com.github.devil0414.bedwars.process.BedWarProcess.stopProcess
import com.github.devil0414.bedwars.utils.ShopInventory
import com.github.noonmaru.invfx.openWindow
import com.github.noonmaru.kommand.KommandBuilder
import com.github.noonmaru.kommand.argument.PlayerArgument
import com.github.noonmaru.kommand.sendFeedback
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

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
        }
    }
    val red = arrayListOf<Player>()
    val blue = arrayListOf<Player>()
    val green = arrayListOf<Player>()
    val yellow = arrayListOf<Player>()
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