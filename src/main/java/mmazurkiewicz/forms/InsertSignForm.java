package mmazurkiewicz.forms;

import mmazurkiewicz.models.Board;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class InsertSignForm {
   // private Board board=new Board();

    //@Size(min = 1, max = 10, message = "Wprowadzona wartość musi zawierać się w planszy")
    @Min(1)
    //@Max(10)
    private int x;

//    @Size(min = 1, max = 10, message = "Wprowadzona wartość musi zawierać się w planszy")
//    @NotEmpty
    @Min(1)
    //@Max(10)
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
