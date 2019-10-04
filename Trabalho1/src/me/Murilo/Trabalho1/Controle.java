package me.Murilo.Trabalho1;

public class Controle {
    public static void main(String[] args) {

        System.out.println(cores(Semaforo.MostraCor())); //Não Presente No Trabalho Enviado.

        for (int i = 0; i < 10; i++) {

            Semaforo.MudaProximaCor();
            System.out.println(cores(Semaforo.MostraCor()));
        }
        Semaforo.MudaCorFixa(1);
        System.out.println(cores(Semaforo.MostraCor()));
    }

    public static String cores(int cor) {
        String cores = "";
        switch (cor) {
            case 0:
                cores = "Verde";
            break;
            case 1:
                cores = "Amarelo";
            break;
            case 2:
                cores = "Vermelho";
            break;
        }
        return cores;
    }
}
