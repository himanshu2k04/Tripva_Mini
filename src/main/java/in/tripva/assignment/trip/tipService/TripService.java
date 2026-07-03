package in.tripva.assignment.trip.tipService;

import in.tripva.assignment.bus.busRepo.BusRepo;
import in.tripva.assignment.exception.ResourceNotFoundException;
import in.tripva.assignment.trip.tripDTO.TripDTO;
import in.tripva.assignment.trip.tripEntity.TripEntity;
import in.tripva.assignment.trip.tripRepo.TripRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class TripService {

    private final TripRepo tripRepo;
    private final ModelMapper modelMapper;
    private final BusRepo busRepo;


    public TripDTO createTrip(TripEntity trip) {

        Optional<TripEntity> findBus = tripRepo.findByBusBusId(trip.getBus().getBusId());


        if(findBus.isEmpty()){
            throw new RuntimeException("Enter valid busId");
        }

        TripEntity createdTrip = tripRepo.save(trip);
        return modelMapper.map(createdTrip, TripDTO.class);

    }

    public List<TripDTO> searchTrip(String source, String destination, LocalDate date) {

        List<TripEntity> searchedTrip = tripRepo
                .findBySourceAndDestinationAndDate(source, destination, date);

        return searchedTrip
                .stream()
                .map(t ->
                        modelMapper
                                .map(t, TripDTO.class))
                .toList();

    }

    public TripDTO findTrip(Long id) {

        TripEntity trip = tripRepo.findById(id).orElseThrow(() -> new RuntimeException("trip not found"));

        return modelMapper.map(trip, TripDTO.class);

    }
}
