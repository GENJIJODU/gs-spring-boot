package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojos.Greeting;
import pojos.LoginForm;

import java.util.concurrent.atomic.AtomicLong;

@RestController  //@Controller + @ResponseBody
@RequestMapping("/hello") // optional. could default internal methods
public class HelloController {

    @RequestMapping()
    public String defaultMethod() {
        return "Greetings from The default HelloController";
    }

    @RequestMapping("/ping")
    public String ping() {
        return "Greetings from The HelloController";
    }

    @RequestMapping("/info")
    public String info(@RequestParam(value="type", defaultValue = "Default") String type) {
        return type + " Info: 123";
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "Dude") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value={"method1", "method1/deeper"})
    public String method1() {
        return "You have hit method1";
    }

    @PostMapping("/request")
    public ResponseEntity postController(@RequestBody LoginForm loginForm) {

        // exampleService.fakeAuthenticate(loginForm);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}