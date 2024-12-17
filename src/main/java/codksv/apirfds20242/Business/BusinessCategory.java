package codksv.apirfds20242.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codksv.apirfds20242.Dto.DtoCategory;
import codksv.apirfds20242.Entity.TCategory;
import codksv.apirfds20242.Repository.RepoCategory;

@Service
public class BusinessCategory {
    @Autowired
    private RepoCategory repoCategory;

    public void insert(DtoCategory dtoCategory) {

        dtoCategory.setIdcategory(UUID.randomUUID().toString());
        dtoCategory.setCreatedAt(new Date());
		dtoCategory.setUpdatedAt(new Date());

        TCategory tCategory = new TCategory();
        tCategory.setIdcategory(dtoCategory.getIdcategory());
        tCategory.setName(dtoCategory.getName());
        tCategory.setDescription(dtoCategory.getDescription());
        tCategory.setState(dtoCategory.isStatus());
        tCategory.setCreatedAt(dtoCategory.getCreatedAt());
        tCategory.setUpdatedAt(dtoCategory.getUpdatedAt());

        repoCategory.save(tCategory);
    }

    public List<DtoCategory> getAll() {

		List<TCategory> listTCategory = repoCategory.findAll();
		List<DtoCategory> listDtoCategory = new ArrayList<>(); 

		for (TCategory item : listTCategory) {
			DtoCategory dtoCategory = new DtoCategory();

			dtoCategory.setIdcategory(item.getIdcategory()); 
            dtoCategory.setName(item.getName()); 
			dtoCategory.setDescription(item.getDescription());
			dtoCategory.setStatus(item.isState());
            dtoCategory.setCreatedAt(item.getCreatedAt());
            dtoCategory.setUpdatedAt(item.getUpdatedAt());

			listDtoCategory.add(dtoCategory); 
		}

		return listDtoCategory;
	}

    public boolean delete(String idcategory) {
        Optional<TCategory> tCategory = repoCategory.findById(idcategory);
        
        if (tCategory.isPresent()) {
            repoCategory.deleteById(idcategory);
        }
    
        return true;
    }
    
}