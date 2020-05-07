package com.ws.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Integer commentId;

    private String comments;

    private Float rate;

    private String movieId;

    private Integer userId;

    private Date date;

    private Date createTime;

    private Date updateTime;

    private Movie movie;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId == null ? null : movieId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comments='" + comments + '\'' +
                ", rate=" + rate +
                ", movieId='" + movieId + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", movie=" + movie +
                '}';
    }
}