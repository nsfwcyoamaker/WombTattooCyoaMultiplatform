package com.nsfwcyoamaker.wombtattoocyoa.state

data class LimiterContribution(
    val points: PointsPool = PointsPool(),
    val multiplier: Float = 1f,
) {
    operator fun plus(other: LimiterContribution): LimiterContribution {
        return this.copy(
            points = this.points + other.points,
            multiplier = minOf(this.multiplier, other.multiplier)
        )
    }
}
