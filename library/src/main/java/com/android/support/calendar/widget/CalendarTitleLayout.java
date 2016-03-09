/*
 * Copyright (C) 2015 Jonatan E. Salas { link: http://the-android-developer.blogspot.com.ar }
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.support.calendar.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.support.calendar.exception.IllegalViewArgumentException;
import com.android.support.calendar.R;
import com.android.support.calendar.util.CalendarUtility;

import static com.android.support.calendar.exception.IllegalViewArgumentException.*;

import java.util.Locale;

/**
 * LinearLayout class that provides the Calendar Title View.
 *
 * @author jonatan.salas
 */
public class CalendarTitleLayout extends BaseLinearLayout {
    private OnButtonClickedListener onButtonClicked;
    private ImageView nextButton;
    private ImageView backButton;
    private TextView dateTitle;
    private View view;

    private int monthIndex = 0;

    /**
     * @author jonatan.salas
     */
    public interface OnButtonClickedListener {

        /**
         *
         * @param view
         * @param monthIndex
         */
        void onButtonClicked(@NonNull View view, int monthIndex);
    }

    /**
     *
     * @param context
     */
    public CalendarTitleLayout(Context context) {
        this(context, null, 0);
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public CalendarTitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CalendarTitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode()) {
            init();
        }
    }

    /**
     *
     */
    private void init() {
        final LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.header_view, this, false);

        backButton = (ImageView) view.findViewById(R.id.back_button);
        nextButton = (ImageView) view.findViewById(R.id.next_button);
        dateTitle = (TextView) view.findViewById(R.id.date_title);

        setDateTitleText();

        dateTitle.setEnabled(true);

        backButton.setEnabled(true);
        backButton.setClickable(true);
        backButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    monthIndex--;
                    prepareListeners(view);
                }
        });

        nextButton.setEnabled(true);
        nextButton.setClickable(true);
        nextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    monthIndex++;
                    prepareListeners(view);
                }
        });
    }

    /**
     *
     */
    private void setDateTitleText() {
        final String title = CalendarUtility.getDateTitle(monthIndex);
        final String upperCaseTitle = title.toUpperCase(Locale.getDefault());

        dateTitle.setText(upperCaseTitle);
        updateLayout();
    }

    /**
     *
     */
    private void prepareListeners(View view) {
        setDateTitleText();

        if (null != onButtonClicked) {
            onButtonClicked.onButtonClicked(view, monthIndex);
        } else {
            throw new IllegalViewArgumentException(BUTTON_CLICK_LISTENER_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param typeface
     */
    public void setTitleTextTypeface(Typeface typeface) {
        setTitleTextTypeface(typeface, Typeface.NORMAL);
    }

    /**
     *
     * @param typeface
     * @param style
     */
    public void setTitleTextTypeface(Typeface typeface, int style) {
        if (null != typeface) {
            dateTitle.setTypeface(typeface, style);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(TYPEFACE_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param size
     */
    public void setTitleTextSize(Float size) {
        if (null != size) {
            dateTitle.setTextSize(size);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(SIZE_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param colorId
     */
    public void setTitleTextColor(@ColorRes int colorId) {
        if (0 != colorId) {
            final Integer textColor = getColor(colorId);
            setTitleTextColor(textColor);
        } else {
            throw new IllegalViewArgumentException(COLOR_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param color
     */
    public void setTitleTextColor(Integer color) {
        if (null != color) {
            dateTitle.setTextColor(color);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(COLOR_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param drawableId
     */
    public void setNextButtonDrawable(@DrawableRes Integer drawableId) {
        if (null != drawableId) {
            final Drawable drawable = getDrawable(drawableId);
            setNextButtonDrawable(drawable);
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param drawableId
     */
    public void setBackButtonDrawable(@DrawableRes Integer drawableId) {
        if (null != drawableId) {
            final Drawable drawable = getDrawable(drawableId);
            setBackButtonDrawable(drawable);
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param drawable
     */
    public void setNextButtonDrawable(Drawable drawable) {
        if (null != drawable) {
            nextButton.setImageDrawable(drawable);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param drawable
     */
    public void setBackButtonDrawable(Drawable drawable) {
        if (null != drawable) {
            backButton.setImageDrawable(drawable);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param colorId
     */
    public void setNextButtonDrawableColor(@ColorRes int colorId) {
        if (0 != colorId) {
            final Integer color = getColor(colorId);
            setNextButtonDrawableColor(color);
        } else {
            throw new IllegalViewArgumentException(COLOR_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param colorId
     */
    public void setBackButtonDrawableColor(@ColorRes int colorId) {
        if (0 != colorId) {
            final Integer color = getColor(colorId);
            setBackButtonDrawableColor(color);
        } else {
            throw new IllegalViewArgumentException(COLOR_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param color
     */
    public void setNextButtonDrawableColor(Integer color) {
        if (null != color) {
            backButton.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(COLOR_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param color
     */
    public void setBackButtonDrawableColor(Integer color) {
        if (null != color) {
            backButton.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(COLOR_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param colorId
     */
    public void setBackgroundColorResource(@ColorRes int colorId) {
        if (0 != colorId) {
            view.setBackgroundResource(colorId);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(COLOR_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param color
     */
    public void setBackgroundColor(Integer color) {
        if (null != color) {
            view.setBackgroundColor(color);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(COLOR_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param drawableId
     */
    public void setBackgroundColorDrawable(@DrawableRes int drawableId) {
        if (0 != drawableId) {
            final Drawable drawable = getDrawable(drawableId);
            setBackgroundColorDrawable(drawable);
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_ID_NOT_ZERO_VALUE);
        }
    }

    /**
     *
     * @param drawable
     */
    public void setBackgroundColorDrawable(Drawable drawable) {
        if (null != drawable) {
            //TODO JS: Investigate how to this without a deprecated method.
            view.setBackgroundDrawable(drawable);
            updateLayout();
        } else {
            throw new IllegalViewArgumentException(DRAWABLE_NOT_NULL_MESSAGE);
        }
    }

    /**
     *
     * @param onButtonClicked
     */
    public void setOnButtonClicked(@NonNull OnButtonClickedListener onButtonClicked) {
        this.onButtonClicked = onButtonClicked;
    }
}
