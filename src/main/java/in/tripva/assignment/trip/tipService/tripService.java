package in.tripva.assignment.trip.tipService;

import in.tripva.assignment.bus.busEntity.busEntity;
import in.tripva.assignment.bus.busRepo.busRepo;
import in.tripva.assignment.trip.tripDTO.tripDTO;
import in.tripva.assignment.trip.tripEntity.tripEntity;
import in.tripva.assignment.trip.tripRepo.tripRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class tripService {

    private final tripRepo tripRepo;
    private final ModelMapper modelMapper;
    private final busRepo busRepo;


    public tripDTO createTrip(tripEntity trip) {

        Optional<tripEntity> findBus = tripRepo.findByBusBusId(trip.getBus().getBusId());

        if(findBus.isEmpty()){
            throw new RuntimeException("Enter valid busId");
        }

        tripEntity createdTrip = tripRepo.save(trip);
        return modelMapper.map(createdTrip, tripDTO.class);

    }

    public List<tripDTO> searchTrip(String source, String destination, LocalDate date) {

        List<tripEntity> searchedTrip = tripRepo
                .findBySourceAndDestinationAndDate(source, destination, date);

        return searchedTrip
                .stream()
                .map(t ->
                        modelMapper
                                .map(t, tripDTO.class))
                .toList();

    }

    public tripDTO findTrip(Long id) {

        tripEntity trip = tripRepo.findById(id).orElseThrow(() -> new RuntimeException("trip not found"));

        return modelMapper.map(trip, tripDTO.class);

    }
}
