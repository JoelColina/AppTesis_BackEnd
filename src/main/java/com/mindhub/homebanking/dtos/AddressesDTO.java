package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Addresses;

public class AddressesDTO {

    private long id;
    private String direccion;
    private Number numero;
    private String ciudad;
    private String comuna;
    private Number codigoPostal;

    public AddressesDTO(Addresses addresses) {
        this.direccion = getDireccion();
        this.numero = getNumero();
        this.ciudad = getCiudad();
        this.comuna = getComuna();
        this.codigoPostal = getCodigoPostal();
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
}
