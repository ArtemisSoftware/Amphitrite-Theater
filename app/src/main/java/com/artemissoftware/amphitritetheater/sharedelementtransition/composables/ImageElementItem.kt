package com.artemissoftware.amphitritetheater.sharedelementtransition.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.artemissoftware.amphitritetheater.sharedelementtransition.MockData
import com.artemissoftware.amphitritetheater.sharedelementtransition.models.ImageElement
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme

@Composable
internal fun ImageElementItem(
    image: ImageElement,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(image.id) }
            .padding(8.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(size = 12.dp)),
            model = image.url,
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = image.location,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .alpha(0.5f),
                text = image.author,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageElementItemPreview() {
    AmphitriteTheaterTheme {
        ImageElementItem(
            modifier = Modifier.fillMaxWidth(),
            image = MockData.images[1],
            onClick = {}
        )
    }
}