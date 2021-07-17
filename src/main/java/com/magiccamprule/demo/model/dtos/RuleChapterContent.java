package com.magiccamprule.demo.model.dtos;

import com.magiccamprule.demo.model.Rule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class RuleChapterContent {


    private String contentFullTitle;
    private String chapterFullTitle;
    private Rule rule;
}
