package com.artemissoftware.amphitritetheater.dynamictabselector

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.ui.theme.Black10
import com.artemissoftware.amphitritetheater.ui.theme.Blue10
import com.artemissoftware.amphitritetheater.ui.theme.WarmBlue

@Composable
fun DynamicTabSelector(
    tabs: List<String>, // Can be 2 to 4 options
    onTabSelected: (selectedIndex: Int) -> Unit = {}
) {

    if (tabs.size !in 2..4) {
        throw IllegalArgumentException("DynamicTabSelector must have between 2 and 4 options")
    }

    val spacing: Dp = 4.dp
    val selectorHeight: Dp = 48.dp
    val tabHeight: Dp = 40.dp
    var selectedOption by remember { mutableIntStateOf(0) }

    // Use BoxWithConstraints to get the width of the container
    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .height(selectorHeight)
            .fillMaxWidth()
            .background(Blue10)
    ) {

        val segmentWidth = maxWidth / tabs.size
        // Adjusted width for each tab, accounting for spacing
        val boxWidth = segmentWidth - spacing * 2
        val positions = tabs.indices.map { index ->
            segmentWidth * index + (segmentWidth - boxWidth) / 2
        }
        // Animate the X offset of the selected tab to smoothly transition between tabs
        val animatedOffsetX by animateDpAsState(targetValue = positions[selectedOption], label = "")

        // Determine the maximum height available for alignment
        val containerHeight = maxHeight
        // Center the tab selector vertically within the container
        val verticalOffset = (containerHeight - tabHeight) / 2

        Row(
            modifier = Modifier.fillMaxHeight(),
            // Ensures spacing between options
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, text ->
                Text(
                    text = text,
                    style = TextStyle(
                        color = Black10.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    ),
                    modifier = Modifier
                        .width(segmentWidth)
                        .clickable(
                            indication = null,
                            // Avoids ripple effect for a cleaner look
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            // Trigger callback with the index of the selected tab
                            onTabSelected(index)
                            selectedOption = index
                        }
                )
            }
        }
        // Selected tab highlighted by applying the selected tab text style
        Box(
            modifier = Modifier
                // Position the selector dynamically based on the selected tab
                .offset(x = animatedOffsetX, y = verticalOffset)
                .clip(RoundedCornerShape(12.dp))
                .width(boxWidth) // Updated box width
                .height(tabHeight)
                .background(Color.White)
        ) {
            Text(
                text = tabs[selectedOption], // Use the selected option's text
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = WarmBlue,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DynamicTabSelectorPreview() {

    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

    AmphitriteTheaterTheme {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            DynamicTabSelector(
                tabs = tabs,
                onTabSelected =  {}
            )
        }
    }
}
