package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp
import com.nsfwcyoamaker.wombtattoocyoa.theme.*
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.core.generated.resources.*

sealed interface MarkSpecialization {
    val changePointsPool: Points.Change
    val powerPointsPool: Points.Power

    val changePointsPerMark: Points.Change?
    val powerPointsPerMark: Points.Power?

    val markDesignsLimit: Int?
    val canPlayerGetEnchantments: Boolean

    val image: DrawableResource
    @get:Composable
    val name: AnnotatedString
    @get:Composable
    val description: AnnotatedString

    data object Contract: MarkSpecialization {
        override val changePointsPool: Points.Change = 8000.cp
        override val powerPointsPool: Points.Power = 8000.pp
        override val changePointsPerMark: Points.Change? = null
        override val powerPointsPerMark: Points.Power? = null
        override val markDesignsLimit: Int = 1
        override val canPlayerGetEnchantments: Boolean = true

        override val image: DrawableResource = Res.drawable.specialization_contract
        override val name: AnnotatedString
            @Composable
            get() = AnnotatedString("Contract", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = AnnotatedString("By entering into a contract, usually along with a marriage ceremony, your subject willingly accepts the mark and all that comes with it. There is no way to deceive or coerce someone down this path. They must truly want it.", ChiSpeech.toSpanStyle())
    }

    data object Forced: MarkSpecialization {
        override val changePointsPool: Points.Change = 6000.cp
        override val powerPointsPool: Points.Power = 6000.pp
        override val changePointsPerMark: Points.Change? = null
        override val powerPointsPerMark: Points.Power? = null
        override val markDesignsLimit: Int = 1
        override val canPlayerGetEnchantments: Boolean = true

        override val image: DrawableResource = Res.drawable.specialization_forced
        override val name: AnnotatedString
            @Composable 
            get() = AnnotatedString("Forced", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = AnnotatedString("Or you can simply force the mark onto someone. We will not aid in your attempt at kidnap or other such methods. So you may have to spend some points into making the subject you can get, into the lover you want.", ChiSpeech.toSpanStyle())
    }

    data object Harem: MarkSpecialization {
        override val changePointsPool: Points.Change = 28000.cp
        override val powerPointsPool: Points.Power = 7000.pp
        override val changePointsPerMark: Points.Change = 4000.cp
        override val powerPointsPerMark: Points.Power = 1000.pp
        override val markDesignsLimit: Int? = null
        override val canPlayerGetEnchantments: Boolean = false

        override val image: DrawableResource = Res.drawable.specialization_harem
        override val name: AnnotatedString
            @Composable 
            get()  = AnnotatedString("Harem", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = buildAnnotatedString {
            withStyle(TwinsSpeech.toSpanStyle()) {
                append("Now we are talking. When with the harem you can have as you go many girls as you want. Well, as many as you can afford actually. Each qirl requires their own mark and you gotta spend your power points for each one. Each harem mark can\'t hold very as much power on its own though. Only about ")
                withStyle(Points.Change.standardStyle.toSpanStyle()) { append("4000 Change Points") }
                append(" and ")
                withStyle(Points.Power.standardStyle.toSpanStyle()) { append("1000 Power Points") }
                append(" per mark")
            }
        }
    }

    data object Squad: MarkSpecialization {
        override val changePointsPool: Points.Change = 8000.cp
        override val powerPointsPool: Points.Power = 14000.pp
        override val changePointsPerMark: Points.Change = 2000.cp
        override val powerPointsPerMark: Points.Power = 3500.pp
        override val markDesignsLimit: Int = 5
        override val canPlayerGetEnchantments: Boolean = false

        override val image: DrawableResource = Res.drawable.specialization_squad
        override val name: AnnotatedString
            @Composable 
            get()  = AnnotatedString("Squad", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = buildAnnotatedString {
            withStyle(TwinsSpeech.toSpanStyle()) {
                append("This is where things get interesting. Ever wanted your own group of girts that transform to fight evil before heading back into one giant bed with you? Squad marks are each stronger. but you cannot mark more than 5 individuals. Each squad mark has a limit of ")
                withStyle(Points.Change.standardStyle.toSpanStyle()) { append("2000 Change Points") }
                append(" and ")
                withStyle(Points.Power.standardStyle.toSpanStyle()) { append("3500 Power Points") }
                append(".")
            }
        }
    }

    data object Copulation: MarkSpecialization {
        override val changePointsPool: Points.Change = 1500.cp
        override val powerPointsPool: Points.Power = 1500.pp
        override val changePointsPerMark: Points.Change? = null
        override val powerPointsPerMark: Points.Power? = null
        override val markDesignsLimit: Int = 1
        override val canPlayerGetEnchantments: Boolean = false

        override val image: DrawableResource = Res.drawable.specialization_copulation
        override val name: AnnotatedString
            @Composable 
            get()  = AnnotatedString("Copulation", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = AnnotatedString("A simple matter really. Just have intercourse with someone until completion and the mark will appear on them. You decide what aspects of the mark you have designed that the subject will receive as the mark is made.", SylanSpeech.toSpanStyle())
    }

    data object Insemination: MarkSpecialization {
        override val changePointsPool: Points.Change = 2000.cp
        override val powerPointsPool: Points.Power = 2000.pp
        override val changePointsPerMark: Points.Change? = null
        override val powerPointsPerMark: Points.Power? = null
        override val markDesignsLimit: Int = 1
        override val canPlayerGetEnchantments: Boolean = false

        override val image: DrawableResource = Res.drawable.specialization_insemination
        override val name: AnnotatedString
            @Composable 
            get()  = AnnotatedString("Insemination", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString
            @Composable 
            get() = AnnotatedString("The difficult, if more traditional path. The mark does not require a birth, only impregnation. Just as before you decide what aspects of the mark you have designed that the subject will receive as the mark is made. This option will also work in the other direction if you have the right equipment, marking the person who impregnates you.", SylanSpeech.toSpanStyle())
    }
}