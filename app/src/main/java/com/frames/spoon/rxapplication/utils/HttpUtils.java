package com.frames.spoon.rxapplication.utils;

import com.spoon.okmanager.OkManager;
import com.spoon.okmanager.callback.StringCallback;

import okhttp3.Request;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhanxiaolin-n22 on 2016/12/9.
 */

public class HttpUtils {
    public Observable<String> get(final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {//未解除关系
                    //访问网络操作
                    OkManager.get()
                            .url(url)
                            .build()
                            .execute(new StringCallback() {

                                @Override
                                public void onError(okhttp3.Call call, Exception e, int id) {
//                                    tv_result.setText(e.toString());
                                    subscriber.onError(e);
                                }

                                @Override
                                public void onResponse(String response, int id) {
//                                    tv_result.setText(response);
                                    subscriber.onNext(response);
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onBefore(Request request, int id) {
                                    super.onBefore(request, id);
//                                    showProgressDialog();
                                }

                                @Override
                                public void onAfter(int id) {
                                    super.onAfter(id);
//                                    closeProgressDialog();
                                }
                            });
                }
            }
        });
    }
}
