package in.tripva.assignment.bus.busService;

import in.tripva.assignment.bus.busDTO.busDTO;
import in.tripva.assignment.bus.busEntity.busEntity;
import in.tripva.assignment.bus.busRepo.busRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class busService {

    private final busRepo busrepo;
    private final ModelMapper modelMapper;


    public busDTO addBus(busEntity bus) {
        busEntity createedBus = busrepo.save(bus);

        return modelMapper.map(createedBus, busDTO.class);
    }

    public String removeBus(busEntity bus) {

        busEntity existingBus = busrepo.findByBusNo(bus.getBusNo());

        if (existingBus != null) {
            busrepo.delete(existingBus);
            return "Bus Removed Successfully";
        }

        return "No bus present like this";
    }
    public List<busDTO> getAllBus() {
        List<busEntity> bus = busrepo.findAll();

        return bus
                .stream()
                .map(b ->modelMapper
                        .map(b, busDTO.class))
                .toList();
    }


    public busDTO updateBus(busEntity bus) {

        busEntity existingBus = busrepo.findById(bus.getBusId())
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

        busEntity updatedBus = busrepo.save(existingBus);

        return modelMapper.map(updatedBus, busDTO.class);

    }
}
