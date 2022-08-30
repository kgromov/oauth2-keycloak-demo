package oauth2.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableWebSecurity
@SpringBootApplication
public class Oauth2DemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoClientApplication.class, args);
    }
}

@RestController
@RequestMapping
class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@CurrentSecurityContext(expression = "authentication.name") String name) {
        return "Hello, " + name;
    }
}
