package com.flesh.todo

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.flesh.todo.common.sealed.UIEmpty
import com.flesh.todo.common.sealed.UIState
import com.robinhood.ticker.TickerView

object BindingAdapters {

    @BindingAdapter("isVisibleGone")
    @JvmStatic
    fun setVisibleGone(view: View, visible: Boolean){
        view.visibility = if(visible){
            View.VISIBLE
        }else{
            View.GONE
        }
    }


    @BindingAdapter("isVisibleGoneTicker")
    @JvmStatic
    fun setVisibleGoneTicker(ticker: TickerView, visible: Boolean){
        ticker.setText(if(visible){
            ticker.context.getString(R.string.completed)
        }else{
            ""
        },true)
    }


    @BindingAdapter("isVisibleWhenEmpty")
    @JvmStatic
    fun setVisibleWhenEmpty(view: TextView, state: UIState){
        view.visibility = if(state is UIState.Empty<*>) {
            view.text = (state as UIState.Empty<UIEmpty.StringEmpty>).empty.empty
            View.VISIBLE
        }else{
            View.GONE
        }
    }

}