package com.magiccamprule.demo.repository;


import com.magiccamprule.demo.model.Chapter;
import com.magiccamprule.demo.model.Content;
import com.magiccamprule.demo.model.Glossary;
import com.magiccamprule.demo.model.Rule;
import com.magiccamprule.demo.model.dtos.ContentResponse;
import com.magiccamprule.demo.model.dtos.RuleChapterContent;
import com.magiccamprule.demo.service.ProjectMappingServices;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

// In case we need to switch implementation
@Repository("fakeRepository")
// The Database I'm creating for this application is just an arrayList, In real applications I usually use PostgreSql or Sql Litw
public class FakeRepository implements DataRepository{
    public static List<Content> DB_Content;
    public static List<Glossary> DB_Glossary;
    public FakeRepository() {
        try {
            List<Content> contents = ParsingServices.fileContent();
            List<List<Rule>> rolesByContent = ParsingServices.setRules(contents);
            DB_Content = ParsingServices.setRulesToContent(rolesByContent, contents);
            DB_Glossary = ParsingServices.parseGlossary();


        } catch (Exception c){
            System.out.println("Reading file failed");
        }
    }
    /////A method to extract all the rules that contains a specific
      public List<Rule> getRulesByOneWord(String word) {
        List<Rule> arrayTobeReturned = new ArrayList<>();
        DB_Content.stream().forEach(content -> {
            content.getChapters().stream().forEach(c -> {
                List<Rule> listToBeAdded = c.getRulesInList().stream().filter(r ->
                        r.getRoleText().toUpperCase().contains(word.toUpperCase()))
                        .collect(Collectors.toList());
                arrayTobeReturned.addAll(listToBeAdded);
            });
        });
        return arrayTobeReturned;
    }
////////
    public List<Rule> getRulesByChapterNumber(Integer chapterNumber) {

        for(Content content : DB_Content) {
             for (Chapter chapter : content.getChapters()) {
                 if (chapter.getChapterNumber().equals(chapterNumber)) return chapter.getRulesInList();
             }
        }
        return null;
    }
/////////
    public List<ContentResponse> getAllContents() {
        return ProjectMappingServices.toContentResponseList(DB_Content);
    }
////////
    public List<Glossary> getAllGlossary() {
        return DB_Glossary;
    }

    public Glossary getGlossaryByTerm(String term){
        return DB_Glossary.stream().filter(g -> g.getTerm().equalsIgnoreCase(term))
                .findFirst().get();
    }

    @Override
    public Optional<RuleChapterContent> getSpecificRuleByRoleId(String ruleId) {
        AtomicReference<RuleChapterContent> roleChapterContent = new AtomicReference<>();
        for (Content content : DB_Content) {
            for (Chapter chapter : content.getChapters()) {
              chapter.getRulesInList().stream()
                        .filter(rule -> rule.getRoleId().equals(ruleId))
                        .findFirst()
                        .ifPresent(rule -> {
                            roleChapterContent.set(new RuleChapterContent(content.getFullTitle(), chapter.getFullTitle(),rule));
                        });
            }
        }
        return Optional.of(roleChapterContent.get());
    }

    @Override
    public Optional<ContentResponse> getContentByNumber(Integer idNumber) {
        Content content = DB_Content.stream().filter(c -> c.getContentNumber().equals(idNumber))
                .findFirst().get();
        return Optional.of(ProjectMappingServices.toContentResponse(content));
    }

    @Override
    public List<Content> getAll() {
        return DB_Content;
    }

}

