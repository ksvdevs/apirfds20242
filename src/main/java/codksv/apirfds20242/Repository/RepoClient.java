package codksv.apirfds20242.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codksv.apirfds20242.Entity.TClient;

@Repository
public interface RepoClient extends JpaRepository<TClient, String> {
    TClient findByFirstNameAndDni(String firstName, String dni);
}