package apap.tugaskelompok.sirekrutmen.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Entity
@Table(name="user")
public class UserModel implements Serializable{
	
	
	@NotNull
	@Size(max=50)
	@Column(name="username", nullable=false)
	private String username;
	
	@NotNull
	@Size(max=200)
	@Lob
	@Column(name="password", nullable=false)
	private String password;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(
			name= "system-uuid",
			strategy = "uuid"
			
	)
	private String uuid;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "roleId", referencedColumnName = "idRole", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private RoleModel role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<LowonganModel> listLowongan;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PelamarModel> listPelamar;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public List<LowonganModel> getListLowongan() {
		return listLowongan;
	}

	public void setListLowongan(List<LowonganModel> listLowongan) {
		this.listLowongan = listLowongan;
	}

	public List<PelamarModel> getListPelamar() {
		return listPelamar;
	}

	public void setListPelamar(List<PelamarModel> listPelamar) {
		this.listPelamar = listPelamar;
	}
    
	

}
