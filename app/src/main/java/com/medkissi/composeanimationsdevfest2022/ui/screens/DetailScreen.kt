package com.medkissi.composeanimationsdevfest2022.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.medkissi.composeanimationsdevfest2022.models.speakerList
import com.medkissi.composeanimationsdevfest2022.ui.component.DescriptionCard
import com.medkissi.composeanimationsdevfest2022.ui.component.SpeakerInfoBloc
import com.medkissi.composeanimationsdevfest2022.ui.component.SpeakerPicture
import com.medkissi.composeanimationsdevfest2022.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(id: String?,  onNavigate: (Screen?) -> Unit) {
    val speaker = speakerList.first { it.id == id?.toInt() }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge,
                        text = "${speaker.name}"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigate(null) },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    SpeakerPicture(speaker =speaker )
                }
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = speaker.description,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        val modifier = Modifier.weight(1f)
                        SpeakerInfoBloc(modifier,speaker.country, "Pays")
                        SpeakerInfoBloc(modifier,speaker.time, "Heure")
                    }
                    DescriptionCard(speaker)
                }
            }

    }
}