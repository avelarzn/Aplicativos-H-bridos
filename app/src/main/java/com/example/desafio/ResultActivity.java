package com.example.desafio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ResultActivity extends Activity {
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Assuming your date is in this format

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        TextView valueInitialTextView = findViewById(R.id.value_initial);
        TextView valueEarningsTextView = findViewById(R.id.value_earnings);
        TextView moneyTextView = findViewById(R.id.value_money);
        TextView dateTextView = findViewById(R.id.value_date);
        TextView percentageTextView = findViewById(R.id.value_percentage);
        TextView brutoInvestTextView = findViewById(R.id.value_bruto_invest);
        TextView irTextView = findViewById(R.id.value_ir);
        TextView rescueDateTextView = findViewById(R.id.value_rescue_date);
        TextView elapsedDaysTextView = findViewById(R.id.value_elapsed_days);
        TextView monthlyEarningsTextView = findViewById(R.id.value_monthly_earnings);
        TextView annualProfitabilityTextView = findViewById(R.id.value_annual_profitability);
        TextView periodProfitabilityTextView = findViewById(R.id.value_period_profitability);
        Button button = findViewById(R.id.button2);

        Intent intent = getIntent();
        String moneyStr = intent.getStringExtra("money");
        String date = intent.getStringExtra("date");
        String percentageStr = intent.getStringExtra("percentage");

        double principal = Double.parseDouble(moneyStr.replace(",", ""));
        double rate = Double.parseDouble(percentageStr) / 100;
        int days = 365;

        double earnings = principal * rate * days / 365;
        double brutoInvest = principal + earnings;
        double ir = earnings * 0.15;
        double monthlyEarnings = (rate / 12) * 100;
        double annualProfitability = rate * 100;
        double periodProfitability = (earnings / principal) * 100;

        valueInitialTextView.setText("R$ " + decimalFormat.format(principal));
        valueEarningsTextView.setText("Rendimento total de R$ " + decimalFormat.format(earnings));
        moneyTextView.setText("R$ " + decimalFormat.format(principal));
        dateTextView.setText(date);
        percentageTextView.setText(percentageStr + "%");
        brutoInvestTextView.setText("R$ " + decimalFormat.format(brutoInvest));
        irTextView.setText("R$ " + decimalFormat.format(ir));
        rescueDateTextView.setText(date);
        elapsedDaysTextView.setText(String.valueOf(days));
        monthlyEarningsTextView.setText(decimalFormat.format(monthlyEarnings) + "%");

        long elapsedDays = calculateElapsedDays(date);
        elapsedDaysTextView.setText(String.valueOf(elapsedDays));

        monthlyEarningsTextView.setText(decimalFormat.format(monthlyEarnings) + "%" );
        annualProfitabilityTextView.setText(decimalFormat.format(annualProfitability) + "%");
        periodProfitabilityTextView.setText(decimalFormat.format(periodProfitability) + "%");


        button.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
        });
    }

    private long calculateElapsedDays(String inputDate) {
        try {
            Date date = dateFormat.parse(inputDate);
            Date currentDate = Calendar.getInstance().getTime();

            long diffInMillis = currentDate.getTime() - date.getTime();
            return Math.abs(TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
