package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojos.Greeting;
import pojos.LoginForm;

@Controller
@RequestMapping("/home")
public class HomeController {

    //fallback method
    @RequestMapping("*")
    @ResponseBody
    public String fallBackMethod() {
        return "WRONG";
    }

    // Simple GETs
    @RequestMapping(value="method1")
    @ResponseBody //can be declared at class level and would be inherited by all methods
    public String method1(@RequestParam(name="input", defaultValue = "input") String input) {
        return "method 1: " + input;
    }

    private static final String loginFormTemplate = "Authorizing user %s with password %s";
    @PostMapping("/request")
    public String postController(@RequestBody LoginForm loginForm) {

        // exampleService.fakeAuthenticate(loginForm);
        return String.format(loginFormTemplate, loginForm.getUserName(), loginForm.getPassword());
    }

    // POST and GET Methods
    @RequestMapping(value="/post", method=RequestMethod.POST)
    @ResponseBody
    public String post(){
        return "You hit the POST method";
    }

    @RequestMapping(value="/postOrGet", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String postOrGet(){
        return "Can be hit by either";
    }

    // Request Headers
    @RequestMapping(value="/method4", headers="name=kerim") //wrong or missing headers return 404
    @ResponseBody
    public String method4(){
        return "method4";
    }

    @RequestMapping(value="/method5", headers={"name=kerim", "id=1"})
    @ResponseBody
    public String method5(){
        return "method5";
    }

    // Produces & Consumes
    @RequestMapping(value = "/method6", produces =
            {"application/json","application/XML"}, consumes = "text/html") //header Content-Type
    @ResponseBody
    public Greeting method6() {
        return new Greeting(12, "Hello");
    }
}
