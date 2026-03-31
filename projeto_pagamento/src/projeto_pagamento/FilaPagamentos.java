package projeto_pagamento;

import java.util.LinkedList;
import java.util.Queue;

// Classe que representa a fila de pagamentos
public class FilaPagamentos {

    // Estrutura de fila (FIFO)
    private Queue<Pagamento> fila = new LinkedList<>();

    // Método para adicionar um pagamento na fila
    public synchronized void adicionar(Pagamento p) {
        fila.add(p);

        // Avisa as threads que estavam esperando que chegou um novo pagamento
        notify();
    }

    // Método para pegar um pagamento da fila
    public synchronized Pagamento pegar() {

        // Se a fila estiver vazia, a thread espera
        while (fila.isEmpty()) {
            try {
                wait(); // pausa até chegar pagamento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Retorna o próximo pagamento da fila
        return fila.poll();
    }
}