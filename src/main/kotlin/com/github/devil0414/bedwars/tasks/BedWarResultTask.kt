package com.github.devil0414.bedwars.tasks

import com.github.devil0414.bedwars.process.CommandBW
import com.github.devil0414.bedwars.process.BedWarProcess
import org.bukkit.Bukkit

class BedWarResultTask : BedWarTask {
    override fun execute() : BedWarTask? {
        for(player in Bukkit.getOnlinePlayers()) {
            if(CommandBW.red.count() == 0 && CommandBW.blue.count() == 0 && CommandBW.green.count() == 0) {
                player.sendTitle("게임 종료!", "우승 : yellow", 5, 60, 10)
            }
            else if(CommandBW.red.count() == 0 && CommandBW.blue.count() == 0 && CommandBW.yellow.count() == 0) {
                player.sendTitle("게임 종료!", "우승 : green", 5, 60, 10)
            }
            else if(CommandBW.red.count() == 0 && CommandBW.green.count() == 0 && CommandBW.yellow.count() == 0) {
                player.sendTitle("게임 종료!", "우승 : blue", 5, 60, 10)
            }
            else if(CommandBW.blue.count() == 0 && CommandBW.green.count() == 0 && CommandBW.yellow.count() == 0) {
                player.sendTitle("게임 종료!", "우승 : red", 5, 60, 10)
            }
            BedWarProcess.stopProcess()
        }
        return null
    }
}