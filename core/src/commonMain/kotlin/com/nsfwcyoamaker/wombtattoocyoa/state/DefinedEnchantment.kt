package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Enchantment
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import kotlinx.serialization.Serializable

@Serializable
sealed class DefinedEnchantment {
    abstract val id: String
    abstract val enchantment: Enchantment
    abstract val linkedLimiters: List<DefinedLimiter>
    abstract val linkedActivatorId: String?
    abstract val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?
    abstract val notes: String

    abstract val baseEnchantmentContribution: PointsPool
    abstract val empoweredEnchantmentContribution: PointsPool

    abstract fun updateWith(base: MarkBaseDesign): DefinedEnchantment

    val limitersTotalContribution: LimiterContribution by lazy {
        linkedLimiters
            .map { it.pointContribution }
            .fold(LimiterContribution()) { acc, it -> acc + it }
    }

    val limiterMultiplier: Float
        get() = limitersTotalContribution.multiplier

    val baseEnchantmentAndBaseMarkContribution by lazy {
        markBaseDesignComponent
            ?.modifyBasePointsContribution(this)
            ?: baseEnchantmentContribution
    }

    val empoweredEnchantmentAndBaseMarkContribution by lazy {
        markBaseDesignComponent
            ?.modifyEmpoweredPointsContribution(this)
            ?: empoweredEnchantmentContribution
    }

    val pointsContribution: PointsPool by lazy {
        limitersTotalContribution.points + baseEnchantmentAndBaseMarkContribution + empoweredEnchantmentAndBaseMarkContribution
    }

    @Serializable
    data class Simple(
        override val id: String,
        override val enchantment: Enchantment,
        override val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?,
        override val linkedLimiters: List<DefinedLimiter> = emptyList(),
        override val linkedActivatorId: String? = null,
        override val notes: String = "",
        val base: PaymentType = PaymentType.NORMAL,
        val empowered: PaymentType? = null,
    ): DefinedEnchantment() {
        override val baseEnchantmentContribution: PointsPool by lazy {
            var points = PointsPool()

            enchantment.standardCost.let { standardCost ->
                base.let { standardPaymentType ->
                    points -= when (standardPaymentType) {
                        PaymentType.NORMAL -> standardCost * limiterMultiplier
                        PaymentType.MIXED_POINTS -> standardCost.amount.mp
                    }
                }
            }

            points
        }

        override val empoweredEnchantmentContribution: PointsPool by lazy {
            var points = PointsPool()

            enchantment.empoweredCost?.let { empoweredCost ->
                empowered?.let { empoweredPaymentType ->
                    points -= when(empoweredPaymentType) {
                        PaymentType.NORMAL -> empoweredCost * limiterMultiplier
                        PaymentType.MIXED_POINTS -> empoweredCost.amount.mp
                    }
                }
            }

            points
        }

        override fun updateWith(base: MarkBaseDesign): DefinedEnchantment {
            return copy(
                markBaseDesignComponent = markBaseDesignComponent
                    ?.takeIf { it.forMarkBaseType == base }
                    ?: MarkBaseDesignComponent.EnchantmentSpecific.makeNew(base),
            )
        }
    }

    @Serializable
    data class Drawback(
        override val id: String,
        override val enchantment: Enchantment.Drawback,
        override val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?,
        override val linkedLimiters: List<DefinedLimiter> = emptyList(),
        override val linkedActivatorId: String? = null,
        override val notes: String = "",
        val override: DrawbackPointsContributionType? = null,
        val paymentType: PaymentType = PaymentType.NORMAL,
    ): DefinedEnchantment() {
        @Serializable
        enum class DrawbackPointsContributionType {
            GAIN, PAY
        }

        val pointsContributionType: DrawbackPointsContributionType by lazy {
            override ?: if(linkedActivatorId != null || linkedLimiters.isNotEmpty())
                DrawbackPointsContributionType.PAY
            else
                DrawbackPointsContributionType.GAIN
        }

        override val baseEnchantmentContribution: PointsPool by lazy {
            when(pointsContributionType) {
                DrawbackPointsContributionType.GAIN -> PointsPool() + enchantment.gainedMixedPoints
                DrawbackPointsContributionType.PAY -> PointsPool() - enchantment.standardCost.let {
                    when (paymentType) {
                        PaymentType.NORMAL -> it * limiterMultiplier
                        PaymentType.MIXED_POINTS -> it.amount.mp
                    }
                }
            }
        }

        override val empoweredEnchantmentContribution: PointsPool
            get() = PointsPool()

        override fun updateWith(base: MarkBaseDesign): DefinedEnchantment {
            return copy(
                markBaseDesignComponent = markBaseDesignComponent
                    ?.takeIf { it.forMarkBaseType == base }
                    ?: MarkBaseDesignComponent.EnchantmentSpecific.makeNew(base),
            )
        }
    }
}