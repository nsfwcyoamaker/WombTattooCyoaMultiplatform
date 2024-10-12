package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkSpecialization
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp
import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val markSpecialization: MarkSpecialization? = null,
    val markSpecializationComponent: MarkSpecializationComponent? = null,
    val markBaseDesign: MarkBaseDesign? = null,
    val markBaseDesignComponent: MarkBaseDesignComponent.Global? = null,
    val definedMarks: List<DefinedMark> = emptyList(),
    val acceptedOffer: Boolean = false,
) {
    val pointsPool: PointsGlobalState by lazy {
        markSpecialization?.let { spec ->
            val pointsByMark = definedMarks.associateBy(
                keySelector = { it.id },
                valueTransform = { it.getPointsContribution(spec) },
            )

            val specComponentPointsPool: PointsPool = markSpecializationComponent?.pointsContribution ?: PointsPool()

            val baseComponentPointsPool: PointsPool = markBaseDesignComponent?.getPointsContribution(definedMarks) ?: PointsPool()

            val evaluatedPools = pointsByMark.values + specComponentPointsPool + baseComponentPointsPool

            PointsGlobalState(
                totalPointsPool = PointsPool(
                    baseChangePoints = spec.changePointsPool,
                    basePowerPoints = spec.powerPointsPool,
                    baseMixedPoints = if (acceptedOffer) 2000.mp else 0.mp,

                    usedChangePoints = evaluatedPools.sumOf { it.usedChangePoints.amount }.cp,
                    usedPowerPoints = evaluatedPools.sumOf { it.usedPowerPoints.amount }.pp,
                    usedMixedPoints = evaluatedPools.sumOf { it.usedMixedPoints.amount }.mp,

                    gainedChangePoints = evaluatedPools.sumOf { it.gainedChangePoints.amount }.cp,
                    gainedPowerPoints = evaluatedPools.sumOf { it.gainedPowerPoints.amount }.pp,
                    gainedMixedPoints = evaluatedPools.sumOf { it.gainedMixedPoints.amount }.mp,
                ),
                pointsByMark
            )
        } ?: PointsGlobalState()
    }
}