package com.pinecone.technology.mcommerce.learning.android.chapter04.alarm;

import android.content.Context;

public class BaseTester {
	protected IReportBack mReportTo;
	protected Context mContext;

	public BaseTester(Context ctx, IReportBack target) {
		mReportTo = target;
		mContext = ctx;
	}

}
