package com.coeusnews.model;

public class ArticleCategory {

    private String id;
    private String author_name;
    private String title;
    private String category;
    private String description;
    private String article_text;
    private String article_image_URL;
    private String article_URL;
    private String updated_at;
    private String published_at;
    private String article_status;
    private String article_location;
    private String article_approved_by;
    private String source;
    private int counter;

    public ArticleCategory() {}

    public ArticleCategory(String id, String author_name, String title, String category, String description, String article_text, String article_image_URL, String article_URL, String updated_at, String published_at, String article_status, String article_location, String article_approved_by, String source, int counter) {
        this.id = id;
        this.author_name = author_name;
        this.title = title;
        this.category = category;
        this.description = description;
        this.article_text = article_text;
        this.article_image_URL = article_image_URL;
        this.article_URL = article_URL;
        this.updated_at = updated_at;
        this.published_at = published_at;
        this.article_status = article_status;
        this.article_location = article_location;
        this.article_approved_by = article_approved_by;
        this.source = source;
        this.counter = counter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticle_text() {
        return article_text;
    }

    public void setArticle_text(String article_text) {
        this.article_text = article_text;
    }

    public String getArticle_image_URL() {
        return article_image_URL;
    }

    public void setArticle_image_URL(String article_image_URL) {
        this.article_image_URL = article_image_URL;
    }

    public String getArticle_URL() {
        return article_URL;
    }

    public void setArticle_URL(String article_URL) {
        this.article_URL = article_URL;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getArticle_status() {
        return article_status;
    }

    public void setArticle_status(String article_status) {
        this.article_status = article_status;
    }

    public String getArticle_location() {
        return article_location;
    }

    public void setArticle_location(String article_location) {
        this.article_location = article_location;
    }

    public String getArticle_approved_by() {
        return article_approved_by;
    }

    public void setArticle_approved_by(String article_approved_by) {
        this.article_approved_by = article_approved_by;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
