package com.magiccamprule.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Content {

    private Integer contentNumber;
    private String contentTitle;
    private String fullTitle;
    private List<Chapter> chapters;

}
