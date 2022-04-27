package com.bilalov.testapplicationforappricot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.bilalov.testapplicationforappricot.navigation.Navigation
import com.bilalov.testapplicationforappricot.request.ResponseBodyInfo
import com.bilalov.testapplicationforappricot.ui.theme.TestApplicationForAppricotTheme
import com.bilalov.testapplicationforappricot.view.DefaultPreview
import com.bilalov.testapplicationforappricot.view.ErrorView
import com.bilalov.testapplicationforappricot.view.LoaderView
import com.bilalov.testapplicationforappricot.viewmodel.MainFactory
import com.bilalov.testapplicationforappricot.viewmodel.MainViewModel


var size: String = ""
var responseObject: ResponseBodyInfo? = null
lateinit var viewModel: MainViewModel

var url: String = "https://rickandmortyapi.com/api/character?page=1"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TestApplicationForAppricotTheme {
                var isLoading by remember { mutableStateOf(false) }

                isLoading = true
                LoaderView(isLoading = isLoading) {
                }
            }
        }

        viewModel = ViewModelProvider(
            this,
            MainFactory(application, url, true)
        ).get(MainViewModel::class.java)

        viewModel.statusApi.observe(this, Observer {

            if (viewModel.statusApi.value == "OK") {

                responseObject = viewModel.responseObject

                setContent {
                    TestApplicationForAppricotTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            DefaultPreview(
                                responseObject = responseObject,
                                viewModel = viewModel,
                                context = application
                            )
                            //хотел сделать навигацию но чет не получилось
//                            Navigation(
//                                responseObject = responseObject,
//                                viewModel = viewModel,
//                                context = application
//                            )
                        }
                    }
                }
            } else {
                setContent {
                    TestApplicationForAppricotTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            ErrorView(url = url, context = application, viewModel = viewModel)
                        }
                    }
                }
            }
        })
    }
}

class RowProfile(
    var name: String,
    var image: String,
    var species: String,
    var gender: String,
    var status: String,
    var location: String,
    var episodes: String,
   // var navController: NavController
)

