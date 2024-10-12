package com.nsfwcyoamaker.wombtattoocyoa.state

import kotlinx.serialization.Serializable

@Serializable
data class URPChange(
    val removedDefinedEnchantment: DefinedEnchantment,
    val addedDefinedEnchantment: DefinedEnchantment,
    val notes: String = "",
)
