package com.search.files;

import com.search.files.resources.FileSearchResource;
import com.search.files.services.FileSearchService;
import exception.FileSearchExceptionMapper;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;


public class FileSearchApplication extends Application<FileSearchConfiguration> {
    public static void main(String[] args) throws Exception {
        new FileSearchApplication().run(args);
    }

    @Override
    public String getName(){
        return "search-files";
    }

    @Override
    public void initialize(Bootstrap<FileSearchConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<FileSearchConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(FileSearchConfiguration configuration) {
                return configuration.getSwaggerBundleConfig();
            }
        });
    }

    @Override
    public void run(FileSearchConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final FileSearchService fileSearchService = new FileSearchService(configuration.getSearchPath());

        environment.jersey().register(new FileSearchResource(fileSearchService));
        environment.jersey().register(new FileSearchExceptionMapper());
    }
}
