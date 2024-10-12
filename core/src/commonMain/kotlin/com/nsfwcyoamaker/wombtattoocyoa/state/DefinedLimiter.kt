package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Limiter
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import kotlinx.serialization.Serializable

@Serializable
data class DefinedLimiter(
    val id: String,
    val limiter: Limiter,
    val notes: String = "",
    val limiterComponent: LimiterComponent? = LimiterComponent.makeNew(limiter),
    val cpPaymentType: PaymentType = PaymentType.NORMAL,
    val ppPaymentType: PaymentType = PaymentType.NORMAL,
) {
    val pointContribution: LimiterContribution by lazy {
        var points = PointsPool()

        points += limiter.cpCost
            ?.let { cpCost ->
                when (cpPaymentType) {
                    PaymentType.NORMAL -> cpCost
                    else -> cpCost.amount.mp
                }
            } as Points

        points += limiter.ppCost
            ?.let { ppCost ->
                when (ppPaymentType) {
                    PaymentType.NORMAL -> ppCost
                    else -> ppCost.amount.mp
                }
            } as Points

        val multiplier = limiter.multiplier
            .let { limiterComponent?.adjustLimiterMultiplier(it) ?: it }

        LimiterContribution(
            points,
            multiplier
        )
    }
}
