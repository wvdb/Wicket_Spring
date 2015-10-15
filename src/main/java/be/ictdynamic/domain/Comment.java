package be.ictdynamic.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Comment.
 *
 * @author Wim Van den Brande
 * @since 03/10/2015 - 20:35
 */
public class Comment implements Serializable {
    private String text;
    private Date date = new Date();
    private String author;

    public Comment() {
    }

    public Comment(String text, Date date, String author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                '}';
    }
}
