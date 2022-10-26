package me.thugrzz.arch.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

/**
 * Класс адаптера-обертки для получения данных из [Flow] в iOS
 */
class FlowAdapter<T : Any>(
    private val scope: CoroutineScope,
    private val flow: Flow<T>,
) {
    fun subscribe(
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (throwable: Throwable) -> Unit,
    ): Canceller = JobCanceller(
        flow.onEach { onEach(it) }
            .catch { onThrow(it) }
            .onCompletion { onComplete() }
            .launchIn(scope)
    )
}

interface Canceller {
    fun cancel()
}

private class JobCanceller(private val job: Job) : Canceller {
    override fun cancel() {
        job.cancel()
    }
}
