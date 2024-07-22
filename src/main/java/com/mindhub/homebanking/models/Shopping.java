package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Number idCliente;
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

    public Shopping(String comercio, String producto, Number numeroCompra, Number sku, Date fechaCompra, Number cantidad, Number valor, Number numcuotas, Number valorTotal, String tipoTarjeta,AddressType type, String fechaEntrega, String direccionEntrega, String retiradoPor) {
        this.comercio = comercio;
        this.producto = producto;
        this.numeroCompra = numeroCompra;
        this.sku = sku;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
        this.valor = valor;
        this.numcuotas = numcuotas;
        this.valorTotal = valorTotal;
        this.tipoTarjeta = tipoTarjeta;
        this.type = type;
        this.fechaEntrega = fechaEntrega;
        this.direccionEntrega = direccionEntrega;
        this.retiradoPor = retiradoPor;
    }

    public Shopping() {
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

    @Override
    public String toString() {
        return "Shopping{" +
                "comercio='" + comercio + '\'' +
                ", producto='" + producto + '\'' +
                ", numeroCompra=" + numeroCompra +
                ", sku=" + sku +
                ", fechaCompra=" + fechaCompra +
                ", cantidad=" + cantidad +
                ", valor=" + valor +
                ", numcuotas=" + numcuotas +
                ", valorTotal=" + valorTotal +
                ", tipoTarjeta='" + tipoTarjeta + '\'' +
                ", type=" + type +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", retiradoPor='" + retiradoPor + '\'' +
                '}';
    }
}
