package com.HeartAuscultation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.geniuseoe.demo.R;

import java.util.ArrayList;

public class DemoView extends View {
	private Paint paint;
	private int mBorderColor;
	private float mBorderWidth;
	private ArrayList<Float> dataList;
	
	

	public DemoView(Context context, AttributeSet attrs,
					int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		dataList=new ArrayList<Float>();
	}

	public DemoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		final TypedArray attributes = context.getTheme()
				.obtainStyledAttributes(attrs, R.styleable.DemoView,
						0, 0);
		try {
			mBorderColor = attributes.getColor(
					R.styleable.DemoView_border_color, 0);
			mBorderWidth = attributes.getDimension(
					R.styleable.DemoView_border_width, 0);
		} finally {
			attributes.recycle();
		}
		dataList=new ArrayList<Float>();
	}

	public DemoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		dataList=new ArrayList<Float>();
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		float heiht=getHeight()/(float)2000;
        Log.e("height", dataList.size()+"");
        float width=getWidth()/(float)1000;
        float data1=0,data2=0;
		paint = new Paint(); // 设置一个笔刷大小是3的黄色的画笔
		paint.setColor(mBorderColor);
		paint.setStrokeWidth(mBorderWidth);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		if (dataList.size()>1) {
			for (int i = 1; i < dataList.size(); i++) {
				//滤波
				if (dataList.get(i-1)>2900||dataList.get(i-1)==2900) {
					data1=2900;
				}else if(dataList.get(i-1)<1100||dataList.get(i-1)==1100){
					data1=1100;
				}else if(dataList.get(i-1)>1100&&dataList.get(i-1)<2900){
					data1=dataList.get(i-1);
				}
				
				if (dataList.get(i)>2900||dataList.get(i)==2900) {
					data2=2900;
				}else if(dataList.get(i)<1100||dataList.get(i)==1100){
					data2=1100;
				}else if(dataList.get(i)>1100&&dataList.get(i)<2900){
					data2=dataList.get(i);
				}
				canvas.drawLine(i*width,getHeight()-(data1-1000)*heiht, (i+1)*width, getHeight()-(data2-1000)*heiht, paint);
			}
		}

	}

	public void setData(ArrayList<Float> data) {
		this.dataList = data;
		invalidate();
	}

}
