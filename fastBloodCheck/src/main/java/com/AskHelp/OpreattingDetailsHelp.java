package com.AskHelp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.Utils.SysApplication;
import com.geniuseoe.demo.R;

public class OpreattingDetailsHelp extends Activity {

	private TextView details_title;
	private TextView details_content;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);
		setContentView(R.layout.device_use_help_details);
		details_title = (TextView) findViewById(R.id.details_title);
		details_content = (TextView) findViewById(R.id.details_content);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();

		switch (data.getInt("flag")) {
		case 1: {
			details_title.setText("血压计使用帮助");
			details_content.setText("血压");
		}

			break;
		case 2: {
			details_title.setText("血糖仪使用帮助");
			details_content.setText("血糖");
		}

			break;
		case 3: {
			details_title.setText("心脏听诊帮助");
			details_content.setText("听诊");
		}

			break;
		default:
			break;
		}

	}
}
