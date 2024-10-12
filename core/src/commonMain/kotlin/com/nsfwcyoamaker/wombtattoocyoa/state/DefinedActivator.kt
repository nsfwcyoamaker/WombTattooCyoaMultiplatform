package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Activator
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import kotlinx.serialization.Serializable

@Serializable
data class DefinedActivator(
    val id: String,
    val activator: Activator,
    val note: String = "",
    val markBaseDesignComponent: MarkBaseDesignComponent.ActivatorSpecific? = null,
) {
    fun updateWith(base: MarkBaseDesign): DefinedActivator {
        return copy(
            markBaseDesignComponent = markBaseDesignComponent
                ?.takeIf { it.forMarkBaseType == base }
                ?: MarkBaseDesignComponent.ActivatorSpecific.makeNew(base),
        )
    }

    val pointsContribution: PointsPool by lazy {
        PointsPool(usedChangePoints = activator.changePointsCost).let { activatorPoints ->
            markBaseDesignComponent?.modifyPointsContribution(activatorPoints) ?: activatorPoints
        }
    }
}
