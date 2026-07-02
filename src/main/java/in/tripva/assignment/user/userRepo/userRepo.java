package in.tripva.assignment.user.userRepo;

import in.tripva.assignment.user.userEntity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<userEntity,Long> {
    userEntity findByEmail(String email);
}
