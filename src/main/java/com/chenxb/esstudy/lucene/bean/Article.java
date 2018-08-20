package com.chenxb.esstudy.lucene.bean;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String author;

    public Article(Integer id, String title, String content, String author) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }


    @Override
    public String toString() {
        return "Artical [id=" + id + ", title=" + title + ", content="
                + content + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
