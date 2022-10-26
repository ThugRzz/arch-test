package me.thugrzz.arch.data.network

import io.ktor.client.engine.*

expect class HttpEngineFactory() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}