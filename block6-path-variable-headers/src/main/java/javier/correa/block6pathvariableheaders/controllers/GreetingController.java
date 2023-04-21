package javier.correa.block6pathvariableheaders.controllers;

import javier.correa.block6pathvariableheaders.objectsAndRecords.Greeting;
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
    public Greeting postSendBody(@RequestBody Greeting greetingPassed){
        return greetingPassed;
    }

    @GetMapping("/user/{id}")
    public int getGetAndPassUserId(@PathVariable int id){
        return id;
    }

    @PutMapping("/post")
    public Map<String, String > putPassVariables(@RequestParam Map<String, String> paramsRequested){
        Map <String, String> nombreMap = new HashMap<String, String>();
        for (Map.Entry<String,String> entry : paramsRequested.entrySet()) {

            nombreMap.put(entry.getKey(), entry.getValue());
        }
        return nombreMap;
    }

}
