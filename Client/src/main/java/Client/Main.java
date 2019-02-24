package Client;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import org.slf4j.Logger;


@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {

        SpringApplication.run(Main.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder RTBuilder) throws Exception{
        return RTBuilder.build();
    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate)throws Exception{

        System.out.println("Introduce yourself to the server.. ");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();

        String  text = new String();


        while(!msg.equalsIgnoreCase("stop")){

            String resourceURL = "http://localhost:8080/" + msg;

            text = restTemplate.getForObject(resourceURL , String.class);
            System.out.println(text);

            System.out.println("Say something to the server...");
            msg = sc.nextLine();

        }

        String lastMessage = "It didnt crashed , it just stopped ";
        return args -> logger.info(lastMessage);



    }


}
