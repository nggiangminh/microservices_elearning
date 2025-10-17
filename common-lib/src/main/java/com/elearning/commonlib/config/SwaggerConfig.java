package com.elearning.commonlib.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSchemas("ApiResponse", createApiResponseSchema()))
            .info(new Info()
                .title("E-Learning API Documentation")
                .version("1.0")
                .description("API documentation for E-Learning microservices")
                .contact(new Contact()
                    .name("E-Learning Team")
                    .email("contact@elearning.com")));
    }

    private Schema createApiResponseSchema() {
        Schema apiResponseSchema = new Schema<ApiResponse>()
            .type("object")
            .addProperty("status", new Schema<String>()
                .type("string")
                .example("SUCCESS")
                .description("Trạng thái response (SUCCESS/ERROR)"))
            .addProperty("message", new Schema<String>()
                .type("string")
                .example("Thao tác thành công")
                .description("Thông báo kết quả"))
            .addProperty("data", new Schema<Object>()
                .type("object")
                .description("Dữ liệu trả về"))
            .addProperty("error", new Schema<Object>()
                .type("object")
                .addProperty("code", new Schema<String>().type("string"))
                .addProperty("message", new Schema<String>().type("string"))
                .description("Chi tiết lỗi (nếu có)"));
        return apiResponseSchema;
    }
}
