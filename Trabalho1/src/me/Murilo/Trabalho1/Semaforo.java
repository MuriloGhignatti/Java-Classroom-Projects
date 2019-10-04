package me.Murilo.Trabalho1;

public class Semaforo {
   public static int cor = 2;

   public static void MudaProximaCor(){
        switch(cor){
            case 0:
                cor = 1;
                break;
            case 1:
                cor = 2;
                break;
            case 2:
                cor = 0;
                break;
        }
    }

    public static int MudaCorFixa(int cor){
       return Semaforo.cor = cor;
    }

    public static int MostraCor(){
       return cor;
    }
}
