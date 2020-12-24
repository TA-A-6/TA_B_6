package apap.tugaskelompok.sirekrutmen.rest;


import apap.tugaskelompok.sirekrutmen.model.JenisLowonganModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@JsonIgnoreProperties(allowGetters = true)
public class LowonganDto {

    @NotNull
    @Size(max=20)
    @Column(name="divisi", nullable=false)
    private String divisi;

    @NotNull
    @Size(max=20)
    @Column(name="posisi", nullable=false)
    private String posisi;


    @NotNull
    @Column(name="jumlah", nullable=false)
    private Integer jumlah;

    @JsonProperty("jenisLowongan")
    private Long jenisLowongan;

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public Long getJenisLowongan() {
        return jenisLowongan;
    }

    public void setJenisLowongan(Long jenisLowongan) {
        this.jenisLowongan = jenisLowongan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}