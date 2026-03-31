package projeto_pagamento;

public class ProcessadorPagamentos extends Thread {

    private FilaPagamentos fila;
    private Conta conta;
    private int id;

    public ProcessadorPagamentos(int id, FilaPagamentos fila, Conta conta) {
        this.id = id;
        this.fila = fila;
        this.conta = conta;
    }

    @Override
    public void run() {
        while (true) {

            Pagamento p = fila.pegar();

            try {
                StringBuilder log = new StringBuilder();

                log.append("\n==============================\n");
                log.append("TRANSAÇÃO #").append(p.getId()).append("\n");
                log.append("Thread: ").append(id).append("\n");
                log.append("Tipo: ").append(p.getTipo()).append("\n");
                log.append("Valor: ").append(String.format("R$%.2f", p.getValor())).append("\n");

                log.append("Status: VALIDANDO...\n");
                Thread.sleep(2000);

                log.append("Status: PROCESSANDO...\n");
                Thread.sleep(3500);

                log.append("------------------------------\n");

                if (p.getTipo().equals("PAGAMENTO")) {

                    boolean aprovado = conta.debitar(p.getValor());

                    if (aprovado) {
                        log.append("✔ Pagamento aprovado\n");
                    } else {
                        log.append("✖ Pagamento negado\n");
                    }

                } else { // RECEBIMENTO

                    conta.creditar(p.getValor());
                    log.append("✔ Valor recebido com sucesso\n");
                }

                log.append("Saldo atual: ")
                   .append(String.format("R$%.2f", conta.getSaldo()))
                   .append("\n");

                log.append("==============================\n");

                synchronized (System.out) {
                    System.out.println(log.toString());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}