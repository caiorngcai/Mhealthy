package com;

import com.BlueTooth.SettingBlueToothActivity;
import com.Utils.ContantValue;
import com.Utils.SPutil;
import com.Utils.SysApplication;
import com.geniuseoe.demo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 */
public class WelcomeActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		final View view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);

		// 渐变展示启动屏(渐变度与持续时间)
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {

					redirectToSettingBlueActivity();

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	// 跳转到蓝牙初始化界面LoginActivity
	private void redirectToSettingBlueActivity() {
		Intent intent = new Intent(this, SettingBlueToothActivity.class);
		startActivity(intent);
		finish();
	}
	//跳转到登陆界面
	private void redirectToLoginActivity() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	private void redirectToMainTabActivity() {

		Bundle data = new Bundle();
		data.putString("username", SPutil.getString(getApplicationContext(),ContantValue.USERNAME,""));

		// /////////////////执行登陆，转到MainTabHost

		Intent intent = new Intent(this,
				MainTabHost.class);
		intent.putExtras(data);
		startActivity(intent);
	}
}