package com.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearcher {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java WordSearcher <keyword> <file_name> [part] [ci]");
            return;
        }

        String keyword = args[0];
        String fileName = args[1];
        boolean searchPart = false;
        boolean caseInsensitive = false;

        if (args.length > 2) {
            for (int i = 2; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("part")) {
                    searchPart = true;
                } else if (args[i].equalsIgnoreCase("ci")) {
                    caseInsensitive = true;
                }
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 0;
            boolean keywordFound = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String lineToSearch = line;
                String keywordToSearch = keyword;

                if (caseInsensitive) {
                    lineToSearch = line.toLowerCase();
                    keywordToSearch = keyword.toLowerCase();
                }

                int index = searchPart ? lineToSearch.indexOf(keywordToSearch) : lineToSearch.indexOf(" " + keywordToSearch + " ");

                if (index != -1) {
                    keywordFound = true;
                    System.out.println(lineNumber + " " + line);
                    String caret = "-".repeat((lineNumber+"").length()+index+1) + "^".repeat(keyword.length());
                    System.out.println(caret);
                }
            }

            br.close();

            if (!keywordFound) {
                System.out.println("Keyword not found in the file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}



