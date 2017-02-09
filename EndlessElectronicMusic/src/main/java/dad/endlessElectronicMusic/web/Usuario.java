package dad.endlessElectronicMusic.web;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private  String usuario;
	private String email;
	private String contraseña;
	private String autentificacion;
	private boolean prioridad;
	//false prioridad baja y true prioridad alta
	
	@OneToMany(mappedBy="usuario")
	private List<ComentarioEvento>comentarioEvento;
	
	public Usuario(){};
	
	public Usuario(long id, String usuario, String email, String contraseña, String autentificacion, boolean prioridad,
			List<ComentarioEvento> comentario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.contraseña = contraseña;
		this.autentificacion = autentificacion;
		this.prioridad = prioridad;
		this.comentarioEvento = comentario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getAutentificacion() {
		return autentificacion;
	}

	public void setAutentificacion(String autentificacion) {
		this.autentificacion = autentificacion;
	}

	public boolean isPrioridad() {
		return prioridad;
	}

	public void setPrioridad(boolean prioridad) {
		this.prioridad = prioridad;
	}

	public List<ComentarioEvento> getComentario() {
		return comentarioEvento;
	}

	public void setComentario(List<ComentarioEvento> comentario) {
		this.comentarioEvento = comentario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", email=" + email + ", contraseña=" + contraseña
				+ ", autentificacion=" + autentificacion + ", prioridad=" + prioridad + ", comentario=" + comentarioEvento
				+ "]";
	}
	
	
}
