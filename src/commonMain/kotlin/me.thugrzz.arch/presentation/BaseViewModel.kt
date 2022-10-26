package me.thugrzz.arch.presentation

import kotlinx.coroutines.flow.*

/**
 * Базовая мьюмодель слоя бизнес-логики
 *
 * [State] стэйт, актуальный для вьюмодели
 * [Effect] эффект, актуальный для вьюмодели
 *
 * @property stateObject экзэмпляр стэйта
 * @param initParams входные параметры вьюмодели
 */
open class BaseViewModel<State : BaseScreenState, Effect : BaseScreenEffect>(
    private val stateObject: State,
    initParams: ViewModelParameters,
) : ViewModelSuperClass(initParams) {

    private val _stateFlow = MutableStateFlow(stateObject)
    val state: StateFlow<State>
        get() = _stateFlow.asStateFlow()
    protected val stateFlowValue: State
        get() = _stateFlow.value

    private val _effect = MutableSharedFlow<Effect>()
    val effect: SharedFlow<Effect>
        get() = _effect.asSharedFlow()


    protected fun updateStateValue(value: State) {
        _stateFlow.value = value
    }

    protected suspend fun emitEffect(e: Effect) {
        _effect.emit(e)
    }
}