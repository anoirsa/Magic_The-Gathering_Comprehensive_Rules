package com.magiccamprule.demo.controller;


import com.magiccamprule.demo.model.Content;
import com.magiccamprule.demo.model.Glossary;
import com.magiccamprule.demo.model.Rule;
import com.magiccamprule.demo.model.dtos.ContentResponse;
import com.magiccamprule.demo.model.dtos.RuleChapterContent;
import com.magiccamprule.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.text.RuleBasedCollator;
import java.util.List;

@RestController

@RequestMapping("/api/v1/content")
@AllArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class FileController {
    private final FileService fileService;

    @GetMapping("/rules/search_rule/{word}")
    public @ResponseBody List<Rule> getRulesByOneWord(@PathVariable("word") String word){
        return fileService.getRulesByOneWord(word);
    }

    @GetMapping("all/inone")
    public List<Content> getEverything() {
        return fileService.getEverything();
    }
    @GetMapping("/rules/chapter_rules/{chapterNumber}")
    public @ResponseBody List<Rule> getRulesByChapterNumber(@PathVariable("chapterNumber") Integer chapterNumber) {
        return fileService.getRulesByChapterNumber(chapterNumber);
    }

    @GetMapping("/rules/get_rule/{ruleId}")
    public RuleChapterContent getSpecificRuleByRoleId(@PathVariable("ruleId") String ruleId) {
        return fileService.getSpecificRuleByRoleId(ruleId);
    }


    @GetMapping("/all")
    public List<ContentResponse> getAllContents() {
        return fileService.getAllContentResponses();
    }

    @GetMapping("/glossary/all_glossary")
    public List<Glossary> getAllGlossary() {
        return fileService.getAllGlossary();
    }

    @GetMapping("/glossary/find_glossary/{term}")
    public Glossary getGlossaryByTerm(@PathVariable("term")String term){
        return fileService.getGlossaryByTerm(term);
    }
    @GetMapping("/specific/{idNumber}")
    public ContentResponse getContentByNumber(@PathVariable("idNumber") Integer idNumber) {
        return fileService.getContentByNumber(idNumber);
    }


}
