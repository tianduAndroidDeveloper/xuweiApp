package com.android.androidframework.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class LabelGroup extends ViewGroup {

	Context mContext;

	public LabelGroup(Context context) {
		super(context);
		mContext = context;
	}

	public LabelGroup(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mContext = context;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		final int childCount = getChildCount();

		int maxWidth = r - l;

		int x = 0;

		int y = 0;

		int row = 0;

		for (int i = 0; i < childCount; i++) {

			final View child = this.getChildAt(i);

			if (child.getVisibility() != View.GONE) {

				int width = child.getMeasuredWidth();

				int height = child.getMeasuredHeight();

				x += width;

				y = row * height + height + 10;

				if (x > maxWidth) {

					x = width;

					row++;

					y = row * height + height + 10;

				}

				child.layout(x - width, y - height, x, y);
			}
		}
	}


	public int getChildrenHeight() {

		int width = getMeasuredWidth();
		//int height = 0;
		int paddLeft = getPaddingLeft();
		int paddRight = getPaddingRight();
		int padTop = getPaddingTop();
		int padBottom = getPaddingBottom();

		int childWidth = (width - 20 * 3 - paddLeft - paddRight) / 4;
		int childHeight = childWidth;

		int childCount = getChildCount();
		int rowCount = childCount / 4;
		if (childCount % 4 > 0) {
			rowCount++;
		}

		if (rowCount > 0) {
			childHeight = rowCount * childHeight + (rowCount - 1) * 20 + padTop + padBottom;
		} else {
			childHeight = padTop + padBottom;
		}

		return childHeight;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

		int childCount = getChildCount();

		int x = 0;

		int y = 0;

		int row = 0;

		for (int index = 0; index < childCount; index++) {

			final View child = getChildAt(index);

			if (child.getVisibility() != View.GONE) {

				child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

				int width = child.getMeasuredWidth();

				int height = child.getMeasuredHeight();

				x += width;

				y = row * height + height + 20;

				if (x > maxWidth) {

					x = width;

					row++;

					y = row * height + height + 20;

				}

			}

		}

		setMeasuredDimension(maxWidth, y);

	}

}
