package ArrayMeteo;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * @author Alejandro Pina
 */
public class ArrayMeteo {

    public static void main(String[] args) {
        Locale miPais = new Locale("es", "ES");
        LocalDate fechaHoy = LocalDate.now();  // Obtengo la fecha actual

        int mes = fechaHoy.getMonth().getValue() - 1; // Nos posicionamos en el mes anterior
        int anio = fechaHoy.getYear(); // Obtenemos año actual

        fechaHoy = LocalDate.of(anio, mes, 1); // La fecha de trabajo será el día 1 del mes anterior al actual

        int numDiasMes = fechaHoy.lengthOfMonth(); // Averiguamos cuántos días tiene el mes (28, 29, 30 ó 31)
        int numFilas = numDiasMes / 7; // 7 días cada semana. Al menos los meses tienen 4 filas
        int resto = numDiasMes % 7; // A las que pueden añadirse más...
        int diaInicio = fechaHoy.getDayOfWeek().getValue(); // Devuelve 1 a 7

        //////////////////////// Acomodando la tabla////////////////////////////
        System.out.printf("\t\t Lluvia caída en el mes %s de %d:\n", fechaHoy.getMonth().getDisplayName(TextStyle.FULL, miPais), anio);
        char[] letraSem = {'L', 'M', 'X', 'J', 'V', 'S', 'D'};
        for (char letra : letraSem) {
            System.out.print("     " + letra + "    ");
        }
        if (resto > 0) {
            numFilas++; // Para acomodar días extra en otra fila
        }
        if (diaInicio > 5 && numDiasMes == 31 || diaInicio > 6 && (numDiasMes == 30 || numDiasMes == 29)) {
            numFilas++; // Si el mes comienza al final de la semana aún se necesita otra fila
        }
        System.out.print("    \033[31mMedia");
        System.out.println();
        ////////////////////////////////////////////////////////////////////////
        int contador = 1; // Primer día del mes a imprimir

        for (int i = 0; i < diaInicio - 1; i++) {
            System.out.print("          ");
        }

        ArrayList<Integer> dias = new ArrayList();
        dias.add(0, 0);
        int mediaSem = 0;

        while (contador != numDiasMes) {
            int lluvia = (int) Math.floor(Math.random() * 100);
            dias.add(contador, lluvia);
            System.out.printf("    \033[30m%2d", contador);
            System.out.printf("\033[34m(%2d)", lluvia);
            dias.set(0, dias.get(0) + lluvia);
            if ((contador + diaInicio - 1) % 7 == 0) {
                System.out.println();
            }
            contador++;
        }
        
        System.out.println("");
        System.out.println("\t\tTotal litros caídos en " + fechaHoy.getMonth().getDisplayName(TextStyle.FULL, miPais) + ": " + dias.get(0) + " litros");
        dias.set(0, 0);
        System.out.println("\t\tDía más lluvioso: " + dias.indexOf(Collections.max(dias)) + " = " + Collections.max(dias) + " litros");
    }
}
