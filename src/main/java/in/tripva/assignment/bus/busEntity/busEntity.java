package in.tripva.assignment.bus.busEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Data
public class busEntity {

    @Id
    private Long busId;
    private String operatorName;
    @Enumerated
    private busType busType;
    @Column(unique = true,nullable = false)
    private String busNo;
    private int totalSeats;

}
