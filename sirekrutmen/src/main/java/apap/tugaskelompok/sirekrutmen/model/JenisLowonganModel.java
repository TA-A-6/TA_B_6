package apap.tugaskelompok.sirekrutmen.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="jenisLowongan")
public class JenisLowonganModel implements Serializable {
	
	public Long getIdJenisLowongan() {
		return idJenisLowongan;
	}


	public void setIdJenisLowongan(Long idJenisLowongan) {
		this.idJenisLowongan = idJenisLowongan;
	}


	public String getNama() {
		return nama;
	}


	public void setNama(String nama) {
		this.nama = nama;
	}


	public List<LowonganModel> getListLowongan() {
		return listLowongan;
	}


	public void setListLowongan(List<LowonganModel> listLowongan) {
		this.listLowongan = listLowongan;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJenisLowongan;
	
	@NotNull
	@Size(max=50)
	@Column(name="nama", nullable=false)
	private String nama;
	
	
	@OneToMany(mappedBy = "jenisLowongan", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<LowonganModel> listLowongan;
    
}
