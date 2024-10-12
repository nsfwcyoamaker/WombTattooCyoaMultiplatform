package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkSpecialization
import kotlinx.serialization.Serializable

@Serializable
data class DefinedMark(
    val id: String,
    val enchantments: List<DefinedEnchantment> = emptyList(),
    val activators: List<DefinedActivator> = emptyList(),
    val notes: String = "",
    val markBaseDesignComponent: MarkBaseDesignComponent.MarkSpecific? = null,
) {
    fun getPointsContribution(specialization: MarkSpecialization): PointsPool {
        val pool = enchantments.map { it.pointsContribution }
            .plus(activators.map { it.pointsContribution })
            .fold(
                PointsPool(
                    baseChangePoints = specialization.changePointsPerMark ?: specialization.changePointsPool,
                    basePowerPoints = specialization.powerPointsPerMark ?: specialization.powerPointsPool,
                )
            ) { acc, pointsPool -> acc + pointsPool }

        val component = markBaseDesignComponent?.pointsContribution ?: PointsPool()

        return pool + component
    }

    fun updateWith(base: MarkBaseDesign): DefinedMark {
        return copy(
            markBaseDesignComponent = markBaseDesignComponent
                ?.takeIf { it.forMarkBaseType == base }
                ?: MarkBaseDesignComponent.MarkSpecific.makeNew(base),
            enchantments = enchantments.map { enchantment -> enchantment.updateWith(base) },
            activators = activators.map { activator -> activator.updateWith(base) },
        )
    }
}