package api;

public enum ContentTypeEnum {
    JSON("application/json; charset=utf-8"),
    MULTIPART("multipart/form-data");

    public String getOption() {
        return option;
    }

    public String option;


    ContentTypeEnum(String option) {
        this.option = option;
    }

}