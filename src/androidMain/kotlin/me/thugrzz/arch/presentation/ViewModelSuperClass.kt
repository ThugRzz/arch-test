package me.thugrzz.arch.presentation

import androidx.lifecycle.ViewModel as AndroidxViewModel
import androidx.lifecycle.viewModelScope as androidxViewModelScope

/**
 * Android-платформенная ViewModel
 *
 * Компоненты импортированные через псевдонимы
 * [androidx.lifecycle.ViewModel] -> [AndroidxViewModel]
 * [androidx.lifecycle.viewModelScope]->[androidxViewModelScope]
 */
actual open class ViewModelSuperClass actual constructor(
    private val initParams: ViewModelParameters,
) : AndroidxViewModel() {

    /**
     * В качестве скоупа используется viewModelScope, который предоставляется из базовой вью модели androidx
     */
    actual val viewModelScope = androidxViewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
