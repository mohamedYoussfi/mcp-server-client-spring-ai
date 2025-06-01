package net.youssfi.mcpclient.controllers;

import net.youssfi.mcpclient.agents.MyAIAgent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AiWebAPIController {
    private MyAIAgent agent;

    public AiWebAPIController(MyAIAgent agent) {
        this.agent = agent;
    }
    @GetMapping("/chat")
    public String askAgent(String query) {
        return agent.prompt(query);
    }
}
