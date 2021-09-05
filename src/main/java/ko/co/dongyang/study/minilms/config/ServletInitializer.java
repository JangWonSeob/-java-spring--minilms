package ko.co.dongyang.study.minilms.config;

import ko.co.dongyang.study.minilms.MinilmsApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MinilmsApplication.class);
    }

}
