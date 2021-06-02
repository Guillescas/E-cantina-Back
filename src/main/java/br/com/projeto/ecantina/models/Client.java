package br.com.projeto.ecantina.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * This class is used for representing one type of {@code User} that we have in
 * this system, thats why we are extending the User class, so in database we
 * have the {@code id}, {@code email}, {@code password} and {@code name} for
 * this class as well. This representation is for a client that will buy
 * products in from the {@link Restaurant} user.
 * 
 * @implSpec Use this method only for create a new object in a dto request or
 *           something like that, don't ever, use in the controller directly.
 * 
 * @author @LucasAuSilva
 * @author @Guillescas
 * @author @NunuWelinton
 * @author @OzneKx
 * @author @lucasrossi0
 * 
 * @see User
 * @see Order
 * @see Address
 * @since 1.0
 */
@Entity(name = "clients")
public class Client extends User {

    @Column(length = 14, nullable = true)
    private String cpf;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Address> address;

    @OneToMany
    @JoinColumn(name = "client_id")
    private Set<Order> orders;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Card> cards;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<LoyaltyCard> loyaltyCards;

    public Client() {
    }

    /**
     * Use for register a client in the system.
     * 
     * @implSpec The attribute {@code type} is defined automatic and it is
     *           {@code "client"}.
     * @implNote Don't send any of the params null, the unique attribute that is
     *           nullable is the cpf.
     * 
     * @param email    The {@code email} of the {@code Client}.
     * @param password The {@code password} of the {@code Client}.
     * @param name     The {@code name} of the {@code Client}.
     * 
     */
    public Client(String email, String password, String name) {
        super(email, password, name, "client");
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

    public List<LoyaltyCard> getLoyaltyCards() {
        return loyaltyCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Set<Order> getOrders() {
        return orders;
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
    
    @Override
    public String getUsername() {
        return getEmail();
    }
}
