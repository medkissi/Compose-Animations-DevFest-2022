package com.medkissi.composeanimationsdevfest2022.ui.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.medkissi.composeanimationsdevfest2022.models.Speaker
import com.medkissi.composeanimationsdevfest2022.models.speakerList
import com.medkissi.composeanimationsdevfest2022.ui.theme.ComposeAnimationsDevFest2022Theme
import com.medkissi.composeanimationsdevfest2022.R

@Composable
fun SpeakerView(
    speaker: Speaker,
    modifier: Modifier = Modifier
) {
    SpeakerView(
        modifier,
        speaker.name,
        speaker.description,
        speaker.image
    )
}

@Composable
private fun SpeakerView(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    @DrawableRes imagePath: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        val model = ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .crossfade(true)
            .build()
        AsyncImage(
            model = model,
            contentDescription = "$name, $description",
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id =R.drawable.kissi)
        )
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.57f)
            )
        }
    }
}

@Preview(
    name = "",
    group = "Preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "",
    group = "Preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CatViewPreview() {
    val speaker = speakerList[1]
    ComposeAnimationsDevFest2022Theme() {
        SpeakerView(speaker = speaker)
    }
}