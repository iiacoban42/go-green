package Server.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String sendHello() {
        return "Hello World!";
    }

    @RequestMapping(value = {"/{text}"}, method = RequestMethod.GET)
    public String sendMessage(@PathVariable("text") String text) {

        String msg = "Salut " + text;
        return msg;
    }


}
