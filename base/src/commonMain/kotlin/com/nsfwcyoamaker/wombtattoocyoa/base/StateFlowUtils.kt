package com.nsfwcyoamaker.wombtattoocyoa.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*


fun <T, R> StateFlow<T>.mapState(scope: CoroutineScope, start: SharingStarted = SharingStarted.Eagerly, mapper: (T)->R): StateFlow<R> {
    return map(mapper).stateIn(scope, start, mapper(value))
}

fun <T1, T2, R> StateFlow<T1>.combineState(otherFlow: StateFlow<T2>, scope: CoroutineScope, start: SharingStarted = SharingStarted.Eagerly, mapper: (T1, T2)->R): StateFlow<R> {
    return combine(otherFlow, mapper).stateIn(scope, start, mapper(this.value, otherFlow.value))
}

fun <T1, T2, R> combineState(flow1: StateFlow<T1>, flow2: StateFlow<T2>, scope: CoroutineScope, start: SharingStarted = SharingStarted.Eagerly, mapper: (T1, T2)->R): StateFlow<R> {
    return combine(flow1, flow2, mapper).stateIn(scope, start, mapper(flow1.value, flow2.value))
}

fun <T1, T2, T3, R> combineState(flow1: StateFlow<T1>, flow2: StateFlow<T2>, flow3: StateFlow<T3>, scope: CoroutineScope, start: SharingStarted = SharingStarted.Eagerly, mapper: (T1, T2, T3)->R): StateFlow<R> {
    return combine(flow1, flow2, flow3, mapper).stateIn(scope, start, mapper(flow1.value, flow2.value, flow3.value))
}