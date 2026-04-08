package com.rodelindev.moviesnow.core.extensions

/*context(viewModel: ViewModel)
fun <T> Flow<T>.stateInWhileSubscribed(initialValue: T): StateFlow<T> {
    return this.stateIn(
        viewModel.viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue
    )
}*/