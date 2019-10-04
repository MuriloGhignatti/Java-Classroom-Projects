package Projeto3;

public class Banco {
    public static void main(String[] args) {
        Cliente jandira = new Cliente("Jandira Silva");
        Cliente sandra = new Cliente("Sandra Rodrigues");
        Cliente luciana = new Cliente("Luciana Teixeira");
        ContaCorrente cJandira = new ContaCorrente(84037, 2500);
        ContaCorrente cSandra = new ContaCorrente(70662, 1800);
        ContaCorrente cLuciana = new ContaCorrente(20718, 5000);
        jandira.ligar(cJandira);
        sandra.ligar(cSandra);
        luciana.ligar(cLuciana);
        jandira.imprimir();
        sandra.imprimir();
        luciana.imprimir();
        jandira.retirar(1000);
        jandira.imprimir();
        sandra.retirar(2000);
        sandra.depositar(500);
        sandra.imprimir();
        sandra.retirar(2000);
        sandra.imprimir();
        luciana.depositar(1000);
        luciana.imprimir();


    }
}
