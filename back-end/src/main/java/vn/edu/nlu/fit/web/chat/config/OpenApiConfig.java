package vn.edu.nlu.fit.web.chat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${open-api.service.title}") String title,
            @Value("${open-api.service.version}") String version,
            @Value("${open-api.service.server}") String serverUrl) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl)))
                .info(new Info().title(title)
                        .description("API documents")
                        .version(version)
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi publicApi(@Value("${open-api.service.api-docs}") String apiDocs) {
        return GroupedOpenApi.builder()
                .displayName("API Documents")
                .group(apiDocs) // /v3/api-docs/api-service
                .packagesToScan("vn.edu.nlu.fit.web.chat.controller")
                .build();
    }
}
