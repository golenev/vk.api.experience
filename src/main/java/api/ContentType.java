package api;

public enum ContentType {
    JSON("application/json; charset=utf-8");

    public String getOption() {
        return option;
    }

    public String option;

    ContentType(String option) {
        this.option = option;
    }

}