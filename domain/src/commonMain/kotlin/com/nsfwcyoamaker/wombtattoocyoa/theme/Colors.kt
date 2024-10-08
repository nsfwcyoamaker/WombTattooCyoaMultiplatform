package com.nsfwcyoamaker.wombtattoocyoa.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val MainColor = Color(0xFFEA334B)
val NarratorYellow = Color(0xFFFFE700)
val DesignerChiPearl = Color(0xFFE5CEEA)
val DesignerTwinsTeal = Color(0xFF72C6EA)
val DesignerSylanMusky= Color(0xFF00AA00)
val ChoiceBorderColor = Color(0xFFB200FF)

val MyTextFieldColors: TextFieldColors
    @Composable
    get() = TextFieldDefaults.outlinedTextFieldColors(
        textColor = NarratorYellow,
        cursorColor = NarratorYellow,
        focusedBorderColor = MainColor,
        focusedLabelColor = MainColor,
        unfocusedBorderColor = Color.LightGray,
        unfocusedLabelColor = Color.LightGray,
    )

val MySwitchColors: SwitchColors
    @Composable
    get() = SwitchDefaults.colors(
        checkedThumbColor = MainColor,
        checkedTrackColor = MainColor,
        checkedTrackAlpha = 0.54f,
        uncheckedThumbColor = MaterialTheme.colors.surface,
        uncheckedTrackColor = MaterialTheme.colors.onSurface,
        uncheckedTrackAlpha = 0.38f,
    )