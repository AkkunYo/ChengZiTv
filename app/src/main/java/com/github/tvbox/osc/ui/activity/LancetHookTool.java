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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tvbox.osc.BuildConfig;
import com.github.tvbox.osc.R;
import com.github.tvbox.osc.api.ApiConfig;
import com.github.tvbox.osc.ui.dialog.AboutDialog;
import com.github.tvbox.osc.util.HawkConfig;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.This;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.TargetClass;

/**
 * Created by Akkun on 2023/9/26.
 * web: https://www.zkyml.com
 * Des:
 */
public class LancetHookTool {

    @TargetClass("com.github.tvbox.osc.base.App")
    @Insert("initParams")
    private void _initParams() {
        Origin.callVoid();
        System.out.println("--zkyml>>初始化默认设置项");
        // Hawk
        Boolean hasInit = Hawk.get("hasInit", false);
        if (!hasInit) {
            Hawk.put("hasInit", true);
            Hawk.put(HawkConfig.HOME_SHOW_SOURCE, false);       //数据源显示: true=开启, false=关闭
            Hawk.put(HawkConfig.HOME_REC, 0);                  //推荐: 0=豆瓣热播, 1=站点推荐, 2=观看历史
            Hawk.put(HawkConfig.HOME_NUM, 4);                  //历史条数: 0=20条, 1=40条, 2=60条, 3=80条, 4=100条
            Hawk.put(HawkConfig.DOH_URL, 1);                   //安全DNS: 0=关闭, 1=腾讯, 2=阿里, 3=360, 4=Google, 5=AdGuard, 6=Quad9
            Hawk.put(HawkConfig.THEME_SELECT, 2);              //主题: 0=奈飞, 1=哆啦, 2=百事, 3=鸣人, 4=小黄, 5=八神, 6=樱花
        }
    }

    @TargetClass("com.github.tvbox.osc.api.ApiConfig")
    @Insert("loadConfig")
    public void _loadConfig(boolean useCache, ApiConfig.LoadConfigCallback callback, Activity activity) {
        String originUrl = Hawk.get(HawkConfig.API_URL, "");
//        if (originUrl.isEmpty()) {
            System.out.println("--zkyml>>设置默认API");
            originUrl = "https://git.zkyml.com/github/tvbox/raw/branch/main/1.json";
            Hawk.put(HawkConfig.API_URL, originUrl);
            ArrayList<String> history = Hawk.get(HawkConfig.API_HISTORY, new ArrayList<String>());
            history.add(originUrl);
            history.add("http://www.饭太硬.net/tv/");
            history.add("https://cdn.jsdelivr.net/gh/a736240087/tvbox@main/1.json");
            history.add("https://cdn.jsdelivr.net/gh/gaotianliuyun/gao@master/0825.json");
            Hawk.put(HawkConfig.API_HISTORY, history);
//        }
//        String liveUrl = Hawk.get(HawkConfig.LIVE_URL, "");
//        if (liveUrl.isEmpty()) {
//            System.out.println("--zkyml>>设置默认直播源");
//            liveUrl = "https://cdn.jsdelivr.net/gh/a736240087/tvbox@main/tvlive/tvlive.txt";
//            Hawk.put(HawkConfig.LIVE_URL, liveUrl);
//
//            ArrayList<String> liveHistory = Hawk.get(HawkConfig.LIVE_HISTORY, new ArrayList<String>());
//            liveHistory.add(0, liveUrl);
//            liveHistory.add("https://cdn.jsdelivr.net/gh/Guovin/iptv-api@gd/output/result.txt");
//            Hawk.put(HawkConfig.LIVE_HISTORY, liveHistory);
//        }
//
//        String epgUrl = Hawk.get(HawkConfig.EPG_URL, "");
//        if (epgUrl.isEmpty()) {
//            System.out.println("--zkyml>>设置默认EPG");
//            epgUrl = "https://epg.112114.xyz?ch={name}&date={date}";
//            Hawk.put(HawkConfig.EPG_URL, epgUrl);
//            ArrayList<String> epgHistory = Hawk.get(HawkConfig.EPG_HISTORY, new ArrayList<String>());
//            epgHistory.add(epgUrl);
//            epgHistory.add("https://epg.112114.free.hr?ch={name}&date={date}");
//            epgHistory.add("https://epg.112114.eu.org?ch={name}&date={date}");
//            epgHistory.add("https://diyp.112114.xyz?ch={name}&date={date}");
//            Hawk.put(HawkConfig.EPG_HISTORY, epgHistory);
//        }
        Origin.callVoid();
    }

    @TargetClass("com.github.tvbox.osc.api.ApiConfig")
    @Insert("parseJson")
    private void _parseJson(String apiUrl, String jsonStr) {
        if (apiUrl.equals("http://饭太硬.com/tv")
                && TextUtils.isEmpty(Hawk.get(HawkConfig.HOME_API, ""))) {
            System.out.println("--zkyml>>设置默认数据源");
            Hawk.put(HawkConfig.HOME_API, "萌米");
        }
        Origin.callVoid();
    }

