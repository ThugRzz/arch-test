package me.thugrzz.arch.presentation

/**
 * Базовый класс для параметров вьюмодели передаваемых снаружи
 * внутрь share модуля
 */
abstract class ViewModelParameters

/**
 * Класс пустых параметров.
 * Нужен для реализации обращения ко вьюмодели, когда ей не надо передавать параметры извне
 */
class EmptyParameters(): ViewModelParameters()