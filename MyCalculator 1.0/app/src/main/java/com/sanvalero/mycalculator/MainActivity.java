package com.sanvalero.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean coma = false;       // Se vuelve TRUE cuando se escribe una coma para no repetir comas
    private boolean escribiendo = true; // Es FALSE solo cuando se pulsa un operador para que no se repita
    private float resultado = 0;        // Guarda los resultados de las operaciones
    private float resultadoMemoria = 0; // Variable para guardar cifras en la memoria de la calculadora
    private String operador = "";       // Guarda en un String el operador pulsado para contar con ello


    //                      ///// TECLAS /////

    public void teclaMas(View view) {
        operacion("+");
    }

    public void teclaMenos(View view) {
        operacion("-");
    }

    public void teclaMultiplicacion(View view) {
        operacion("*");
    }

    public void teclaDivision(View view) {
        operacion("/");
    }

    public void teclaIgual(View view) {
        operacion("=");
    }

    public void tecla0(View view) {
        añadirDigito("0");
    }

    public void tecla1(View view) {
        añadirDigito("1");
    }

    public void tecla2(View view) {
        añadirDigito("2");
    }

    public void tecla3(View view) {
        añadirDigito("3");
    }

    public void tecla4(View view) {
        añadirDigito("4");
    }

    public void tecla5(View view) {
        añadirDigito("5");
    }

    public void tecla6(View view) {
        añadirDigito("6");
    }

    public void tecla7(View view) {
        añadirDigito("7");
    }

    public void tecla8(View view) {
        añadirDigito("8");
    }

    public void tecla9(View view) {
        añadirDigito("9");
    }

    public void tecla00(View view) {
        añadirDigito("00");
    }

    /**
     * Si  coma(BOOLEAN) es FALSE, escribe una coma y cambia coma(BOOLEAN) a TRUE
     */
    public void teclaComa(View view) {
        if (!coma) {
            TextView textViewCal = findViewById(R.id.textView);
            String screen = textViewCal.getText().toString();
            screen += ",";
            coma = true;
            textViewCal.setText(screen);
        }
    }

    /**
     * Pone a 0 la pantalla y resetea las variables coma, resultado, operador y escribiendo
     */
    public void teclaC(View view) {
        TextView textViewCal = findViewById(R.id.textView);
        textViewCal.setText("0");

        coma = false;
        resultado = 0;
        operador = "";
        escribiendo = true;
    }

    /**
     * NO SIRVE PARA NADA
     */
    public void teclaMasMenos(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Paga la versión completa para esta funcionalidad, RUIN!!!", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * NO SIRVE PARA NADA
     */
    public void teclaPorcentaje(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Paga la versión completa para esta funcionalidad, RUIN!!!", Toast.LENGTH_LONG);
        toast.show();
    }

    public void teclaMc(View view) {
        memoria("mc");
    }

    public void teclaMMas(View view) {
        memoria("m+");
    }

    public void teclaMMenos(View view) {
        memoria("m-");
    }

    public void teclaMr(View view) {
        memoria("mr");
    }


//---------------------------------------------------------------------------
//-----------------------------MÉTODOS---------------------------------------
//---------------------------------------------------------------------------

    /**
     * Lee la pantalla de la calculadora y añade un digito a la pantalla, excepto
     * si hay un 0 que lo sustituye por el nuevo.
     *
     * @param digitoTecla digito de la tecla pulsada
     */
    public void añadirDigito(String digitoTecla) {
        TextView textViewCal = findViewById(R.id.textView);
        String numberoPantalla = textViewCal.getText().toString();

        if (numberoPantalla.equals("0") || !escribiendo) {
            numberoPantalla = digitoTecla;
            coma = false;
        } else if (operador.equals("=")) {
            numberoPantalla = digitoTecla;
            operador = "";
            coma = false;
        } else {
            numberoPantalla += digitoTecla;
        }

        escribiendo = true;
        textViewCal.setText(numberoPantalla);
    }


    /**
     * Lee la pantalla, cambia las comas por puntos, luego de String a float
     *
     * @return devuelve el numero formato FLOAT para operar con él
     */
    public float leerPantalla() {
        TextView textViewCal = findViewById(R.id.textView);
        String numeroStr = textViewCal.getText().toString().replace(',', '.');

        return Float.valueOf(numeroStr);
    }

    /**
     * Si su decimal es ".0" convierte el numero a entero,
     * cambia de FLOAT o INT a String, despues los puntos por comas y escribe en pantalla
     *
     * @param number Recibe el numero en formato FLOAT para imprimir por pantalla
     */
    public void escribirPantalla(float number) {

        String newNumber = "";

        if ((int) Math.round(number) == number && !coma) {
            newNumber = String.valueOf((int) number);
        } else {
            newNumber = String.valueOf(number);
        }


        TextView textViewCal = findViewById(R.id.textView);
        textViewCal.setText(newNumber.replace('.', ','));

    }

    public void operacion(String operadorTecla) {

        float numeroPantalla = leerPantalla();

        if (operador.equals("") && !operadorTecla.equals("=")) {
            resultado = numeroPantalla;
        } else if (escribiendo || operadorTecla.equals("=")) {

            switch (operador) {

                case "+":
                    resultado += numeroPantalla;
                    break;
                case "-":
                    resultado -= numeroPantalla;
                    break;
                case "*":
                    resultado *= numeroPantalla;
                    break;
                case "/":
                    if (numeroPantalla == 0) {
                        Toast toast4 = Toast.makeText(getApplicationContext(), "NO SE PUEDE DIVIDIR ENTRE 0!!!", Toast.LENGTH_LONG);
                        toast4.show();
                    } else {
                        resultado /= numeroPantalla;
                    }
                    break;
                case "=":
                    resultado = numeroPantalla;
                    break;
            }
        }

        // Si se pulsa "=" termina la operacion, el operador se reinicia a "" y escribiendo es TRUE,
        // si no el operadorTecla pasa a ser el nuevo operador guardado en memoria  y escribiendo es FALSE
        // para que no se repita la operacion si se vuelve a pulsar un operando
        if (operadorTecla.equals("=")) {
            operador = "=";
            escribiendo = true;
        } else {
            operador = operadorTecla;
            escribiendo = false;
        }

        escribirPantalla(resultado);
    }

    public void memoria(String teclaMemoria) {
        Button botonMr = findViewById(R.id.button4);
        switch (teclaMemoria) {

            case "mc":
                resultadoMemoria = 0;
                botonMr.setTextColor(Color.parseColor("#000000"));
                break;
            case "m+":
                resultadoMemoria += leerPantalla();
                botonMr.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case "m-":
                resultadoMemoria -= leerPantalla();
                botonMr.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case "mr":
                coma = false;   // Evita que salgan decimales en el 0
                escribirPantalla(resultadoMemoria);
                break;
        }
        escribiendo = false;
    }

}
