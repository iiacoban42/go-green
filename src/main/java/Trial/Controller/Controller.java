package Trial.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    @RequestMapping( value ={"/{text}"} , method = RequestMethod.GET)
    public String sendMessage(@PathVariable("text")  String text){

        String msg = "Salut " + text;
        return msg;
    }


    }
