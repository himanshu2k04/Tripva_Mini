package in.tripva.assignment.bus.busDTO;

import in.tripva.assignment.bus.busEntity.busType;
import lombok.Data;

@Data
public class busDTO {

    private Long busId;
    private String operatorName;
    private busType busType;
    private String busNo;
    private int totalSeats;

}
