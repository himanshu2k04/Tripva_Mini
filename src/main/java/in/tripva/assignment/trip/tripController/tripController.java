package in.tripva.assignment.trip.tripController;


import in.tripva.assignment.trip.tipService.tripService;
import in.tripva.assignment.trip.tripDTO.searchTripDTO;
import in.tripva.assignment.trip.tripDTO.tripDTO;
import in.tripva.assignment.trip.tripEntity.tripEntity;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Data
@RequestMapping("/api")
public class tripController {

    private final tripService tripService;

    @PostMapping("/trips")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<tripDTO> createTrip(@RequestBody tripEntity trip){

        tripDTO createdTrip = tripService.createTrip(trip);
        return ResponseEntity.ok(createdTrip);
    }

    @GetMapping("/trips/search")
    public ResponseEntity<List<tripDTO>> searchTrip(@RequestParam String source,
                                           @RequestParam String destination,
                                           @RequestParam LocalDate date)
    {

        return ResponseEntity.ok(tripService.searchTrip(source,destination,date));

    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<tripDTO> findTrip(@PathVariable Long id){

        return ResponseEntity.ok(tripService.findTrip(id));

    }

}
