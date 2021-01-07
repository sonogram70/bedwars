package com.github.devil0414.bedwars.utils

import com.github.noonmaru.tap.fake.FakeProjectile

open class PluginProjectile(maxTicks: Int, range: Double) : FakeProjectile(maxTicks, range) {
    lateinit var bedwar: BedWar
        internal set
}