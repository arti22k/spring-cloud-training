package pl.training.cloud.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.cloud.users.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
