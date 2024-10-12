package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Enchantment
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp

import kotlinx.serialization.Serializable
import kotlin.math.ceil

@Serializable
sealed interface MarkBaseDesignComponent {
    val forMarkBaseType: MarkBaseDesign

    sealed interface Global: MarkBaseDesignComponent {
        companion object {
            fun makeNew(forMarkBaseType: MarkBaseDesign): Global? {
                return when (forMarkBaseType) {
                    is MarkBaseDesign.Uniform -> Uniform
                    else -> null
                }
            }
        }

        fun getPointsContribution(definedMarks: List<DefinedMark>): PointsPool

        @Serializable
        object Uniform: Global {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Uniform

            override fun getPointsContribution(definedMarks: List<DefinedMark>): PointsPool {
                val activatorsCosts = definedMarks
                    .flatMap { it.activators }
                    .distinctBy { it.activator }
                    .map { it.activator.changePointsCost }
                    .fold(PointsPool()) { acc, activatorCost ->
                        acc - activatorCost
                    }

                val markMagicCosts = definedMarks
                    .flatMap {
                        it.enchantments
                            .filterIsInstance<DefinedEnchantment.Simple>()
                            .filter { it.enchantment is Enchantment.MarkMagic }
                    }
                    .groupBy { it.enchantment }
                    .map { (_, definedEnchantments) ->
                        val baseCost = definedEnchantments
                            .map { it.baseEnchantmentContribution }
                            .maxByOrNull { it.extractSingleCost()?.amount ?: 0 }
                            ?: PointsPool()

                        val empoweredCost = definedEnchantments
                            .map { it.empoweredEnchantmentContribution }
                            .maxByOrNull { it.extractSingleCost()?.amount ?: 0 }
                            ?: PointsPool()

                        baseCost + empoweredCost
                    }
                    .fold(PointsPool()) { acc, enchantmentCost ->
                        acc + enchantmentCost
                    }

                return activatorsCosts + markMagicCosts
            }
        }
    }

    sealed interface MarkSpecific: MarkBaseDesignComponent {
        companion object {
            fun makeNew(forMarkBaseType: MarkBaseDesign): MarkSpecific? {
                return when(forMarkBaseType) {
                    is MarkBaseDesign.Powerful -> Powerful()
                    else -> null
                }
            }
        }

        val pointsContribution: PointsPool

        @Serializable
        data class Powerful(
            /**
             * Number of power points obtained from change points conversion (2cp to 1pp ratio)
             * */
            val conversionAmount: Int = 0,
        ): MarkSpecific {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Powerful

            override val pointsContribution: PointsPool
                get() = PointsPool(
                    usedChangePoints = (conversionAmount * 2).cp,
                    gainedPowerPoints = conversionAmount.pp,
                )
        }
    }

    sealed interface EnchantmentSpecific: MarkBaseDesignComponent {
        companion object {
            fun makeNew(forMarkBaseType: MarkBaseDesign): EnchantmentSpecific? {
                return when (forMarkBaseType) {
                    is MarkBaseDesign.Deviancy -> Deviancy()
                    is MarkBaseDesign.Inheritance -> Inheritance()
                    is MarkBaseDesign.Uniform -> Uniform
                    else -> null
                }
            }
        }

        fun pointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool): PointsPool

        fun modifyBasePointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool
        fun modifyEmpoweredPointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool

        @Serializable
        data class Deviancy(
            val useChangeForPowerPointsForBase: Boolean = false,
            val useChangeForPowerPointsForEmpowered: Boolean = false,
        ): EnchantmentSpecific {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Deviancy

            override fun pointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool) = PointsPool()

            private fun modifyPointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool, useChangeForPowerPointsForBase: Boolean): PointsPool {
                if(attachedEnchantment !is DefinedEnchantment.Simple) return enchantmentPointsContribution
                if(enchantmentPointsContribution.extractSingleCost() !is Points.Power) return enchantmentPointsContribution

                return enchantmentPointsContribution.copy(
                    usedChangePoints = if(useChangeForPowerPointsForBase) { enchantmentPointsContribution.usedPowerPoints.amount.cp } else { 0.cp },
                    usedPowerPoints = if(useChangeForPowerPointsForBase) { 0.pp } else { ceil(enchantmentPointsContribution.usedPowerPoints.amount / 2f).toInt().pp },
                )
            }

            override fun modifyBasePointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return modifyPointsContribution(attachedEnchantment, attachedEnchantment.baseEnchantmentContribution, useChangeForPowerPointsForBase)
            }

            override fun modifyEmpoweredPointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return modifyPointsContribution(attachedEnchantment, attachedEnchantment.empoweredEnchantmentContribution, useChangeForPowerPointsForEmpowered)
            }
        }

        @Serializable
        object Uniform: EnchantmentSpecific {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Uniform

            override fun pointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool) = PointsPool()

            private fun modifyPointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool): PointsPool {
                if(attachedEnchantment !is DefinedEnchantment.Simple) return enchantmentPointsContribution
                if(attachedEnchantment.enchantment !is Enchantment.MarkMagic) return enchantmentPointsContribution

                return PointsPool()
            }

            override fun modifyBasePointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return modifyPointsContribution(attachedEnchantment, attachedEnchantment.baseEnchantmentContribution)
            }

            override fun modifyEmpoweredPointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return modifyPointsContribution(attachedEnchantment, attachedEnchantment.empoweredEnchantmentContribution)
            }
        }

        @Serializable
        data class Inheritance(
            val isInherited: Boolean = false,
        ): EnchantmentSpecific {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Inheritance

            override fun pointsContribution(attachedEnchantment: DefinedEnchantment, enchantmentPointsContribution: PointsPool): PointsPool {
                return PointsPool(usedMixedPoints = enchantmentPointsContribution.extractSingleCost()?.amount?.mp ?: 0.mp)
            }

            override fun modifyBasePointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return attachedEnchantment.baseEnchantmentContribution
            }

            override fun modifyEmpoweredPointsContribution(attachedEnchantment: DefinedEnchantment): PointsPool {
                return attachedEnchantment.empoweredEnchantmentContribution
            }
        }
    }

    sealed interface ActivatorSpecific: MarkBaseDesignComponent {
        companion object {
            fun makeNew(forMarkBaseType: MarkBaseDesign): ActivatorSpecific? {
                return when (forMarkBaseType) {
                    is MarkBaseDesign.Uniform -> Uniform
                    else -> null
                }
            }
        }

        fun modifyPointsContribution(activatorPointsContribution: PointsPool): PointsPool

        @Serializable
        object Uniform: ActivatorSpecific {
            override val forMarkBaseType: MarkBaseDesign
                get() = MarkBaseDesign.Uniform

            override fun modifyPointsContribution(activatorPointsContribution: PointsPool): PointsPool {
                return PointsPool()
            }
        }
    }
}