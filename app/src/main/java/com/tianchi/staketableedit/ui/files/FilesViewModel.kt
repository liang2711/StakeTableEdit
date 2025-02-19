package com.tianchi.staketableedit.ui.files

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilesViewModel @Inject constructor() : ViewModel() {
    val fileState = FileState()
}