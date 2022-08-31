package oauth2.demo.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@SpringBootApplication
public class Oauth2DemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoClientApplication.class, args);
    }
}

@RestController
@RequestMapping("/greeting-flow")
@RequiredArgsConstructor
class GreetingFlowController {
    private final WebClient webClient;

    @GetMapping
    public Mono<String> callGreeting(@RegisteredOAuth2AuthorizedClient("greeting-client") OAuth2AuthorizedClient authorizedClient,
                                     OAuth2AuthenticationToken oauth2Authentication) {
        return this.webClient
                .get()
                .uri(URI.create("http://localhost:8090/greeting-service/greeting"))
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class);
    }
}
