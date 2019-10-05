package Especialista;

import Matematica.Retangulo;

public class EngenheiroCivil {

    public static void main(String[] args) {
        exibir_dados_pessoais();
        Retangulo.definir_lados(4F,5F);
        System.out.println(Retangulo.area());
        System.out.println(Retangulo.perimetro());
    }

    private static String nome = "Oscar Niemeyer";
    private static int idade = 104;

    private static void exibir_dados_pessoais()
    {
        System.out.println( nome );
        System.out.println( idade + " anos");
    }
}
