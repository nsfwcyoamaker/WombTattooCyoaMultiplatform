package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Change.Companion.cp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Mixed.Companion.mp
import com.nsfwcyoamaker.wombtattoocyoa.domain.Points.Power.Companion.pp
import kotlinx.serialization.Serializable
import com.nsfwcyoamaker.wombtattoocyoa.theme.*
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.core.generated.resources.*

@Serializable
sealed interface Enchantment {
    val image: DrawableResource
    val standardCost: Points
    val empoweredCost: Points?
    @get:Composable
    val name: AnnotatedString
    @get:Composable
    val description: AnnotatedString
    @get:Composable
    val caption: AnnotatedString?
    val requiredSpecialization: List<MarkSpecialization>?

    @Serializable
    sealed interface MultiBuy: Enchantment {
        val additionalPurchasesCost: Points
    }

    @Serializable
    sealed interface Category {
        @get:Composable
        val name: AnnotatedString
        @get:Composable
        val description: AnnotatedString
        @get:Composable
        val additionalInfo: AnnotatedString?
        val sectionHeaderImage: SectionHeaderImage
        val enchantments: Map<String?, List<Enchantment>>

        companion object {
            val all = listOf(
                MarkMagic,
                Drawback,
                Appearance,
                Personality,
            )
        }

        @Serializable
        data object MarkMagic: Category {
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString("Mark Magic")
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString("Some of the enchantments we weave into a design are more about the mark itself than the subject it is inked onto. These powers tend to be minor, visually based changes that help make a mark unique or special. Like almost all powers they can be tied to an activation trigger, turning them on or off.")
            override val additionalInfo: AnnotatedString?
                @Composable 
                get() = null
            override val sectionHeaderImage: SectionHeaderImage
                get() = SectionHeaderImage.MarkMagic
            override val enchantments: Map<String?, List<Enchantment>>
                get() = Enchantment.MarkMagic.all.groupBy { null }
        }

        @Serializable
        data object Drawback: Category {
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString("Drawbacks")
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString("There are a select few enchantments we can offer that can strengthen the mark, granting additional Power Points to use elsewhere. This can be done by overloading the bearer's sexuality, allowing the mark to gather the excess sexual energy, or by failing to conceal the mark's power, leaving it much more noticable. However, these options can only be used to grant power if they are always on. If you link these enchantments with any of activator, or have any ability that can hide the effect from others these enchantments will cost points instead.")
            override val additionalInfo: AnnotatedString
                @Composable 
                get() = buildAnnotatedString {
                    append("The ")
                    withStyle(style = Points.Mixed.standardStyle.toSpanStyle()) { append("Mixed Point") }
                    append(" values listed are ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("gained") }
                    append(" only if there is no way to disable or hide the enchantment. Otherwise, the center value must be ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("paid") }
                    append(" instead. Points gained from drawbacks can exceed the normal limit for each mark.")
                }
            override val sectionHeaderImage: SectionHeaderImage
                get() = SectionHeaderImage.MarkMagic
            override val enchantments: Map<String?, List<Enchantment>>
                get() = Enchantment.Drawback.all.groupBy { null }
        }

        @Serializable
        data object Appearance: Category {
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString("Appearance")
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString("As with almost all enchantments the mark can give, the ones that alter the subject's appearance can be numerous. But as long as you have the power points to spend, you can assign as many as you like. Just be aware that while certain appearances might suggest certain character traits, someone with an Angel mark is not guaranteed to be any more or less evil than one with the Succubus mark. Appearance marks also do not grant supernatural powers, you must obtain those separately.")
            override val additionalInfo: AnnotatedString?
                @Composable 
                get() = null
            override val sectionHeaderImage: SectionHeaderImage
                get() = SectionHeaderImage.Appearance
            override val enchantments: Map<String?, List<Enchantment>>
                get() = Enchantment.Appearance.all.groupBy { null }
        }

        @Serializable
        data object Personality: Category {
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString("Personality")
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString("There are two schools of thought as to how to best use the personality shaping enchantments of the mark. The first is to create an ideal, a paragon of what you desire. That could be the perfect life, the submissive slave, the fiery lover, or anything else your heart desires. The other way is to have most, if not all the powers given here linked with an activator. In that way you can truly shape a person into a personality you might never see otherwise. Many design their mark so the bearer is essentially two very different people, one when activated, and one when not. Just be careful when having more than one personality active at once, some do not mix well with others")
            override val additionalInfo: AnnotatedString?
                @Composable
                get() = null
            override val sectionHeaderImage: SectionHeaderImage
                get() = SectionHeaderImage.Personality
            override val enchantments: Map<String?, List<Enchantment>>
                get() = Enchantment.Personality.all.groupBy { null }
        }
    }

