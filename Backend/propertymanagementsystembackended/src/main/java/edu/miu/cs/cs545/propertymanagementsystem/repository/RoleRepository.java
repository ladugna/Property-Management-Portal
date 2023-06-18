package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.Role;
import edu.miu.cs.cs545.propertymanagementsystem.model.enums.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select r from Role r where r.roles=:roles")
    public Role findByRoles(Roles roles);
}
