package rw.ac.rca.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.auth.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findUserByUsername(String username);
}
