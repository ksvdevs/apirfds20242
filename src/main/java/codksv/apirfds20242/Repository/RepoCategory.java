package codksv.apirfds20242.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import codksv.apirfds20242.Entity.TCategory;

@Repository
public interface RepoCategory extends JpaRepository<TCategory, String> {}