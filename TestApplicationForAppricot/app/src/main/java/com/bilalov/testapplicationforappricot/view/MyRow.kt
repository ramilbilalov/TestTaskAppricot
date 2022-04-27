package com.bilalov.testapplicationforappricot.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.bilalov.testapplicationforappricot.R
import com.bilalov.testapplicationforappricot.RowProfile
import com.bilalov.testapplicationforappricot.navigation.Screen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MyRow(item: RowProfile) {
    var isFullScreenOpenFirst = remember {
        mutableStateOf(false)
    }

    val painterFirst = rememberImagePainter(
        data = item.image,
        builder = {
            placeholder(R.drawable.ic_baseline_find_replace_24)
            error(R.drawable.error_img)
            crossfade(1000)
        })

    SecondView(
        isFullScreenOpen = isFullScreenOpenFirst,
        name = item.name,
        species = item.species,
        gender = item.gender,
        painter = painterFirst,
        status = item.status,
        location = item.location,
        episodes = item.episodes
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
             //   router?.routeTo(Screen.NewMessage.screenName)
                isFullScreenOpenFirst.value = true
                //item.navController.navigate(Screen.SecondView.screenName)

            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.Cyan)
                .padding(3.dp)
        )

        {
            Image(
                painter = painterFirst,
                contentDescription = "cardBackground",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(3.dp)
                    .size(76.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = "Name: ${item.name}",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = "Gender: ${item.gender}",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = "Species: ${item.species}",
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}
