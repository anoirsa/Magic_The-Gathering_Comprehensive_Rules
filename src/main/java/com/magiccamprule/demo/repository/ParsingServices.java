package com.magiccamprule.demo.repository;

import com.magiccamprule.demo.model.Chapter;
import com.magiccamprule.demo.model.Content;
import com.magiccamprule.demo.model.Glossary;
import com.magiccamprule.demo.model.Rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// A class to parse the text file of the book rule then split it into Database of objects

public class ParsingServices {
    public static Path path = FileSystems.getDefault().getPath("game.txt");
    public static File file = new File(path.toString());


    // We get the contents of the file and split the chapters
    public static List<Content> fileContent() throws Exception {
        List<Content> contents = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String number = (i + 1) + ".";
            Integer numberOfLine = getLineNumber(number, false);
            int counter = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String strline;
            Content newContent = new Content();
            List<Chapter> chapters = new ArrayList<>();
            boolean reached = false;
            while ((strline = br.readLine()) != null) {

                counter++;
                if (counter == numberOfLine) {
                    String[] numberAndTitle = strline.split(" ", 2);
                    newContent.setFullTitle(strline);
                    newContent.setContentTitle(numberAndTitle[1]);
                    newContent.setContentNumber(i + 1);
                    reached = true;

                } else if (strline.equals("") && reached) {
                    newContent.setChapters(chapters);
                    contents.add(newContent);
                    fileInputStream.close();
                    br.close();
                    break;

                } else if (counter > numberOfLine) {
                    String[] numberAndTitle = strline.split("[.] ");
                    Chapter chapter = new Chapter(numberAndTitle[1], Integer
                            .parseInt(numberAndTitle[0]),strline);
                    chapters.add(chapter);
                }
            }
        }
        return contents ;
    }
    // We divide the rules into nine array lists
    public static List<List<Rule>> setRules(List<Content> contents) throws Exception{
        List<List<Rule>> rolesByContent = new ArrayList<>();
        for (Content i : contents) {
            List<Rule> rules = new ArrayList<>();
            String fullTitle = i.getChapters().get(0).getFullTitle();
            String endTitle = i.getChapters().get(i.getChapters().size() - 1).getFullTitle();

            Integer numberOfLineStart = getLineNumber(fullTitle, true);
            Integer numberOfLineEnd = getLineNumber(endTitle, true);
            int counter = 0;
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String previousDescription = "";
            String previousId = "";
            String strline;
            while ((strline = br.readLine()) != null) {
                counter++;
                if ((counter >= (numberOfLineStart + 2)) && (counter <= (numberOfLineEnd + 2))) {
                    String objectedString = new String(strline);
                    boolean conditionOne = i.getChapters().stream()
                            .anyMatch(e -> e.getFullTitle().equals(objectedString));
                    boolean conditionTwo = i.getChapters().stream()
                            .anyMatch(e -> objectedString.startsWith(Integer.toString(e.getChapterNumber())));
                    if (!strline.equals("") && !conditionTwo) {
                        previousDescription = previousDescription +"\n" + strline;
                    }
                    else if (!strline.equals("") && !conditionOne) {
                        String[] numberAndTitle = strline.split(" ", 2);
                        previousDescription = numberAndTitle[1];
                        previousId = removeLastDot(numberAndTitle[0]);
                    }
                    else if (strline.equals("")) {
                        Rule newRule = new Rule(previousId, previousDescription);
                        rules.add(newRule);
                        previousDescription = "";
                        previousId = "";
                    }
                }
            }
            rolesByContent.add(rules);
            fileInputStream.close();
            br.close();
        }

        return rolesByContent;
    }

    public static List<Content> setRulesToContent(List<List<Rule>> rulesByContent, List<Content> contents) {
        rulesByContent.stream().forEach(ruleList -> {
            int index = rulesByContent.indexOf(ruleList);
            contents.get(index).getChapters().stream().forEach(chapter -> {
                List<Rule> collect = ruleList.stream().filter(rule -> rule.getRoleId()
                        .startsWith(Integer.toString(chapter.getChapterNumber())))
                        .collect(Collectors.toList());
                chapter.setRulesInList(collect);
            });
        });
        return contents;
    }
    public static List<Glossary> parseGlossary() throws Exception {
        List<Glossary> arrayToBeReturned = new ArrayList<>();
        int counter = 0;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
        int firstLineNumber = getLineNumber("Glossary", true);
        String strline = "";
        String term ="";
        String description ="";
        boolean thisWord = true;
        while ((strline = br.readLine()) != null) {
            counter++;
            if(counter >= (firstLineNumber +2)) {
                if (strline.equals("Credits")) {
                    fileInputStream.close();
                    br.close();
                    break;
                }
                else if (!strline.equals("") && thisWord) {
                    term = strline;
                    thisWord = false;
                }
                else if (strline.equals("")) {
                    Glossary glossary = new Glossary(term , description);
                    description = "";
                    arrayToBeReturned.add(glossary);
                    thisWord = true;
                }
                else if (!strline.equals("") && !thisWord) {
                    description += strline + "\n";
                }
            }
        }
        return arrayToBeReturned;
    }



    public static Integer getLineNumber(String word,boolean rules) throws Exception{
        Scanner input = new Scanner(file);
        int versionSecond = 0;
        int counter = 0;
        while (input.hasNextLine()) {
            counter++;
            String x = input.nextLine();
            if (x.startsWith(word)) {
                if (rules) {
                    if (versionSecond == 1 ) break;
                    else versionSecond++;
                }
                else break;
            }
        }

        return counter;
    }

    public static String removeLastDot(String idGiven) {
        String returnedString;
        if (idGiven.endsWith(".")) {
            returnedString = idGiven.substring(0, idGiven.length() - 1);
            return returnedString;
        }

        else return idGiven;
    }

}
