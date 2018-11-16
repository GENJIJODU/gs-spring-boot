package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dynamic")
public class PathVariableController {

    @RequestMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Greetings from The PathVariableController";
    }

    String template1 = "Called method 1 with id=%s";
    @RequestMapping(value = "/method1/{id}")
    @ResponseBody
    public String method1(@PathVariable("id") String id) {
        return String.format(template1, id);
    }

    String template2 = "Called method 2 with id %d and name %s";
    @RequestMapping(value = "/method2/{id:[\\d]}/{name}")
    @ResponseBody
    public String method2(@PathVariable("id") int id,
                          @PathVariable("name") String name) {
        return String.format(template2, id, name);
    }
}
