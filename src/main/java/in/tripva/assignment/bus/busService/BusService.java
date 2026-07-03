package in.tripva.assignment.bus.busService;

import in.tripva.assignment.bus.busDTO.BusDTO;
import in.tripva.assignment.bus.busEntity.BusEntity;
import in.tripva.assignment.bus.busRepo.BusRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class BusService {

    private final BusRepo busrepo;
    private final ModelMapper modelMapper;


    public BusDTO addBus(BusEntity bus) {
        BusEntity createedBus = busrepo.save(bus);

        return modelMapper.map(createedBus, BusDTO.class);
    }

    public String removeBus(BusEntity bus) {

        BusEntity existingBus = busrepo.findByBusNo(bus.getBusNo());

        if (existingBus != null) {
            busrepo.delete(existingBus);
            return "Bus Removed Successfully";
        }

        return "No bus present like this";
    }
    public List<BusDTO> getAllBus() {
        List<BusEntity> bus = busrepo.findAll();

        return bus
                .stream()
                .map(b ->modelMapper
                        .map(b, BusDTO.class))
                .toList();
    }


    public BusDTO updateBus(BusEntity bus) {

        BusEntity existingBus = busrepo.findById(bus.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (bus.getOperatorName() != null) {
            existingBus.setOperatorName(bus.getOperatorName());
        }

        if (bus.getBusNo() != null) {
            existingBus.setBusNo(bus.getBusNo());
        }

        if (bus.getBusType() != null) {
            existingBus.setBusType(bus.getBusType());
        }

        BusEntity updatedBus = busrepo.save(existingBus);

        return modelMapper.map(updatedBus, BusDTO.class);

    }
}
