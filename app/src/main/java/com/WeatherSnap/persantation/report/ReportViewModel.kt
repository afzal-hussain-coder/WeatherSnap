package com.weathersnap.persantation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weathersnap.data.local.entity.ReportEntity
import com.weathersnap.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(

    private val repository:
    WeatherRepository

) : ViewModel() {
    val reports =
        repository.getReports()
    private val _notes =
        MutableStateFlow("")
    val notes: StateFlow<String> =
        _notes

    fun updateNotes(
        value: String
    ) {
        _notes.value = value
    }


    fun saveReport(
        report: ReportEntity,
        onSuccess: () -> Unit
    ) {

        viewModelScope.launch {
            repository.saveReport(report)
            onSuccess()
        }
    }
}