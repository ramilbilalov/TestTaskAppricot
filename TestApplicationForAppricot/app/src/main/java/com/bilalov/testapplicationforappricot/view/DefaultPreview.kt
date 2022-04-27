package com.bilalov.testapplicationforappricot.view

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bilalov.testapplicationforappricot.R
import com.bilalov.testapplicationforappricot.RowProfile
import com.bilalov.testapplicationforappricot.request.ResponseBodyInfo
import com.bilalov.testapplicationforappricot.size
import com.bilalov.testapplicationforappricot.ui.theme.TestApplicationForAppricotTheme
import com.bilalov.testapplicationforappricot.url
import com.bilalov.testapplicationforappricot.viewmodel.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay


@Composable
fun DefaultPreview(
    responseObject: ResponseBodyInfo?,
    viewModel: MainViewModel,
    context: Application,
    //navController: NavHostController
) {

    TestApplicationForAppricotTheme {

        var refreshing by rememberSaveable { mutableStateOf(false) }
        val countImage = rememberSaveable { mutableStateOf(1) }

        val urlNext = responseObject?.info?.next.toString()
        val urlPrev = responseObject?.info?.prev.toString()

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
                        Toast.makeText(context, "Connection is good", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(context, "Error, connection is lost", Toast.LENGTH_LONG)
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
                    modifier = Modifier
                        .fillMaxHeight(4f)
                        .background(Color.DarkGray)
                        .padding(bottom = 37.dp)
                ) {
                    items(count = 1) {
                        var i = 0
                        size = responseObject?.results?.size.toString()
                        while (i < size.toInt()) {
                            MyRow(
                                item = RowProfile(
                                  //  navController = navController,
                                    name = responseObject?.results?.get(i)?.name.toString(),
                                    image = responseObject?.results?.get(i)?.image.toString(),
                                    species = responseObject?.results?.get(i)?.species.toString(),
                                    gender = responseObject?.results?.get(i)?.gender.toString(),
                                    status = responseObject?.results?.get(i)?.status.toString(),
                                    location = responseObject?.results?.get(i)?.location?.name.toString(),
                                    episodes = responseObject?.results?.get(i)?.episode?.size.toString()
                                )
                            )
                            i++
                        }
                    }
                }
            }

        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            Image(
                painter = painterResource(id = R.drawable.left),
                contentDescription = "ConnectionIsLost",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable {
                        url = urlPrev
                        if (url != "null") {
                            viewModel.i -= 2
                            viewModel.sendApi(url, context, true)
                        }
                    })
            Text(
                text = "Page: ${viewModel.i}",
                color = Color.White,
                modifier = Modifier
                    .padding(7.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.right),
                contentDescription = "ConnectionIsLost",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable {
                        url = urlNext
                        if (url != "null") {
                            viewModel.sendApi(url, context, true)
                            countImage.value += 1
                        }
                    }
            )
        }

    }
}