    sealed interface RequiresActivation: Enchantment

    @Serializable
    sealed interface MarkMagic: Enchantment {
        companion object {
            val all: List<MarkMagic>
                get() = listOf(
                    CharacterSheet,
                    Stats,
                    OvulationStatus,
                    Mood,
                    FetishSign,
                    Communication,
                    OrgasmMeter,
                    Arousal,
                    AnimatedMark,
                    PubicHair,
                    Spreading,
                    Size,
                    BodyMark,
                    Misplaced,
                    Hidden,
                    PersonalizeActivator,
                    MachineMark,
                    InfectiousMark,
                    TemporaryMark,
                    MaleMark,
                )
        }

        @Serializable
        data object CharacterSheet: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_character_sheet
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Character Sheet",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "You can read the mark and see generic information about the subject like how old or strong they are, what their skill set might be, even moral leanings.",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Stats: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_stats
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Stats",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The mark will keep track of a statistic you decide upon when activated. For example, it could tell you how many hours it has been since they last had sex, or a running total of orgasms.",
                    NarratorSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object OvulationStatus: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_ovulation_status
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Ovulation Status",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "If breeding is your driving force, this enchantment can help. Also indicates when fertilization happens and tracks the pregnancy as well.",
                    ChiSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Mood: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_mood
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Mood",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "If's like a mood ring except it actually works.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object FetishSign: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_fetish_sign
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Fetish Sign",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Want to know what kinky shit a girl is into? With this magic the mark will show you different symbols that each mean a different fetish. like a sperm hitting an egg wink wink.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Communication: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_communication
            override val standardCost: Points
                get() = 125.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Communication",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The subject can display short, simple messages on their mark. Useful if they happen to be mute or otherwise silent.",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object OrgasmMeter: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_orgasm_meter
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Orgasm Meter",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Real simple, the mark either fills up or glows brighter the closer to the big O she gets. Turn it on to help get her off.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Arousal: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_arousal
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Arousal",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "When she gets turned on so does the mark it either glows or fills out or somehow shows up on the mark itself.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object AnimatedMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_animated_mark
            override val standardCost: Points
                get() = 50.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Animated Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The mark can animate in a crude fashion, most commonly during some sort of transformation.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object PubicHair: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_pubic_hair
            override val standardCost: Points
                get() = 50.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Pubic Hair",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A simple enchantment that causes the subject's pubic hair to grow in a design that complements the pattern of the mark.",
                    ChiSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Size: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_size
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Size",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment is simply vanity. but some who follow my path want size to mean something. In this case, the more powers the mark is enchanted with the larger it gets.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Spreading: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_spreading
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Spreading",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The mark has a mind of its own. But does it speak towards the subjects desires when it chooses where to spread, or to its own?",
                    ChiSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object BodyMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_body_mark
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Body Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The mark is designed to be focused on the womb, but it need not be contained there. If desired, it can stretch over every part of the subject's body.",
                    ChiSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Misplaced: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_misplaced
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Misplaced",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Though customary, a mark need not be put directly over the womb to function. This also changes what area you need to touch to place the mark.",
                    NarratorSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object Hidden: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_hidden
            override val standardCost: Points
                get() = 300.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Hidden",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment makes it so only you and the subiect can see the mark. Potentially vital in keeping you and your conquests secret.",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object PersonalizeActivator: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_personalize_activator
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Personalize Activator",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "For the enchantments you take here in the mark magic section, you can set your own custom activator. For example you could specify that the glowing enchantment of the mark only turns on at night.",
                    NarratorSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }

        @Serializable
        data object MachineMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_machine_mark
            override val standardCost: Points
                get() = 100.pp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Machine Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Yes I can give vou the technology to automate marking subjects. Just how many people are you planning to enchant?",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Requires Copulation or Insemination specialization",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
            override val requiredSpecialization: List<MarkSpecialization>
                get() = listOf(MarkSpecialization.Copulation, MarkSpecialization.Insemination)
        }

