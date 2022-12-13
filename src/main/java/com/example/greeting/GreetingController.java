package com.example.greeting;

import com.example.greeting.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.parseInt;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class GreetingController {
    //dependency injection
    //avoids us needing to make a new instance

    @Autowired
    GreetingRepository repository;
    private ArrayList<Greeting> greetings = new ArrayList<>();

    private Random r = new Random();

    @GetMapping("/greeting/{id}")
    public ResponseEntity<String> getGreetingById(@PathVariable int id) {
//        public ResponseEntity<Optional<Greeting>> getGreetingById(@PathVariable String id){
        //    public Greeting getGreetingById(@PathVariable int id) {
        //        return repository.findById(id);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findByid(id).toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id: " + id + " Doesn't exist");
        }
//        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
        //                greetings.stream()
        //                .filter(greeting -> greeting.getId() == parseInt(id))
        //                .findFirst()
        //                .orElse(null);
    }

    //findAll()
    @GetMapping("/greetings")
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    //random
    @GetMapping("/random")
    public ResponseEntity<Greeting> getRandomGreeting() {
        // example
        // could be shortened

//        int index = 1+r.nextInt((int) (repository.count()));
//        return repository.findByid(index);

        // refactor to get random greeting from database, not greetings array
        // .count to find number of entries in repository
        // .findById with repository.count() as argument
        //----Shenna's suggestion
        // int index = 1 + r.nextInt((int) (repository.count()));
        // return repository.findByid(index);
        //-----Second option using getAllGreetings
        // .findAll (already written ^^) to get all of the existing greetings

        List<Greeting> allGreetings = repository.findAll();
        Greeting randomGreeting = allGreetings.get(r.nextInt(allGreetings.size()));
        return ResponseEntity.status(HttpStatus.OK).body(randomGreeting);
    }

    @PostMapping("/greetings")
    public ResponseEntity<String> createGreeting(@RequestBody Greeting greeting) {
        // set the greetings id based on the greetings list
        // set the created by
//        greeting.setId(greetings.size() + 1);
//        greeting.setCreatedBy("Ollie");
//        greeting.setDateCreated(new Timestamp(System.currentTimeMillis()));
//        greetings.add(greeting);
        repository.save(greeting);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(greeting.toString() + " added");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }

    }

    // UPDATE route
    @PutMapping("/greetings/{id}")
    public String updateFullGreeting(@PathVariable int id, @RequestBody Greeting newGreeting) {
        Greeting updatedGreeting = repository.findByid(id);
        if (newGreeting.getGreeting() != null) {
            updatedGreeting.setGreeting(newGreeting.getGreeting());
        }
        if (newGreeting.getCreatedBy() != null) {
            updatedGreeting.setCreatedBy(newGreeting.getCreatedBy());
        }
        if (newGreeting.getOriginCountry() != null) {
            updatedGreeting.setOriginCountry(newGreeting.getOriginCountry());
        }
        repository.save(updatedGreeting);
        return "Greeting with id: " + id + "changed to" + updatedGreeting;
    }
//        greetings.remove(greetings.get(id));
    // DELETE route
    @Transactional
    @DeleteMapping("/greetings/{id}")
    public String deleteGreeting(@PathVariable int id) {
        repository.delete(repository.findByid(id));
        return "Greeting with id: " + id + " deleted.";
    }
}
