package server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String sendHello() {
        return "Hello World!";
    }

    /**
     * Returns string with salute to given name.
     * @param text with name to salute to
     * @return the salute
     */
    @RequestMapping(value = {"/{text}"}, method = RequestMethod.GET)
    public String sendMessage(@PathVariable("text") String text) {
        return "Salute " + text;
    }
}
