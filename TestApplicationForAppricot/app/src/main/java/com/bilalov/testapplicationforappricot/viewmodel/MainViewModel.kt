package com.bilalov.testapplicationforappricot.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bilalov.testapplicationforappricot.request.ResponseBodyInfo
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(application: Application, url: String, page: Boolean):ViewModel() {

    val statusApi = MutableLiveData<String>()
    var name:String = ""
    var responseObject: ResponseBodyInfo? = null
    var i:Int = 0

    init {

        sendApi(url, application, page)
    }

 fun sendApi(url:String, application: Application, page:Boolean){
    Fuel.get(url)
        .response { request, response, result ->

            Log.d("TAG_REQ_1", request.toString())
            Log.d("TAG_REQ_2", response.toString())
            Log.d("TAG_REQ_3", result.toString())

            val responseObjectType =
                object : TypeToken<ResponseBodyInfo>() {}.type

            if (response.isSuccessful) {
                 responseObject = Gson().fromJson(
                    response.body().asString("application/json; charset=utf-8"),
                    responseObjectType
                ) as? ResponseBodyInfo
                if (page){
                    i++
                }
                 name = responseObject?.results?.get(0)?.name.toString()
                statusApi.value = response.responseMessage
                
            } else {
                statusApi.value = response.responseMessage
                Toast.makeText(
                    application,
                    "Код ошибки: ${response.statusCode}, Не удалось отправить запрос",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
}

}