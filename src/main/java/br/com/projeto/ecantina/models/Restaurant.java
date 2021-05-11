package br.com.projeto.ecantina.models;

import java.util.List;

public class Restaurant {
    
    private Long id;
    private String cnpj;

    private List<Product> products;

    private Establishment establishment;

    private TypeRestaurant typeRestaurant;
    private Integer rating;
    private Boolean open;
    private String description;

    public Restaurant() {}

    public Restaurant(String cnpj, TypeRestaurant typeRestaurant, String description, Establishment establishment) {
        this.cnpj = cnpj;
        this.typeRestaurant = typeRestaurant;
        this.description = description;
        this.setEstablishment(establishment);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
        Restaurant other = (Restaurant) obj;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TypeRestaurant getTypeRestaurant() {
        return typeRestaurant;
    }

    public void setTypeRestaurant(TypeRestaurant typeRestaurant) {
        this.typeRestaurant = typeRestaurant;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
