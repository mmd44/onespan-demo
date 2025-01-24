package com.onespan.android.interview.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * An implementation of the **one-shot** use-case concept in Clean Architecture using Kotlin coroutines. Override [performAction] to implement
 * the use-case.
 *
 * @param T Type of the value to be returned by the [execute] function, i.e. the output
 * @param Params Type of the class that will be passed to the [execute] function, i.e. the input
 * @property coroutineDispatcher The context in which the use-case will run the [performAction] function (called by [execute]).
 */
abstract class SuspendableUseCase<T, in Params>(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    /**
     * Runs [performAction] inside the provided [coroutineDispatcher]. This is the main entry point for the use-case (the function that is
     * accessible to callers).
     *
     * @param params The input of type [Params] needed for the use-case (will be passed to [performAction])
     * @return The output of type [T] returned by [performAction]
     */
    suspend fun execute(params: Params): T = withContext(coroutineDispatcher) {
        performAction(params)
    }

    /**
     * This is the heart of [SuspendableUseCase]. Override this function as needed to implement the use-case.
     *
     * @param params The input of type [Params]
     * @return The output of type [T]
     */
    protected abstract suspend fun performAction(params: Params): T

    /**
     * This provides a shortcut to calling [execute].
     *
     * e.g.:
     * ```
     * someUseCase(Input())
     * ```
     *
     * @see execute
     */
    suspend operator fun invoke(params: Params): T = execute(params)
}