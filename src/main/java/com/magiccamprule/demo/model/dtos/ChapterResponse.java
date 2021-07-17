package com.magiccamprule.demo.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChapterResponse {

    private Integer chapterNumber;
    private String title;
    private Integer ruleSize;
}
