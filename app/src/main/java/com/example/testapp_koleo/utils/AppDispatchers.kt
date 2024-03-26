package com.example.testapp_koleo.utils

import javax.inject.Qualifier


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val value: AppDispatchers)


enum class AppDispatchers {

    Default,

    IO,

    Main
}