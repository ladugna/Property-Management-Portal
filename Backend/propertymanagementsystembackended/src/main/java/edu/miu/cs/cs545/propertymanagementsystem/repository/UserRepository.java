package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
