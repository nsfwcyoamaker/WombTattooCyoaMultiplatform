package com.nsfwcyoamaker.wombtattoocyoa.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import wombtattoocyoa.core.generated.resources.*

val ChoiceTitleStyle: TextStyle 
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
    )

val ChoiceRequirementStyle: TextStyle 
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Light,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 10.sp,
    )

val SectionTitleStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        shadow = Shadow(Color.Black, offset = Offset(1f, 1f), blurRadius = 10f)
    )

val NarratorSpeechBig: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(Res.font.autobus_bold)),
        color = NarratorYellow,
        letterSpacing = 0.5.sp,
    )

val NarratorSpeech: TextStyle
    @Composable
    get() = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(Res.font.autobus_bold)),
    color = NarratorYellow,
    letterSpacing = 0.5.sp,
)

val ChiSpeechBig: TextStyle
    @Composable
    get() = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(Res.font.my_happy_ending)),
    color = DesignerChiPearl,
    letterSpacing = 0.5.sp,
)

val ChiSpeech: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(Res.font.my_happy_ending)),
        color = DesignerChiPearl,
        letterSpacing = 0.5.sp,
    )

val TwinsSpeechBig: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(Res.font.comic_spans)),
        color = DesignerTwinsTeal,
        letterSpacing = 0.5.sp,
    )

val TwinsSpeech: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(Res.font.comic_spans)),
        color = DesignerTwinsTeal,
        letterSpacing = 0.5.sp,
    )

val SylanSpeechBig: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(Res.font.exton)),
        color = DesignerSylanMusky,
        letterSpacing = 0.5.sp,
    )

val SylanSpeech: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(Res.font.exton)),
        color = DesignerSylanMusky,
        letterSpacing = 0.5.sp,
    )

val GetsuMagic: TextStyle
    @Composable
    get() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = MainColor,
        fontFamily = FontFamily(Font(Res.font.getsu_magic)),
        letterSpacing = 0.5.sp,
    )