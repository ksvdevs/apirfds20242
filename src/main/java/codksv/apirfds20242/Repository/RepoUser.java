package codksv.apirfds20242.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import codksv.apirfds20242.Entity.TUser;

@Repository
public interface RepoUser extends JpaRepository<TUser, String> {
    @Query(value = "select * from tuser where email = :email", nativeQuery = true)
	Optional<TUser> getByEmail(String email);
}