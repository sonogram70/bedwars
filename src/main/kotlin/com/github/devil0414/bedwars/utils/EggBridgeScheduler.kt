package com.github.devil0414.bedwars.utils

import com.github.noonmaru.tap.fake.FakeEntity
import org.bukkit.Location
import org.bukkit.Material

class EggBridgeScheduler(private val entity: FakeEntity) : Runnable {
    private val entities = arrayListOf<FakeEntity>()
    override fun run() {
        entities.add(entity)
        for(enti in entities) {
            if(enti.location.block.type == Material.AIR) {
                enti.location.block.type = Material.WHITE_WOOL
            }
            val location = Location(enti.location.world, enti.location.x, enti.location.y - 0.1, enti.location.z)
            if(location.block.type != Material.AIR) {
                enti.remove()
                entities.remove(enti)
            }
        }
    }
}