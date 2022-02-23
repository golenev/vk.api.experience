package api;

public enum StatusCode {
    OK(200),
    NOT_FOUND(404),
    CREATED(201);

    public int option;

    public int getOption() {
        return option;
    }

    StatusCode(int option) {
        this.option = option;
    }

}
