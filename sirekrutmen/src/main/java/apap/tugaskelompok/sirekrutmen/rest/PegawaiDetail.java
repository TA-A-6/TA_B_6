package apap.tugaskelompok.sirekrutmen.rest;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class PegawaiDetail {
	
	private String username;
	
	private String nama;
	
	private String noTelepon;
	
	private String tempatLahir;
	
	private Date tanggalLahir;
	
	private String alamat;
	
	private Integer roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoTelepon() {
		return noTelepon;
	}

	public void setNoTelepon(String noTelepon) {
		this.noTelepon = noTelepon;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRole(Integer roleId) {
		this.roleId = roleId;
	}
	
}
