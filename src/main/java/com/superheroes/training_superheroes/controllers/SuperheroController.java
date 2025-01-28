package com.superheroes.training_superheroes.controllers;
import com.superheroes.training_superheroes.Services.SuperHeroConsumer;
import com.superheroes.training_superheroes.Services.SuperheroService;
import com.superheroes.training_superheroes.config.SqsClientConfig;
import com.superheroes.training_superheroes.config.SqsConfig;
import com.superheroes.training_superheroes.model.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.List;
@RestController
@RequestMapping("/superheroes")
public class SuperheroController {
    private final SuperheroService superheroService;
    @Autowired
    private SqsConfig sqsConfig;

    @Autowired
    private SqsClient sqsClient;

    @Autowired
    private SuperHeroConsumer superheroConsumer;
    @Autowired
    public SuperheroController(SuperheroService superheroService, SqsClient sqsClient) {
        this.sqsClient = sqsClient;
        this.superheroService = superheroService;
    }
//    }
//    @Autowired
//    public SuperheroController(SuperheroService superheroService) {
//        this.superheroService = superheroService;
//    }
    @PostMapping
    public Superhero addSuperhero(@RequestBody Superhero superhero) {
        return superheroService.addSuperhero(superhero);
    }
    @PostMapping("/many")
    public List<Superhero> addManySuperheroes(@RequestBody List<Superhero> superheroes) {
        return superheroService.addManySuperheroes(superheroes);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "username",defaultValue = "World") String UserName){
        SendMessageResponse response = sqsClient.sendMessage(SendMessageRequest
                .builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .messageBody("Vinayak")
                .build());
        ReceiveMessageResponse msg = sqsClient.receiveMessage(ReceiveMessageRequest
                .builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .build());
        return String.format("Hello %s!,%s , %s",UserName,sqsConfig.getQueueName(),msg);
    }

    @PutMapping("/update_superhero_async")
    public ResponseEntity<String> updatesuperhero(@RequestBody Superhero superhero){
         sqsClient.sendMessage(SendMessageRequest
                .builder()
                .queueUrl(sqsConfig.getQueueUrl())
                .messageBody(superhero.getName())
                .build());
        return ResponseEntity.accepted().body("Request to update "+superhero.getName() + " has  been queued");
    }

    @PutMapping("/{name}")
    public Superhero updateSuperhero(@PathVariable String name, @RequestBody Superhero superhero) {
        return superheroService.updateSuperhero(name, superhero);
    }
    @DeleteMapping("/{name}")
    public void deleteSuperhero(@PathVariable String name) {
        superheroService.deleteSuperhero(name);
    }
    @GetMapping("/name/{name}")
    public Superhero getSuperheroByName(@PathVariable String name) {
        return superheroService.getSuperheroByName(name);
    }
    @GetMapping("/universe/{universe}")
    public List<Superhero> getSuperheroesByUniverse(@PathVariable String universe) {
        return superheroService.getSuperheroesByUniverse(universe);
    }
}