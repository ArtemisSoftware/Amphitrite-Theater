package com.artemissoftware.amphitritetheater.threedimensiongraph

import com.artemissoftware.amphitritetheater.threedimensiongraph.models.BarInfo
import com.artemissoftware.amphitritetheater.ui.theme.BlueGray
import com.artemissoftware.amphitritetheater.ui.theme.BrightBlue
import com.artemissoftware.amphitritetheater.ui.theme.DarkGray
import com.artemissoftware.amphitritetheater.ui.theme.Green
import com.artemissoftware.amphitritetheater.ui.theme.Orange
import com.artemissoftware.amphitritetheater.ui.theme.Purple80
import com.artemissoftware.amphitritetheater.ui.theme.RedOrange

internal object PreviewData {
    val bars = listOf(
        BarInfo(28,"Kotlin", Orange),
        BarInfo(15,"Swift", BrightBlue),
        BarInfo(11,"Ruby", Green),
        BarInfo(7,"Cobol", Purple80),
        BarInfo(14,"C++", BlueGray),
        BarInfo(9,"C", RedOrange),
        BarInfo(21,"Python", DarkGray)
    )

    val bars2 = listOf(
        BarInfo(25,"Kotlin", Orange),
        BarInfo(50,"Swift", BrightBlue),
        BarInfo(75,"Ruby", Green),
        BarInfo(100,"Cobol", Purple80),
//        BarInfo(14,"C++", BlueGray),
//        BarInfo(9,"C", RedOrange),
//        BarInfo(21,"Python", DarkGray)
    )
}