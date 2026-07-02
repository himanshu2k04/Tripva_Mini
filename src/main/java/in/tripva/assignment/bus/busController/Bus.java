package in.tripva.assignment.bus.busController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bus {

    @GetMapping("/bus")
    public String showBus(){
        return "Hello Tripva";
    }

}
