package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp
import com.nsfwcyoamaker.wombtattoocyoa.theme.*
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.domain.generated.resources.*

sealed interface Limiter {
    val image: DrawableResource
    val name: AnnotatedString
    val description: AnnotatedString
    val cpCost: Points.Change?
    val ppCost: Points.Power?
    val multiplier: Float

    companion object {
        val all: List<Limiter>
            get() = listOf(
                Blinded,
                CumSoaked,
                Location,
                Sex,
                Exposed,
                Gagged,
                Plugged,
                Pregnancy,
                Marriage,
                Murder,
                Temporal,
                Teamwork,
            )
    }

    sealed class Simple: Limiter {
        override val ppCost: Points.Power?
            get() = null
        override val cpCost: Points.Change?
            get() = null
    }

    data object Blinded: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_blinded
        override val name: AnnotatedString = AnnotatedString(
            "Blinded",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The linked enchantments only function if the bearer is blindfolded or actually blind. Simply closing her eyes won't cut it.",
            NarratorSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.5f
    }

    data object CumSoaked: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_cum_soaked
        override val name: AnnotatedString = AnnotatedString(
            "Cum Soaked",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Give her some kind of superpower fo fight super villains with but she has to do it before all your spunk dries up, a real fun time limit.",
            TwinsSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.5f
    }

    data object Location: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_location
        override val name: AnnotatedString = AnnotatedString(
            "Location",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "A common tool to limit the reach of some enchantments. Choose an area when you apply the mark no larger than one square mile and linked enchantments will only function in that area. The location can be changed once per year.",
            SylanSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.75f
    }

    data object Sex: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_sex
        override val name: AnnotatedString = AnnotatedString(
            "Sex",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "My favorite one is to have her go full lesbian when getting the dick, so she goes from super horny to impossibly confused. Linked enchantments work during sex and for about an hour afterward.",
            NarratorSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.75f
    }

    data object Exposed: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_exposed
        override val name: AnnotatedString = AnnotatedString(
            "Exposed",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Her enchantments only work while she's completely naked. Anything more than simple jewelry will cancel the effect. Cannot be combined with Normalized",
            NarratorSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.5f
    }

    data object Gagged: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_gagged
        override val name: AnnotatedString = AnnotatedString(
            "Gagged",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The enchantments require the bearer to be unable to speak Usually involves a ball gag although you could just ram you cock down her throat.",
            TwinsSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.75f
    }

    data object Plugged: Limiter {
        override val image: DrawableResource = Res.drawable.limiter_plugged
        override val name: AnnotatedString = AnnotatedString(
            "Plugged",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Choose any sexual orifices. They all have to be filled for the enchantments to work. It's a +5% discount for each one, 10% if they specifically need vibrators instead, but this cannot go above 50%.",
            NarratorSpeech.toSpanStyle()
        )
        override val cpCost: Points.Change?
            get() = null
        override val ppCost: Points.Power?
            get() = null
        override val multiplier: Float = 1f
    }

    data object Pregnancy: Simple() {
        override val image: DrawableResource = Res.drawable.fetish_pregnancy
        override val name: AnnotatedString = AnnotatedString(
            "Pregnancy",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "A little extra motivation to make sure your relationship is fruitful. Her enchantments only work while she's with child. The Pregnant appearance enchantment doesn't count even in its empowered form.",
            ChiSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.75f
    }

    data object Marriage: Limiter {
        override val image: DrawableResource = Res.drawable.limiter_marriage
        override val name: AnnotatedString = AnnotatedString(
            "Marriage",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = buildAnnotatedString {
            withStyle(ChiSpeech.toSpanStyle()) {
                append("The subject must be in a monogamous marriage with you and never be unfaithful. This limiter costs points instead of providing a discount, but can be useful on its own.\n")
            }
            withStyle(TwinsSpeech.toSpanStyle()) {
                append("It's up to you to decide if sex with your other marks counts as cheating.")
            }
        }
        override val cpCost: Points.Change = 100.cp
        override val ppCost: Points.Power = 100.pp
        override val multiplier: Float = 1f
    }

    data object Murder: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_murder
        override val name: AnnotatedString = AnnotatedString(
            "Murder",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "A client meant for this limiter to be a deterrent, requiring the subject commit a murder for the enchantments to be usable for one hour. They misjudged their subject, don't make the same mistake.",
            SylanSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.25f
    }

    data object Temporal: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_temporal
        override val name: AnnotatedString = AnnotatedString(
            "Temporal",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Most who take this enchantment do so for thematic purposes. Creating the equivalent of a Mr. Hyde of the subject that comes out at night or a werewolf at the full moon. Choose any regular period of time not more than half of each year and the enchantments only work then.",
            NarratorSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.75f
    }

    data object Teamwork: Simple() {
        override val image: DrawableResource = Res.drawable.limiter_teamwork
        override val name: AnnotatedString = AnnotatedString(
            "Teamwork",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Two or more marked subjects are linked together. Unless they are willingly and actively working together as a group the linked enchantments will not work",
            TwinsSpeech.toSpanStyle()
        )
        override val multiplier: Float = 0.5f
    }
}