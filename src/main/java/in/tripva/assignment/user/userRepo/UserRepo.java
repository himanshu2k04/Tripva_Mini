package in.tripva.assignment.user.userRepo;

import in.tripva.assignment.user.userEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
}
