package Model;
// Generated Dec 28, 2015 10:28:32 PM by Hibernate Tools 4.3.1

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "Cidade")
public class Cidade  implements java.io.Serializable {
     
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
     private int id;
     private String cidade;
     private String uf;

    public Cidade() {
    }
    
    public Cidade(String cidade, String uf){
      this.cidade = cidade;
      this.uf = uf;
    }
	
    public Cidade(int id) {
        this.id = id;
    }
    public Cidade(int id, String cidade, String uf) {
       this.id = id;
       this.cidade = cidade;
       this.uf = uf;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getCidade() {
        return this.cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }

}


