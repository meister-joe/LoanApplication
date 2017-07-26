package com.joemanahan.meister.loanapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText loanAmount;
    EditText downPaymentAmount;
    TextView monthlyRepay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        loanAmount=(EditText) findViewById(R.id.loan_amount);
        downPaymentAmount=(EditText) findViewById(R.id.down_payment);
        monthlyRepay=(TextView) findViewById(R.id.monthly_repayment);
    }

    public void doCalculate(View v)
    {
        String loanAmnt=loanAmount.getText().toString();
        String dpAmnt=downPaymentAmount.getText().toString();

        Double dblLoanAmnt=Double.valueOf(loanAmnt);
        Double dblDpAmnt=Double.valueOf(dpAmnt);

        Double dblActualLoanAmnt=dblLoanAmnt-dblDpAmnt;

        if (dblActualLoanAmnt<0)
        {
            Toast.makeText(getBaseContext(),getString(R.string.loan_amount) + " should be greater than " + getString(R.string.down_payment),Toast.LENGTH_LONG).show();
        }
        else
        {
            monthlyRepay.setText(String.format(dblActualLoanAmnt.toString()));
        }
    }
}
