package com.search.files.services;

import exception.FileSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
                    .filter(foundPath -> {
                        try {
                            if (foundPath.toFile().isFile())
                                return words.parallelStream().allMatch(new String(Files.readAllBytes(foundPath), "UTF-8")::contains);

                        } catch (IOException e) {
                            LOGGER.error("Unexpected Exception occurred: ", e);
                            throw new FileSearchException(e);
                        }
                        return false;
                    })
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Unexcepted Exception occurred:", e);
            throw new FileSearchException(e);
        }


    }

}
