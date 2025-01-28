package codksv.apirfds20242.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codksv.apirfds20242.Dto.DtoClient;
import codksv.apirfds20242.Entity.TClient;
import codksv.apirfds20242.Repository.RepoClient;
import jakarta.transaction.Transactional;

@Service
public class BusinessClient {
    @Autowired 
    private RepoClient repoClient;

    public List<DtoClient> getAll() {

        List<TClient> listTClient = repoClient.findAll();
        List<DtoClient> listDtoClient = new ArrayList<>();

        for (TClient item : listTClient) {
            DtoClient dtoClient = new DtoClient();

            dtoClient.setIdClient(item.getIdClient());
            dtoClient.setFirstName(item.getFirstName());
            dtoClient.setSurName(item.getSurName());
            dtoClient.setDni(item.getDni());
            dtoClient.setBirthDate(item.getBirthDate());
            dtoClient.setCreatedAt(item.getCreatedAt());
            dtoClient.setUpdatedAt(item.getUpdatedAt());

            listDtoClient.add(dtoClient);
        }
        return listDtoClient;
    }

    @Transactional
    public void insert(DtoClient dtoClient) {
        dtoClient.setIdClient(UUID.randomUUID().toString());
        dtoClient.setCreatedAt(new Date());
        dtoClient.setUpdatedAt(new Date());

        TClient tClient = new TClient();

        tClient.setIdClient(dtoClient.getIdClient());
        tClient.setFirstName(dtoClient.getFirstName());
        tClient.setSurName(dtoClient.getSurName());
        tClient.setDni(dtoClient.getDni());
        tClient.setGender(dtoClient.isGender());
        tClient.setPhone(dtoClient.getPhone());
        tClient.setAddress(dtoClient.getAddress());
        tClient.setBirthDate(dtoClient.getBirthDate());
        tClient.setEmail(dtoClient.getEmail());
        tClient.setCreatedAt(dtoClient.getCreatedAt());
        tClient.setUpdatedAt(dtoClient.getUpdatedAt());

        repoClient.save(tClient);
    }
}
