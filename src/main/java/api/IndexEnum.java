package api;

public enum IndexEnum {
    ZERO(0),
    FIRST(1);

   int indexValue;

    IndexEnum(int indexValue) {
        this.indexValue = indexValue;
    }

    public int getIndexValue() {
        return indexValue;
    }
}
