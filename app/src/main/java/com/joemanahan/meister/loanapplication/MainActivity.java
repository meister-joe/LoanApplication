package com.joemanahan.meister.loanapplication;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

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

        Calendar cal=Calendar.getInstance();
        Calendar cal2= Calendar.getInstance();
        cal.set(2017,7,26);
        cal2.set(2017,7,28);


        Intent inten=new Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE,"Loan")
                .putExtra(CalendarContract.Events.EVENT_LOCATION,"CBJ")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal2.getTimeInMillis());
    }
}
