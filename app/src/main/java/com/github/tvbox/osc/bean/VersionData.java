package com.github.tvbox.osc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Keep;

/**
 * Created by Akkun on 2023/8/7.
 * web: https://www.zkyml.com
 * Des:
 */
@Keep
public class VersionData implements Serializable {

    /**
     * url : https://api.github.com/repos/AkkunYo/ChengZiTv/releases/140782616
     * assets_url : https://api.github.com/repos/AkkunYo/ChengZiTv/releases/140782616/assets
     * html_url : https://github.com/AkkunYo/ChengZiTv/releases/tag/v10
     * id : 140782616
     * tag_name : v10
     * target_commitish : main
     * name : v10
     * created_at : 2024-02-09T06:43:28Z
     * published_at : 2024-02-09T10:54:06Z
     * assets : [{"url":"https://api.github.com/repos/AkkunYo/ChengZiTv/releases/assets/150688459","id":150688459,"node_id":"RA_kwDOLO_hCs4I-1LL","name":"ChengZiTv-v10.apk","label":"","uploader":{"login":"github-actions[bot]","id":41898282,"node_id":"MDM6Qm90NDE4OTgyODI=","avatar_url":"https://avatars.githubusercontent.com/in/15368?v=4","gravatar_id":"","url":"https://api.github.com/users/github-actions%5Bbot%5D","html_url":"https://github.com/apps/github-actions","followers_url":"https://api.github.com/users/github-actions%5Bbot%5D/followers","following_url":"https://api.github.com/users/github-actions%5Bbot%5D/following{/other_user}","gists_url":"https://api.github.com/users/github-actions%5Bbot%5D/gists{/gist_id}","starred_url":"https://api.github.com/users/github-actions%5Bbot%5D/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/github-actions%5Bbot%5D/subscriptions","organizations_url":"https://api.github.com/users/github-actions%5Bbot%5D/orgs","repos_url":"https://api.github.com/users/github-actions%5Bbot%5D/repos","events_url":"https://api.github.com/users/github-actions%5Bbot%5D/events{/privacy}","received_events_url":"https://api.github.com/users/github-actions%5Bbot%5D/received_events","type":"Bot","site_admin":false},"content_type":"application/vnd.android.package-archive","state":"uploaded","size":43877884,"download_count":2,"created_at":"2024-02-09T10:54:07Z","updated_at":"2024-02-09T10:54:08Z","browser_download_url":"https://github.com/AkkunYo/ChengZiTv/releases/download/v10/ChengZiTv-v10.apk"}]
     * tarball_url : https://api.github.com/repos/AkkunYo/ChengZiTv/tarball/v10
     * zipball_url : https://api.github.com/repos/AkkunYo/ChengZiTv/zipball/v10
     * body : **Full Changelog**: https://github.com/AkkunYo/ChengZiTv/commits/v10
     */

    private String url;
    private String assets_url;
    private String tag_name;
    private String target_commitish;
    private String name;
    private String created_at;
    private String published_at;
    private String body;
    private List<AssetsBean> assets;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAssets_url() {
        return assets_url;
    }

    public void setAssets_url(String assets_url) {
        this.assets_url = assets_url;
    }

    public String getTag_name() {
        return tag_name;
    }

    public List<String> getLog() {
        return new ArrayList<>();
    }

    public String getDownloadUrl(){
        return "https://mirror.ghproxy.com/" + assets.get(0).getBrowser_download_url();
    }

    public int getMode() {
        return 1;
    }

    public int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = Integer.parseInt(getTag_name().replace("v", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTarget_commitish() {
        return target_commitish;
    }

    public void setTarget_commitish(String target_commitish) {
        this.target_commitish = target_commitish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<AssetsBean> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetsBean> assets) {
        this.assets = assets;
    }

}
