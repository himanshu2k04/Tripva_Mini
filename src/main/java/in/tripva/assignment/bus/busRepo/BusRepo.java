package in.tripva.assignment.bus.busRepo;


import in.tripva.assignment.bus.busEntity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepo extends JpaRepository<BusEntity,Long> {


    BusEntity findByBusNo(String busNo);


}
