package ro.atelieruldigital.news.home.filters.utilities;

public class ToggledPair {
    private String value;
    private Boolean isOn;

    public ToggledPair(String value, Boolean isOn) {
        this.value = value;
        this.isOn = isOn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }

    @Override
    public String toString() {
        return "ToggledPair{" +
                "value='" + value + '\'' +
                ", isOn=" + isOn +
                '}';
    }
}
