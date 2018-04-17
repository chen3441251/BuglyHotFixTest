package com.buglyhotfix.demo.buglyhotfixdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.buglyhotfix.demo.buglyhotfixdemo.utils.ChannelUtil;

/**
 * Bugly版本升级和热修复
 * create:chenchao 2018/4/16
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        TextView version = (TextView) findViewById(R.id.tv_version);
        TextView channel = (TextView) findViewById(R.id.tv_channel);
        Button checkupdata = (Button) findViewById(R.id.btn_checkupdata);
        Button exist = (Button) findViewById(R.id.btn_exist);
        int versionCode=0;
        try {
            PackageInfo packageInfo = getApplication().getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
             versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        version.setText("当前版本号："+versionCode);
//        channel.setText("当前渠道版本："+ WalleChannelReader.getChannel(getApplicationContext(),"default"));
        channel.setText("当前渠道版本："+ ChannelUtil.getChannel());
        checkupdata.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        MyTinkerApplicationLike.getTinkerApplicationLike().exitBy2Click(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_checkupdata:
                Toast.makeText(getApplicationContext(),"这是walle渠道打包热更新",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
