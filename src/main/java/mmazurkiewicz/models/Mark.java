package mmazurkiewicz.models;

public enum Mark {
    CIRCLE('o'),
    CROSS('x'),
    EMPTY('_');

    private char mark;

    Mark(char mark){
        this.mark=mark;
    }

    public char getMark() {
        return mark;
    }
}