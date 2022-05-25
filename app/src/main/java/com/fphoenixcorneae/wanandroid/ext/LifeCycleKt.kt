package com.fphoenixcorneae.wanandroid.ext

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun ComponentActivity.launchRepeatOnLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

inline fun Fragment.launchRepeatOnLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}