package com.beauti.crm.config;

import com.beauti.crm.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;


/**
 * @author jingpb
 * @version 1.0
 * @date 2021/1/4 10:50
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 注入InternalResourceViewResolver类：
     * 说明：springmvc下有一个接口叫ViewResolver，(我们的viewResolver都实现该接口)，实现这个接口要重写
     * resolverName(),这个方法的返回值接口View，而view的职责就是使用model、request、response对象，并
     * 渲染视图(不一定是html、可能是json、xml、pdf等)给浏览器 。
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //viewResolver.setPrefix("/WEB-INF/classes/views/");//打war后默认编译的路径
        /*viewResolver.setPrefix("/WEB-INF/views/");//使用tomcat7:run插件后要放的位置
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);*/
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
//        viewResolver.setViewClass(JstlView.class);
        return viewResolver;

    }

    /**
     * 设置域名端口号默认跳转页面（static下login.html）
     * 效果类似方法
     *
     * @GetMapping("/") public String login(){
     * return "login";
     * }
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认地址（可以是页面或后台请求接口）
        registry.addViewController("/").setViewName("login.html");
        //设置过滤优先级最高
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        }
    //静态资源映射 等同于配置文件配置
    /**
     * # 默认值为 /**
     * spring.mvc.static-path-pattern=
     * # 默认值为 classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
     * spring.resources.static-locations=这里设置要指向的路径，多个使用英文逗号隔开，
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }// 3

    //拦截器
    /**
     * 拦截器踩坑
     * 使用spring 5.x时(springboot2.x)，静态资源也会执行自定义的拦截器，因此在配置拦截器的时候需要指定排除静态资源的访问路径
     * */
    @Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 2
        //
        registry.addInterceptor(new DemoInterceptor()).excludePathPatterns(Arrays.asList("/static/**"));
        registry.addInterceptor(demoInterceptor());
    }
}