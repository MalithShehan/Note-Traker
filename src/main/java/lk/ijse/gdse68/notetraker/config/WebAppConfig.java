package lk.ijse.gdse68.notetraker.config;


import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse68.notetraker")
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "lk.ijse.gdse68.notetraker")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, // 10MBx
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class WebAppConfig {
}

