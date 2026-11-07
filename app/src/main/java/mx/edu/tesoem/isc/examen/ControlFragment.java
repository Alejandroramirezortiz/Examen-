
package mx.edu.tesoem.isc.examen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    public interface Listener {
        void iniciarProceso(String categoria);
    }

    private Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Listener) {
            listener = (Listener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_control, container, false);

        Spinner spinner = view.findViewById(R.id.spinnerCategorias);
        Button boton = view.findViewById(R.id.btnIniciar);

        String[] categorias = {
                "PRODUCTOS DE OFICINA",
                "BASE DE DATOS",
                "RED",
                "SINCRONIZACIÓN",
                "REPORTES"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                categorias
        );

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        boton.setOnClickListener(v -> {
            String categoria = spinner.getSelectedItem().toString();

            if (listener != null) {
                listener.iniciarProceso(categoria);
            }
        });

        return view;
    }

    // Mostrar el detalle sin quitar el Spinner
    public void mostrarResultado(String categoria) {

        ResultFragment fragment =
                ResultFragment.newInstance(categoria);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorDetalle, fragment)
                .commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
