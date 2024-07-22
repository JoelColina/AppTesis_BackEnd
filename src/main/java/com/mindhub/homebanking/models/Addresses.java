package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Number idCliente;
    private String direccion;
    private Number numero;
    private String ciudad;
    private String comuna;
    private Number codigoPostal;
    private AddressType type;
    private boolean habilitado;


    public Addresses(String direccion, Number numero, String ciudad, String comuna, Number codigoPostal) {
        this.direccion = direccion;
        this.numero = numero;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.codigoPostal = codigoPostal;
    }

    public Addresses() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Number getNumero() {
        return numero;
    }

    public void setNumero(Number numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public Number getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Number codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Addresses{" +
                "direccion='" + direccion + '\'' +
                ", numero=" + numero +
                ", ciudad='" + ciudad + '\'' +
                ", comuna='" + comuna + '\'' +
                ", codigoPostal=" + codigoPostal +
                '}';
    }
}
