package pl.training.cloud.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.training.cloud.organization.entity.Department;
import pl.training.cloud.organization.repository.DepartmentRepository;

import java.util.List;

@Service
public class OrganizationService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public OrganizationService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department add(Department department) {
        departmentRepository.saveAndFlush(department);
        return department;
    }

    public Department getDepartment(Long departmentId) {
        return departmentRepository.getById(departmentId)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public void update(Department department) {
        getDepartment(department.getId());
        departmentRepository.saveAndFlush(department);
    }

    public void delete(Long departmentId) {
        Department department = getDepartment(departmentId);
        departmentRepository.delete(department);
    }

}
