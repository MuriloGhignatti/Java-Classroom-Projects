package Matematica;

public class Retangulo {
    private static float ladoH;
    private static float ladoV;

    public static void definir_lados(float h, float v){
        ladoH = h;
        ladoV = v;
        assert(ladoH >= 0 && ladoV >= 0);
    }
    public static float area(){
        return ladoH * ladoV;
    }

    public static float perimetro(){
        return 2 * ladoH + 2 * ladoV;
    }
}