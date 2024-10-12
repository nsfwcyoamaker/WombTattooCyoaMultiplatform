package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Enchantment
import com.nsfwcyoamaker.wombtattoocyoa.domain.MarkBaseDesign

sealed interface DefinedEnchantmentBuilder {
    val id: String
    val enchantment: Enchantment?
    val linkedLimiters: List<DefinedLimiter>?
    val linkedActivatorId: String?
    val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?
    val notes: String?

    fun updateEnchantment(enchantment: Enchantment?): DefinedEnchantmentBuilder
    fun updateLinkedLimiters(linkedLimiters: List<DefinedLimiter>?): DefinedEnchantmentBuilder
    fun updateLinkedActivatorId(linkedActivatorId: String?): DefinedEnchantmentBuilder
    fun updateMarkBaseDesignComponent(markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?): DefinedEnchantmentBuilder
    fun updateNotes(notes: String?): DefinedEnchantmentBuilder

    fun build(): DefinedEnchantment?

    companion object {
        operator fun invoke(definedEnchantment: DefinedEnchantment): DefinedEnchantmentBuilder {
            return when(definedEnchantment) {
                is DefinedEnchantment.Drawback -> {
                    Drawback(
                        id = definedEnchantment.id,
                        enchantment = definedEnchantment.enchantment,
                        linkedLimiters = definedEnchantment.linkedLimiters,
                        linkedActivatorId = definedEnchantment.linkedActivatorId,
                        markBaseDesignComponent = definedEnchantment.markBaseDesignComponent,
                        notes = definedEnchantment.notes,
                        override = definedEnchantment.override,
                        paymentType = definedEnchantment.paymentType,
                    )
                }
                is DefinedEnchantment.Simple -> {
                    Simple(
                        id = definedEnchantment.id,
                        enchantment = definedEnchantment.enchantment,
                        linkedLimiters = definedEnchantment.linkedLimiters,
                        linkedActivatorId = definedEnchantment.linkedActivatorId,
                        markBaseDesignComponent = definedEnchantment.markBaseDesignComponent,
                        notes = definedEnchantment.notes,
                        base = definedEnchantment.base,
                        empowered = definedEnchantment.empowered,
                    )
                }
            }
        }

        operator fun invoke(id: String, baseDesign: MarkBaseDesign): DefinedEnchantmentBuilder {
            return Undefined(
                id = id,
                markBaseDesignComponent = MarkBaseDesignComponent.EnchantmentSpecific.makeNew(baseDesign)
            )
        }
    }

    data class Undefined(
        override val id: String,
        override val linkedLimiters: List<DefinedLimiter>? = null,
        override val linkedActivatorId: String? = null,
        override val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific? = null,
        override val notes: String? = null,
    ): DefinedEnchantmentBuilder {
        override val enchantment: Enchantment?
            get() = null

        override fun updateEnchantment(enchantment: Enchantment?): DefinedEnchantmentBuilder {
            return when(enchantment) {
                null -> this
                is Enchantment.Drawback -> Drawback(id, enchantment, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
                else -> Simple(id, enchantment, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
            }
        }
        override fun updateLinkedLimiters(linkedLimiters: List<DefinedLimiter>?) = this.copy(linkedLimiters = linkedLimiters)
        override fun updateLinkedActivatorId(linkedActivatorId: String?) = this.copy(linkedActivatorId = linkedActivatorId)
        override fun updateMarkBaseDesignComponent(markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?) = this.copy(markBaseDesignComponent = markBaseDesignComponent)
        override fun updateNotes(notes: String?) = this.copy(notes = notes)
        override fun build(): DefinedEnchantment? { return null }
    }

    data class Simple(
        override val id: String,
        override val enchantment: Enchantment,
        override val linkedLimiters: List<DefinedLimiter>? = null,
        override val linkedActivatorId: String? = null,
        override val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific? = null,
        override val notes: String? = null,

        val base: PaymentType = PaymentType.NORMAL,
        val empowered: PaymentType? = null,
    ): DefinedEnchantmentBuilder {
        override fun updateEnchantment(enchantment: Enchantment?): DefinedEnchantmentBuilder {
            return when(enchantment) {
                null -> Undefined(id, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
                is Enchantment.Drawback -> Drawback(id, enchantment, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
                else -> this.copy(enchantment = enchantment)
            }
        }
        override fun updateLinkedLimiters(linkedLimiters: List<DefinedLimiter>?) = this.copy(linkedLimiters = linkedLimiters)
        override fun updateLinkedActivatorId(linkedActivatorId: String?) = this.copy(linkedActivatorId = linkedActivatorId)
        override fun updateMarkBaseDesignComponent(markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?) = this.copy(markBaseDesignComponent = markBaseDesignComponent)
        override fun updateNotes(notes: String?) = this.copy(notes = notes)
        fun updateBase(base: PaymentType) = this.copy(base = base)
        fun updateEmpowered(empowered: PaymentType?) = this.copy(empowered = empowered)
        override fun build() = DefinedEnchantment.Simple(
            id = id,
            enchantment = enchantment,
            markBaseDesignComponent = markBaseDesignComponent,
            linkedLimiters = linkedLimiters.orEmpty(),
            linkedActivatorId = linkedActivatorId,
            notes = notes.orEmpty(),
            base = base,
            empowered = empowered,
        )
    }

    data class Drawback(
        override val id: String,
        override val enchantment: Enchantment.Drawback,
        override val linkedLimiters: List<DefinedLimiter>? = null,
        override val linkedActivatorId: String? = null,
        override val markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific? = null,
        override val notes: String? = null,

        val override: DefinedEnchantment.Drawback.DrawbackPointsContributionType? = null,
        val paymentType: PaymentType? = null,
    ): DefinedEnchantmentBuilder {
        override fun updateEnchantment(enchantment: Enchantment?): DefinedEnchantmentBuilder {
            return when(enchantment) {
                null -> this
                is Enchantment.Drawback -> Drawback(id, enchantment, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
                else -> Simple(id, enchantment, linkedLimiters, linkedActivatorId, markBaseDesignComponent, notes)
            }
        }
        override fun updateLinkedLimiters(linkedLimiters: List<DefinedLimiter>?) = this.copy(linkedLimiters = linkedLimiters)
        override fun updateLinkedActivatorId(linkedActivatorId: String?) = this.copy(linkedActivatorId = linkedActivatorId)
        override fun updateMarkBaseDesignComponent(markBaseDesignComponent: MarkBaseDesignComponent.EnchantmentSpecific?) = this.copy(markBaseDesignComponent = markBaseDesignComponent)
        override fun updateNotes(notes: String?) = this.copy(notes = notes)
        fun updateOverride(override: DefinedEnchantment.Drawback.DrawbackPointsContributionType?) = this.copy(override = override)
        fun updatePaymentType(paymentType: PaymentType?) = this.copy(paymentType = paymentType)
        override fun build() = DefinedEnchantment.Drawback(
            id = id,
            enchantment = enchantment,
            markBaseDesignComponent = markBaseDesignComponent,
            linkedLimiters = linkedLimiters.orEmpty(),
            linkedActivatorId = linkedActivatorId,
            notes = notes.orEmpty(),
            override = override,
            paymentType = paymentType ?: PaymentType.NORMAL,
        )
    }
}