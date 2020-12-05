package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var env = new Facts();
        env.setFact("name", "Bob");
        env.setFact("jobTitle", "CEO");

        final var businessRuleEngine = new BusinessRuleEngine(env);

        final Rule ruleSendEmailToSalesWhenCEO =
                RuleBuilder
                        .when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                        .then(facts -> {
                            var name = facts.getFact("name");
                            System.out.println("Relevant customer!!!: " + name);
                        });

        businessRuleEngine.addRule(ruleSendEmailToSalesWhenCEO);
        businessRuleEngine.run();

    }
}