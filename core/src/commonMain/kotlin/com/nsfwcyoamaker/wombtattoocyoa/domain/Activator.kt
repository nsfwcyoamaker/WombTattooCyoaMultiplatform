package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.ui.text.AnnotatedString
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.theme.*
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.domain.generated.resources.*

sealed interface Activator {
    val image: DrawableResource
    val changePointsCost: Points.Change
    val name: AnnotatedString
    val description: AnnotatedString

    companion object {
        val all: List<Activator>
            get() = listOf(
                AtWill,
                Creampie,
                Fingering,
                Ritual,
                Intercourse,
                Painted,
                Publicity,
                Self,
                Delayed,
                Rewrite,
            )
    }

    data object AtWill: Activator {
        override val image: DrawableResource = Res.drawable.activator_at_will
        override val changePointsCost: Points.Change = 400.cp
        override val name: AnnotatedString = AnnotatedString(
            "At Will",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Simply command the power to activate and it does. At mark creation you may choose to assign another person instead to control this activator instead, also giving them control of any enchantments attached to it as if they were the master.",
            SylanSpeech.toSpanStyle()
        )
    }

    data object Creampie: Activator {
        override val image: DrawableResource = Res.drawable.activator_creampie
        override val changePointsCost: Points.Change = 100.cp
        override val name: AnnotatedString = AnnotatedString(
            "Creampie",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "You know when would be a great time to magically activate a girls creampie fetish? When you give her one. Ooh or maybe have her orgasm attached to it. even better.",
            TwinsSpeech.toSpanStyle()
        )
    }

    data object Fingering: Activator {
        override val image: DrawableResource = Res.drawable.activator_fingering
        override val changePointsCost: Points.Change = 200.cp
        override val name: AnnotatedString = AnnotatedString(
            "Fingering",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "A favorite of mine. You must reach inside and push out from beneath the mark to activate. Try not to have her change shape from this activation, or things could get messy.",
            NarratorSpeech.toSpanStyle()
        )
    }

    data object Ritual: Activator {
        override val image: DrawableResource = Res.drawable.activator_ritual
        override val changePointsCost: Points.Change = 50.cp
        override val name: AnnotatedString = AnnotatedString(
            "Ritual",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The ritual is a ceremony that takes at least an hour, where the specifics are up to you. This activation is useful for keeping you or the subject safe during the more unpredictable powers.",
            SylanSpeech.toSpanStyle()
        )
    }

    data object Intercourse: Activator {
        override val image: DrawableResource = Res.drawable.activator_intercourse
        override val changePointsCost: Points.Change = 150.cp
        override val name: AnnotatedString = AnnotatedString(
            "Intercourse",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The obvious thing here is to have her slutty up as soon as you start fucking. But the more fun way is to have something like her super strength be dependent on you giving her the dick",
            TwinsSpeech.toSpanStyle()
        )
    }

    data object Painted: Activator {
        override val image: DrawableResource = Res.drawable.activator_painted
        override val changePointsCost: Points.Change = Points.Change(100)
        override val name: AnnotatedString = AnnotatedString(
            "Painted",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "You must spread your seed directly onto the mark for this activation to work. Perhaps a good way to avoid unwanted pregnancies?",
            ChiSpeech.toSpanStyle()
        )
    }

    data object Publicity: Activator {
        override val image: DrawableResource = Res.drawable.activator_publicity
        override val changePointsCost: Points.Change = 150.cp
        override val name: AnnotatedString = AnnotatedString(
            "Publicity",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The mark bearer must expose the mark and themselves if you wish, in public for the chosen powers to activate. Can either be a thrill or punishment, depending on the woman.",
            TwinsSpeech.toSpanStyle()
        )
    }

    data object Self: Activator {
        override val image: DrawableResource = Res.drawable.activator_self
        override val changePointsCost: Points.Change = 300.cp
        override val name: AnnotatedString = AnnotatedString(
            "Self",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "The same as \"At Will\" but for the subject only. When attached to this activator, any enchantments the mark master would control are instead controlled by the subject.",
            NarratorSpeech.toSpanStyle()
        )
    }

    data object Delayed: Activator {
        override val image: DrawableResource = Res.drawable.activator_delayed
        override val changePointsCost: Points.Change = 100.cp
        override val name: AnnotatedString = AnnotatedString(
            "Delayed",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Enchantments linked to this take effect gradually. You may either select an amount of time for the enchantment to take effect, or a specific condition that causes it to advance. This is a one-way modifier, once the enchantments have reached a certain stage they will always activate at that stage immediately if using another activator.",
            NarratorSpeech.toSpanStyle()
        )
    }

    data object Rewrite: Activator {
        override val image: DrawableResource = Res.drawable.activator_rewrite
        override val changePointsCost: Points.Change = 600.cp
        override val name: AnnotatedString = AnnotatedString(
            "Rewrite",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "Normally when you choose an activation and the powers it controls, that is set in stone. With this enchantment you can once a day rework a mark so that while the activation methods stay the same, which powers they affect can be swapped out.",
            NarratorSpeech.toSpanStyle()
        )
    }

    data object PersonalizedActivator: Activator {
        override val image: DrawableResource = Res.drawable.mark_magic_personalize_activator
        override val changePointsCost: Points.Change = 0.cp
        override val name: AnnotatedString = AnnotatedString(
            "Personalized Activator",
            ChoiceTitleStyle.toSpanStyle()
        )
        override val description: AnnotatedString = AnnotatedString(
            "This activator function is entirely up to you, but can only applied to mark magic enchantments",
            NarratorSpeech.toSpanStyle()
        )
    }
}