    @TargetClass("com.github.tvbox.osc.ui.activity.HomeActivity")
    @Insert(value = "tvNameAnimation")
    private void _tvNameAnimation(){
        //no exec
        System.out.println("--zkyml>>复写动画空实现，不做动画");
    }

    @TargetClass("com.github.tvbox.osc.ui.activity.HomeActivity")
    @Insert(value = "init")
    protected void init() {
        Origin.callVoid();
        Object o = This.get();
        if (o instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) o;
            ImageView tvUpdate = homeActivity.findViewById(R.id.tvUpdate);
            tvUpdate.setVisibility(View.GONE);
            Boolean hasUpdate = Hawk.get("hasUpdate", false);
            Long checkTime = Hawk.get("checkTime", 0L);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - checkTime > 1000 * 3600 * 24 * 2L || hasUpdate) {
                checkTime = currentTimeMillis;
                Hawk.put("checkTime", checkTime);
                System.out.println("--zkyml>>检查版本升级");
                CheckUpdateTool.checkVersion(homeActivity, tvUpdate);
                return;
            }
            System.out.println("--zkyml>>检查版本周期内，不再重复动作");
        }

    }

    @TargetClass("com.github.tvbox.osc.ui.dialog.AboutDialog")
    @Insert(value = "onCreate", mayCreateSuper = true)
    protected void _onCreate(Bundle savedInstanceState) {
        Origin.callVoid();
        System.out.println("--zkyml>>关于弹窗的文字描述");

        Object o = This.get();
        if (o instanceof AboutDialog) {
            AboutDialog dialog = (AboutDialog) o;

            String arch = "未知";
            try {
                String nativeLibraryDir = dialog.getContext().getApplicationInfo().nativeLibraryDir;
                int nextIndexOfLastSlash = nativeLibraryDir.lastIndexOf('/') + 1;
                //  return "arm64" || "x86_64"  || "mips64" ;
                arch = nativeLibraryDir.substring(nextIndexOfLastSlash);
            } catch (Exception x) {
                x.printStackTrace();
            }

            Window window = dialog.getWindow();
            if (window != null) {
                ViewGroup rootView = (ViewGroup) window.findViewById(android.R.id.content);
                rootView = (ViewGroup) rootView.getChildAt(0);
                if (rootView != null && rootView.getChildCount() > 0) {
                    View child = rootView.getChildAt(rootView.getChildCount() - 1);
                    if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        CharSequence oldText = textView.getText();

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.format("版本：%s for %s by github\n", BuildConfig.VERSION_NAME.replace("1.0.", "1.0." + BuildConfig.VERSION_CODE + "."), arch));
                        stringBuilder.append(oldText);
                        System.out.println(stringBuilder);

                        textView.setText(stringBuilder);
                    }
                }
            }
        }
    }


    //    @TargetClass("android.widget.Toast")
//    @Proxy("makeText")
    public static Toast _makeText(Context context, CharSequence text, int duration) {
        System.out.println("--zkyml>>" + text);
        if (text.toString().contains("软件接口免费")) {
            text = "你好啊";
        }
        return (Toast) Origin.call();
    }

    /**
     * FocusFinder焦点自定义
     */
//    @TargetClass("androidx.appcompat.app.AppCompatActivity")
//    @Insert(value = "dispatchKeyEvent", mayCreateSuper = true)
    public boolean _dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                boolean diyKey = false;
                Activity activity = (Activity) This.get();
                ViewGroup parent = (ViewGroup) (activity.getWindow().getDecorView());

                if (activity.getCurrentFocus() != null) {
                    ViewGroup viewGroup = (ViewGroup) activity.getCurrentFocus().getParent();
                    do {
                        if (viewGroup != null) {

                            Object tag = viewGroup.getTag();
                            if (tag != null) {
                                System.out.println("--zkyml>" + tag);
                                diyKey = tag.toString().equals("diyKey");
                            }
//                            if (viewGroup.getId() == R.id.tvHotListForGrid || viewGroup.getId() == R.id.mGridView) {
                            if (viewGroup.getId() == R.id.mGridView) {
                                diyKey = true;
                                System.out.println("--zkyml>" + viewGroup.getId());
                            }
                            viewGroup = (ViewGroup) viewGroup.getParent();
                        }
                    } while (viewGroup != null && viewGroup != parent && !diyKey);
                }
                if (diyKey) {
                    int direct = View.FOCUS_DOWN;
                    if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
                        direct = View.FOCUS_DOWN;
                    }
                    if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                        direct = View.FOCUS_UP;
                    }

                    Rect rect = new Rect();
                    parent.findFocus().getGlobalVisibleRect(rect);
                    System.out.println("before:" + rect.toString());
                    rect.set(0, rect.top, rect.right - rect.left, rect.bottom);
                    System.out.println("after:" + rect.toString());
                    View nextFocus = FocusFinder.getInstance().findNextFocusFromRect(parent, rect, direct);
                    if (nextFocus != null) {
                        nextFocus.requestFocus();
                        return true;
                    }
                }
            }
        }
        return (boolean) Origin.call();
    }

}
