package com.android.capstone.hairapy.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.content.res.getIntOrThrow
import androidx.core.widget.addTextChangedListener
import com.android.capstone.hairapy.R

class CustomEditText: AppCompatEditText, View.OnTouchListener {

    private var startIconsDrawable: Drawable? = null
    private var showPasswordIconsDrawable: Drawable? = null
    private var hidePasswordIconsDrawable: Drawable? = null
    private lateinit var backgroundsDrawable: Drawable
    private lateinit var backgroundErrorsDrawable: Drawable
    private var backgroundCorrectsDrawable: Drawable? = null
    private var minPasswordsLength = 8
    private var maxCharLength = 12
    private var password = ""
    private var etHints = ""
    private var isPasswordsShown = false
    private var snapsInputType = SnapInputType.PASSWORD
    constructor(context: Context) : super(context) {
        inits()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getsAttribute(attrs)
        inits()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getsAttribute(attrs)
        inits()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setsDrawableIcon(
            left = startIconsDrawable,
            right = if (isPasswordsShown) showPasswordIconsDrawable else hidePasswordIconsDrawable
        )
        background = if (error.isNullOrEmpty()) backgroundCorrectsDrawable ?: backgroundsDrawable else backgroundErrorsDrawable
        hint = etHints
    }
    private fun inits() {
        when (snapsInputType) {
            SnapInputType.USERNAME -> {
                inputType = INPUT_TYPE_USERNAMES
                etHints = resources.getString(R.string.username_hint)
                addTextChangedListener(onTextChanged = { username, _, _, _ ->
                    if (!isValidUsername(username)) setError(resources.getString(R.string.username_error), null)
                    backgroundCorrectsDrawable = ContextCompat.getDrawable(context, R.drawable.bg_snap_edit_text_correct)
                })
            }
            SnapInputType.PASSWORD -> {
                inputType = INPUT_TYPE_PASSWORDS
                etHints = resources.getString(R.string.password_hint)
                showPasswordIconsDrawable = ContextCompat.getDrawable(context, R.drawable.ic_show_password)
                hidePasswordIconsDrawable = ContextCompat.getDrawable(context, R.drawable.ic_hide_password)
                addTextChangedListener(onTextChanged = { password, _, _, _ ->
                    if (!isValidPassword(password)) setError(resources.getString(R.string.password_error), null)
                    backgroundCorrectsDrawable = ContextCompat.getDrawable(context, R.drawable.bg_snap_edit_text_correct)
                })
                setOnTouchListener(this)
            }
        }
        backgroundsDrawable = ContextCompat.getDrawable(context, R.drawable.bg_snap_edit_text) as Drawable
        backgroundErrorsDrawable = ContextCompat.getDrawable(context, R.drawable.bg_snap_edit_text_error) as Drawable
    }

    private fun getsAttribute(attrs: AttributeSet?) {
        val styles = context.obtainStyledAttributes(attrs, R.styleable.SnapEditText)
        minPasswordsLength = styles.getInt(R.styleable.SnapEditText_min_password_length, 8)
        snapsInputType = when (styles.getIntOrThrow(R.styleable.SnapEditText_custom_type)) {
            SnapInputType.PASSWORD.value -> SnapInputType.PASSWORD
            SnapInputType.USERNAME.value -> SnapInputType.USERNAME
            else -> throw IllegalArgumentException("Invalid custom_type value")
        }
        styles.recycle()
    }
    private fun setsDrawableIcon(
        left: Drawable? = null,
        top: Drawable? = null,
        right: Drawable? = null,
        bottom: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            left, top, right, bottom
        )
        compoundDrawablePadding = 16
    }

    private fun isValidPassword(password: CharSequence?) =
        !password.isNullOrEmpty() && password.length >= minPasswordsLength
    private fun isValidUsername(username: CharSequence?) =
        !username.isNullOrEmpty() && username.length <= maxCharLength

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val showsHideButtonStart: Float
            val showsHideButtonEnd: Float
            var isShowsHideButtonClicked = false
            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                showsHideButtonEnd = ((hidePasswordIconsDrawable?.intrinsicWidth ?: 0) + paddingStart).toFloat()
                when {
                    event.x < showsHideButtonEnd -> isShowsHideButtonClicked = true
                }
            } else {
                showsHideButtonStart = (width - paddingEnd - (hidePasswordIconsDrawable?.intrinsicWidth ?: 0)).toFloat()
                when {
                    event.x > showsHideButtonStart -> isShowsHideButtonClicked = true
                }
            }
            return if (isShowsHideButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        isPasswordsShown = !isPasswordsShown
                        updatePasswordsVisibility()
                        true
                    }
                    else -> false
                }
            } else false
        }
        return false
    }

    private fun updatePasswordsVisibility() {
        inputType = if (isPasswordsShown) INPUT_TYPE_VISIBLE_PASSWORDS else INPUT_TYPE_PASSWORDS
        setSelection(text?.length ?: 0)
    }

    enum class SnapInputType(val value: Int) {
        USERNAME(0),
        PASSWORD(1),
    }

    companion object {
        private const val INPUT_TYPE_USERNAMES = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
        private const val INPUT_TYPE_PASSWORDS = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        private const val INPUT_TYPE_VISIBLE_PASSWORDS = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }

}