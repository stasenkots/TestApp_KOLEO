package com.example.testapp_koleo.ui.search

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp_koleo.R

@Composable
fun StationNotFoundScreen(
    uiState: MainSearchUiState.StationNotFound,
    onSearchClicked: () -> Unit,
) {
    Box(
        Modifier.background(
            color = Color(0xFF2B2F50)
        )
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Spacer(modifier = Modifier.height(100.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.station_not_found_title, uiState.stationName),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                lineHeight = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                modifier = Modifier.padding(horizontal = 16.dp),
                painter = painterResource(id = R.drawable.ic_location_not_found),
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