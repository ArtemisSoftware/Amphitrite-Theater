package com.artemissoftware.amphitritetheater.util

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

object PhotoPickerLauncherUtil {

    @Composable
    fun photoPickerLauncher(onImageSelected: (imageUri: Uri) -> Unit): ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?> {
        return rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { imageUri ->
                imageUri?.let(onImageSelected)
            }
        )
    }
}