package projeto_pagamento;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        FilaPagamentos fila = new FilaPagamentos();
        Conta conta = new Conta(500);

        // mostrar saldo inicial
        System.out.println("===== SISTEMA INICIADO =====");
        System.out.println("Saldo inicial: " + String.format("R$%.2f", conta.getSaldo()));
        System.out.println("============================\n");

        ProcessadorPagamentos t1 = new ProcessadorPagamentos(1, fila, conta);
        ProcessadorPagamentos t2 = new ProcessadorPagamentos(2, fila, conta);

        t1.start();
        t2.start();

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {

            double valor = (int)(Math.random() * 200);

            String tipo = random.nextBoolean() ? "PAGAMENTO" : "RECEBIMENTO";

            Pagamento p = new Pagamento(i, valor, tipo);

            fila.adicionar(p);

            System.out.println("Transação " + i + " (" + tipo + ") adicionada na fila");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}