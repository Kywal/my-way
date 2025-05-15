package br.ufrn.myway.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AiController {

    private static final Logger log = LoggerFactory.getLogger(AiController.class);
    private final ChatClient chatClient;

    @Autowired
    public AiController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> chat() {
        String text = "Ping";
        String answer = chatClient.prompt(text).call().content();

        log.info("Gemini: {}", answer);

        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }
}
