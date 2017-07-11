package pl.training.cloud.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.cloud.organization.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> getById(Long id);

    List<Department> getByName(String name);

}
