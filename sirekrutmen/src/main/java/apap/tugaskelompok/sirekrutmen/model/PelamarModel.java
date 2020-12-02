package apap.tugaskelompok.sirekrutmen.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="pelamar")
public class PelamarModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPelamar;
	
	@NotNull
	@Size(max=100)
	@Column(name="nama", nullable=false)
	private String nama;

	@NotNull
	@Size(max=20)
	@Column(name="noTelepon", nullable=false)
	private String noTelepon;

	@NotNull
	@Size(max=20)
	@Column(name="tempatLahir", nullable=false)
	private String tempatLahir;
	
	@NotNull
	@Column(name="tanggalLahir")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date tanggalLahir;
	
	@NotNull
	@Size(max=100)
	@Column(name="alamat", nullable=false)
	private String alamat;
	
//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name= "userId", referencedColumnName = "uuid", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonIgnore
//	private UserModel user;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", referencedColumnName ="uuid")
	private UserModel user;
	
	
	@OneToMany(mappedBy = "pelamar", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<LamaranModel> listLamaran;

	public Long getIdPelamar() {
		return idPelamar;
	}

	public void setIdPelamar(Long idPelamar) {
		this.idPelamar = idPelamar;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<LamaranModel> getListLamaran() {
		return listLamaran;
	}

	public void setListLamaran(List<LamaranModel> listLamaran) {
		this.listLamaran = listLamaran;
	}
    
	

}
