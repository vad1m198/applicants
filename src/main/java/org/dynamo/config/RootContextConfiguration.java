package org.dynamo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = {"org.dynamo.repository","org.dynamo.service","org.dynamo.dao"},
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
@Import({DataSourceConfig.class, SecurityConfiguration.class})
public class RootContextConfiguration {

}
