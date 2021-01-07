package com.github.devil0414.bedwars.utils

import com.github.noonmaru.tap.fake.Movement
import com.github.noonmaru.tap.fake.Trail
import com.github.noonmaru.tap.math.normalizeAndLength
import com.github.noonmaru.tap.trail.trail
import org.bukkit.FluidCollisionMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player

class EggBridgeProjectile(private val player: Player, private val eggBridge: EggBridge) : PluginProjectile(100, 127.0) {
    override fun onMove(movement: Movement) {
        eggBridge.updateLocation(0.0, movement.to)
    }

    override fun onTrail(trail: Trail) {
        trail.velocity?.let { velocity ->
            val from = trail.from
            val world = from.world
            val length = velocity.normalizeAndLength()
            val filter = TargetFilter(player)
            world.rayTrace(from, velocity, length, FluidCollisionMode.NEVER, true, 1.0, filter)?.let { result ->
                remove()
                val hitPosition = result.hitPosition
                val hitLocation = hitPosition.toLocation(world)
                val to = trail.to
                trail(from, to, 0.25) {w, x, y, z ->
                    val location = Location(w, x, y, z)
                    location.block.type = Material.WHITE_WOOL
                }
            }
        }
    }

    override fun onPostUpdate() {
        velocity = velocity.multiply(1.0 + 0.1)
    }

    override fun onRemove() {
        eggBridge.remove()
    }
}