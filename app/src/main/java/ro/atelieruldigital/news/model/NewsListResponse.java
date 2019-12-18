package ro.atelieruldigital.news.model;

import java.util.List;

public class NewsListResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return "NewsListResponse{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
