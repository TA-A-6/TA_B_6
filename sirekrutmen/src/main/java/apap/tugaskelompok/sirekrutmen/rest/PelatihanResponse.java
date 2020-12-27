package apap.tugaskelompok.sirekrutmen.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(allowGetters = true)
public class PelatihanResponse {
    private int status;
    private String message;
    private PelatihanDetail result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PelatihanDetail getResult() {
        return result;
    }

    public void setResult(PelatihanDetail result) {
        this.result = result;
    }
}
