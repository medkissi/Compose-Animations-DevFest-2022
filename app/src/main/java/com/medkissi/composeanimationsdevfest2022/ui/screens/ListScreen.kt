package com.medkissi.composeanimationsdevfest2022.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.medkissi.composeanimationsdevfest2022.models.speakerList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListScreen(onGoDetail: (String) -> Unit) {

    val lazyListState = rememberLazyListState()
    val fabExpand = remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }.value

    var sorted by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val onInverseSort = {
        sorted = !sorted
        coroutineScope.launch {
            //delay(50)
           // lazyListState.animateScrollToItem(0)
        }
    }
    val myItems = remember(sorted) {
        derivedStateOf {
            if (sorted) {
                speakerList.sortedBy { it.name }
            } else {
                 speakerList.sortedByDescending { it.name }
            }
        }
    }.value


    val scaleY by animateFloatAsState(
        targetValue = (if (sorted) {
            -1f
        } else {
            1f
        }),
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    var atEnd by remember { mutableStateOf(false) }
    val fabIcon = Icons.Filled.Sort

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge,
                        text = "DevFest Conakry 2022"
                    )
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onInverseSort()
                atEnd = !atEnd
            }) {
                Row(Modifier.padding(horizontal = 16.dp)) {
                    Icon(
                        imageVector = fabIcon,

                        contentDescription = null
                    )
                    AnimatedVisibility (false) {
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = "Trier"
                        )
                    }
                }
            }
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = lazyListState
        ) {
            items(items = myItems, key = { it.id }) {speaker ->
                SpeakerView(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.animateItemPlacement()
                        .clickable {
                            onGoDetail(speaker.id.toString())
                        }
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    speaker= speaker
                )
            }
        }
    }
}