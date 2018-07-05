package org.zerocode.soawizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by Damjan on 28-Jun-18.
 */

public class MainActivity extends AppCompatActivity {


    // Damjan Tutorial: Button Declaration
    public Button StartQuiz;
    public Button Questions;
    public Button Statistics;
    int mypicturecounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        // Damjan Tutorial: Declaring what layout to use in this activity
        setContentView(R.layout.activity_main);

        // Damjan Tutorial: Assigning buttons from layout to our code buttons
        StartQuiz = (Button) findViewById(R.id.btn_start_quiz);
        Questions = (Button) findViewById(R.id.btn_questions);
        Statistics = (Button) findViewById(R.id.btn_statistics);


        // Damjan Tutorial: Adding a "On Click Listener" that will register click and perform an action
        StartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub 


                // Damjan Tutorial: If button is visible, it will disappear on click, and vice versa


                android.app.AlertDialog.Builder QuizStarter = new android.app.AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.ready_for_quiz, null);
                Button Quiz1 = (Button) mView.findViewById(R.id.btn_quiz1);
                Button Quiz2 = (Button) mView.findViewById(R.id.btn_quiz2);
                TextView GoodLuck = (TextView)mView.findViewById(R.id.text_efficiency);
                TextView Motivation = (TextView)mView.findViewById(R.id.text_points);
                ImageView Ready = (ImageView)mView.findViewById(R.id.imageView5);




                QuizStarter.setView(mView);
                final android.app.AlertDialog QuizDialog = QuizStarter.create();
                QuizDialog.show();
                Quiz1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent StartQuizInt = new Intent(getApplicationContext(),StartQuizActivity.class);
                        // Adding params to see which Quiz to invoke
                        Bundle b = new Bundle();
                        b.putInt("key", 1); //Your id
                        StartQuizInt.putExtras(b);
                        // Starting Activity
                        startActivity(StartQuizInt);

                        QuizDialog.dismiss();



                    }
                });

                Quiz2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent StartQuizInt = new Intent(getApplicationContext(),StartQuizActivity.class);

                        Bundle b = new Bundle();
                        b.putInt("key", 2); //Your id
                        StartQuizInt.putExtras(b);

                        startActivity(StartQuizInt);
                        QuizDialog.dismiss();



                    }
                });



            }
        });

        Questions.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub


                // Damjan Tutorial: If button is visible, it will disappear on click, and vice versa
                Intent QuestionsInt = new Intent(MainActivity.this,QuestionsActivity.class);
                startActivity(QuestionsInt);



            }
        });

        Statistics.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(getApplicationContext(),"Implementing solution soon - Damjan", Toast.LENGTH_LONG).show();
                // Damjan Tutorial: If button is visible, it will disappear on click, and vice versa
                /*Intent StatisticsInt = new Intent(getApplicationContext(),StatisticsActivity.class);
                startActivity(StatisticsInt);*/
            }
        });



    }



}
