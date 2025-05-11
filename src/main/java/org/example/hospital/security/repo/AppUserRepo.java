package org.example.hospital.security.repo;

import org.example.hospital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    AppUser findByUserName(String email);

}
