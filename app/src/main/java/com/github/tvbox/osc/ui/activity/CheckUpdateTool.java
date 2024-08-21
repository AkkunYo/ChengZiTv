package com.github.tvbox.osc.ui.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.github.tvbox.osc.BuildConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.king.app.updater.AppUpdater;
import com.king.app.updater.UpdateConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CheckUpdateTool {

    public static void checkVersion(Context context, View view) {
        view.setVisibility(View.GONE);
        OkGo.<String>get("https://git.zkyml.com/api/v1/repos/zkyml/ChengZiTv/releases").execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (!TextUtils.isEmpty(body)) {
                    JsonArray jsonArray = JsonParser.parseString(body).getAsJsonArray();
                    if (jsonArray == null || jsonArray.isEmpty()) {
                        System.out.println("--zkyml>>查询服务器接口数据异常:" + body);
                        return;
                    }
                    JsonObject jsonObject = (JsonObject) jsonArray.get(0);

                    int remoteVersionCode = 0;
                    String downloadUrl = "";

                    if (jsonObject.has("name")) {
                        String tag_name = jsonObject.get("name").getAsString();
                        try {
                            remoteVersionCode = Integer.parseInt(tag_name.split(".v")[1].toString().split(" ")[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (jsonObject.has("assets")) {
                        JsonArray assets = jsonObject.getAsJsonArray("assets");
                        if (assets != null && !assets.isEmpty()) {
                            JsonElement asset = assets.get(0);
                            if (asset != null && asset.getAsJsonObject().has("browser_download_url")) {
                                downloadUrl = asset.getAsJsonObject().get("browser_download_url").getAsString();
                            }
                        }
                    }

                    String browserDownloadUrl = downloadUrl;
                    int serveVersionCode = remoteVersionCode;

                    int localVersionCode = 10;
//                    try {
//                        localVersionCode = Integer.parseInt(BuildConfig.INJECT_VERSION_CODE);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(String.format("--zkyml>>服务器版本: %d，本地版本：%d", serveVersionCode, localVersionCode));
                    System.out.println(String.format("--zkyml>>browser_download_url: %s", browserDownloadUrl));

                    if (serveVersionCode > localVersionCode  ) {
                        Hawk.put("hasUpdate", true);
                        view.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "当前有新版本可以更新啦", Toast.LENGTH_SHORT).show();
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                view.setVisibility(View.GONE);
                                //一句代码，傻瓜式更新
                                UpdateConfig updateConfig = new UpdateConfig();
                                updateConfig.setUrl(browserDownloadUrl);
                                updateConfig.setVersionCode(serveVersionCode);
                                new AppUpdater(context, updateConfig).start();
                            }
                        });
                    } else {
                        Hawk.put("hasUpdate", false);
                        Toast.makeText(context, "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
