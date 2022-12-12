package com.medkissi.composeanimationsdevfest2022.ui.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.medkissi.composeanimationsdevfest2022.models.Speaker
import com.medkissi.composeanimationsdevfest2022.ui.anim.BounceState

@Composable
fun SpeakerPicture(speaker: Speaker) {
    val painter = painterResource(id = speaker.image)

    var currentState: BounceState by remember { mutableStateOf(BounceState.Released) }
    val transition = updateTransition(targetState = currentState, label = "animation")

    val scale: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500, easing = CubicBezierEasing(0.18f,1.66f,0.25f,0.77f)) },
        label = ""
    ) { state ->
        if (state == BounceState.Pressed) {
            0.85f
        } else {
            1f
        }
    }

    Image(
        painter = painter,
        contentDescription = "$speaker.name",
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        currentState = BounceState.Pressed
                        tryAwaitRelease()
                        currentState = BounceState.Released
                    }
                )
            },
        contentScale = ContentScale.Crop
    )
}