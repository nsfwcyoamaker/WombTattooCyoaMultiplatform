package com.nsfwcyoamaker.wombtattoocyoa.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import wombtattoocyoa.domain.generated.resources.*
import wombtattoocyoa.domain.generated.resources.Res
import wombtattoocyoa.domain.generated.resources.comic_spans
import wombtattoocyoa.domain.generated.resources.exton
import wombtattoocyoa.domain.generated.resources.getsu_magic

val ChoiceTitleStyle: TextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    color = Color.White,
    textAlign = TextAlign.Center,
    fontSize = 20.sp,
)

val ChoiceRequirementStyle: TextStyle = TextStyle(
    fontWeight = FontWeight.Light,
    color = Color.White,
    textAlign = TextAlign.Center,
    fontSize = 10.sp,
)

val SectionTitleStyle: TextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    color = Color.White,
    textAlign = TextAlign.Center,
    fontSize = 28.sp,
    shadow = Shadow(Color.Black, offset = Offset(1f, 1f), blurRadius = 10f)
)

val NarratorSpeechBig: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(Res.font.autobus_bold)),
    color = NarratorYellow,
    letterSpacing = 0.5.sp,
)

val NarratorSpeech: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(Res.font.autobus_bold)),
    color = NarratorYellow,
    letterSpacing = 0.5.sp,
)

val ChiSpeechBig: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(Res.font.my_happy_ending)),
    color = DesignerChiPearl,
    letterSpacing = 0.5.sp,
)

val ChiSpeech: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(Res.font.my_happy_ending)),
    color = DesignerChiPearl,
    letterSpacing = 0.5.sp,
)

val TwinsSpeechBig: TextStyle  = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(Res.font.comic_spans)),
    color = DesignerTwinsTeal,
    letterSpacing = 0.5.sp,
)

val TwinsSpeech: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(Res.font.comic_spans)),
    color = DesignerTwinsTeal,
    letterSpacing = 0.5.sp,
)

val SylanSpeechBig: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(Res.font.exton)),
    color = DesignerSylanMusky,
    letterSpacing = 0.5.sp,
)

val SylanSpeech: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    fontFamily = FontFamily(Font(Res.font.exton)),
    color = DesignerSylanMusky,
    letterSpacing = 0.5.sp,
)

val GetsuMagic: TextStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = MainColor,
    fontFamily = FontFamily(Font(Res.font.getsu_magic)),
    letterSpacing = 0.5.sp,
)