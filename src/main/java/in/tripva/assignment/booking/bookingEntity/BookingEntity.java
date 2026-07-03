package in.tripva.assignment.booking.bookingEntity;

import in.tripva.assignment.trip.tripEntity.TripEntity;
import in.tripva.assignment.user.userEntity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @OneToMany(mappedBy = "booking",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BookedSeat> bookedSeats;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Double totalAmount;
    private LocalDateTime createdAt;

}
