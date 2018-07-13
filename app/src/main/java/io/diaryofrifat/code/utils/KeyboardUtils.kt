package io.diaryofrifat.code.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class KeyboardUtils private constructor() {

    companion object {
        /**
         * This method hides the keyboard if it was being shown
         *
         * @param activity current activity
         * */
        fun hideKeyboard(activity: Activity) {
            var view = activity.currentFocus
            if (view == null) view = View(activity)
            val imm =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        /**
         * This method shows soft keyboard
         *
         * @param editText the text field where we want to provide our input
         * @param context current context
         * */
        fun showKeyboard(editText: EditText, context: Context) {
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.requestFocus()
            val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, 0)
        }

        /**
         * This method toggles soft keyboard
         *
         * @param context current context
         * */
        fun toggleKeyboard(context: Context) {
            val imm = context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}