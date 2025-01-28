package codksv.apirfds20242.Dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DtoUser {
    private String idUser;
    private String nameUser;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
}
