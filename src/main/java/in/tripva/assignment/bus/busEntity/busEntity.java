package in.tripva.assignment.bus.busEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Data
@Table(name = "bus_details")
public class busEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;
    private String operatorName;
    @Enumerated(EnumType.STRING)
    private busType busType;
    @Column(unique = true,nullable = false)
    private String busNo;
    private int totalSeats;

}
