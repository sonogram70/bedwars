package com.github.devil0414.bedwars.process

import com.github.noonmaru.kommand.sendFeedback
import org.bukkit.Bukkit

object BedWarProcess {
    private var game: BedWarGame? = null
    fun startProcess() {
        game = BedWarGame()
    }
    fun stopProcess() {
        if(game == null) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.sendFeedback("진행 중인 게임이 없습니다.")
            }
        }
        game?.unregister()
        game = null
    }
}