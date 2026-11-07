
package mx.edu.tesoem.isc.examen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity
        implements ControlFragment.Listener {

    private ProgressBar progressBar;
    private TextView txtEstado;
    private Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        progressBar = findViewById(R.id.progressBar);
        txtEstado = findViewById(R.id.txtEstado);
        btnSolicitar = findViewById(R.id.btnSolicitar);

        // Ocultar elementos al iniciar
        progressBar.setVisibility(View.GONE);

        findViewById(R.id.contenedorFragment)
                .setVisibility(View.GONE);

        // Botón para solicitar datos
        btnSolicitar.setOnClickListener(v -> {

            btnSolicitar.setEnabled(false);

            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);

            txtEstado.setText("Cargando datos...");

            new CargarDatosTask().execute();
        });
    }

    // PROCESAMIENTO EN SEGUNDO PLANO
    private class CargarDatosTask
            extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            for (int i = 0; i <= 100; i += 10) {

                try {
                    Thread.sleep(300);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                    return false;
                }

                publishProgress(i);
            }

            return true;
        }

        // ACTUALIZAR BARRA DE PROGRESO
        @Override
        protected void onProgressUpdate(Integer... values) {

            progressBar.setProgress(values[0]);

            txtEstado.setText(
                    "Cargando datos... " + values[0] + "%"
            );
        }

        // FINALIZAR CARGA DE DATOS
        @Override
        protected void onPostExecute(Boolean correcto) {

            progressBar.setVisibility(View.GONE);

            if (correcto) {

                // Ocultar pantalla de solicitud
                findViewById(R.id.panelSolicitud)
                        .setVisibility(View.GONE);

                // Mostrar pantalla de categorías
                findViewById(R.id.contenedorFragment)
                        .setVisibility(View.VISIBLE);

                ControlFragment control =
                        new ControlFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.contenedorFragment,
                                control
                        )
                        .commit();

            } else {

                btnSolicitar.setEnabled(true);
                txtEstado.setText("Error al cargar datos");
            }
        }
    }

    // COMUNICACIÓN CON CONTROLFRAGMENT
    @Override
    public void iniciarProceso(String categoria) {

        // Buscar el ControlFragment que está en pantalla
        ControlFragment control =
                (ControlFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.contenedorFragment);

        // Mostrar el resultado dentro del ControlFragment
        if (control != null) {

            control.mostrarResultado(categoria);
        }
    }
}
