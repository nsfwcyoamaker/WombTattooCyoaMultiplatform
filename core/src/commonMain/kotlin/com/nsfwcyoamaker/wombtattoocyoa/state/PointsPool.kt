package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Points
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp

data class PointsPool(
    val baseChangePoints: Points.Change = 0.cp,
    val usedChangePoints: Points.Change = 0.cp,
    val gainedChangePoints: Points.Change = 0.cp,

    val basePowerPoints: Points.Power = 0.pp,
    val usedPowerPoints: Points.Power = 0.pp,
    val gainedPowerPoints: Points.Power = 0.pp,

    val baseMixedPoints: Points.Mixed = 0.mp,
    val usedMixedPoints: Points.Mixed = 0.mp,
    val gainedMixedPoints: Points.Mixed = 0.mp,
) {
    operator fun plus(other: PointsPool): PointsPool {
        return this.copy(
            usedChangePoints = this.usedChangePoints + other.usedChangePoints,
            baseChangePoints = this.baseChangePoints + other.baseChangePoints,
            gainedChangePoints = this.gainedChangePoints + other.gainedChangePoints,
            usedPowerPoints = this.usedPowerPoints + other.usedPowerPoints,
            basePowerPoints = this.basePowerPoints + other.basePowerPoints,
            gainedPowerPoints = this.gainedPowerPoints + other.gainedPowerPoints,
            usedMixedPoints = this.usedMixedPoints + other.usedMixedPoints,
            baseMixedPoints = this.baseMixedPoints + other.baseMixedPoints,
            gainedMixedPoints = this.gainedMixedPoints + other.gainedMixedPoints,
        )
    }

    operator fun minus(cost: Points): PointsPool {
        return when(cost) {
            is Points.Change -> {
                this.copy(usedChangePoints = this.usedChangePoints + cost)
            }
            is Points.Power -> {
                this.copy(usedPowerPoints = this.usedPowerPoints + cost)
            }
            is Points.Mixed -> {
                this.copy(usedMixedPoints = this.usedMixedPoints + cost)
            }
        }
    }

    operator fun plus(gain: Points): PointsPool {
        return when(gain) {
            is Points.Change -> {
                this.copy(gainedChangePoints = this.gainedChangePoints + gain)
            }
            is Points.Power -> {
                this.copy(gainedPowerPoints = this.gainedPowerPoints + gain)
            }
            is Points.Mixed -> {
                this.copy(gainedMixedPoints = this.gainedMixedPoints + gain)
            }
        }
    }

    val changeState: String
        get() = "${usedChangePoints.amount} of ${baseChangePoints.amount + gainedChangePoints.amount}"

    val powerState: String
        get() = "${usedPowerPoints.amount} of ${basePowerPoints.amount + gainedPowerPoints.amount}"

    val mixedState: String
        get() = "${usedMixedPoints.amount} of ${baseMixedPoints.amount + gainedMixedPoints.amount}"

    fun extractSingleCost(): Points? {
        if(
            listOf(
                baseChangePoints,
                basePowerPoints,
                basePowerPoints,
                gainedChangePoints,
                gainedPowerPoints,
                gainedMixedPoints
            ).any {
                it.amount != 0
            }
        ) throw RuntimeException()

        val costs = listOf(
            usedChangePoints,
            usedPowerPoints,
            usedMixedPoints,
        )

        if(costs.all { it.amount == 0 }) return null

        return costs.singleOrNull { it.amount != 0 } ?: throw RuntimeException()
    }
}