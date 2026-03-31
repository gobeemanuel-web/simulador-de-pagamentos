package projeto_pagamento;

// Classe que representa uma transação (pode ser pagamento ou recebimento)
public class Pagamento {
    private int id;
    private double valor;
    private String tipo; // "PAGAMENTO" ou "RECEBIMENTO"

    public Pagamento(int id, double valor, String tipo) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
}