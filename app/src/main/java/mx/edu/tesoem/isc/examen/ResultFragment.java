
package mx.edu.tesoem.isc.examen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private static final String ARG_CATEGORIA = "categoria";

    public static ResultFragment newInstance(String categoria) {

        ResultFragment fragment = new ResultFragment();

        Bundle datos = new Bundle();
        datos.putString(ARG_CATEGORIA, categoria);

        fragment.setArguments(datos);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LinearLayout principal = new LinearLayout(requireContext());
        principal.setOrientation(LinearLayout.VERTICAL);
        principal.setPadding(12, 12, 12, 12);
        principal.setBackgroundColor(Color.WHITE);

        TextView titulo = new TextView(requireContext());
        titulo.setText("FRAGMENTO DE DETALLE");
        titulo.setTextSize(17);
        titulo.setTextColor(Color.rgb(35, 65, 130));
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(8, 12, 8, 12);
        titulo.setBackgroundColor(Color.rgb(235, 240, 248));

        principal.addView(titulo);

        String categoria = getArguments() != null
                ? getArguments().getString(ARG_CATEGORIA)
                : "PRODUCTOS DE OFICINA";

        TextView seleccion = new TextView(requireContext());
        seleccion.setText("Categoría: " + categoria);
        seleccion.setTextSize(14);
        seleccion.setPadding(8, 12, 8, 12);

        principal.addView(seleccion);

        if (categoria.equals("PRODUCTOS DE OFICINA")) {

            String[][] productos = {
                    {"✎  Bolígrafo Azul", "$1.50"},
                    {"▦  Cuaderno A4", "$4.00"},
                    {"▥  Marcadores Color", "$12.00"},
                    {"✎  Marcadores A", "$4.00"},
                    {"✏  Cuaderno A2", "$4.00"},
                    {"▏  Cuaderno Bolígrafo Azul", "$11.50"}
            };

            for (String[] producto : productos) {

                LinearLayout fila = new LinearLayout(requireContext());
                fila.setOrientation(LinearLayout.HORIZONTAL);
                fila.setGravity(Gravity.CENTER_VERTICAL);
                fila.setPadding(10, 14, 10, 14);
                fila.setBackgroundColor(Color.rgb(248, 249, 252));

                TextView nombre = new TextView(requireContext());
                nombre.setText(producto[0]);
                nombre.setTextSize(14);
                nombre.setTextColor(Color.DKGRAY);
                nombre.setLayoutParams(new LinearLayout.LayoutParams(
                        0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

                TextView precio = new TextView(requireContext());
                precio.setText(producto[1]);
                precio.setTextSize(14);
                precio.setTextColor(Color.rgb(30, 90, 150));

                fila.addView(nombre);
                fila.addView(precio);

                principal.addView(fila);

                View separador = new View(requireContext());
                separador.setBackgroundColor(Color.LTGRAY);
                principal.addView(separador,
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, 1));
            }

        } else {

            TextView mensaje = new TextView(requireContext());
            mensaje.setText("Datos procesados de la categoría: " + categoria);
            mensaje.setTextSize(15);
            mensaje.setPadding(10, 20, 10, 20);

            principal.addView(mensaje);
        }

        return principal;
    }
}
