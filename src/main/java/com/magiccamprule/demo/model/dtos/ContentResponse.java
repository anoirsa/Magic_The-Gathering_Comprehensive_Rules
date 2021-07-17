package com.magiccamprule.demo.model.dtos;

import com.magiccamprule.demo.model.Chapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@ToString
@Getter
@Setter
@AllArgsConstructor
public class ContentResponse {

    private Integer contentNumber;
    private String contentTitle;
    private List<String> chapterTitles;
}
