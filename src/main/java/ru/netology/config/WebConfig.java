package ru.netology.config;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;
import ru.netology.controller.PostController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig{
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        final var bean = new RequestMappingHandlerAdapter();
        bean.getMessageConverters().add(new GsonHttpMessageConverter());
        return bean;
    }
}

//@Configuration
//@EnableWebMvc
//public class WebConfig {
//    @Bean
//    public PostRepository postRepository(){
//        return new PostRepository();
//    }
//
//    @Bean
//    public PostService postService(PostRepository repository){
//        return new PostService(repository);
//    }
//
//    @Bean
//    public PostController postController(PostService service){
//        return new PostController(service);
//    }
//}
