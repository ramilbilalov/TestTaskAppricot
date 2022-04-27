package com.bilalov.testapplicationforappricot.view

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bilalov.testapplicationforappricot.R
import com.bilalov.testapplicationforappricot.ui.theme.TestApplicationForAppricotTheme
import com.bilalov.testapplicationforappricot.viewmodel.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun ErrorView(url: String, context: Application, viewModel: MainViewModel) {
    TestApplicationForAppricotTheme {

        var refreshing by rememberSaveable { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress { _, dragAmount ->
                        Log.e("TTT_Scan", "$dragAmount")
                    }
                }
        ) {
            LaunchedEffect(refreshing) {
                if (refreshing) {
                    delay(1000)
                    viewModel.sendApi(url, context, false)
                    if (viewModel.statusApi.value == "OK") {
                        Toast.makeText(context, "Connection is good", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Error, connection is lost", Toast.LENGTH_SHORT)
                            .show()
                    }
                    refreshing = false
                }
            }
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = {
                    refreshing = true
                },
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(count = 1) {
                        Image(
                            painter = painterResource(id = R.drawable.connection_lost),
                            contentDescription = "ConnectionIsLost"
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 10.dp)
                        ) {
                            Text(
                                text = "Please check your internet connections and swipe up to refresh",
                                modifier = Modifier
                                    .padding(3.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}