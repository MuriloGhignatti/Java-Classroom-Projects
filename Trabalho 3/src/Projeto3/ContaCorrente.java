package Projeto3;

public class ContaCorrente {
    private int numero;
    private double saldo;
    public ContaCorrente(int numero, double saldo){
        assert saldo > 0;
        this.numero = numero;
        this.saldo = saldo;

    }
    public void depositar(double valor){
        assert valor > 0;
        saldo = saldo + valor;


    }
    public void retirar(double valor){
        assert valor <= saldo;
        saldo = saldo - valor;
    }
    public void imprimir(){
        System.out.println(numero);
        System.out.println(saldo);
    }
}
