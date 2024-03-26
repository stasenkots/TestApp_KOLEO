package com.example.testapp_koleo.ui.search

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.testapp_koleo.R
import com.example.testapp_koleo.domain.models.StationKeyword
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MainSearchScreen(
    viewModel: MainSearchViewModel
) {
    var showSearchBottomSheet by remember { mutableStateOf(false) }
    val uiState = viewModel.uiState.value

    val startStationKeyword by viewModel.startStationKeywords.collectAsStateWithLifecycle()
    val endStationKeyword by viewModel.endStationKeywords.collectAsStateWithLifecycle()

    when (uiState) {
        MainSearchUiState.Initial -> MainSearchScreen { showSearchBottomSheet = true }
        is MainSearchUiState.Calculated -> CalculatedDistanceScreen(uiState) {
            showSearchBottomSheet = true
        }

        is MainSearchUiState.StationNotFound -> StationNotFoundScreen(uiState) {
            showSearchBottomSheet = true
        }
    }

    if (showSearchBottomSheet) {
        MainSearchBottomSheet(
            onClose = { showSearchBottomSheet = false },
            startSearchStation = { viewModel.startSearchQuery.value = it },
            endSearchStation = { viewModel.endSearchQuery.value = it },
            startStationKeywords = startStationKeyword,
            endStationKeywords = endStationKeyword,
            onSearchClicked = {
                viewModel.calculateDistanceBetweenStations()
                viewModel.startSearchQuery.value = ""
                viewModel.endSearchQuery.value = ""
            },
        )
    }
}

@Composable
fun MainSearchScreen(
    onSearchClicked: () -> Unit,
) {
    Box(
        Modifier.background(
            color = Color(0xFF2B2F50)
        )
    ) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.search_main_screen_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                lineHeight = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                modifier = Modifier.padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.ic_station),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .fillMaxWidth(),
                onClick = onSearchClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC7B333),
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    stringResource(R.string.search_button_text),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}