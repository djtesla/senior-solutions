package employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private HelloService helloService;



/*    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }*/

    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String sayHello() {
        return helloService.sayHello();
    }
}
