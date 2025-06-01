package net.youssfi.ssemcpserver;

import net.youssfi.ssemcpserver.tools.StockExchangeService;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SseMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseMcpServerApplication.class, args);
    }
    @Bean
    public MethodToolCallbackProvider getMethodToolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(new StockExchangeService())
                .build();
    }

}
