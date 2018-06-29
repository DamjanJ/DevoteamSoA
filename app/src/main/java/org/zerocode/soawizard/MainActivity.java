package org.zerocode.soawizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Damjan on 28-Jun-18.
 */

public class MainActivity extends AppCompatActivity {


    // Damjan Tutorial: Button Declaration
    public Button StartQuiz;
    public Button Questions;
    public Button Statistics;
    public TextView Tekst1;


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
                Intent StartQuizInt = new Intent(getApplicationContext(),StartQuizActivity.class);
                startActivity(StartQuizInt);


            }
        });

        Questions.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub


                // Damjan Tutorial: If button is visible, it will disappear on click, and vice versa
                Intent QuestionsInt = new Intent(getApplicationContext(),QuestionsActivity.class);
                startActivity(QuestionsInt);



            }
        });

        Statistics.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // TODO Auto-generated method stub


                // Damjan Tutorial: If button is visible, it will disappear on click, and vice versa
                Intent StatisticsInt = new Intent(getApplicationContext(),StatisticsActivity.class);
                startActivity(StatisticsInt);


            }
        });



    }



}
