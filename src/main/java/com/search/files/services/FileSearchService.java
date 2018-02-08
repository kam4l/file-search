package com.search.files.services;

import exception.FileSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileSearchService.class);
    private String searchPath;

    public FileSearchService(String searchPath) {

        this.searchPath = searchPath;
    }

    public List<String> findFiles(List<String> words) {
        try {
            return Files.walk(Paths.get(searchPath))
                    .filter(foundPath -> foundPath.toFile().isFile() && words.parallelStream().allMatch(word -> {
                        try {
                            return containsWholeWord(new String(Files.readAllBytes(foundPath), "UTF-8"), word);
                        } catch (IOException e) {
                            LOGGER.error("Unexpected Exception occurred: ", e);
                            throw new FileSearchException(e);
                        }
                    }))
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Unexcepted Exception occurred:", e);
            throw new FileSearchException(e);
        }
    }

    private boolean containsWholeWord(String file, String word) {
        String pattern = "\\b" + word + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(file);
        return m.find();
    }
}
