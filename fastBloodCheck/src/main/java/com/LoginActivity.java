package com;

import com.BlueTooth.SettingBlueToothActivity;
import com.NetWork.LoginService;
import com.NetWork.ReponseService;
import com.Register.RegisterActivity;
import com.Register.User;
import com.Register.UserDAO;
import com.Utils.ContantValue;
import com.Utils.SPutil;
import com.Utils.SysApplication;
import com.geniuseoe.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends Activity {

	/**
	 * 用户登陆类,用户输入的信息与注册时填写的信息进行比对下发登陆使用权限
	 *
	 */

	Button registerButton, loginButton;
	EditText login_username, login_password;
	User user=new User();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		setContentView(R.layout.login_layout);
		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);

		// 执行登陆
		loginButton = (Button) findViewById(R.id.loginButton);

//		loginButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//			}
//		});


		// 转交注册账号类处理
		registerButton = (Button) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,
						RegisterActivity.class));
			}
		});
	}
    public void login(View v)
    {
        String username = login_username.getText().toString();
        String password = login_password.getText().toString();
        UserDAO uService = new UserDAO(LoginActivity.this);
        // 登陆成功失败的标记
        boolean flag = uService.login(username, password);
		if(!flag)
		{
			Toast.makeText(getApplicationContext(),"登陆失败",Toast.LENGTH_SHORT).show();
		}else {
			SPutil.putBoolean(getApplicationContext(), ContantValue.IS_FIRST_LOGIN, true);
			SPutil.putString(getApplicationContext(), ContantValue.USERNAME, username);
			Bundle data = new Bundle();
			data.putString("username", username);
			// /////////////////执行登陆，转到MainTabHost
			//开启服务器响应服务器
//        Intent startService=new Intent(this,ReponseService.class);
//        startService(startService);
			//登陆到主页面
			Intent intent = new Intent(LoginActivity.this,
					MainTabHost.class);
			intent.putExtras(data);
			startActivity(intent);
			finish();
		}

    }
}
