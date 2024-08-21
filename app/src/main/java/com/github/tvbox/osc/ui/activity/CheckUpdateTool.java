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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CheckUpdateTool {

    public static void checkVersion(Context context, View view) {
        view.setVisibility(View.GONE);
        OkGo.<String>get("https://api.github.com/repos/AkkunYo/ChengZiTv/releases/latest").execute(new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (!TextUtils.isEmpty(body)) {
                    JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();

                    String utcTime = "";
                    int remoteVersionCode = 0;
                    String downloadUrl = "";

                    if (jsonObject.has("created_at")) {
                        utcTime = jsonObject.get("created_at").getAsString();
                    }
                    if (jsonObject.has("tag_name")) {
                        String tag_name = jsonObject.get("tag_name").getAsString();
                        try {
                            remoteVersionCode = Integer.parseInt(tag_name.replace("v", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (jsonObject.has("assets")) {
                        JsonArray assets = jsonObject.getAsJsonArray("assets");
                        for (JsonElement asset : assets) {
                            if (asset != null && asset.getAsJsonObject().has("browser_download_url")) {
                                downloadUrl = asset.getAsJsonObject().get("browser_download_url").getAsString();
                            }
                        }
                    }

                    String browserDownloadUrl = downloadUrl;
                    int serveVersionCode = remoteVersionCode;

                    ZoneId zoneId = ZoneId.of("Asia/Shanghai");

                    OffsetDateTime odt = OffsetDateTime.parse(utcTime);
                    // 将OffsetDateTime转换为北京时间的ZonedDateTime

                    ZonedDateTime bjt = odt.atZoneSameInstant(zoneId);

                    long serverTime = bjt.toInstant().toEpochMilli();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
                    LocalDateTime ldt = LocalDateTime.parse(BuildConfig.PACK_TIME, formatter);
                    long localTime = ldt.atZone(zoneId).toInstant().toEpochMilli();

                    System.out.println(String.format("--zkyml>>服务器版本时间:%s ,本地版本时间：%s ", bjt, ldt));
                    System.out.println(String.format("--zkyml>>服务器版本: %d，本地版本：%d", serveVersionCode, BuildConfig.VERSION_CODE));
                    System.out.println(String.format("--zkyml>>browser_download_url: %s", browserDownloadUrl));

                    if (serveVersionCode > BuildConfig.VERSION_CODE || serverTime > localTime) {
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
                        Toast.makeText(context, "当前已经是最新版本了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
