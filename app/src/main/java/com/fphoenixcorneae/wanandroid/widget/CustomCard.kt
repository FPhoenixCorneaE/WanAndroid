package com.fphoenixcorneae.wanandroid.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.fphoenixcorneae.common.dsl.layout.*
import com.fphoenixcorneae.common.ext.Dp
import com.fphoenixcorneae.common.ext.getColor
import com.fphoenixcorneae.wanandroid.R
import com.google.android.material.card.MaterialCardView

/**
 * @desc：
 * @date：2023/02/23 11:24
 */
class CustomCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr) {
    private lateinit var ivIcon: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvValue: TextView

    init {
        ConstraintLayout {
            layout_width = match_parent
            layout_height = match_parent
            setBackgroundColor(getColor(R.color.color_gray_aaeeeeee))
            ivIcon = ImageView {
                layout_id = "ivIcon"
                layout_width = 36
                layout_height = 36
                center_vertical = true
                start_toStartOf = parent_id
                margin_start = 30
            }
            tvName = TextView {
                layout_id = "tvName"
                layout_width = wrap_content
                layout_height = wrap_content
                align_vertical_to = "ivIcon"
                start_toEndOf = "ivIcon"
                margin_start = 20
                textStyle = bold
                textSize = 16f
                textColor = "#000000"
            }
            tvValue = TextView {
                layout_id = "tvValue"
                layout_width = wrap_content
                layout_height = wrap_content
                align_vertical_to = "ivIcon"
                start_toEndOf = "tvName"
                margin_start = 20
                textStyle = bold
                textSize = 16f
                textColor = "#00FFFF"
            }
        }
        radius = 8.Dp
        cardElevation = 8f
        useCompatPadding = true
        preventCornerOverlap = true
        setCardBackgroundColor(Color.WHITE)
    }

    fun setIconResource(iconRes: Int?) = run {
        ivIcon.isVisible = iconRes != null
        ivIcon.load(iconRes)
    }

    fun setName(name: String?) = run {
        tvName.isVisible = !name.isNullOrEmpty()
        tvName.text = name
    }

    fun setValue(value: String?) = run {
        tvValue.isVisible = !value.isNullOrEmpty()
        tvValue.text = value
    }
}

@BindingAdapter(value = ["iconRes", "name", "value"], requireAll = false)
fun CustomCard.set(
    iconRes: Int?,
    name: String?,
    value: String?,
) {
    setIconResource(iconRes)
    setName(name)
    setValue(value)
}

