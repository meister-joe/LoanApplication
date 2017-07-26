package com.joemanahan.meister.loanapplication;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CalculatorActivity extends AppCompatActivity {
    private EditText loanAmount,downPaymentAmount,etTerm,annualInterestRate;
    private TextView monthlyRepay,totalRepay,totalInter,avgMonthlyInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        loanAmount=(EditText) findViewById(R.id.loan_amount);
        downPaymentAmount=(EditText) findViewById(R.id.down_payment);
        etTerm=(EditText) findViewById(R.id.term);
        annualInterestRate=(EditText) findViewById(R.id.annual_interest_rate);

        monthlyRepay=(TextView) findViewById(R.id.monthly_repayment);
        totalRepay=(TextView) findViewById(R.id.total_repayment);
        totalInter=(TextView) findViewById(R.id.total_interest);
        avgMonthlyInterest=(TextView) findViewById(R.id.average_monthly_interest);
    }

    public void onClick(View v)
    {
        //Log.d("check","button clicked!");
        switch (v.getId())
        {
            case R.id.button_calculate:
                doCalculate();
                break;
            case R.id.button_reset:
                reset();
                break;
        }
    }

    private void reset()
    {
        loanAmount.setText("");
        downPaymentAmount.setText("");
        etTerm.setText("");
        annualInterestRate.setText("");

        monthlyRepay.setText(R.string.default_result);
        totalRepay.setText(R.string.default_result);
        totalInter.setText(R.string.default_result);
        avgMonthlyInterest.setText(R.string.default_result);

    }
    private void doCalculate()
    {
        String loanAmnt=loanAmount.getText().toString();
        String dpAmnt=downPaymentAmount.getText().toString();
        String interestRate=annualInterestRate.getText().toString();
        String term=etTerm.getText().toString();

        try {
            Double dblLoanAmnt = Double.valueOf(loanAmnt);
            Double dblDpAmnt = Double.valueOf(dpAmnt);
            Double dblInterestRate = Double.valueOf(interestRate) / 12 / 100;
            double dbNumOfMonth = (Integer.parseInt(term) * 12);
            Double dblActualLoanAmnt = dblLoanAmnt - dblDpAmnt;

            if (dblActualLoanAmnt < 0 || dbNumOfMonth < 1) {
                Toast.makeText(getBaseContext(), getString(R.string.term) + " should be greater than 0 and " + getString(R.string.loan_amount) + " should be greater than " + getString(R.string.down_payment), Toast.LENGTH_LONG).show();
            } else {
                double monthlyRepayment = dblActualLoanAmnt * (dblInterestRate + (dblInterestRate / (java.lang.Math.pow((1 + dblInterestRate), dbNumOfMonth) - 1)));

                double totalRepayment = monthlyRepayment * dbNumOfMonth;
                double totalInterest = totalRepayment - dblActualLoanAmnt;
                double monthlyInterest = totalInterest / dbNumOfMonth;

                monthlyRepay.setText(String.format("%.2f", monthlyRepayment));
                totalRepay.setText(String.format("%.2f", totalRepayment));
                totalInter.setText(String.format("%.2f", totalInterest));
                avgMonthlyInterest.setText(String.format("%.2f", monthlyInterest));
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getBaseContext(),"Please complete all fields.",Toast.LENGTH_LONG).show();
        }finally
        {

        }

//        Calendar cal=Calendar.getInstance();
//        Calendar cal2= Calendar.getInstance();
//        cal.set(2017,7,26);
//        cal2.set(2017,7,28);
//
//
//        Intent inten=new Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI)
//                .putExtra(CalendarContract.Events.TITLE,"Loan")
//                .putExtra(CalendarContract.Events.EVENT_LOCATION,"AmCorp Mall")
//                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,cal.getTimeInMillis())
//                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,cal2.getTimeInMillis());
    }
}
