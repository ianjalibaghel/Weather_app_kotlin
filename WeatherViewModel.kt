package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Constant
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    //expose above private val
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city:String){
        _weatherResult.value =NetworkResponse.Loading
        try {
            viewModelScope.launch {
                val response= weatherApi.getWeather(Constant.apiKey,city)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value= NetworkResponse.Success(it)
                    }
                }
                else{
                    _weatherResult.value= NetworkResponse.Error("Failed to Load")
                }
            }
        }
        catch (e: Exception){
            _weatherResult.value= NetworkResponse.Error("Failed to Load")
        }

    }
}