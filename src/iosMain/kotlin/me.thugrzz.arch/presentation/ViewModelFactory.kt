package me.thugrzz.arch.presentation

import org.kodein.di.DI
import org.kodein.di.instance

actual open class ViewModelFactory actual constructor(
    private val injector: DI,
    private val initParams: ViewModelParameters,
) {
    @Suppress("UNCHECKED_CAST")
    actual open fun <T : ViewModelSuperClass> createViewModel(tag: String): T? {
        val viewModel: ViewModelSuperClass by injector.instance(tag, initParams)
        return viewModel as T?
    }
}