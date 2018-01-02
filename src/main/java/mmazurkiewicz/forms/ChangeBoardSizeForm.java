package mmazurkiewicz.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class ChangeBoardSizeForm {
    // private Board board=new Board();

    @Min(2)
    //@Size(min = 2)//, message = "Plansza musi mieć co najmniej 1 wiersz")
    private int x;

    @Min(2)
    //@Size(min = 2)//, message = "Plansza musi mieć co najmniej 1 kolumnę")
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
