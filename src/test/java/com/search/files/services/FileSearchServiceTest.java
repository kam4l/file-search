package com.search.files.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FileSearchServiceTest {

   private static String searchPath;

    public FileSearchServiceTest(){
        Path path = Paths.get(".", "src//test//resources//FileSearchDirectory");
        searchPath = path.toAbsolutePath().toString();
    }

    @Test
    public void findFilesThatContainsGivenWords() throws Exception {
        //when
        List<String> files = new FileSearchService(searchPath).findFiles(Collections.singletonList("testFile1 main directory"));

        //then
        assertEquals(files.size(), 1);
        assertEquals(files.get(0), searchPath+"/testFile1");
    }

    @Test
    public void findFilesThatContainsGivenWordInSubDirectory() throws Exception {
        //when
        List<String> files = new FileSearchService(searchPath).findFiles(Collections.singletonList("testFile1"));

        //then
        assertEquals(files.size(), 2);
        assertEquals(files.get(0), searchPath+"/subDirectory/testFile1");
        assertEquals(files.get(1), searchPath+"/testFile1");
    }

    @Test
    public void returnsNoFilesIfGivenWordIsNotPresent() throws Exception {
        //when
        List<String> files = new FileSearchService(searchPath).findFiles(Arrays.asList("testFile1", "THIS_IS_NOT_PRESENT"));

        //then
        assertEquals(files.size(), 0);
    }

}
