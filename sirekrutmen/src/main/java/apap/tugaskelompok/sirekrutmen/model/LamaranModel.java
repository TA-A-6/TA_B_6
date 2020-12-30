package apap.tugaskelompok.sirekrutmen.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="lamaran")
public class LamaranModel implements Serializable {
	
	@NotNull
	@Column(name="tanggalDiterima")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date tanggalDiterima;
	
	@NotNull
	@Column(name="status", nullable=false)
	private Integer status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLamaran;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "pelamarId", referencedColumnName = "idPelamar", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PelamarModel pelamar;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "lowonganId", referencedColumnName = "idLowongan", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private LowonganModel lowongan;

	public Date getTanggalDiterima() {
		return tanggalDiterima;
	}

	public void setTanggalDiterima(Date tanggalDiterima) {
		this.tanggalDiterima = tanggalDiterima;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIdLamaran() {
		return idLamaran;
	}

	public void setIdLamaran(Long idLamaran) {
		this.idLamaran = idLamaran;
	}

	public PelamarModel getPelamar() {
		return pelamar;
	}

	public void setPelamar(PelamarModel pelamar) {
		this.pelamar = pelamar;
	}

	public LowonganModel getLowongan() {
		return lowongan;
	}

	public void setLowongan(LowonganModel lowongan) {
		this.lowongan = lowongan;
	}
	
	
	
}
