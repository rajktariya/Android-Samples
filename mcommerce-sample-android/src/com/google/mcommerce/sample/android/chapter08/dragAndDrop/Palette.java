package com.google.mcommerce.sample.android.chapter08.dragAndDrop;

// This file is Palette.java
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.mcommerce.sample.android.R;

public class Palette extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle icicle) {
		View v = inflater.inflate(R.layout.c08_drag_drop_palette, container,
				false);
		return v;
	}
}
