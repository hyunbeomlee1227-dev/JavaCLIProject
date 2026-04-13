package com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private DateTimeFormatter forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");

    public WiseSaying(String author, String content) {
        this.content = content;
        this.author = author;
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    public LocalDateTime getModifyDate() {
        return this.modifyDate;
    }

    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    public void setModifyDate() {
        this.modifyDate = LocalDateTime.now();
    }

    public boolean isNew() {
        return getId() == 0;
    }

    public String getForPrintCreateDate() {
        return createDate.format(forPrintDateTimeFormatter);
    }

    public String getForPrintModifyDate() {
        return modifyDate.format(forPrintDateTimeFormatter);
    }
}
