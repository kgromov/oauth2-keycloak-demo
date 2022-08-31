package oauth2.resource.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Oauth2ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceServerApplication.class, args);
    }

}

@RestController
@RequestMapping("/greeting-service")
class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@CurrentSecurityContext(expression = "authentication.name") String name) {
        return "Hello, " + name;
    }
}
