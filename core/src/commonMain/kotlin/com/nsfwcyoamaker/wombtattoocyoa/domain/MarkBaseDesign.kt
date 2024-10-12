package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.nsfwcyoamaker.wombtattoocyoa.theme.*
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.core.generated.resources.*

sealed interface MarkBaseDesign {
    val requirement: List<MarkSpecialization>?

    val image: DrawableResource
    @get:Composable
    val name: AnnotatedString
    @get:Composable
    val description: AnnotatedString

    companion object {
        val all: List<MarkBaseDesign>
            get() = listOf(
                Deviancy,
                Inheritance,
                Rebirth,
                Borrowing,
                Powerful,
                Twins,
                UltimateRolePlayer,
                Uniform,
                Girl
            )
    }

    data object Deviancy: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_deviancy
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Deviancy", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = buildAnnotatedString {
                withStyle(TwinsSpeech.toSpanStyle()) {
                    append("This design is made especially for harems to draw out their full sexual potential. Enchantments from the Sexual Powers and Mind Control subsections may be purchased using ")
                    withStyle(Points.Change.standardStyle.toSpanStyle()) { append("Change Points") }
                    append(" instead of ")
                    withStyle(Points.Power.standardStyle.toSpanStyle()) { append("Power Points") }
                    append(", or for half price if using ")
                    withStyle(Points.Power.standardStyle.toSpanStyle()) { append("Power Points") }
                    append(" normally.")
                }
            }
    }

    data object Inheritance: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_inheritance
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Inheritance", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() {
                val chiSpeech = ChiSpeech
                val narratorSpeech = NarratorSpeech

                return remember(chiSpeech, narratorSpeech) {
                    buildAnnotatedString {
                        withStyle(chiSpeech.toParagraphStyle()) {
                            withStyle(chiSpeech.toSpanStyle()) {
                                append("If you\'re thinking about starting a family, this mark will help your kids as well. Pick ")
                                withStyle(Points.Mixed.standardStyle.toSpanStyle()) { append("1000 Mixed Points") }
                                append(" worth of enchantments you\'ve purchased for any of your marks, and you can give those to your children. Also to the children of any of your marked subjects, grandchildren, and any other direct descendants, forever.")
                            }
                        }
                        withStyle(narratorSpeech.toParagraphStyle()) {
                            withStyle(narratorSpeech.toSpanStyle()) {
                                append("\nAppearance traits will be what they grow into, other enchantments activate at the age of your choosing.")
                            }
                        }
                    }
                }
            }
    }

    data object Rebirth: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_rebirth
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Rebirth", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Power does you no good if you aren\'t alive to use it. With this design, if you die you will be reborn as a child to one of your marked subjects. Other bearers of this mark can also be reborn at your discretion.", SylanSpeech.toSpanStyle())
    }

    data object Borrowing: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_borrowing
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Borrowing", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Any time you have sex with one of your mark bearers, you may share the effects of any number of their enchantments for one day.", NarratorSpeech.toSpanStyle())
    }

    data object Powerful: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_powerful
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Powerful", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = buildAnnotatedString {
            withStyle(NarratorSpeech.toSpanStyle()) {
                append("Any unspent ")
                withStyle(Points.Change.standardStyle.toSpanStyle()) { append("Change Points") }
                append(" assigned to each mark can be converted into ")
                withStyle(Points.Power.standardStyle.toSpanStyle()) { append("Power Points") }
                append(", exceeding the normal power point limit for the mark if using the Harem or Squad specialization. Exchange is done at a ")
                withStyle(Points.Change.standardStyle.toSpanStyle()) { append("2cp") }
                append(" to ")
                withStyle(Points.Power.standardStyle.toSpanStyle()) { append("1pp") }
                append(" ratio.")
            }
        }
    }

    data object Twins: MarkBaseDesign {
        override val requirement: List<MarkSpecialization> = listOf(
            MarkSpecialization.Contract,
            MarkSpecialization.Forced
        )
        override val image: DrawableResource = Res.drawable.base_design_twins
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Twins", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Your subject is magically split into two people. They will be treated as twins by society and they both have identical marks and enchantments.", ChiSpeech.toSpanStyle())
    }

    data object UltimateRolePlayer: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_urp
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Ultimate Role Player", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = buildAnnotatedString {
            withStyle(ChiSpeech.toSpanStyle()) {
                append("Once per week you remove a single appearance, personality, or fetish enchantment from each of your marks, regaining the ")
                withStyle(Points.Change.standardStyle.toSpanStyle()) { append("Change Points") }
                append(" and ")
                withStyle(Points.Power.standardStyle.toSpanStyle()) { append("Power Points") }
                append(" spent. You may then add any number of new enchantments from those categories so long as doing so does not exceed any point limits. When adding new enchantments, you may link them to activators and customize any details.")
            }
        }
    }

    data object Uniform: MarkBaseDesign {
        override val requirement: List<MarkSpecialization> = listOf(MarkSpecialization.Harem, MarkSpecialization.Squad)
        override val image: DrawableResource = Res.drawable.base_design_uniform
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Uniform", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = AnnotatedString("You only have to buy Activators and Mark Magic enchantments once, not individually for each subject. They also do not count towards the point limits on any individual marks.", TwinsSpeech.toSpanStyle())
    }

    data object Girl: MarkBaseDesign {
        override val requirement: List<MarkSpecialization>? = null
        override val image: DrawableResource = Res.drawable.base_design_girl
        override val name: AnnotatedString 
            @Composable 
            get() = AnnotatedString("Girl", ChoiceTitleStyle.toSpanStyle())
        override val description: AnnotatedString 
            @Composable 
            get() = AnnotatedString("With this mark, the subject\'s body will be transformed completely into that of a rather attractive young adult girl of average proportions. This is a cheap way to allow formerly male subjects to hold on to the Mark\'s power, or give females an acceptable baseline form without needing any enchantments.", NarratorSpeech.toSpanStyle())
    }
}