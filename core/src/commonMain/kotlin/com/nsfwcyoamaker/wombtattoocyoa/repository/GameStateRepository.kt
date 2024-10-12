package com.nsfwcyoamaker.wombtattoocyoa.repository

import com.nsfwcyoamaker.wombtattoocyoa.base.mapState
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkSpecialization
import com.nsfwcyoamaker.wombtattoocyoa.state.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class GameStateRepository {
    private val scope by lazy { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> by ::_gameState

    val pointsGlobalState: StateFlow<PointsGlobalState> = gameState.mapState(scope) { gameState ->
        gameState.pointsPool
    }

    fun setSpecialization(newSpec: MarkSpecialization?) {
        _gameState.update { currentState ->
            val spec = newSpec.takeIf { currentState.markSpecialization != newSpec }

            val specComponent = spec?.let { specNotNull ->
                currentState.markSpecializationComponent
                    ?.takeIf { it.compatibleWith(specNotNull) }
                    ?: MarkSpecializationComponent.makeNew(spec)
            }

            val base = currentState.markBaseDesign?.takeIf { it.isValidWith(spec) }

            val baseComponent = base?.let { baseNotNull ->
                currentState.markBaseDesignComponent
                    ?.takeIf { it.forMarkBaseType == baseNotNull }
                    ?: MarkBaseDesignComponent.Global.makeNew(baseNotNull)
            }

            val designs = runCatching { currentState.definedMarks.map { useRightComponents(it, spec, base) } }.getOrElse { emptyList() }
                .take(spec?.markDesignsLimit ?: Int.MAX_VALUE)

            val offer = currentState.acceptedOffer && currentState.definedMarks.isNotEmpty()

            currentState.copy(
                markSpecialization = spec,
                markSpecializationComponent = specComponent,
                markBaseDesign = base,
                markBaseDesignComponent = baseComponent,
                definedMarks = designs,
                acceptedOffer = offer,
            )
        }
    }

    fun setMarkBaseDesign(newBaseDesign: MarkBaseDesign?) {
        _gameState.update { currentState ->
            val base = newBaseDesign?.takeIf { currentState.markBaseDesign != newBaseDesign }

            if(base != null) {
                if(!newBaseDesign.isValidWith(currentState.markSpecialization))
                    throw RuntimeException()
            }

            val baseComponent = base?.let { baseNotNull ->
                currentState.markBaseDesignComponent
                    ?.takeIf { it.forMarkBaseType == baseNotNull }
                    ?: MarkBaseDesignComponent.Global.makeNew(baseNotNull)
            }

            val designs = runCatching { currentState.definedMarks.map { useRightComponents(it, currentState.markSpecialization, base) } }.getOrElse { emptyList() }

            val offer = currentState.acceptedOffer && currentState.definedMarks.isNotEmpty()

            currentState.copy(
                markBaseDesign = base,
                markBaseDesignComponent = baseComponent,
                definedMarks = designs,
                acceptedOffer = offer,
            )
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun addDesign() {
        _gameState.update { currentState ->
            val newDesign = newDesignOf(Uuid.random().toString(), currentState.markSpecialization, currentState.markBaseDesign)

            currentState.copy(definedMarks = currentState.definedMarks + newDesign)
        }
    }

    fun deleteDesign(design: DefinedMark) {
        _gameState.update { currentState ->
            val designs = currentState.definedMarks - design
            val offer = currentState.acceptedOffer && currentState.definedMarks.isNotEmpty()

            currentState.copy(
                definedMarks = designs,
                acceptedOffer = offer,
            )
        }
    }

    fun updateDesignedMark(design: DefinedMark) {
        _gameState.update { currentState ->
            val newList = currentState.definedMarks
                .toMutableList()
                .apply {
                    val old = this.singleOrNull { it.id == design.id } ?: return@update currentState
                    val index = this.indexOf(old).takeIf { it != -1 } ?: return@update currentState

                    this[index] = design
                }
                .toList()

            currentState.copy(definedMarks = newList)
        }
    }

    fun setOfferAccepted(accepted: Boolean) {
        _gameState.update { currentState ->
            currentState.copy(
                acceptedOffer = accepted && currentState.definedMarks.isNotEmpty()
            )
        }
    }

    companion object {
        private fun MarkBaseDesign.isValidWith(specialization: MarkSpecialization?): Boolean {
            val requirements = this.requirement?.takeIf { it.isNotEmpty() } ?: return true
            return requirements.contains(specialization)
        }

        private fun useRightComponents(design: DefinedMark, spec: MarkSpecialization?, base: MarkBaseDesign?): DefinedMark {
            if(spec == null || base == null)
                throw RuntimeException("can't add design without selecting specialization and base design first")

            return design.updateWith(base)
        }

        private fun newDesignOf(id: String, spec: MarkSpecialization?, base: MarkBaseDesign?): DefinedMark {
            return useRightComponents(DefinedMark(id), spec, base)
        }
    }
}