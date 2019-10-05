package Projeto3;

public class Cliente {
    private String nome;
    private ContaCorrente conta_corrente;
    public Cliente(String nome){
        this.nome = nome;
    }
    public void ligar(ContaCorrente conta_corrente){
        this.conta_corrente = conta_corrente;
    }
    public void depositar(double valor){
        this.conta_corrente.depositar(valor);

    }
    public void retirar(double valor){
        this.conta_corrente.retirar(valor);
    }
    public void imprimir(){
        System.out.println(nome);
        this.conta_corrente.imprimir();
    }
}
