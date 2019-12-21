package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Pets {

    private Integer id;
    private Category category;
    private String name;
    private List<Object> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
