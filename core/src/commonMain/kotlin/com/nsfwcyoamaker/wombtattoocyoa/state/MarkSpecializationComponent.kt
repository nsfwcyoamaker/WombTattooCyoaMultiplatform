package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkSpecialization
import kotlinx.serialization.Serializable


@Serializable
sealed interface MarkSpecializationComponent {
    val specialization: MarkSpecialization
    fun compatibleWith(specialization: MarkSpecialization): Boolean

    val pointsContribution: PointsPool

    companion object {
        fun makeNew(
            specialization: MarkSpecialization,
        ): MarkSpecializationComponent? = when(specialization) {
            is MarkSpecialization.Contract, is MarkSpecialization.Forced -> Chi(specialization)
            else -> null
        }
    }

    @Serializable
    data class Chi(
        override val specialization: MarkSpecialization,
        val selfEnchantments: List<DefinedEnchantment> = emptyList(),
    ): MarkSpecializationComponent {
        override fun compatibleWith(specialization: MarkSpecialization): Boolean {
            return specialization in listOf(MarkSpecialization.Contract, MarkSpecialization.Forced)
        }

        override val pointsContribution: PointsPool
            get() = selfEnchantments.fold(PointsPool()) { acc, it -> acc + it.pointsContribution }
    }
}