package com.artemissoftware.amphitritetheater.animation.bubblesort

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.amphitritetheater.animation.bubblesort.domain.model.InteractionInfo
import com.artemissoftware.amphitritetheater.animation.bubblesort.domain.usecase.BubbleSortUseCase
import com.artemissoftware.amphitritetheater.animation.bubblesort.models.SortItem
import kotlinx.coroutines.launch
import java.util.Random

class BubbleSortViewModel(
    private val bubbleSortUseCase: BubbleSortUseCase = BubbleSortUseCase()
) : ViewModel() {

    var listToSort = mutableStateListOf<SortItem>()
    var interationStat = mutableStateOf<InteractionInfo>(InteractionInfo(0, 0))

    init {
        generateList()
    }

    fun generateList(){
        listToSort.clear()
        for(i in 0 until 9){
            val rnd = Random()

            listToSort.add(
                SortItem(
                    id = i,
                    isCurrentlyCompared = false,
                    value = rnd.nextInt(8),
                    color = Color(
                        255,
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        255)
                )
            )
        }
    }

    fun startSorting(){
        viewModelScope.launch {

            bubbleSortUseCase(
                list = listToSort.map { it.value }.toMutableList()
            ).collect { swapInfo ->

                interationStat.value = swapInfo.interactionInfo

                val currentItemIndex = swapInfo.currentItem
                val nextItemIndex = currentItemIndex + 1

                listToSort[currentItemIndex] = listToSort[currentItemIndex].copy(isCurrentlyCompared = true)
                listToSort[nextItemIndex] = listToSort[nextItemIndex].copy(isCurrentlyCompared = true)

                if(swapInfo.shouldSwap){
                    val firstItem = listToSort[currentItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[currentItemIndex] = listToSort[nextItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[nextItemIndex] = firstItem
                }
                if(swapInfo.hadNoEffect){
                    listToSort[currentItemIndex] = listToSort[currentItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[nextItemIndex] = listToSort[nextItemIndex].copy(isCurrentlyCompared = false)
                }
            }
        }
    }
}