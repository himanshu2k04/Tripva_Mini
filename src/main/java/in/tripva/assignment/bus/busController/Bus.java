package in.tripva.assignment.bus.busController;


import in.tripva.assignment.bus.busDTO.busDTO;
import in.tripva.assignment.bus.busEntity.busEntity;
import in.tripva.assignment.bus.busService.busService;
import in.tripva.assignment.user.userEntity.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class Bus {

    private final busService busService;

    public Bus(busService busService) {
        this.busService = busService;
    }

    @GetMapping("/showbus")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<busDTO> showBus(){

        return busService.getAllBus();
    }

    @PostMapping("/addbus")
    @PreAuthorize("hasRole('ADMIN')")
    public busDTO addBus(@RequestBody busEntity busEntity){

        return busService.addBus(busEntity);
    }

    @PostMapping("/deletebus")
    @PreAuthorize("hasRole('ADMIN')")
    public String removeBus(@RequestBody busEntity bus){

        return busService.removeBus(bus);
    }

    @PatchMapping("/updatebus")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<busDTO> updateBus(@RequestBody busEntity bus){

       busDTO updatedBus = busService.updateBus(bus);

        return ResponseEntity.ok(updatedBus);

    }





}
