package com.rxkj.haixiangou.model;

import java.util.List;

/**
 * 创建时间: 2016/12/20 14:11 <br>
 * 作者: zhangbin <br>
 * 描述: 首页model
 */

public class HomePageModel {

  /**
   * request_id : 1234531
   * error_code : 500
   * error_msg : 服务器错误
   * sign : 123948219
   * data : {"banners":[{"scheme":"Google","url":"http://www.google.com"},{"scheme":"Baidu","url":"http://www.baidu.com"},{"scheme":"SoSo","url":"http://www.SoSo.com"}],"channel":[{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"}],"recommend":{"title":"个性推荐","recommend_list":[{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]},"ranking":{"title":"本周TOP10排行榜","ranking_list":[{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]}}
   */

  private String sign;
  /**
   * banners : [{"scheme":"Google","url":"http://www.google.com"},{"scheme":"Baidu","url":"http://www.baidu.com"},{"scheme":"SoSo","url":"http://www.SoSo.com"}]
   * channel : [{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价"}]
   * recommend : {"title":"个性推荐","recommend_list":[{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]}
   * ranking : {"title":"本周TOP10排行榜","ranking_list":[{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]}
   */

  private DataEntity data;

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public DataEntity getData() {
    return data;
  }

  public void setData(DataEntity data) {
    this.data = data;
  }

  public static class DataEntity {
    /**
     * title : 个性推荐
     * recommend_list : [{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]
     */

    private RecommendEntity recommend;
    /**
     * title : 本周TOP10排行榜
     * ranking_list : [{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"},{"scheme":"Google","desc":"描述","icon_url":"http://www.google.com","name":"今日特价","price":"66"}]
     */

    private RankingEntity ranking;
    /**
     * scheme : Google
     * url : http://www.google.com
     */

    private List<BannersEntity> banners;
    /**
     * scheme : Google
     * icon_url : http://www.google.com
     * name : 今日特价
     */

    private List<ChannelEntity> channel;

    public RecommendEntity getRecommend() {
      return recommend;
    }

    public void setRecommend(RecommendEntity recommend) {
      this.recommend = recommend;
    }

    public RankingEntity getRanking() {
      return ranking;
    }

    public void setRanking(RankingEntity ranking) {
      this.ranking = ranking;
    }

    public List<BannersEntity> getBanners() {
      return banners;
    }

    public void setBanners(List<BannersEntity> banners) {
      this.banners = banners;
    }

    public List<ChannelEntity> getChannel() {
      return channel;
    }

    public void setChannel(List<ChannelEntity> channel) {
      this.channel = channel;
    }

    public static class RecommendEntity {
      private String title;
      /**
       * scheme : Google
       * icon_url : http://www.google.com
       * name : 今日特价
       * price : 66
       */

      private List<RecommendListEntity> recommend_list;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public List<RecommendListEntity> getRecommend_list() {
        return recommend_list;
      }

      public void setRecommend_list(List<RecommendListEntity> recommend_list) {
        this.recommend_list = recommend_list;
      }

      public static class RecommendListEntity {
        private String scheme;
        private String icon_url;
        private String name;
        private String price;

        public String getScheme() {
          return scheme;
        }

        public void setScheme(String scheme) {
          this.scheme = scheme;
        }

        public String getIcon_url() {
          return icon_url;
        }

        public void setIcon_url(String icon_url) {
          this.icon_url = icon_url;
        }

        public String getName() {
          return name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String getPrice() {
          return price;
        }

        public void setPrice(String price) {
          this.price = price;
        }
      }
    }

    public static class RankingEntity {
      private String title;
      /**
       * scheme : Google
       * desc : 描述
       * icon_url : http://www.google.com
       * name : 今日特价
       * price : 66
       */

      private List<RankingListEntity> ranking_list;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public List<RankingListEntity> getRanking_list() {
        return ranking_list;
      }

      public void setRanking_list(List<RankingListEntity> ranking_list) {
        this.ranking_list = ranking_list;
      }

      public static class RankingListEntity {
        private String scheme;
        private String desc;
        private String icon_url;
        private String name;
        private String price;

        public String getScheme() {
          return scheme;
        }

        public void setScheme(String scheme) {
          this.scheme = scheme;
        }

        public String getDesc() {
          return desc;
        }

        public void setDesc(String desc) {
          this.desc = desc;
        }

        public String getIcon_url() {
          return icon_url;
        }

        public void setIcon_url(String icon_url) {
          this.icon_url = icon_url;
        }

        public String getName() {
          return name;
        }

        public void setName(String name) {
          this.name = name;
        }

        public String getPrice() {
          return price;
        }

        public void setPrice(String price) {
          this.price = price;
        }
      }
    }

    public static class BannersEntity {
      private String scheme;
      private String url;
      private String name;

      public String getScheme() {
        return scheme;
      }

      public void setScheme(String scheme) {
        this.scheme = scheme;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }
    }

    public static class ChannelEntity {
      private String scheme;
      private String icon_url;
      private String name;

      public String getScheme() {
        return scheme;
      }

      public void setScheme(String scheme) {
        this.scheme = scheme;
      }

      public String getIcon_url() {
        return icon_url;
      }

      public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }
    }
  }
}