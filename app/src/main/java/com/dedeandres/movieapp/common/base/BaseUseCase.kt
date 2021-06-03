package com.dedeandres.movieapp.common.base

import com.dedeandres.movieapp.common.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<RESULT> {

    abstract suspend fun buildUseCase(data: Map<String, Any?> = emptyMap()): Either<Exception, RESULT>

    operator fun invoke(
        data: Map<String, Any?> = emptyMap(),
        onResult: (Either<Exception, RESULT>) -> Unit = {}
    ) {
        val job = GlobalScope.async(Dispatchers.IO) { buildUseCase(data) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

}