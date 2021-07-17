package com.magiccamprule.demo.service;

import com.magiccamprule.demo.model.Chapter;
import com.magiccamprule.demo.model.Content;
import com.magiccamprule.demo.model.dtos.ChapterResponse;
import com.magiccamprule.demo.model.dtos.ContentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// All the mapping services exist in this class

public class ProjectMappingServices {

    public static ContentResponse toContentResponse(Content content) {
        List<String> arrayTobeInseted = content.getChapters()
                .stream().map(chapter -> chapter.getFullTitle())
                .collect(Collectors.toList());
        return new ContentResponse(content.getContentNumber(),content.getContentTitle()
                ,arrayTobeInseted);
    }

    public static List<ContentResponse> toContentResponseList(List<Content> contents) {
        return contents.stream().map(content -> toContentResponse(content))
                .collect(Collectors.toList());
    }

    public static ChapterResponse toChapterResponse(Chapter chapter) {
        return new ChapterResponse(chapter.getChapterNumber(), chapter.getTitle(),
                chapter.getRulesInList().size());
    }
    public static List<ChapterResponse> toChapterResponseList(List<Chapter> chapters) {
        return chapters.stream().map(chapter -> toChapterResponse(chapter))
                .collect(Collectors.toList());
    }
}
