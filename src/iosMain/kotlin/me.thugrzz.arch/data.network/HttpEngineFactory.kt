package me.thugrzz.arch.data.network

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual class HttpEngineFactory {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Darwin
    }
}