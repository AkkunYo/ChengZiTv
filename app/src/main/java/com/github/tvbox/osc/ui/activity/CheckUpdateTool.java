package com.github.tvbox.osc.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tvbox.osc.BuildConfig;
import com.github.tvbox.osc.R;
import com.github.tvbox.osc.api.ApiConfig;
import com.github.tvbox.osc.bean.VersionData;
import com.github.tvbox.osc.ui.dialog.AboutDialog;
import com.github.tvbox.osc.util.HawkConfig;
import com.google.gson.Gson;
import com.king.app.updater.AppUpdater;
import com.king.app.updater.UpdateConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.This;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.TargetClass;

public class CheckUpdateTool {

    private static boolean hasCheckVersion = false;
    public static void checkVersion(Context context) {
        if (hasCheckVersion) return;
        hasCheckVersion = true;
        System.out.println("--zkyml>>检查版本升级");
        OkGo.<String>get("https://api.github.com/repos/AkkunYo/ChengZiTv/releases/latest")
                .execute(new StringCallback() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hasCheckVersion = false;
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        hasCheckVersion = false;
                        String body = response.body();
                        if (!TextUtils.isEmpty(body)) {
                            VersionData versionData = new Gson().fromJson(body, VersionData.class);
                            System.out.println(String.format("--zkyml>>服务器版本:%d，本地版本：%d", versionData.getVersionCode(), BuildConfig.VERSION_CODE));
                            if (versionData.getVersionCode() > BuildConfig.VERSION_CODE) {
                                if (versionData.getMode() < 2) {
                                    //一句代码，傻瓜式更新
                                    UpdateConfig updateConfig = new UpdateConfig();
                                    updateConfig.setUrl(versionData.getDownloadUrl());
                                    updateConfig.setVersionCode(versionData.getVersionCode());
                                    new AppUpdater(context, updateConfig).start();
                                }
                            } else {
                                Toast.makeText(context, "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
