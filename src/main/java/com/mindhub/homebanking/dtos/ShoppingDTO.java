package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.AddressType;
import com.mindhub.homebanking.models.Shopping;
import java.util.Date;

public class ShoppingDTO {

    private long id;
    private String comercio;
    private String producto;
    private Number numeroCompra;
    private Number sku;
    private Date fechaCompra;
    private Number cantidad;
    private Number valor;
    private Number numcuotas;
    private Number valorTotal;
    private String tipoTarjeta;
    private AddressType type;
    private String fechaEntrega;
    private String direccionEntrega;
    private String retiradoPor;

    public ShoppingDTO(Shopping shopping) {
        this.comercio = shopping.getComercio();
        this.producto = shopping.getProducto();
        this.numeroCompra = shopping.getNumeroCompra();
        this.sku = shopping.getSku();
        this.fechaCompra = shopping.getFechaCompra();
        this.cantidad = shopping.getCantidad();
        this.valor = shopping.getValor();
        this.numcuotas = shopping.getNumcuotas();
        this.valorTotal = shopping.getValorTotal();
        this.tipoTarjeta = shopping.getTipoTarjeta();
        this.type = shopping.getType();
        this.fechaEntrega = shopping.getFechaEntrega();
        this.direccionEntrega = shopping.getDireccionEntrega();
        this.retiradoPor = shopping.getRetiradoPor();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Number getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(Number numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public Number getSku() {
        return sku;
    }

    public void setSku(Number sku) {
        this.sku = sku;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Number getCantidad() {
        return cantidad;
    }

    public void setCantidad(Number cantidad) {
        this.cantidad = cantidad;
    }

    public Number getValor() {
        return valor;
    }

    public void setValor(Number valor) {
        this.valor = valor;
    }

    public Number getNumcuotas() {
        return numcuotas;
    }

    public void setNumcuotas(Number numcuotas) {
        this.numcuotas = numcuotas;
    }

    public Number getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Number valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getRetiradoPor() {
        return retiradoPor;
    }

    public void setRetiradoPor(String retiradoPor) {
        this.retiradoPor = retiradoPor;
    }
}
