package codksv.apirfds20242.Service.User.RequestObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResquestInsert {
    @NotBlank(message = "El campo \"nameUser\" es requerido")
    private String nameUser;
    @NotBlank(message = "El campo \"password\" es requerido")
    private String password;
}
