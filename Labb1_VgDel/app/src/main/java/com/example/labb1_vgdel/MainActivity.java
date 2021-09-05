package com.example.labb1_vgdel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1, num2;
    private TextView result;
    private TextView operator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButtonListener();
    }

    public void addButtonListener(){
        Button plusBtn = findViewById(R.id.plusBtn);
        Button minusBtn = findViewById(R.id.minusBtn);
        Button multiplyBtn = findViewById(R.id.multiplyBtn);
        Button divideBtn = findViewById(R.id.divideBtn);
        Button clearBtn = findViewById(R.id.clearBtn);
        num1 = findViewById(R.id.num1);
        num2 =  findViewById(R.id.num2);
        result = findViewById(R.id.resultView);
        operator = findViewById(R.id.OperatorTxt);

        clearBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView txtView;
        switch (view.getId()){

            case R.id.plusBtn:
                int result1 = (int) calculate(1);
                result.setText(String.valueOf(result1));
                operator.setText("+");
                break;

            case R.id.minusBtn:
                int result2 = (int) calculate(2);
                result.setText(String.valueOf(result2));
                operator.setText("-");
                break;

            case R.id.divideBtn:
                float result3 = calculate(3);
                result.setText(String.valueOf(result3));
                operator.setText("/");
                break;

            case R.id.multiplyBtn:
                int result4 = (int) calculate(4);
                result.setText(String.valueOf(result4));
                operator.setText("*");
                break;

            case R.id.clearBtn:
                result.setText("Result");
                operator.setText("Operator");
                num1.setText("");
                num2.setText("");
                break;
            default:
                break;
        }
    }

    public float calculate(int operator){

        try{
            String value1 = num1.getText().toString();
            String value2 = num2.getText().toString();
            int a = Integer.parseInt(value1);
            int b = Integer.parseInt(value2);

            if(operator == 1){
                int result1 = a+b;
                return (int)result1;
            }
            if(operator == 2){
                int result2 = a-b;
                return result2;
            }
            if(operator == 3){
                float c = Float.parseFloat(value1);
                float d = Float.parseFloat(value2);
                float result3 = c/d;
                return result3;
            }
            if(operator == 4){
                int result4 = a*b;
                return result4;
            }

        }catch (NumberFormatException e){
            Toast toast=Toast.makeText(getApplicationContext(),"Error: "+""+e.toString(),Toast.LENGTH_SHORT);
            toast.setMargin(80,50);
            toast.show();
        }
        return 0;
    }
}