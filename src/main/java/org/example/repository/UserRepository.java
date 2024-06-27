package org.example.repository;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    //@Modifying
    //@Transactional
    @Query(value="SELECT * FROM User WHERE email=:inputEmail",nativeQuery = true)
    User getByEmail(String inputEmail);

}
