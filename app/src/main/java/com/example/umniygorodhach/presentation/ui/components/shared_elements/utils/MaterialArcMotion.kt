package com.example.traininghakatonsever.presentation.ui.components.shared_elements.utils

import androidx.compose.ui.geometry.Offset
import com.example.umniygorodhach.presentation.ui.components.shared_elements.utils.KeyframeBasedMotion
import com.example.umniygorodhach.presentation.ui.components.shared_elements.utils.PathMotionFactory
import com.example.umniygorodhach.presentation.ui.components.shared_elements.utils.QuadraticBezier

class MaterialArcMotion : KeyframeBasedMotion() {

    override fun getKeyframes(start: Offset, end: Offset): Pair<FloatArray, LongArray> =
        QuadraticBezier.approximate(
            start,
            if (start.y > end.y) Offset(end.x, start.y) else Offset(start.x, end.y),
            end,
            0.5f
        )

}

val MaterialArcMotionFactory: PathMotionFactory = { MaterialArcMotion() }
