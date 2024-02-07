package com.github.tvbox.osc.bean;

import java.io.Serializable;

import androidx.annotation.Keep;

/**
 * Created by Akkun on 2024/3/13.
 * web: http://www.zkyml.com
 * Des:
 */
@Keep
public class AssetsBean implements Serializable {
   /**
    * url : https://api.github.com/repos/AkkunYo/ChengZiTv/releases/assets/150688459
    * id : 150688459
    * node_id : RA_kwDOLO_hCs4I-1LL
    * name : ChengZiTv-v10.apk
    * label :
    * uploader : {"login":"github-actions[bot]","id":41898282,"node_id":"MDM6Qm90NDE4OTgyODI=","avatar_url":"https://avatars.githubusercontent.com/in/15368?v=4","gravatar_id":"","url":"https://api.github.com/users/github-actions%5Bbot%5D","html_url":"https://github.com/apps/github-actions","followers_url":"https://api.github.com/users/github-actions%5Bbot%5D/followers","following_url":"https://api.github.com/users/github-actions%5Bbot%5D/following{/other_user}","gists_url":"https://api.github.com/users/github-actions%5Bbot%5D/gists{/gist_id}","starred_url":"https://api.github.com/users/github-actions%5Bbot%5D/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/github-actions%5Bbot%5D/subscriptions","organizations_url":"https://api.github.com/users/github-actions%5Bbot%5D/orgs","repos_url":"https://api.github.com/users/github-actions%5Bbot%5D/repos","events_url":"https://api.github.com/users/github-actions%5Bbot%5D/events{/privacy}","received_events_url":"https://api.github.com/users/github-actions%5Bbot%5D/received_events","type":"Bot","site_admin":false}
    * content_type : application/vnd.android.package-archive
    * state : uploaded
    * size : 43877884
    * download_count : 2
    * created_at : 2024-02-09T10:54:07Z
    * updated_at : 2024-02-09T10:54:08Z
    * browser_download_url : https://github.com/AkkunYo/ChengZiTv/releases/download/v10/ChengZiTv-v10.apk
    */

   private String url;
   private int id;
   private String node_id;
   private String name;
   private String label;
   private UploaderBean uploader;
   private String content_type;
   private String state;
   private int size;
   private int download_count;
   private String created_at;
   private String updated_at;
   private String browser_download_url;

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNode_id() {
      return node_id;
   }

   public void setNode_id(String node_id) {
      this.node_id = node_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLabel() {
      return label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public UploaderBean getUploader() {
      return uploader;
   }

   public void setUploader(UploaderBean uploader) {
      this.uploader = uploader;
   }

   public String getContent_type() {
      return content_type;
   }

   public void setContent_type(String content_type) {
      this.content_type = content_type;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public int getDownload_count() {
      return download_count;
   }

   public void setDownload_count(int download_count) {
      this.download_count = download_count;
   }

   public String getCreated_at() {
      return created_at;
   }

   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }

   public String getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(String updated_at) {
      this.updated_at = updated_at;
   }

   public String getBrowser_download_url() {
      return browser_download_url;
   }

   public void setBrowser_download_url(String browser_download_url) {
      this.browser_download_url = browser_download_url;
   }

   public static class UploaderBean {
      /**
       * login : github-actions[bot]
       * id : 41898282
       * node_id : MDM6Qm90NDE4OTgyODI=
       * avatar_url : https://avatars.githubusercontent.com/in/15368?v=4
       * gravatar_id :
       * url : https://api.github.com/users/github-actions%5Bbot%5D
       * html_url : https://github.com/apps/github-actions
       * followers_url : https://api.github.com/users/github-actions%5Bbot%5D/followers
       * following_url : https://api.github.com/users/github-actions%5Bbot%5D/following{/other_user}
       * gists_url : https://api.github.com/users/github-actions%5Bbot%5D/gists{/gist_id}
       * starred_url : https://api.github.com/users/github-actions%5Bbot%5D/starred{/owner}{/repo}
       * subscriptions_url : https://api.github.com/users/github-actions%5Bbot%5D/subscriptions
       * organizations_url : https://api.github.com/users/github-actions%5Bbot%5D/orgs
       * repos_url : https://api.github.com/users/github-actions%5Bbot%5D/repos
       * events_url : https://api.github.com/users/github-actions%5Bbot%5D/events{/privacy}
       * received_events_url : https://api.github.com/users/github-actions%5Bbot%5D/received_events
       * type : Bot
       * site_admin : false
       */

      private String login;
      private int id;
      private String node_id;
      private String avatar_url;
      private String gravatar_id;
      private String url;
      private String html_url;
      private String followers_url;
      private String following_url;
      private String gists_url;
      private String starred_url;
      private String subscriptions_url;
      private String organizations_url;
      private String repos_url;
      private String events_url;
      private String received_events_url;
      private String type;
      private boolean site_admin;

      public String getLogin() {
         return login;
      }

      public void setLogin(String login) {
         this.login = login;
      }

      public int getId() {
         return id;
      }

      public void setId(int id) {
         this.id = id;
      }

      public String getNode_id() {
         return node_id;
      }

      public void setNode_id(String node_id) {
         this.node_id = node_id;
      }

      public String getAvatar_url() {
         return avatar_url;
      }

      public void setAvatar_url(String avatar_url) {
         this.avatar_url = avatar_url;
      }

      public String getGravatar_id() {
         return gravatar_id;
      }

      public void setGravatar_id(String gravatar_id) {
         this.gravatar_id = gravatar_id;
      }

      public String getUrl() {
         return url;
      }

      public void setUrl(String url) {
         this.url = url;
      }

      public String getHtml_url() {
         return html_url;
      }

      public void setHtml_url(String html_url) {
         this.html_url = html_url;
      }

      public String getFollowers_url() {
         return followers_url;
      }

      public void setFollowers_url(String followers_url) {
         this.followers_url = followers_url;
      }

      public String getFollowing_url() {
         return following_url;
      }

      public void setFollowing_url(String following_url) {
         this.following_url = following_url;
      }

      public String getGists_url() {
         return gists_url;
      }

      public void setGists_url(String gists_url) {
         this.gists_url = gists_url;
      }

      public String getStarred_url() {
         return starred_url;
      }

      public void setStarred_url(String starred_url) {
         this.starred_url = starred_url;
      }

      public String getSubscriptions_url() {
         return subscriptions_url;
      }

      public void setSubscriptions_url(String subscriptions_url) {
         this.subscriptions_url = subscriptions_url;
      }

      public String getOrganizations_url() {
         return organizations_url;
      }

      public void setOrganizations_url(String organizations_url) {
         this.organizations_url = organizations_url;
      }

      public String getRepos_url() {
         return repos_url;
      }

      public void setRepos_url(String repos_url) {
         this.repos_url = repos_url;
      }

      public String getEvents_url() {
         return events_url;
      }

      public void setEvents_url(String events_url) {
         this.events_url = events_url;
      }

      public String getReceived_events_url() {
         return received_events_url;
      }

      public void setReceived_events_url(String received_events_url) {
         this.received_events_url = received_events_url;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }

      public boolean isSite_admin() {
         return site_admin;
      }

      public void setSite_admin(boolean site_admin) {
         this.site_admin = site_admin;
      }
   }
}
