package dad.endlessElectronicMusic.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String url;

	protected Imagen(){
		
	}
	
	public Imagen(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Imagen [id=" + id + ", url=" + url + "]";
	}	
}
