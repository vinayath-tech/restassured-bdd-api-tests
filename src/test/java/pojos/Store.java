package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Store {

    private Integer sold;
    private Integer deceased;
    private Integer pending;
    private Integer available;
    @JsonIgnoreProperties private String string;
}
