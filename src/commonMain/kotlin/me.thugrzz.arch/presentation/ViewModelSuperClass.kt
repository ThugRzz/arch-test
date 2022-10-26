package me.thugrzz.arch.presentation

import kotlinx.coroutines.CoroutineScope
import me.thugrzz.arch.presentation.ViewModelParameters

/**
 * Базовая вьюмодель с переопределением скоупа
 * Должна быть платформозависимой
 */
expect open class ViewModelSuperClass(initParams: ViewModelParameters) {
    protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}