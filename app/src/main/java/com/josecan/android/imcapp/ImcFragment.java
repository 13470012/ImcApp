package com.josecan.android.imcapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by jose.can on 26/10/2017.
 */

public class ImcFragment extends Fragment {
    EditText mCampoEstatura;
    EditText mCampoPeso;
    Button mBotonCalcular;
    Button mBotonLimpiar;
    TextView mEtiquetaImc;
    TextView mEtiquetaClasificacion;
    public ImcFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_imc, container, false);
        mCampoEstatura = (EditText) v.findViewById(R.id.campo_estatura);
        mCampoPeso = (EditText) v.findViewById(R.id.campo_peso);
        mBotonCalcular = (Button) v.findViewById(R.id.boton_calcular);
        mBotonLimpiar = (Button) v.findViewById(R.id.boton_limpiar);
        mEtiquetaImc = (TextView) v.findViewById(R.id.etiqueta_imc);
        mEtiquetaClasificacion = (TextView) v.findViewById(R.id.etiqueta_clasificacion);

        mBotonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPeso, strEstatura;
                Double peso = null, estatura = null;
                try{
                    strEstatura = mCampoEstatura.getText().toString();
                    estatura = Double.parseDouble(strEstatura);
                }
                catch (Exception $e)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Estatura: " + getResources().getString(R.string.valor_invalido), Toast.LENGTH_SHORT).show();
                    mCampoEstatura.setText("");
                }
                try{
                    strPeso = mCampoPeso.getText().toString();
                    peso = Double.parseDouble(strPeso);
                }
                catch (Exception $e)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Peso: " + getResources().getString(R.string.valor_invalido), Toast.LENGTH_SHORT).show();
                    mCampoPeso.setText("");
                }
                if (estatura != null && peso != null){
                    double imc = peso / (double)Math.pow(estatura, 2);
                    mEtiquetaImc.setText(new DecimalFormat("0.00").format(imc));
                    String clasificacion;
                    if (imc >= 40) {
                        clasificacion = getResources().getString(R.string.obesidad_extrema);
                    } else if(imc >= 30) {
                        clasificacion = getResources().getString(R.string.obesidad);
                    } else if(imc >= 25) {
                        clasificacion = getResources().getString(R.string.sobrepeso);
                    } else if(imc > 18.5) {
                        clasificacion = getResources().getString(R.string.peso_normal);
                    } else {
                        clasificacion = getResources().getString(R.string.peso_bajo);
                    }
                    mEtiquetaClasificacion.setText(clasificacion);
                } else {
                    mEtiquetaImc.setText("0.0");
                    mEtiquetaClasificacion.setText(getResources().getString(R.string.clasificacion_indefinida));
                }
            }
        });
        mBotonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCampoEstatura.setText("");
                mCampoPeso.setText("");
                mEtiquetaImc.setText("0.0");
                mEtiquetaClasificacion.setText("");
                mCampoEstatura.requestFocus();
            }
        });
        return  v;
    }
}
