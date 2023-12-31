package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;


@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "User.FindListLikedByVideoHref",
			procedureName = "sp_selectUsersLikedVideoByVideoHref",
			resultClasses = {User.class},
			parameters = @StoredProcedureParameter(name = "videoHref", type = String.class))	
})



@Entity
@Table(name ="[user]")
public class User {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name ="username")
	private String username;
	@Column(name ="password")
	private String password;
	@Column(name ="email")
	private String email;
	@Column(name ="isAdmin")
	private boolean isAdmin;
	@Column(name ="isActive")
	private boolean isActive;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}	
	
}
