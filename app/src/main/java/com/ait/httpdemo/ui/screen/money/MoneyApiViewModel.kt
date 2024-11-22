package com.ait.httpdemo.ui.screen.money

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.ait.httpdemo.data.money.MoneyResult
import com.ait.httpdemo.network.MoneyApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MoneyViewModel @Inject constructor(
    val moneyApi: MoneyApi
) : ViewModel() {
    var moneyUiState: MoneyUiState by mutableStateOf(MoneyUiState.Loading)

    fun getRates() {
        moneyUiState = MoneyUiState.Loading
        viewModelScope.launch {
            moneyUiState = try {
                val result = moneyApi.getRates()
                MoneyUiState.Success(result)
            } catch (e: IOException) {
                MoneyUiState.Error
            } catch (e: HttpException) {
                MoneyUiState.Error
            } catch (e: Exception) {
                MoneyUiState.Error
            }
        }
    }
}

sealed interface MoneyUiState {
    object Init: MoneyUiState
    data class Success(val moneyRates: MoneyResult) : MoneyUiState
    object Error : MoneyUiState
    object Loading : MoneyUiState
}