package com.search.files.resources;

import com.search.files.services.FileSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;


@Path("/search-files")
@Api(basePath = "/search-files",
     value = "Find the list of files which contain all the search words",
     description = "Find the list of files which contain all the search words")
public class FileSearchResource {
    private FileSearchService fileSearchService;

    public FileSearchResource(final FileSearchService fileSearchService) {
        this.fileSearchService = fileSearchService;
    }

    @ApiOperation( value = "Find and return list of files that contains all the specified search words")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation Successful"),
            @ApiResponse(code = 400, message = "No search words specified"),
            @ApiResponse(code = 500, message = "Processing Error")
    })
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFiles(@QueryParam("word") List<String> wordList) {
        if (wordList.isEmpty()) {
            return Response.status(BAD_REQUEST).build();
        }
        return Response.ok()
                .entity(fileSearchService.findFiles(wordList))
                .build();
    }

}
