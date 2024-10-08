package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import kotlinx.serialization.Serializable

@Serializable
sealed interface Points {
    val amount: Int
    val standardTextColor: Color
    val empoweredTextColor: Color
    val standardStyle: TextStyle
    val empoweredStyle: TextStyle

    operator fun plus(other: Points): Points
    operator fun minus(other: Points): Points
    operator fun times(factor: Float): Points

    @Serializable
    data class Change(override val amount: Int): Points {
        companion object {
            val Int.cp get() = Change(this)
            val standardTextColor: Color = Color(0xFFFFFFFF)
            val empoweredTextColor: Color = Color(0xFFFBFB00)
            val standardStyle: TextStyle get() = TextStyle(fontWeight = FontWeight.Bold, color = standardTextColor)
            val empoweredStyle : TextStyle get() = TextStyle(fontWeight = FontWeight.Bold, color = empoweredTextColor)
        }
        override val standardTextColor: Color get() = Change.standardTextColor
        override val empoweredTextColor: Color get() = Change.empoweredTextColor
        override val standardStyle: TextStyle get() = Change.standardStyle
        override val empoweredStyle: TextStyle get() = Change.empoweredStyle
        override fun toString() = "${amount}cp"

        override fun plus(other: Points): Points = this + ((other as? Change) ?: throw RuntimeException())
        override fun minus(other: Points): Points = this - ((other as? Change) ?: throw RuntimeException())
        operator fun plus(other: Change): Change = (this.amount + other.amount).cp
        operator fun minus(other: Change): Change = (this.amount - other.amount).cp
        override operator fun times(factor: Float): Change = (this.amount * factor).toInt().cp
    }
    @Serializable
    data class Power(override val amount: Int): Points {
        companion object {
            val Int.pp get() = Power(this)
            val standardTextColor: Color = Color(0xFFFF00DC)
            val empoweredTextColor: Color = Color(0xFFFF0000)
            val standardStyle: TextStyle get() = TextStyle(fontWeight = FontWeight.Bold, color = standardTextColor)
            val empoweredStyle : TextStyle get() = TextStyle(fontWeight = FontWeight.Bold, color = empoweredTextColor)
        }
        override val standardTextColor: Color get() = Power.standardTextColor
        override val empoweredTextColor: Color get() = Power.empoweredTextColor
        override val standardStyle: TextStyle get() = Power.standardStyle
        override val empoweredStyle: TextStyle get() = Power.empoweredStyle
        override fun toString() = "${amount}pp"

        override fun plus(other: Points): Points = this + ((other as? Power) ?: throw RuntimeException())
        override fun minus(other: Points): Points = this - ((other as? Power) ?: throw RuntimeException())
        operator fun plus(other: Power): Power = (this.amount + other.amount).pp
        operator fun minus(other: Power): Power = (this.amount - other.amount).pp
        override operator fun times(factor: Float): Power = (this.amount * factor).toInt().pp
    }
    @Serializable
    data class Mixed(override val amount: Int): Points {
        companion object {
            val Int.mp get() = Mixed(this)
            val standardTextColor: Color = Color(0xFF0AE320)
            val standardStyle: TextStyle get() = TextStyle(fontWeight = FontWeight.Bold, color = standardTextColor)
        }
        override val standardTextColor: Color get() = Mixed.standardTextColor
        override val empoweredTextColor: Color get() = Mixed.standardTextColor
        override val standardStyle: TextStyle get() = Mixed.standardStyle
        override val empoweredStyle: TextStyle get() = Mixed.standardStyle
        override fun toString() = "${amount}mp"

        override fun plus(other: Points): Points = this + ((other as? Mixed) ?: throw RuntimeException())
        override fun minus(other: Points): Points = this - ((other as? Mixed) ?: throw RuntimeException())
        operator fun plus(other: Mixed): Mixed = (this.amount + other.amount).mp
        operator fun minus(other: Mixed): Mixed = (this.amount - other.amount).mp
        override operator fun times(factor: Float): Mixed = (this.amount * factor).toInt().mp
    }

    companion object {
        val explanationText = buildAnnotatedString {
            withStyle(Change.standardStyle.toSpanStyle()) { append("Change Points (cp)") }
            append(" in ")
            withStyle(Change.standardStyle.toSpanStyle()) { append("white") }
            append(" or ")
            withStyle(Change.empoweredStyle.toSpanStyle()) { append("yellow") }
            append(" are for changing the subject into something more suited to your tastes. These are mainly spent on Mark Magic, Appearance, Personality, and Fetish changes. ")
            withStyle(Power.standardStyle.toSpanStyle()) { append("Power Points (pp)") }
            append(" in ")
            withStyle(Power.standardStyle.toSpanStyle()) { append("pink") }
            append(" or ")
            withStyle(Power.empoweredStyle.toSpanStyle()) { append("red") }
            append(" are for granting special abilities and skills, mainly spent on Supernatural Powers. The second color denotes an EMPOWERED choice. These add an additional or stronger effect to an enchantment and require purchasing both the base and empowered version. Lastly, ")
            withStyle(Mixed.standardStyle.toSpanStyle()) { append("Mixed Points (mp)") }
            append(" in ")
            withStyle(Mixed.standardStyle.toSpanStyle()) { append("green") }
            append(" can be spent as if they were either of the other kinds.")
        }
    }
}