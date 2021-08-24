package com.mycoding.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import  org.mariuszgromada.math.mxparser.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b_clear, b_delete, bLBracket, bRBracket, bLog, bSquare, bMod, bAdd, bSub, bMup,
            bDiv, bDot, bEqual;
    TextView previous;
    EditText input;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0 = findViewById(R.id.btn_0);
        b1 = findViewById(R.id.btn_1);
        b2 = findViewById(R.id.btn_2);
        b3 = findViewById(R.id.btn_3);
        b4 = findViewById(R.id.btn_4);
        b5 = findViewById(R.id.btn_5);
        b6 = findViewById(R.id.btn_6);
        b7 = findViewById(R.id.btn_7);
        b8 = findViewById(R.id.btn_8);
        b9 = findViewById(R.id.btn_9);

        b_clear = findViewById(R.id.btn_clear);
        b_delete = findViewById(R.id.btn_delete);

        bLBracket = findViewById(R.id.btn_leftBracket);
        bRBracket = findViewById(R.id.btn_rightBracket);

        bLog = findViewById(R.id.btn_log);
        bSquare = findViewById(R.id.btn_square);

        bMod = findViewById(R.id.btn_mod);
        bAdd = findViewById(R.id.btn_add);
        bSub = findViewById(R.id.btn_sub);
        bDiv = findViewById(R.id.btn_div);
        bMup = findViewById(R.id.btn_mul);

        bDot = findViewById(R.id.btn_dot);
        bEqual = findViewById(R.id.btn_equal);

        previous = findViewById(R.id.previousExp);
        input = findViewById(R.id.screen);

        input.setShowSoftInputOnFocus(false);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("0");
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("1");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("2");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("3");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("4");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("5");
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("6");
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("7");
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("9");
            }
        });

        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                previous.setText("");
            }
        });

        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursorPosition = input.getSelectionStart();
                int textLength = input.getText().length();

                if (cursorPosition != 0 && textLength!=0){
                    SpannableStringBuilder select = (SpannableStringBuilder) input.getText();
                    select.replace(cursorPosition-1, cursorPosition, "");
                    input.setText(select);
                    input.setSelection(cursorPosition-1);
                }
            }
        });

        bLBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("(");
            }
        });

        bRBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput(")");
            }
        });

        bLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("ln(");
            }
        });

        bSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("^2");
            }
        });

        bMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("%");
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("+");
            }
        });

        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("-");
            }
        });

        bMup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("*");
            }
        });

        bDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput("/");
            }
        });

        bDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInput(".");
            }
        });

        bEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Exp = input.getText().toString();

                previous.setText(Exp);
                Exp = Exp.replaceAll("%", "/100");
                Expression expression = new Expression(Exp);
                String output = String.valueOf(expression.calculate());

                input.setText(output);
                input.setSelection(output.length());
            }
        });
    }

    private void updateInput(String str){
        String previousInput = input.getText().toString();

        int cursorPosition = input.getSelectionStart();
        String lefts = previousInput.substring(0, cursorPosition);
        String rights = previousInput.substring(cursorPosition);

        input.setText(String.format("%s%s%s", lefts, str, rights));
        input.setSelection(cursorPosition + str.length());
    }
}