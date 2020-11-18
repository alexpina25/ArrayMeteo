package arraymeteo;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
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
        System.out.printf(" Lluvia caída en el mes %s de %d:\n", fechaHoy.getMonth().getDisplayName(TextStyle.FULL, miPais), anio);
        char[] letraSem = {'L', 'M', 'X', 'J', 'V', 'S', 'D'};
        for (char letra : letraSem) {
            System.out.print(" " + letra + " ");
        }
        if (resto > 0) {
            numFilas++; // Para acomodar días extra en otra fila
        }
        if (diaInicio > 5 && numDiasMes == 31 || diaInicio > 6 && (numDiasMes == 30 || numDiasMes == 29)) {
            numFilas++; // Si el mes comienza al final de la semana aún se necesita otra fila
        }
        System.out.println(" Media");
        ////////////////////////////////////////////////////////////////////////
        int contador = 1; // Primer día del mes a imprimir

    }
}
