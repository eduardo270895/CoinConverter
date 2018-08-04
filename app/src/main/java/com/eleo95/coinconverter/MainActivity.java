package com.eleo95.coinconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioGroup radGroup1;
    private RadioGroup radGroup2;
    private EditText tasaEurPeso, tasaUSPeso, tasaEurUs, amountInput;
    private TextView resultado;
    DecimalFormat numberFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.convert).setOnClickListener(this);
        tasaEurPeso = findViewById(R.id.EuroPeso);
        tasaUSPeso = findViewById(R.id.UsPeso);
        tasaEurUs = findViewById(R.id.EuroUs);
        amountInput =  findViewById(R.id.amountInput);
        radGroup1 = findViewById(R.id.radioGrupoFrom);
        radGroup2 = findViewById(R.id.radioGrupoTo);
        resultado = findViewById(R.id.resultMoney);
        findViewById(R.id.radioGrupoTo).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        RadioButton selector1 = findViewById(radGroup1.getCheckedRadioButtonId());
        RadioButton selector2 = findViewById(radGroup2.getCheckedRadioButtonId());

        switch (v.getId()){
            case R.id.convert:
                if (selector1 != null && selector2 != null && amountInput != null ) {
                    //Toast.makeText(this, "Selected: " + selector1.getText()+" y "+selector2.getText()+tasaEurPeso.getText()+tasaUSPeso.getText()+tasaEurUs.getText(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this,EurToPeso(amountInput.getText().toString()),Toast.LENGTH_LONG).show();
                    if(selector1.getText().equals("Euro") && selector2.getText().equals("Dolar") && !tasaEurUs.getText().toString().matches("")){
                        resultado.setText(EurToUs(amountInput.getText().toString()));
                    }
                    if(selector1.getText().equals("Euro") && selector2.getText().equals("Peso")&& !tasaEurPeso.getText().toString().matches("")){
                        resultado.setText(EurToPeso(amountInput.getText().toString()));
                    }
                    if(selector1.getText().equals("Dolar") && selector2.getText().equals("Euro")&& !tasaEurUs.getText().toString().matches("")){
                        resultado.setText(UsToEur(amountInput.getText().toString()));
                    }
                    if(selector1.getText().equals("Dolar") && selector2.getText().equals("Peso")&& !tasaUSPeso.getText().toString().matches("")){
                        resultado.setText(UstoPeso(amountInput.getText().toString()));
                    }
                    if(selector1.getText().equals("Peso") && selector2.getText().equals("Dolar")&& !tasaUSPeso.getText().toString().matches("")){
                        resultado.setText(PesoToUs(amountInput.getText().toString()));
                    }
                    if(selector1.getText().equals("Peso") && selector2.getText().equals("Euro")&& !tasaEurPeso.getText().toString().matches("")){
                        resultado.setText(PesoToEur(amountInput.getText().toString()));
                    }
                    if(selector1.getText() == selector2.getText()){
                        Toast.makeText(this, "Equal currency", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Check the fields", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.clear:
                amountInput.setText("");
                tasaEurPeso.setText("");
                tasaUSPeso.setText("");
                tasaEurUs.setText("");
                radGroup1.clearCheck();
                radGroup2.clearCheck();
                resultado.setText("");
                break;
        }
    }


    public String EurToPeso(String amount){
        Double result = (Double.parseDouble(amount))*Double.parseDouble(tasaEurPeso.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }

    public String EurToUs(String amount){
        Double result = (Double.parseDouble(amount))*Double.parseDouble(tasaEurUs.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }
    public String UstoPeso(String amount){
        Double result = (Double.parseDouble(amount))*Double.parseDouble(tasaUSPeso.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }
    public String UsToEur(String amount){
        Double result = (Double.parseDouble(amount))/Double.parseDouble(tasaEurUs.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }
    public String PesoToUs(String amount){
        Double result = (Double.parseDouble(amount))/Double.parseDouble(tasaUSPeso.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }
    public String PesoToEur(String amount){
        Double result = (Double.parseDouble(amount))/Double.parseDouble(tasaEurPeso.getText().toString());
        numberFormat = new DecimalFormat("$#,###.00");
        return numberFormat.format(result);
    }
}
