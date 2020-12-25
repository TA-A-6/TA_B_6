package apap.tugaskelompok.sirekrutmen.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(value={"gaji"},allowGetters = true)
public class GajiDetails {

    private Long id;
    private Integer gajiPokok;
    private Integer statusPersetujuan;
    private Date tanggalMasuk;

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGaji() {
        return id;
    }

    public void setIdGaji(Long idGaji) {
        this.id = idGaji;
    }

    public Integer getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Integer gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getStatusPersetujuan() {
        return statusPersetujuan;
    }

    public void setStatusPersetujuan(Integer statusPersetujuan) {
        this.statusPersetujuan = statusPersetujuan;
    }
}
