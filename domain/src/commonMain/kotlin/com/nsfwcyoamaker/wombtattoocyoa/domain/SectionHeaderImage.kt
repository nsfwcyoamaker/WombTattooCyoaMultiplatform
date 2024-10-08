package com.nsfwcyoamaker.wombtattoocyoa.domain

import androidx.compose.ui.unit.IntSize
import org.jetbrains.compose.resources.DrawableResource
import wombtattoocyoa.domain.generated.resources.*

sealed interface SectionHeaderImage {
    val imageRes: DrawableResource
    val imageSizePx: IntSize
    val frameWidthPx: Int

    data object Activators: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_activators
        override val imageSizePx: IntSize
            get() = IntSize(1297, 1086)
        override val frameWidthPx: Int
            get() = 667
    }

    data object MarkMagic: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_mark_magic
        override val imageSizePx: IntSize
            get() = IntSize(1955, 1223)
        override val frameWidthPx: Int
            get() = 531
    }

    data object Appearance: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_appearance
        override val imageSizePx: IntSize
            get() = IntSize(1446, 1306)
        override val frameWidthPx: Int
            get() = 623
    }

    data object Fetishes: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_fetishes
        override val imageSizePx: IntSize
            get() = IntSize(0, 0) //TODO
        override val frameWidthPx: Int
            get() = 0 //TODO
    }

    data object Personality: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_personality
        override val imageSizePx: IntSize
            get() = IntSize(1176, 1120)
        override val frameWidthPx: Int
            get() = 560
    }

    data object SupernaturalPowers: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_supernatural_powers
        override val imageSizePx: IntSize
            get() = IntSize(0, 0) //TODO
        override val frameWidthPx: Int
            get() = 0 //TODO
    }

    data object Ending: SectionHeaderImage {
        override val imageRes: DrawableResource
            get() = Res.drawable.section_ending
        override val imageSizePx: IntSize
            get() = IntSize(1467, 1640)
        override val frameWidthPx: Int
            get() = 386
    }
}