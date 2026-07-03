package in.tripva.assignment.bus.busController;


import in.tripva.assignment.bus.busDTO.BusDTO;
import in.tripva.assignment.bus.busEntity.BusEntity;
import in.tripva.assignment.bus.busService.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class Bus {

    private final BusService busService;

    public Bus(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/showbus")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<BusDTO> showBus(){

        return busService.getAllBus();
    }

    @PostMapping("/addbus")
    @PreAuthorize("hasRole('ADMIN')")
    public BusDTO addBus(@RequestBody BusEntity busEntity){

        return busService.addBus(busEntity);
    }

    @PostMapping("/deletebus")
    @PreAuthorize("hasRole('ADMIN')")
    public String removeBus(@RequestBody BusEntity bus){

        return busService.removeBus(bus);
    }

    @PatchMapping("/updatebus")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusEntity bus){

       BusDTO updatedBus = busService.updateBus(bus);

        return ResponseEntity.ok(updatedBus);

    }





}
