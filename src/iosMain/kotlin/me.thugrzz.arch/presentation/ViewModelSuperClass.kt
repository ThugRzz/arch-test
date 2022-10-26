package me.thugrzz.arch.presentation

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

/**
 * Класс вью модели для iOS
 */
actual open class ViewModelSuperClass actual constructor(
    private val initParams: ViewModelParameters,
) {
    /**
     * Скоуп вью модели для работы с корутинами. Из коробки реализация MainScope состоит из
     * ContextScope(SupervisorJob() + Dispatchers.Main)
     */
    actual val viewModelScope = MainScope()

    /**
     * Необходимо переопределить этот метод чтобы выполнить очистку непосредственно перед вызовом [clear] метода
     */
    protected actual open fun onCleared() {
    }

    /**
     * Отменяет скоуп корутины. После вызова этого метода вью модель больше не может использоваться
     */
    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

/**
 * Колбэки вью модели для iOS
 */
abstract class CallbackViewModel {
    protected abstract val viewModel: ViewModelSuperClass

    /**
     * Создание адаптера для Flow чтобы облегчить работу на Swift
     */
    fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModel.viewModelScope, this)

    fun clear() = viewModel.clear()
}
