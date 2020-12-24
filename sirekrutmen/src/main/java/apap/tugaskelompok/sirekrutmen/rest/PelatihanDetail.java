package apap.tugaskelompok.sirekrutmen.rest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PelatihanDetail {

    @JsonProperty("namaPelatihan")
    private String namaPelatihan;

    @JsonProperty("deskripsi")
    private String deskripsi;

    @JsonProperty("kapasitas")
    private Integer kapasitas;

    @JsonProperty("tanggalMulai")
    private Date tanggalMulai;

    @JsonProperty("tanggalSelesai")
    private Date tanggalSelesai;

    @JsonProperty("waktuMulai")
    private Date waktuMulai;

    @JsonProperty("waktuSelesai")
    private Date waktuSelesai;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaPelatihan() {
        return namaPelatihan;
    }

    public void setNamaPelatihan(String namaPelatihan) {
        this.namaPelatihan = namaPelatihan;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Integer kapasitas) {
        this.kapasitas = kapasitas;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public Date getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(Date waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public Date getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(Date waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }
}
