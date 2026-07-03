package in.tripva.assignment.bus.busDTO;

import in.tripva.assignment.bus.busEntity.BusType;
import lombok.Data;

@Data
public class BusDTO {

    private Long busId;
    private String operatorName;
    private BusType busType;
    private String busNo;
    private int totalSeats;

}