        @Serializable
        data object InfectiousMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_infectous_mark
            override val standardCost: Points
                get() = 100.pp
            override val empoweredCost: Points
                get() = 50.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Infectious Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Those with the mark can spread it to others. You still decide what enchantments are included, but the empowered version allows the spreader to decide.",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Requires Copulation or Insemination specialization",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
            override val requiredSpecialization: List<MarkSpecialization>
                get() = listOf(MarkSpecialization.Copulation, MarkSpecialization.Insemination)
        }

        @Serializable
        data object TemporaryMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_temporary_mark
            override val standardCost: Points
                get() = 50.pp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Temporary Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "When you bestow the mark you can opt for it to be temporary and disappear after a set time or on a condition of your choice. Clever clients have found many uses for this.",
                    SylanSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Requires Copulation or Insemination specialization",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
            override val requiredSpecialization: List<MarkSpecialization>
                get() = listOf(MarkSpecialization.Copulation, MarkSpecialization.Insemination)
        }

        @Serializable
        data object MaleMark: MarkMagic {
            override val image: DrawableResource
                get() = Res.drawable.mark_magic_male_mark
            override val standardCost: Points
                get() = 200.pp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Male Mark",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Normally, a subject requires a womb in order to hold the magical power in the mark. Marks can be placed on anybody. but if the end result is lacking a womb the magic will quickly fade and they will return to normal in a few minutes. With this enchantment that is no longer the case.",
                    NarratorSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }
    }
    @Serializable
    sealed interface Drawback: Enchantment {
        companion object {
            val all: List<Drawback>
                get() = listOf(
                    Insatiable,
                    CumAddiction,
                    Moist,
                    Edging,
                    Haunted,
                    DemonBait,
                    RapeMagnet,
                    OralFixation,
                    Glowing,
                    HoloMark,
                    UnhidableMark,
                    Succubus,
                )
        }

        val gainedMixedPoints: Points.Mixed
        override val empoweredCost: Points?
            get() = null
        override val requiredSpecialization: List<MarkSpecialization>?
            get() = null

        @Serializable
        data object Insatiable: Drawback {
            //TODO If taken with Lust Potion you may not gain points from this and must instead pay.
            override val gainedMixedPoints: Points.Mixed
                get() = 300.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_insatiable
            override val standardCost: Points
                get() = 250.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Insatiable",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Ravenous, unquenchable, a thirst that never ends. For some heaven, for those unable to keep up, a curse. If taken with Lust Potion you may not gain points from this and must instead pay.",
                    ChiSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object CumAddiction: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 200.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_cum_addiction
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Cum Addiction",
                    ChoiceTitleStyle.toSpanStyle()
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The bearer is addicted to cum and goes through unending withdrawals without a fix. Oh, and girlcum won't work for this one.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object Moist: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 150.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_moist
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Moist",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "She is always wet and ready to go with this enchantment. Doesn't actually affect her arousal though. which can lead to some unpleasantness",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object Edging: Drawback, SexualPower {
            override val gainedMixedPoints: Points.Mixed
                get() = 500.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_edging
            override val standardCost: Points
                get() = 100.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Edging",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "She's completely incapable of orgasm. She can get close, but nothing will ever bring her over the edge. This one can be particularly cruel, some people would probably seek revenge.",
                    TwinsSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Counts as a Sexual Power enchantment",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
        }

        @Serializable
        data object Haunted: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 400.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_haunted
            override val standardCost: Points
                get() = 200.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Haunted",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Restless spirits of the unborn will haunt the subject, often with mischievous intent.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object DemonBait: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 500.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_demon_bait
            override val standardCost: Points
                get() = 200.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Demon Bait",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Better hope they like demons because demons will really like them! Oh, and pretty much any demon that really wants someone like this won't take no for an answer.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object RapeMagnet: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 400.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_rape_magnet
            override val standardCost: Points
                get() = 150.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Rape Magnet",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "People who spend any time around her will get the urge to molest and eventually outright rape her. The longer they spend nearby the stronger it gets, eventually overcoming even the strongest of wills. The mark master is immune to this effect.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object OralFixation: Drawback, MindControl {
            override val gainedMixedPoints: Points.Mixed
                get() = 400.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_oral_fixation
            override val standardCost: Points
                get() = 200.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Oral Fixation",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Almost the inverse of the previous option. She has a strong urge to suck on things. The more phallic, the stronger the urge. If anyone even suggests she suck their cock she'll be pulling their pants down before she even knows what's happening. This tends to make it impossible to have sex any other way unless she's tied down.",
                    NarratorSpeech.toSpanStyle()
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Counts as a Mind Control enchantment",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
        }

        @Serializable
        data object Glowing: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 200.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_glowing
            override val standardCost: Points
                get() = 100.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Glowing",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The mark glows noticeably, enough to make it obviously unnatural and visible through at least thin clothing.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object HoloMark: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 350.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_holo_mark
            override val standardCost: Points
                get() = 150.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Holo Mark",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Instead of a typical tattoo, the mark manifests as a projection a few inches away from the skin where it would be. This means that it can be seen \"through\" almost any clothing.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object UnhidableMark: Drawback {
            override val gainedMixedPoints: Points.Mixed
                get() = 150.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_unhidable_mark
            override val standardCost: Points
                get() = 100.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Unhidable Mark",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Attempting to hide or conceal the mark will cause an increasing and irresistible desire to reveal it.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable 
                get() = null
        }

        @Serializable
        data object Succubus: Drawback, Appearance {
            override val gainedMixedPoints: Points.Mixed
                get() = 400.mp
            override val image: DrawableResource
                get() = Res.drawable.drawback_succubus
            override val standardCost: Points
                get() = 400.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Succubus",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "We could just turn her into a succubus, at least physically. Wings, horns, tail and some kind of red or blue toned skin. If you're really lucky someone might mistake it for cosplay, but I wouldn't bet on that.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Counts as an Appearance enchantment",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
            override val requiredSpecialization: List<MarkSpecialization>?
                get() = null
        }
    }
    @Serializable
    sealed interface Appearance: Enchantment {
        companion object {
            val all: List<Appearance>
                get() = listOf(
                    BreastSize,
                    ButtSize,
                    ThighsAndHips,
                    Femininity,
                    Chubby,
                    Thin,
                    Athletic,
                    Height,
                    Lolita,
                    SexChange,
                    SkinTone,
                    TanLines,
                    Heterochromia,
                    GlowingEyes,
                    EyeSclera,
                    HeartPupils,
                    HairColor,
                    LongHair,
                    DrillHair,
                    Hairy,
                    CleanShaven,
                    Tattoos,
                    Piercings,
                    TorpedoTits,
                    Kemonomimi,
                    ElfEars,
                    Horns,
                    Wings,
                    AngelHalo,
                    Tail,
                    Pregnant,
                    Statuesque,
                    MonsterGirl,
                    Cyborg,
                    Amputee,
                    Negative
                )
        }

        override val requiredSpecialization: List<MarkSpecialization>?
            get() = null

        @Serializable
        data object BreastSize: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_breast_size
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Breast Size",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Choose any size you like, from flat as a board to immobile. Enhanced provides extra support, though magic can only do so much for extreme sizes. Remember that the details like this are decided when each mark is placed, so not every girl has to be the same size.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object ButtSize: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_butt_size
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Butt Size",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "We have crafted this enchantment so many times I have learned to do it almost for free. Whether you want them large and voluptuous or small and firm, we have shaped them all.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object ThighsAndHips: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_thighs_and_hips
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Thighs & Hips",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Not Everyone wants to change the subject's thigh shape and size, but when they do it almost always includes a gap.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Femininity: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_femininity
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Femininity",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The base purchase makes the bearer of the mark almost androgynous and cute, regardless of what they were to begin with. Enhanced makes them unmistakably feminine and gorgeous, especially the face.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Chubby: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_chubby
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Chubby",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "One might say the most natural child bearing form. With this enchantment they shall stay as plump as you like regardless of how much, or how little they eat.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Thin: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_thin
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Thin",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "All over or in just the right places, these enchantments can be combined for more varied look.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Athletic: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_athletic
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Athletic",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment allows you to set muscle size and tone without affecting strength. The bearer will not seem to gain or lose muscle naturally. but if you want them to actually get stronger they will either need to exercise or get another enchantment.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Height: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_height
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Height",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "You can set the bearer's height to anything in human ranges when the mark is applied. Empowered increases the range from about 12 feet down to just 3 inches.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Lolita: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_lolita
            override val standardCost: Points
                get() = 450.cp
            override val empoweredCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Lolita",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A package deal including breasts, butt, thighs, weight, height, body hair and face. Enhanced locks the appearance but doesn't actually prevent aging.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object SexChange: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_sex_change
            override val standardCost: Points
                get() = 300.cp
            override val empoweredCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sex Change",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment alters the sex of the marked subject however you wish, making them male, female, or anything in between. Empowered allows for multiples, which can be placed wherever you like.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object SkinTone: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_skin_tone
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Skin Tone",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Use empowered if you want a girl that has a blue, green or red skin. I kinda like purple, they seem to taste better. Otherwise you can only choose from boring human ranges.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object TanLines: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_tan_lines
            override val standardCost: Points
                get() = 50.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Tan Lines",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Where a girl has a tan and how strong it is can tell you a lot. Well. a lot about how much she is in the sun naked",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Heterochromia: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_heterochromia
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Heterochromia",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The eyes are the gateway to the soul some say. This enchantment not only lets you change the iris color of the subject, but also allows a different color for each еyе.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object GlowingEyes: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_glowing_eyes
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Glowing Eyes",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "I admit, cosmetic enchantments normally bore me but not this one. It is simultaneously both a subtle and intense sign of supernatural power.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object EyeSclera: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_eye_sclera
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Eye Sclera",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A human normally has white outer eye color, known as the Sclera. With this enchantment the mark bearer will have a different color that you choose when the mark is created.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object HeartPupils: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_hearth_pupils
            override val standardCost: Points
                get() = 75.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Heart Pupils",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This one is a must for me. The real question is. how and when do they get activated?",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object HairColor: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_hair_color
            override val standardCost: Points
                get() = 75.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Hair Color",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment changes the subjects hair color, often to an exotic shade, and causes it to grow out naturally in that color.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object LongHair: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_long_hair
            override val standardCost: Points
                get() = 125.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Long Hair",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "If cut short this enchantment will cause hair to grow back to full length overnight, which is a tricky bit of magic I must admit.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object DrillHair: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_drill_hair
            override val standardCost: Points
                get() = 75.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Drill Hair",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Someone with a mark can style their hair as normal. This particular look however requires magic to do properly.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Hairy: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_hairy
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Hairy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "I think this works best with your savage jungle girts but hey, whatever works for you. Empowered gives them actual fur. or scales or feathers if you'd prefer.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object CleanShaven: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_clean_shaven
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Clean Shaven",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This thing is a lifesaver on shaving. I literally don't grow any hair below my eyebrows with this magic.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Tattoos: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_tattoos
            override val standardCost: Points
                get() = 50.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Tattoos",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Our skills translate to more traditional tattoo artwork as well. Although will they be to her liking, or yours?",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Piercings: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_piercings
            override val standardCost: Points
                get() = 75.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Piercings",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "I designed this enchantment. Piercings you add with the mark are magical and cannot be damaged or removed by mundane means.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object TorpedoTits: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_torpedo_tits
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Torpedo Tits",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The only way you are getting this shape without a bra is with magic.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Kemonomimi: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_kemonomimi
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Kemonomimi",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Any kind of animal ears you like, optional human ears as well. If you choose elephant ears I may seriously question your sanity. If you want to go for a full 'Furry' look, empowered gives them a snout or whatever it is that belongs on that animal. Buy Hairy for the fur.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object ElfEars: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_elf_ears
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Elf Ears",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Elves in fiction tend to be long-lived practitioners of ancient magic. Our basic package just comes with pointy ears.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Horns: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_horns
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Horns",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sure you got them for the looks. It has nothing to do with blowjob handlebars.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Wings: Appearance, MultiBuy {
            override val image: DrawableResource
                get() = Res.drawable.appearance_wings
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points?
                get() = null
            override val additionalPurchasesCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Wings",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = buildAnnotatedString {
                    withStyle(NarratorSpeech.toSpanStyle()) {
                        append("One pair of any type of wings you choose. You'll probably need to buy the Flight enchantment if you want them to be useful, unless the subject is very light with arm wings. Additional pairs of identical wings can be purchased for ")
                    }
                    withStyle(Points.Change.standardStyle.toSpanStyle()) {
                        append("100cp")
                    }
                    withStyle(NarratorSpeech.toSpanStyle()) {
                        append(" each.")
                    }
                }
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object AngelHalo: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_angel_halo
            override val standardCost: Points
                get() = 200.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Angel Halo",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Wings sold separately. When paired with magic, can convince the masses of divine power.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Tail: Appearance, MultiBuy {
            override val image: DrawableResource
                get() = Res.drawable.appearance_tail
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points
                get() = 100.cp
            override val additionalPurchasesCost: Points
                get() = 50.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Tail",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = buildAnnotatedString {
                    withStyle(NarratorSpeech.toSpanStyle()) {
                        append("If you want something like a cat or succubus tail and nothing else, pick this. Empowered makes the tail prehensile, like a third hand. If the subject already has a tail then additional identical tails may be purchased for only ")
                    }
                    withStyle(Points.Change.standardStyle.toSpanStyle()) {
                        append("50cp")
                    }
                    withStyle(NarratorSpeech.toSpanStyle()) {
                        append(" each.")
                    }
                }
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Pregnant: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_pregnant
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Pregnant",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The base enchantment means she only looks pregnant. Empowered means she is permanently stuck being truly pregnant and will not actually give birth.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Statuesque: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_statuesque
            override val standardCost: Points
                get() = 100.cp
            override val empoweredCost: Points
                get() = 900.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Statuesque",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Basic causes their skin to go rough and gray. Empowered turns them into an actual statue.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object MonsterGirl: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_monstergirl
            override val standardCost: Points
                get() = 600.cp
            override val empoweredCost: Points
                get() = 100.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Monster-Girl",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A package deal that will give the subject the appearance of a Monster-Girl of your choice. Regardless of appearance, they will still be flesh and blood. Empowered is necessary to give them any features like gills or venom. Does not grant flight.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Cyborg: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_cyborg
            override val standardCost: Points
                get() = 400.cp
            override val empoweredCost: Points
                get() = 300.pp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Cyborg",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The base level is mostly just for show, any mechanical parts won't be able to do anything their fleshy counterparts could not. The empowered version provides increased strength and the ability to easily attach \"upgrades\" she can feel through.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Amputee: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_amputee
            override val standardCost: Points
                get() = 600.cp
            override val empoweredCost: Points?
                get() = null
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Amputee",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Choose as many arms and legs as you want. So do you want to make this one an activated enchantment, or just have one of your girls permanently a sex toy? Tough call.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Negative: Appearance {
            override val image: DrawableResource
                get() = Res.drawable.appearance_negative
            override val standardCost: Points
                get() = 150.cp
            override val empoweredCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Negative",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A favorite of mine, especially when tied to an activation. This enchantment inverts and color reverses the subject, and the empowered version affects clothing as well.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }
    }
    @Serializable
    sealed interface Personality: Enchantment {
        companion object {
            val all: List<Personality>
                get() = listOf(
                    Cheerful,
                    Bouncy,
                    Sweetheart,
                    Motherly,
                    Teacher,
                    Embarrassed,
                    Innocent,
                    Pure,
                    Timid,
                    Shy,
                    Cold,
                    Distracted,
                    Artsy,
                    Druggy,
                    Punk,
                    Bookworm,
                    GirlNextDoor,
                    Tomboy,
                    GunNut,
                    Gamer,
                    Reflection,
                    Adventurer,
                    Spunky,
                    Angry,
                    Competitive,
                    ClassPresident,
                    Boss,
                    Sadist,
                    Haughty,
                    Royal,
                    Spider,
                    Sly,
                    PowerMad,
                    Army,
                    Servile,
                    SplitPersonality,
                    Religious,
                    NationalSocialist,
                    Villain,
                    Gloomy,
                    BeachBabe,
                    Bimbo,
                    Amnesiac,
                    Spaced,
                    Tease,
                    Slutty,
                    Thirsty,
                    Prostitute,
                    TabooBreaker,
                    LewdOblivious,
                    Crazy,
                )
        }

        override val empoweredCost: Points?
            get() = null
        override val requiredSpecialization: List<MarkSpecialization>?
            get() = null

        @Serializable
        data object Cheerful: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_cheerful
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Cheerful",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Nice ones are called joyful and bright, mean ones are called cheerleaders.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Bouncy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_bouncy
            override val standardCost: Points
                get() = 125.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Bouncy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Lively, energetic, perky. Yes we are aware of the double entendre.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Sweetheart: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_sweetheart
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sweetheart",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Erk going to get diabetes from this girl. They tend fo be into cream pies though so that's cool.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Motherly: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_motherly
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Motherly",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Often combined with some sort of mind control to make her think she actually IS your mother.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Teacher: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_teacher
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Teacher",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "She wants to take you under her wing and teach you how to be the best you can be, at all things.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Embarrassed: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_embarassed
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Embarrassed",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "You know when would be a great time to magically activate a girl's creampie fetish? When you give her one. Ooh, or maybe have her orgasm attached to it, even better.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Innocent: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_innocent
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Innocent",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A heart untouched by corruption, greed, or wanton lust.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Pure: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_pure
            override val standardCost: Points
                get() = 250.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Pure",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "While most people are born innocent, true purity is actively choosing to be so in a world of hate and fear.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Timid: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_timid
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Timid",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Excitement and fear can be a potent combination. Also useful to keep a subject whose powers might be abusable from using them without caution.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Shy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_shy
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Shy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A popular base choice that many of my clients then build off of into more exotic tastes, but a few are dedicated to the bashful concept.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Cold: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_cold
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Cold",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Slow to emotion or hard to read. Some find great joy in getting one to warm up.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Distracted: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_distracted
            override val standardCost: Points
                get() = 175.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Distracted",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Oh man some people really enjoy giving it to a chick who could not care less. Guess I understand the cute and ditzy part of it at least.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Artsy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_artsy
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Artsy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "For many of this kind, it's not about talent but about the lifestyle.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Druggy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_druggy
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Druggy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Just to be clear, we are talking about stuff like weed and LSD. A valium addiction doesn't count.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Punk: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_punk
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Punk",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Being punk is about defying the norms of society. AND the clothing.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Bookworm: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_bookworm
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Bookworm",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Don't be fooled by labels like this one. They could still be rather dull-witted, or maybe more accurately described as a nerd. All the title bookworm means is you will often find her reading.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object GirlNextDoor: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_girlnextdoor
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Girl Next Door",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Ah nothing quite like having a wholesome, down to earth girl go absolutely sex crazed for you.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Tomboy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_tomboy
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Tomboy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "They don't all have short hair and wear athletic clothing. just most of them.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object GunNut: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_gunnut
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Gun Nut",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "If you are trying to make a super harem squad than a gun girl is really fun to have around. Just don't get on her bad side.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Gamer: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_gamer
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Gamer",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "If you are that demanding you can also specify her as a dedicated fan girl to a specific platform.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Reflection: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_reflection
            override val standardCost: Points
                get() = 500.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Reflection",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A truly unique enchantment. This enchantment will give the subject a complete personality based on your own, but without the same memories and with the differences that an opposite gender brings with it.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Adventurer: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_adventurer
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Adventurer",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A girl with this personality is at home taking risks and facing danger. More rough than your average damsel but still soft where it counts.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Spunky: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_spunky
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Spunky",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "We used to have a 'violent' personality enchantment but our clients would always pair it with more pleasant dispositions to take the edge off. This seems to be what they really wanted.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Angry: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_angry
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Angry",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Angry is too mild a term. You could not just call her a bitch either. I use a different word to describe this type. Rage.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Competitive: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_competitive
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Competitive",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A useful trait to have, especially if you intend for the mark bearer to go out and use what power is given to them to reshape the world. Also enjoyable in the bedroom at times.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object ClassPresident: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_classpresident
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Class President",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A good team leader that is not above a little political maneuvering.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Boss: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_boss
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Boss",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A boss is either: smart, capable and goal driven, or she is controlling, power hungry, and ruthless.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Sadist: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_sadist
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sadist",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sure for some of them it is a kinky sex thing REAL sadists enjoy it even when nobody is getting off. not even them.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Haughty: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_haughty
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Haughty",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "In her mind she doesn't act superior to everyone else, she simply is.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Royal: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_royal
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Royal",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The young are your typical princess, while older mark bearers tend towards being queenly.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Spider: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_spider
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Spider",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Beware the spider mark. Those with such a personality hide their true nature, often until it is too late.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Sly: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_sly
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Sly",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "To her foes she is devious, to her friends she is cunning. A woman who knows her way around the world, and often the bedroom.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object PowerMad: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_powermad
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Power Mad",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The subject is convinced the mark makes them an all powerful and unstoppable force. Which of course they are not.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Army: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_army
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Army",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "The easiest way to build a fighting force. Subjects with this enchantment will automatically view themselves as soldiers in the mark master's army.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Servile: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_servile
            override val standardCost: Points
                get() = 400.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Servile",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Those who bear the mark are naturally subservient to you. They might not kill or die for you, but will do just about anything else.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object SplitPersonality: Personality, RequiresActivation {
            override val image: DrawableResource
                get() = Res.drawable.personality_splitpersonality
            override val standardCost: Points
                get() = 400.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Split Personality",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val caption: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Requires activation",
                    ChoiceRequirementStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Not just an attitude change, when active the subject has an entirely different persona that has its own desires and memories separate from the original.",
                    NarratorSpeech.toSpanStyle(),
                )
        }

        @Serializable
        data object Religious: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_religious
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Religious",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A strong believer in god. Maybe not a strong believer in the commandments. Subjects with this personality and strong powers tend to be... zealous.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object NationalSocialist: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_nationalsocialist
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "National Socialist",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "It isn't just a uniform, she is very much a believer.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Villain: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_villain
            override val standardCost: Points
                get() = 300.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Villain",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "This enchantment brings about true evil. Not just a lack of respect for moral standards, but an actual desire to commit heinous acts and enjoy doing them.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Gloomy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_gloomy
            override val standardCost: Points
                get() = 100.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Gloomy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Someone who views life as nothing but pain and sadness might actually be right depending on the mark you give them.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object BeachBabe: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_beachbabe
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Beach Babe",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "A little on the vain side and a little too much sun makes them light headed. Not too far gone in either. that distinction belongs to Bimbos",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Bimbo: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_bimbo
            override val standardCost: Points
                get() = 400.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Bimbo",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Whoa! Now we are talking Sure she is dumb as a bag of hammers, but that's what you want in a walking fucktoy.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Amnesiac: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_amnesiac
            override val standardCost: Points
                get() = 500.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Amnesiac",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Might want to have this one tied to an activation unless you want her permanently confused as to who she is or why she is naked.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Spaced: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_spaced
            override val standardCost: Points
                get() = 600.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Spaced",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "I call this one the robot. Beep boop. yes we will have the intercourse now.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Tease: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_tease
            override val standardCost: Points
                get() = 125.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Tease",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "When I was little and liked someone, I would pull on their hair. Now I enjoy 'accidentally' leaning my boobs up against them.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Slutty: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_slutty
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Slutty",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Does this mean she sleeps around a lot or she is just an easy girl to get laid with? I guess those kind of go together.",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Thirsty: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_thirsty
            override val standardCost: Points
                get() = 250.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Thirsty",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "She knows what she wants and is not afraid to show it.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Prostitute: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_prostitute
            override val standardCost: Points
                get() = 400.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Prostitute",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Subjects with this enchantment become top grade whores. Able to please dozens of customers a day, but either unwilling or unable to give anything but a token resistance. A decent source of income.",
                    SylanSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object TabooBreaker: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_taboobreaker
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Taboo Breaker",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Taboos are a social invention. This enchantment erases all memory of what those taboos might be.",
                    ChiSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object LewdOblivious: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_lewdoblivious
            override val standardCost: Points
                get() = 150.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Lewd Oblivious",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "See what she is wearing? She has no idea it was a BDSM outfit, thought it was just normal lingerie.",
                    NarratorSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }

        @Serializable
        data object Crazy: Personality {
            override val image: DrawableResource
                get() = Res.drawable.personality_crazy
            override val standardCost: Points
                get() = 200.cp
            override val name: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Crazy",
                    ChoiceTitleStyle.toSpanStyle(),
                )
            override val description: AnnotatedString
                @Composable 
                get() = AnnotatedString(
                    "Go ahead stick your dick in crazy. And give her super powers too!",
                    TwinsSpeech.toSpanStyle(),
                )
            override val caption: AnnotatedString?
                @Composable
                get() = null
        }
    }
    @Serializable
    sealed interface Fetishes: Enchantment
    @Serializable
    sealed interface SupernaturalPower: Enchantment
    @Serializable
    sealed interface SexualPower: SupernaturalPower
    @Serializable
    sealed interface MindControl: SupernaturalPower
    @Serializable
    sealed interface SkillAndPhysicalImprovement: SupernaturalPower
    @Serializable
    sealed interface BodyModification: SupernaturalPower
    @Serializable
    sealed interface Magic: SupernaturalPower
}