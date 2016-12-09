package com.frames.spoon.rxapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frames.spoon.rxapplication.utils.HttpUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

public class MainActivity extends AppCompatActivity {
    private HttpUtils httpUtils;

    private Button button;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);
        textview= (TextView) findViewById(R.id.textview);
        httpUtils = new HttpUtils();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpUtils.get("http://guolin.tech/api/china").observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //处理对话框消失
                    }

                    @Override
                    public void onError(Throwable e) {
                        textview.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        textview.setText(s);
                    }
                });
            }
        });
    }
}
