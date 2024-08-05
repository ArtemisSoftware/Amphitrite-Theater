@file:OptIn(ExperimentalMaterial3Api::class)

package com.artemissoftware.amphitritetheater.colorpalette

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.artemissoftware.amphitritetheater.R
import com.artemissoftware.amphitritetheater.ui.theme.AmphitriteTheaterTheme
import com.artemissoftware.amphitritetheater.util.PhotoPickerLauncherUtil.photoPickerLauncher


@Composable
fun ColorPaletteScreen(viewModel: ColorPaletteViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    ColorPaletteContent(
        state = state,
        onSetImage = viewModel::setImage,
    )
}

@Composable
private fun ColorPaletteContent(
    onSetImage: (Uri) -> Unit,
    state: ColorPaletteState,
) {

    val context = LocalContext.current

    val photoPickerLauncher = photoPickerLauncher(
        onImageSelected = onSetImage
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.color_palette),
                        color = state.colorPalette.getTitleTextColor()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = state.colorPalette.getTopBarColor()),
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(brush = state.colorPalette.getBackgroundColor())
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                state.imageBitmap?.let { imageBitmap ->
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        bitmap = imageBitmap,
                        contentDescription = "Preview From Gallery",
                        contentScale = ContentScale.Crop
                    )
                } ?: run {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        imageVector = Icons.Default.Add, contentDescription = "Placeholder Icon",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = state.colorPalette.getButtonBackgroundColor(),
                        contentColor = state.colorPalette.getButtonContentColor()
                    ),
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.select_image)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun ColorPaletteContentPreview() {
    AmphitriteTheaterTheme {
        ColorPaletteContent(onSetImage = {}, state = ColorPaletteState())
    }
}
