package codksv.apirfds20242.Service.Category.RequestObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsert {
    @NotBlank(message = "El campo \"name\" es requerido")
    private String name;
    @NotBlank(message = "El campo \"message\" es requerido")
    private String description;
    @NotNull(message = "El campo \"state\" es requerido")
    private boolean state;
}