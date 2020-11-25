package apap.tugaskelompok.sirekrutmen.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.io.Serializable;

@Entity
@Table(name="lowongan")
public class LowonganModel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLowongan;
	
	@NotNull
	@Size(max=20)
	@Column(name="kodeLowongan", nullable=false)
	private String kodeLowongan;
	
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
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "userId", referencedColumnName = "uuid", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserModel user;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "jenisLowonganId", referencedColumnName = "idJenisLowongan", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JenisLowonganModel jenisLowongan;
	
	@OneToMany(mappedBy = "lowongan", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<LamaranModel> listLamaran;

	public Long getIdLowongan() {
		return idLowongan;
	}

	public void setIdLowongan(Long idLowongan) {
		this.idLowongan = idLowongan;
	}

	public String getKodeLowongan() {
		return kodeLowongan;
	}

	public void setKodeLowongan(String kodeLowongan) {
		this.kodeLowongan = kodeLowongan;
	}

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

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public JenisLowonganModel getJenisLowongan() {
		return jenisLowongan;
	}

	public void setJenisLowongan(JenisLowonganModel jenisLowongan) {
		this.jenisLowongan = jenisLowongan;
	}

	public List<LamaranModel> getListLamaran() {
		return listLamaran;
	}

	public void setListLamaran(List<LamaranModel> listLamaran) {
		this.listLamaran = listLamaran;
	}
    
	
}
