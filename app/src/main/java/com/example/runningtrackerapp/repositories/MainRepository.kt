package com.example.runningtrackerapp.repositories

import com.example.runningtrackerapp.database.Run
import com.example.runningtrackerapp.database.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val dao: RunDao
) {
    suspend fun insertRun(run: Run) = dao.insertRun(run)

    suspend fun deleteRun(run: Run) = dao.deleteRun(run)

    fun getAllRunsSortedByDate() = dao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = dao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillis() = dao.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByCaloriesBurned() = dao.getAllRunsSortedByCaloriesBurned()

    fun getAllRunsSortedByAvgSpeed() = dao.getAllRunsSortedByAvgSpeed()

    fun getTotalAvgSpeed() = dao.getTotalAvgSpeed()

    fun getTotalDistance() = dao.getTotalDistance()

    fun getTotalCaloriesBurned() = dao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = dao.getTotalTimeInMillis()

}