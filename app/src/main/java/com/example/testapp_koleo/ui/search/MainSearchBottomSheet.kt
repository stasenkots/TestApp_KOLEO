package com.example.testapp_koleo.ui.search

import android.provider.ContactsContract.Contacts.AggregationSuggestions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.paging.compose.LazyPagingItems
import com.example.testapp_koleo.R
import com.example.testapp_koleo.domain.models.StationKeyword
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBottomSheet(
    onClose: () -> Unit,
    startSearchStation: (String) -> Unit,
    endSearchStation: (String) -> Unit,
    startStationKeywords: ImmutableList<StationKeyword>,
    endStationKeywords: ImmutableList<StationKeyword>,
    onSearchClicked: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(true)
    var startStation by remember { mutableStateOf("") }
    var endStation by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        containerColor = Color(0xFF2B2F50),
        onDismissRequest = onClose,
        sheetState = bottomSheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = Color.White,
                width = 100.dp
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.main_search_bottom_sheet_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            StationTextField(
                value = startStation,
                onValueChanged = {
                    startStation = it
                    startSearchStation(it)
                },
                hint = stringResource(R.string.search_start_station_hint),
                suggestions = startStationKeywords
            )
            Spacer(modifier = Modifier.height(16.dp))
            StationTextField(
                value = endStation,
                onValueChanged = {
                    endStation = it
                    endSearchStation(it)
                },
                hint = stringResource(R.string.search_end_station_hint),
                suggestions = endStationKeywords
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
                    .fillMaxWidth(),
                onClick = {
                    scope.launch {
                        bottomSheetState.hide()
                    }.invokeOnCompletion {
                        onSearchClicked()
                        onClose()
                    }
                },
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
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun StationTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    hint: String,
    suggestions: ImmutableList<StationKeyword>
) {

    var expandedState by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused) expandedState = true
                }
                .padding(horizontal = 32.dp),
            value = value,
            onValueChange = {
                onValueChanged(it)
                expandedState = true
            },
            label = {
                Text(
                    text = hint
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                cursorColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White.copy(
                    alpha = 0.5f
                ),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
        )
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            expanded = expandedState && suggestions.isNotEmpty(),
            onDismissRequest = {
                expandedState = false
            },
            properties = PopupProperties(focusable = false)
        ) {
            suggestions.forEach { suggestion ->
                DropdownMenuItem(onClick = {
                    onValueChanged(suggestion.keyword)
                    expandedState = false
                }, text = {
                    Text(text = suggestion.keyword)
                })
            }
        }
    }
}