package za.co.tangentsolutions.myemployeemanager.providers;

public enum  HostProvider {

    tangentHost("http://staging.tangent.tngnt.co/", "");

    private String url;
    private String ip;

    HostProvider(String url, String ip) {
        this.url = url;
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }
}
