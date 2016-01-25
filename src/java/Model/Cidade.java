package Model;
// Generated Dec 28, 2015 10:28:32 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "Cidade")
public class Cidade  implements Serializable {
     
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cidade+"-"+uf+"-"+id;
    }
    
    

}


