package projeto_pagamento;

public class Conta {
    private double saldo;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // débito (pagamento)
    public synchronized boolean debitar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    // crédito (recebimento)
    public synchronized void creditar(double valor) {
        saldo += valor;
    }

    public synchronized double getSaldo() {
        return saldo;
    }
}