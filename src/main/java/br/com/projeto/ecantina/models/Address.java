package br.com.projeto.ecantina.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p> Represents the {@code compound key} that we have in the system.
 * 
 * @implSpec The default is when a user that have this attribute add in his profile a new address, 
 *           use the<pre> setAddress(address) or listOfAddress.add(address) </pre> for link the new
 *           address to his profile.
 * 
 * @author @LucasAuSilva
 * @author @Guillescas
 * @author @NunuWelinton
 * @author @OzneKx
 * @author @lucasrossi0
 * 
 * @see User
 * @see Establishment
 * @since 1.0
*/
@Entity(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 9, nullable = false)
    private String cep;

    @Column(length = 50, nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private Integer number;

    @Column(length = 30, nullable = true)
    private String complement;

    public Address() {}

    public Address(String streetName, String cep, String neighborhood, Integer number, String complement) {
        this.streetName = streetName;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.number = number;
        this.complement = complement;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getComplement() {
        return complement;
    }
    public void setComplement(String complement) {
        this.complement = complement;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
}
