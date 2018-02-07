package mmazurkiewicz.forms;

import javax.validation.constraints.Min;

public class LoadGameForm {

    @Min(1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
