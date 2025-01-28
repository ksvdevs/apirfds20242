package codksv.apirfds20242.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codksv.apirfds20242.Dto.DtoUser;
import codksv.apirfds20242.Entity.TUser;
import codksv.apirfds20242.Repository.RepoUser;
import jakarta.transaction.Transactional;

@Service
public class BusinessUser {
	@Autowired
	private RepoUser repoUser;

	public List<DtoUser> getAll() {

		List<TUser> listTClient = repoUser.findAll();
		List<DtoUser> listDtoUser = new ArrayList<>();

		for (TUser item : listTClient) {
			DtoUser dtoUser = new DtoUser();
			dtoUser.setIdUser(item.getIdUser());
			dtoUser.setNameUser(item.getNameUser());
			dtoUser.setPassword(item.getPassword());
			dtoUser.setCreatedAt(item.getCreatedAt());
			dtoUser.setUpdatedAt(item.getUpdatedAt());
			listDtoUser.add(dtoUser);
		}
		return listDtoUser;
	}

	@Transactional
	public void insert(DtoUser dtoUser) {
		dtoUser.setIdUser(UUID.randomUUID().toString());
		dtoUser.setCreatedAt(new Date());
		dtoUser.setUpdatedAt(new Date());
		TUser tUser = new TUser();
		tUser.setIdUser(dtoUser.getIdUser());
		tUser.setNameUser(dtoUser.getNameUser());
		tUser.setPassword(dtoUser.getPassword());
		tUser.setCreatedAt(dtoUser.getCreatedAt());
		tUser.setUpdatedAt(dtoUser.getUpdatedAt());

		repoUser.save(tUser);
	}

	public DtoUser login(String userName, String password) {
		Optional<TUser> tUser = repoUser.getLogin(userName, password);
		DtoUser dtoUser = null;
		if (tUser.isPresent()) {
			dtoUser = new DtoUser();
			dtoUser.setIdUser(tUser.get().getIdUser());
			dtoUser.setNameUser(tUser.get().getNameUser());
			dtoUser.setCreatedAt(tUser.get().getCreatedAt());
			dtoUser.setUpdatedAt(tUser.get().getUpdatedAt());

		}
		return dtoUser;
	}
}
