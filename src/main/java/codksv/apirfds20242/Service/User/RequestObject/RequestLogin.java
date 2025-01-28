package codksv.apirfds20242.Service.User.RequestObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogin {
    private String email;
    private String password;
}
