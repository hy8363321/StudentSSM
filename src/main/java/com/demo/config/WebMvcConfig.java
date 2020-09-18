package com.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WebMvcConfig implements WebMvcConfigurer {

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")    // 允许跨域访问的路径
        .allowedOrigins("*")    // 允许跨域访问的源
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许请求方法
        .maxAge(168000)    // 预检间隔时间
        .allowedHeaders("*")  // 允许头部设置
        .allowCredentials(true);    // 是否发送cookie
    }*/

    /**
     * https://segmentfault.com/a/1190000015975405
     * 分析
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {


        System.out.println("-----------开始执行--------------");
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        System.out.println("------------执行完毕--------------");
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /*fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty);*/
        //WriteNullListAsEmpty List字段如果为null,输出为[],而非null
        //WriteMapNullValue : 是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                SerializerFeature.SortField,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //处理乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //4、将convert添加到converters中
        //converters.add(fastConverter);
        converters.add(0,fastConverter);
        System.out.println("-------------------fastjson配置成功--------------------");
        //5、追加默认转换器
        //super.addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //打印json格式
        //com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter@5e0ec41f
        for (HttpMessageConverter<?> messageConverter : converters) {
            System.out.println(messageConverter);
        }
    }
}