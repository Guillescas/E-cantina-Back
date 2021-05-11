package br.com.projeto.ecantina.models;

import java.util.List;

public class Client extends User {

    // Own attributes 

    private Long id;
    
    private String address;

    private String cpf;

    private String urlImage;

    // Relations

    private List<Order> orders;

    private List<Card> cards;

    private List<LoyaltyCard> loyaltyCards;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Client other = (Client) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Client() {}

    public Client(String email, String password, String name, String cpf, String address) {
        super(email, password, name, TypeUser.CLIENT);
        this.cpf = cpf;
        this.address = address; 
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<LoyaltyCard> getLoyaltyCards() {
        return loyaltyCards;
    }

    public void setLoyaltyCards(List<LoyaltyCard> loyaltyCards) {
        this.loyaltyCards = loyaltyCards;
    }

    
}
