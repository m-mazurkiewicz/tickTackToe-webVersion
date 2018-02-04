package mmazurkiewicz.models;

public enum Mark {
    CIRCLE('o'),
    CROSS('x'),
    EMPTY('?');

    private char mark;

    Mark(char mark){
        this.mark=mark;
    }

    public char getMark() {
        return mark;
    }
}