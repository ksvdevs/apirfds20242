package codksv.apirfds20242.Service.Category.RequestObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsert {
    private String name;
    private String description;
    private boolean state;
}