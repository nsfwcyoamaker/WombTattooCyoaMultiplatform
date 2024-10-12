package com.nsfwcyoamaker.wombtattoocyoa.state

import com.nsfwcyoamaker.wombtattoocyoa.domain.Limiter
import kotlinx.serialization.Serializable

@Serializable
sealed interface LimiterComponent {
    val forLimiter: Limiter

    fun adjustLimiterMultiplier(currentMultiplier: Float): Float

    companion object {
        fun makeNew(forLimiter: Limiter): LimiterComponent? {
            return when(forLimiter) {
                is Limiter.Plugged -> Plugged()
                else -> null
            }
        }
    }

    @Serializable
    data class Plugged(
        val numberOfPlugs: Int = 0,
        val numberOfVibrators: Int = 0,
    ): LimiterComponent {
        override val forLimiter: Limiter
            get() = Limiter.Plugged

        override fun adjustLimiterMultiplier(currentMultiplier: Float): Float {
            return (1f - (numberOfPlugs * 0.05f) - (numberOfVibrators * 0.1f)).coerceAtLeast(0.5f)
        }
    }
}
