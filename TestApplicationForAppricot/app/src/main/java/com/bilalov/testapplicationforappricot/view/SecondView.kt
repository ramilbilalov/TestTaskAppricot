package com.bilalov.testapplicationforappricot.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.ImagePainter

@Composable
fun SecondView(
    isFullScreenOpen: MutableState<Boolean>,
    name: String,
    species: String,
    gender: String,
    status: String,
    painter: ImagePainter,
    location: String,
    episodes: String,
) {
    if (isFullScreenOpen.value) {
        Dialog(
            onDismissRequest = { isFullScreenOpen.value = false }) {
      Row(modifier = Modifier.fillMaxSize()) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.DarkGray)
                .padding(3.dp)
        )
        {
            Image(painter = painter,
                contentDescription = "FullScreenImg",
                modifier = Modifier
                    .size(450.dp, 450.dp)
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = "Name: $name",
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
                Text(
                    text = "Gender: $gender",
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
                Text(
                    text = "Species: $species",
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
                Text(
                    text = "Status: $status",
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
                Text(
                    text = "Location: $location",
                    color = Color.White,
                    modifier = Modifier
                        .padding(3.dp)
                )
                Text(
                    text = "Episodes: $episodes",
                    color = Color.White,
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