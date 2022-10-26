package me.thugrzz.arch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DI
import org.kodein.di.instance

/**
 * Класс фабрики вью моделей для андроид
 */
actual open class ViewModelFactory actual constructor(
    private val injector: DI,
    private val initParams: ViewModelParameters,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return createViewModel(modelClass.simpleName) ?: modelClass.newInstance()
    }

    @Suppress("UNCHECKED_CAST")
    actual open fun <T : ViewModelSuperClass> createViewModel(tag: String): T? {
        val viewModel: ViewModelSuperClass by injector.instance(tag, initParams)
        return viewModel as T?
    }
}