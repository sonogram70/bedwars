package com.github.devil0414.bedwars.tasks

interface BedWarTask {
    fun execute(): BedWarTask?
}