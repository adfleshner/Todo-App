package com.flesh.todo.common.suspend

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.delay


suspend fun closeKeyboardFirst(view: View) {
    //Close it fist
    val inputMethodManager =
        view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    //let it close
    delay(view.context.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
}