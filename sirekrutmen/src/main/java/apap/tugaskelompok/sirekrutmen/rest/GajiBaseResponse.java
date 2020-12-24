package apap.tugaskelompok.sirekrutmen.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GajiBaseResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("gaji")
    private GajiDetails gaji;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GajiDetails getGaji() {
        return gaji;
    }

    public void setGaji(GajiDetails gaji) {
        this.gaji = gaji;
    }
}
