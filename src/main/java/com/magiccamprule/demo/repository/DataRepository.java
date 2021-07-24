package com.magiccamprule.demo.repository;

import com.magiccamprule.demo.model.Content;
import com.magiccamprule.demo.model.Glossary;
import com.magiccamprule.demo.model.Rule;
import com.magiccamprule.demo.model.dtos.ContentResponse;
import com.magiccamprule.demo.model.dtos.RuleChapterContent;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Optional;

public interface DataRepository {
    List<Rule> getRulesByOneWord(String word);

    List<Rule> getRulesByChapterNumber(Integer chapterNumber);

    List<ContentResponse> getAllContents();

    List<Glossary> getAllGlossary();

    Glossary getGlossaryByTerm(String term);

    Optional<RuleChapterContent> getSpecificRuleByRoleId(String ruleId);

    Optional<ContentResponse> getContentByNumber(Integer idNumber);

    List<Content> getAll();


}

