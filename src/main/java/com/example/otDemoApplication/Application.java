package com.example.otDemoApplication;

import com.example.otDemoApplication.client.ForecastClient;
import feign.Feign;
import feign.Logger;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;


@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean("client")
    ForecastClient weatherForecastBean() {
        JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder()
                .withMarshallerJAXBEncoding("UTF-8")
                .build();
        JAXBDecoder jaxbDecoder = new JAXBDecoder.Builder()
                .withJAXBContextFactory(jaxbFactory)
                .withNamespaceAware(false) // true by default
                .build();

        ForecastClient client = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JAXBEncoder(jaxbFactory))
                .decoder(jaxbDecoder)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(ForecastClient.class, "http://www.ilmateenistus.ee/");
        return client;
    }

}
