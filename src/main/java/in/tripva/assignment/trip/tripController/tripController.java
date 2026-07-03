package in.tripva.assignment.trip.tripController;


import in.tripva.assignment.trip.tipService.TripService;
import in.tripva.assignment.trip.tripDTO.SearchTripDTO;
import in.tripva.assignment.trip.tripDTO.TripDTO;
import in.tripva.assignment.trip.tripEntity.TripEntity;
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

    private final TripService tripService;

    @PostMapping("/trips")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TripDTO> createTrip(@RequestBody TripEntity trip){

        TripDTO createdTrip = tripService.createTrip(trip);
        return ResponseEntity.ok(createdTrip);
    }

    @GetMapping("/trips/search")
    public ResponseEntity<List<TripDTO>> searchTrip(@RequestParam String source,
                                           @RequestParam String destination,
                                           @RequestParam LocalDate date)
    {

        return ResponseEntity.ok(tripService.searchTrip(source,destination,date));

    }

    @GetMapping("/trips/{id}")
    public ResponseEntity<TripDTO> findTrip(@PathVariable Long id){

        return ResponseEntity.ok(tripService.findTrip(id));

    }

}
