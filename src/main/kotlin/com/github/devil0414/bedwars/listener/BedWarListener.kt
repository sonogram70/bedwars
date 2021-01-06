package com.github.devil0414.bedwars.listener

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.CommandBW
import com.github.devil0414.bedwars.utils.EggBridgeScheduler
import com.github.devil0414.bedwars.utils.FakeEntityServ.fakeEntityServer
import com.github.devil0414.bedwars.utils.KillCountdown
import com.github.devil0414.bedwars.utils.Manager.blocks
import com.github.devil0414.bedwars.utils.Manager.blueBed
import com.github.devil0414.bedwars.utils.Manager.greenBed
import com.github.devil0414.bedwars.utils.Manager.redBed
import com.github.devil0414.bedwars.utils.Manager.yellowBed
import com.github.noonmaru.kommand.sendFeedback
import com.github.noonmaru.tap.fake.invisible
import org.bukkit.*
import com.github.noonmaru.tap.fake.setLocation
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.entity.*
import org.bukkit.event.player.PlayerAttemptPickupItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class BedWarListener : Listener {
    @EventHandler
    fun blockExplode(event: BlockExplodeEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun onblockPlace(event: BlockPlaceEvent) {
        if(event.blockPlaced.type == Material.TNT) {
            event.blockPlaced.type = Material.AIR
            val loc = event.blockPlaced.location
            val location = Location(world, loc.x + 0.5, loc.y, loc.z + 0.5)
            event.blockPlaced.location.world.spawnEntity(location, EntityType.PRIMED_TNT)
        } else {
            blocks.add(event.block)
        }
    }
    @EventHandler
    fun onEntityAttacked(event: EntityDamageEvent) {
        if(event.entityType == EntityType.VILLAGER) {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun onPlayerAttackByEntity(event: EntityDamageByEntityEvent) {
        if((event.damager as Player).gameMode == GameMode.ADVENTURE) {
            event.isCancelled = true
        }
        if(CommandBW.red.contains((event.entity) as Player) && CommandBW.red.contains((event.damager) as Player)) {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun blockBreak(event: BlockBreakEvent) {
        if(event.block.type != Material.RED_BED && event.block.type != Material.BLUE_BED && event.block.type != Material.GREEN_BED && event.block.type != Material.YELLOW_BED) {
            if(!blocks.contains(event.block)) {
                event.isCancelled = true
            }
            else if(blocks.contains(event.block)) {
                blocks.remove(event.block)
            }
        }
        else {
            when (event.block.type) {
                Material.RED_BED -> {
                    for (player in CommandBW.red) {
                        if (CommandBW.red.contains(event.player)) event.isCancelled = true
                        else {
                            redBed = false
                            for (players in Bukkit.getOnlinePlayers()) {
                                players.sendFeedback("Red Bed was destroyed by ${event.player.name}")
                            }
                            player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore!", 5, 20, 5)
                        }
                    }
                }
                Material.YELLOW_BED -> {
                    for (player in CommandBW.yellow) {
                        if (CommandBW.yellow.contains(event.player)) event.isCancelled = true
                        else {
                            for (players in Bukkit.getOnlinePlayers()) {
                                players.sendFeedback("Yellow Bed was destroyed by ${event.player.name}")
                            }
                            yellowBed = false
                            player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                        }
                    }
                }
                Material.BLUE_BED -> {
                    for (player in CommandBW.blue) {
                        if (CommandBW.blue.contains(event.player)) event.isCancelled = true
                        else {
                            for (players in Bukkit.getOnlinePlayers()) {
                                players.sendFeedback("Blue Bed was destroyed by ${event.player.name}")
                            }
                            blueBed = false
                            player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                        }
                    }
                }
                Material.GREEN_BED -> {
                    for (player in CommandBW.green) {
                        if (CommandBW.green.contains(event.player)) event.isCancelled = true
                        else {
                            for (players in Bukkit.getOnlinePlayers()) {
                                players.sendFeedback("Green Bed was destroyed by ${event.player.name}")
                            }
                            greenBed = false
                            player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if(event.item?.type == Material.EGG) {
            val egg = fakeEntityServer.spawnEntity(event.player.location, ArmorStand::class.java).apply {
                updateMetadata<ArmorStand> {
                    invisible = true
                    updateEquipment {
                        helmet = ItemStack(Material.EGG)
                    }
                }
            }
            Bukkit.getScheduler().runTaskTimer(instance, EggBridgeScheduler(egg), 0L, 1L)
        }
    }
    @EventHandler
    fun blockBurn(event: BlockExplodeEvent) {
        if(event.block.type == Material.STONE) {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun entityExplode(event: EntityExplodeEvent) {
        event.isCancelled = true
        Bukkit.getServer().worlds[0].spawnParticle(Particle.EXPLOSION_NORMAL, event.location, 10)
        Bukkit.getServer().worlds[0].spawnParticle(Particle.EXPLOSION_LARGE, event.location, 10)
        Bukkit.getServer().worlds[0].playSound(event.location, Sound.ENTITY_GENERIC_EXPLODE, 5.0F, 1.0F)
        for(block in event.blockList()) {
            if(blocks.contains(block)) {
                block.type = Material.AIR
            }
        }
    }
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        event.drops.clear()
        event.entity.setLocation(Location(world, 8.5, 82.5, 8.4))
        for(player in CommandBW.red) {
            if(redBed) {
                if(event.entity != player) return
            } else {
                if(event.entity != player) return
                event.entity.gameMode = GameMode.SPECTATOR
                CommandBW.red.remove(player)
            }
        }
        for(player in CommandBW.blue) {
            if(blueBed) {
                if(event.entity != player) return
            } else {
                if(event.entity != player) return
                event.entity.gameMode = GameMode.SPECTATOR
                CommandBW.blue.remove(player)
            }
        }
        for(player in CommandBW.green) {
            if(greenBed) {
                if(event.entity != player) return
            } else {
                if(event.entity != player) return
                event.entity.gameMode = GameMode.SPECTATOR
                CommandBW.green.remove(player)
            }
        }
        for(player in CommandBW.yellow) {
            if(yellowBed) {
                if(event.entity != player) return
            } else {
                if(event.entity != player) return
                event.entity.gameMode = GameMode.SPECTATOR
                CommandBW.yellow.remove(player)
            }
        }
    }
    @EventHandler
    fun onPlayerPickUpItem(event: PlayerAttemptPickupItemEvent) {
        if(event.player.gameMode == GameMode.ADVENTURE) {
            event.isCancelled = true
        }
    }
    val world : World = Bukkit.getWorlds()[0]
    @EventHandler
    fun onPlayerRevive(event: PlayerRespawnEvent) {
        if(CommandBW.red.contains(event.player) && redBed || CommandBW.blue.contains(event.player) && blueBed || CommandBW.green.contains(event.player) && greenBed || CommandBW.yellow.contains(event.player) && yellowBed) {
            event.player.gameMode = GameMode.ADVENTURE
            event.player.allowFlight = true
            event.player.isInvisible = true
            event.player.inventory.clear()
            event.player.teleport(Location(world, 8.5, 82.5, 8.4))
            Bukkit.getScheduler().runTaskTimer(instance, KillCountdown(event.player), 0L, 1L)
        } else {
            event.player.gameMode = GameMode.SPECTATOR
        }
    }
    @EventHandler
    fun onPlayerReviveEvent(event: PlayerPostRespawnEvent) {
        event.player.setLocation(Location(world, 8.5, 82.5, 8.4))
    }
}