package com.artemissoftware.amphitritetheater.colorpalette

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.InputStream

class ColorPaletteViewModel(application: Application) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(ColorPaletteState())
    val state = _state.asStateFlow()

    private fun getContext(): Context = getApplication<Application>().applicationContext

    fun setImage(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            getContext().contentResolver.openInputStream(uri)?.use { inputStream: InputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                _state.update {
                    val palette = Palette.from(bitmap).generate()

                    it.copy(
                        imageBitmap = bitmap.asImageBitmap(),
                        palette = palette,
                        colorPalette = ColorPalette(palette)
                    )
                }
            }
        }
    }

}