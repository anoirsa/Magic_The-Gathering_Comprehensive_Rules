package com.magiccamprule.demo.service;


import com.magiccamprule.demo.model.Glossary;
import com.magiccamprule.demo.model.Rule;
import com.magiccamprule.demo.model.dtos.ContentResponse;
import com.magiccamprule.demo.model.dtos.RuleChapterContent;
import com.magiccamprule.demo.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class FileService {
    private final DataRepository dataRepository;

    @Autowired
    public FileService(@Qualifier("fakeRepository") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Rule> getRulesByOneWord(String word) {
        return dataRepository.getRulesByOneWord(word);
    }

    public List<Rule> getRulesByChapterNumber(Integer chapterNumber) {
        return dataRepository.getRulesByChapterNumber(chapterNumber);
    }

    public List<ContentResponse> getAllContentResponses() {
        return dataRepository.getAllContents();
    }

    public List<Glossary> getAllGlossary() {
        return dataRepository.getAllGlossary();
    }

    public Glossary getGlossaryByTerm(String word) {
        return dataRepository.getGlossaryByTerm(word);
    }

    public RuleChapterContent getSpecificRuleByRoleId(String ruleId) {
        if (ruleId.endsWith(".")) ruleId = ruleId.substring(0 , ruleId.length() - 1);
        return dataRepository.getSpecificRuleByRoleId(ruleId).orElse(null);
    }



}
