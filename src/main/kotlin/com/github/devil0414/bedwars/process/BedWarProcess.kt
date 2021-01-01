package com.github.devil0414.bedwars.process

import com.github.devil0414.bedwars.utils.FakeEntityServ.fakeEntityServer
import com.github.noonmaru.kommand.sendFeedback
import org.bukkit.Bukkit

object BedWarProcess {
    private var game: BedWarGame? = null
    fun startProcess() {
        if(game != null) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.sendFeedback("게임이 이미 진행 중입니다.")
            }
        } else {
            game = BedWarGame()
        }
    }
    fun stopProcess() {
        if(game == null) {
            for(player in Bukkit.getOnlinePlayers()) {
                player.sendFeedback("진행 중인 게임이 없습니다.")
            }
        }
        for(entity in fakeEntityServer.entities) {
            entity.remove()
        }
        game?.unregister()
        game = null
    }
}