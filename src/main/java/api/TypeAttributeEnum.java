package api;

public enum TypeAttributeEnum {
    HREF("href");

    String type;

    TypeAttributeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
