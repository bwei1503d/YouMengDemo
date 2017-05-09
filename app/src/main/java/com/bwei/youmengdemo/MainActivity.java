package com.bwei.youmengdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends Activity {

    private Button button;
    private Button share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)  findViewById(R.id.login);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                share();



            }
        });

        share = (Button)  findViewById(R.id.fenxiang);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                shareto();



            }


        });




    }


    private void shareto() {

        new ShareAction(MainActivity.this).withText("hello")
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                    }
                }).open();


    }

    public void share(){

        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                System.out.println("onStart share_media = " + share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                System.out.println("onComplete share_media = " + share_media);
//                uid	openid	unionid	id	用户唯一标识	如果需要做跨APP用户打通，QQ需要使用unionID实现
//                name	screen_name	screen_name	screen_name	用户昵称
//                gender	gender	gender	gender	用户性别	该字段会直接返回男女
//                iconurl

                //获取资料
                String uid =  map.get("uid");
                String name =  map.get("name");
                String gender =  map.get("gender");
                String iconurl =  map.get("iconurl");


            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                System.out.println("onError SHARE_MEDIA share_media = " + share_media);

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                System.out.println("onCancel SHARE_MEDIA share_media = " + share_media);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
