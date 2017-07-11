package pl.training.cloud.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.cloud.users.entity.Authority;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
}
