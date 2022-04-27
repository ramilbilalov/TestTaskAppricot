package com.bilalov.testapplicationforappricot.navigation

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.bilalov.testapplicationforappricot.request.ResponseBodyInfo
import com.bilalov.testapplicationforappricot.view.DefaultPreview
import com.bilalov.testapplicationforappricot.view.SecondView
import com.bilalov.testapplicationforappricot.viewmodel.MainViewModel

@Composable
fun Navigation(responseObject: ResponseBodyInfo?, viewModel: MainViewModel, context: Application) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.screenName ){
        composable(route = Screen.Main.screenName){
            DefaultPreview(
                responseObject = responseObject,
                viewModel = viewModel,
                context = context,
                //navController = navController
            )        }
        composable(route = Screen.SecondView.screenName,
            arguments = listOf(
                navArgument(name = route.toString()){
                    type = NavType.StringType
                }
            )
        ){
//SecondView(
//    name =  "",
//    species = "",
//    gender = "",
//    status = "",
//    //painter = "",
//    location = "",
//    episodes = ""
//)
        }
    }
}


