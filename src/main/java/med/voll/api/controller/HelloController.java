package med.voll.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") //Aqui é onde escreveremos no localhost para abrir a pagina
public class HelloController {

    @GetMapping
    public String olamundo(){
            return "hello world!";
    }

}
