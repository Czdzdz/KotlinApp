package com.bulock.common.Widget;

import android.view.View;
import android.view.ViewGroup;

public abstract class SpinningAdapter {

    public abstract View onCreateItemView(ViewGroup parent, int position);

    public abstract int getItemCount();
}