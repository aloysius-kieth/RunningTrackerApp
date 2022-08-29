package com.example.runningtrackerapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtrackerapp.database.Run
import com.example.runningtrackerapp.repositories.MainRepository
import com.example.runningtrackerapp.utils.SortType
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()
    private val runsSortedByTimeInMillis = mainRepository.getAllRunsSortedByTimeInMillis()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate, Observer { result ->
            if (sortType == SortType.DATE) {
                result?.let {
                    runs.value = it
                }
            }
        })
        runs.addSource(runsSortedByAvgSpeed, Observer { result ->
            if (sortType == SortType.AVG_SPEED) {
                result?.let {
                    runs.value = it
                }
            }
        })
        runs.addSource(runsSortedByCaloriesBurned, Observer { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let {
                    runs.value = it
                }
            }
        })
        runs.addSource(runsSortedByDistance, Observer { result ->
            if (sortType == SortType.DISTANCE) {
                result?.let {
                    runs.value = it
                }
            }
        })
        runs.addSource(runsSortedByTimeInMillis, Observer { result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let {
                    runs.value = it
                }
            }
        })
    }

    fun sortRun(sortType: SortType) = when (sortType) {
        SortType.DATE -> runsSortedByDate.value?.let {
            runs.value = it
        }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let {
            runs.value = it
        }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let {
            runs.value = it
        }
        SortType.DISTANCE -> runsSortedByDistance.value?.let {
            runs.value = it
        }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let {
            runs.value = it
        }.also {
            this.sortType = sortType
        }
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

}