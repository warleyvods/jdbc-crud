package model.dao;

import model.entity.Department;

import java.util.List;

public interface DepartmentDao {

    void insert(Department dep);
    void update(Department dep);
    void deleteById(Long id);
    Department findById(Long id);
    List<Department> findAll();
}
