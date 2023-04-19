package javier.correa.block6pathvariableheaders.controllers;

import javier.correa.block6pathvariableheaders.objectsAndRecords.Greeting;
import javier.correa.block6pathvariableheaders.objectsAndRecords.Person;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/sendbody")
    public Person postSendBody(@RequestBody Person personPassed){
        return personPassed;
    }

    @GetMapping("/user/{id}")
    public int getGetAndPassUserId(@PathVariable int id){
        return id;
    }

    /*@PutMapping("/post")
    public Map<String, Integer> putPassVariables(@RequestParam Map<String, String> paramsRequested){
        Map <String, Integer> nombreMap = new HashMap<String, Integer>();
        for (Map.Entry<String,String> entry : paramsRequested.entrySet()) {
            nombreMap.put(entry.getKey(), Integer.valueOf(entry.getValue()));
        }
        return nombreMap;
    }*/

}
