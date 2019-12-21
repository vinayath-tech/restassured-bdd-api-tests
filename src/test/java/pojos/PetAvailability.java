package pojos;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class PetAvailability {

    private Long id;
    private String name;
    private List<Object> photoUrls = null;
    private List<Object> tags = null;
    private Category category;
    private String status;
}
