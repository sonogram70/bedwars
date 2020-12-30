package com.github.devil0414.bedwars.listener

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent
import com.github.devil0414.bedwars.plugin.BedWarPlugin.Companion.instance
import com.github.devil0414.bedwars.process.CommandBW
import org.bukkit.*
import com.github.devil0414.bedwars.process.BedWarGame.Companion.redBed
import com.github.devil0414.bedwars.process.BedWarGame.Companion.blueBed
import com.github.devil0414.bedwars.process.BedWarGame.Companion.greenBed
import com.github.devil0414.bedwars.process.BedWarGame.Companion.yellowBed
import com.github.devil0414.bedwars.tasks.KillCountdown
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.block.BlockFadeEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAttemptPickupItemEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class BedWarListener : Listener {
    @EventHandler
    fun blockExplode(event: BlockExplodeEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun blockBreak(event: BlockBreakEvent) {
        if(event.block.type == Material.STONE) {
            event.isCancelled = true
        }
        else if(event.block.type == Material.RED_BED) {
            for(player in CommandBW.red) {
                if(player == event.player) event.isCancelled = true
                else {
                    redBed = false
                    player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore!", 5, 20, 5)
                }
            }
        }
        else if(event.block.type == Material.YELLOW_BED) {
            for(player in CommandBW.yellow) {
                if(player == event.player) event.isCancelled = true
                else {
                    yellowBed = false
                    player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                }
            }
        }
        else if(event.block.type == Material.BLUE_BED) {
            for(player in CommandBW.blue) {
                if(player == event.player) event.isCancelled = true
                else {
                    blueBed = false
                    player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                }
            }
        }
        else if(event.block.type == Material.GREEN_BED) {
            for(player in CommandBW.green) {
                if(player == event.player) event.isCancelled = true
                else {
                    greenBed = false
                    player.sendTitle("${ChatColor.RED} Bed Destroyed!", "You can't revive anymore", 5, 20, 5)
                }
            }
        }
    }
    @EventHandler
    fun blockBurn(event: BlockExplodeEvent) {
        if(event.block.type == Material.STONE) {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun entityChangeBlock(event: EntityChangeBlockEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun blockDamage(event: BlockDamageEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun blockFade(event: BlockFadeEvent) {
        event.isCancelled = true
    }
    @EventHandler
    fun entityExplode(event: EntityExplodeEvent) {
        event.isCancelled = true
        Bukkit.getServer().worlds[0].spawnParticle(Particle.EXPLOSION_NORMAL, event.location, 10)
        Bukkit.getServer().worlds[0].spawnParticle(Particle.EXPLOSION_LARGE, event.location, 10)
        for(block in event.blockList()) {
            if(block.type != Material.STONE) {
                block.type = Material.AIR
            }
        }
    }
    private var ticks = -1
    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
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
    @EventHandler
    fun onPlayerRevive(event: PlayerRespawnEvent) {
        KillCountdown()
    }
    @EventHandler
    fun onPlayerRevivePost(event: PlayerPostRespawnEvent) {
        KillCountdown()
    }
}