package com.artemissoftware.amphitritetheater.animation.bubblesort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.amphitritetheater.R
import com.artemissoftware.amphitritetheater.animation.bubblesort.domain.model.InteractionInfo
import com.artemissoftware.amphitritetheater.animation.bubblesort.models.SortItem
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
fun BubbleSortScreen(viewmodel: BubbleSortViewModel) {
    BubbleSortContent(
        sortList = viewmodel.listToSort,
        onStartSorting = viewmodel::startSorting,
        onNewList = viewmodel::generateList,
        interactionInfo = viewmodel.interationStat.value
    )
}

@Composable
private fun BubbleSortContent(
    sortList: List<SortItem>,
    interactionInfo: InteractionInfo,
    onStartSorting: () -> Unit,
    onNewList: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(gray)
            .padding(20.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onStartSorting,
            ) {
                Text(
                    text = stringResource(id = R.string.sort_list),
                    fontWeight = FontWeight.Bold,
                )
            }

            Button(
                onClick = onNewList,
            ) {
                Text(
                    text = stringResource(id = R.string.new_list),
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "List size = " + interactionInfo.listSize)

            Text(text = "Interaction number = " + interactionInfo.interactionNumber)
        }

        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            items(
                items = sortList,
                key = {
                    it.id
                }
            ){ item ->
                SortBox(
                    sortItem = item
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BubbleSortContentPreview() {
    AmphitriteTheaterTheme {
        BubbleSortContent(
            sortList = PreviewData.sortList,
            onStartSorting = {},
            onNewList = {},
            interactionInfo = InteractionInfo(0,0)
        )
    }
}

