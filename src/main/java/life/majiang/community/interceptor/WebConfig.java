package life.majiang.community.interceptor;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author lijing
 * @date 2019-06-14-14:27
 * @discroption 拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterveptor sessionInterveptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterveptor).addPathPatterns("/**");
    }
}
