package com.magiccamprule.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;


@Getter
@Setter
@ToString
public class Chapter {

    private Integer chapterNumber;
    private String title;
    private String fullTitle;
    private List<Rule> rulesInList;


    public Chapter(String title, Integer chapterNumber, String fullTitle) {
        this.title = title;
        this.chapterNumber = chapterNumber;
        this.fullTitle = fullTitle;
    }


}
