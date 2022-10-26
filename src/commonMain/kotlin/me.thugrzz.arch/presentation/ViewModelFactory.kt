package me.thugrzz.arch.presentation

import org.kodein.di.DI

/**
 * Класс фабрики вью моделей
 */
expect open class ViewModelFactory(injector: DI, initParams: ViewModelParameters) {
    open fun <T : ViewModelSuperClass> createViewModel(tag: String): T?
}