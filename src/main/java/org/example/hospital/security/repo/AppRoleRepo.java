package org.example.hospital.security.repo;

import org.example.hospital.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, String> {


}
