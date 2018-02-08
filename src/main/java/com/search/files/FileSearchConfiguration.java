package com.search.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSearchConfiguration extends Configuration {
    @NotEmpty
    private String searchPath;

    @Valid
    @NotNull
    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfig;

    public String getSearchPath() {
        Path path = Paths.get(".");
        return path.toAbsolutePath().toString()+searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }


    public SwaggerBundleConfiguration getSwaggerBundleConfig() {
        return swaggerBundleConfig;
    }
}
