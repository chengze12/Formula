package hello.com.chengze.worker.Config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.chengze",
        excludeFilters= @ComponentScan.Filter(type = FilterType.REGEX,pattern="com.chengze.api.*"))
public class DongnaoConfig {
}
