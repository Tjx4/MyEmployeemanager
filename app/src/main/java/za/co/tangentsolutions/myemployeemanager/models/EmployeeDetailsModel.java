package za.co.tangentsolutions.myemployeemanager.models;

public class EmployeeDetailsModel {
    private String key;
    private String value;

    public EmployeeDetailsModel(String key, String value) {
        this.value = value;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}