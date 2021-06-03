package com.dedeandres.scaffoldprojectmvvmcoroutines.common.base

import com.dedeandres.scaffoldprojectmvvmcoroutines.common.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<RESULT> {

    abstract suspend fun buildUseCaseSingle(data: Map<String, Any?> = emptyMap()): Either<Exception, RESULT>

    operator fun invoke(
        data: Map<String, Any?> = emptyMap(),
        onResult: (Either<Exception, RESULT>) -> Unit = {}
    ) {
        val job = GlobalScope.async(Dispatchers.IO) { buildUseCaseSingle(data) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

}