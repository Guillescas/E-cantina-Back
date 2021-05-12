package br.com.projeto.ecantina.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "client")
public class Client extends User{
    
    @Column(length = 14, nullable = true)
    private String cpf;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Address> address;

    @Column
    private String urlImage;

    public Client() {}

    /**
     * Use for register a client in the system.
     * 
     * @LucasAuSilva
     * @Guillescas
     * @lucasrossi0
     * @NunuWelinton
     * @OzneKx
     */
    public Client(String email, String password, String name) {
        super(email, password, name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
