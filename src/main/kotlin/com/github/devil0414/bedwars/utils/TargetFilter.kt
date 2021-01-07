package com.github.devil0414.bedwars.utils

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team
import java.util.function.Predicate

class TargetFilter(private val player: Player, private val team: Team? = Bukkit.getScoreboardManager().mainScoreboard.getEntryTeam(player.name)) :
    Predicate<Entity> {
        private var hostile = true
    override fun test(t: Entity): Boolean {
        if(t === player) return false
        if(t is LivingEntity) {
            if(t is Player) {
                val gameMode = t.gameMode
                if(gameMode == GameMode.SPECTATOR || gameMode == GameMode.CREATIVE) return false
            }
            val team = team ?: return hostile
            val name = if(t is Player) t.name else t.uniqueId.toString()
            val result = team.hasEntry(name)
            return if (hostile) !result else result
        }
        return false
    }
    fun friendly() {
        hostile = false
    }

}