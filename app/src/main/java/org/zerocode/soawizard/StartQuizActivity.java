package org.zerocode.soawizard;

import android.app.AlertDialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Damjan on 29-Jun-18.
 */

public class StartQuizActivity extends AppCompatActivity {

    String[] Example = {"Damjan","Jelic","Software","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer"};
    String[] AnswersQ = {"If you", "See this","Call","Damjan"};
    String[] QuestionsBigStr;
    String[] AnswersBigStr;
    String[] CorrectAnswersBigStr;
    String[] CorrectAnswers;
    Button btn_nextQ;
    ProgressBar progressq;
    Button btn_showQ;
    Button btn_finishedQ;
    int mycounter = 0;
    int jumpedFlag = 0;
    int[] Choice;
    TextView title;
    ListView QuestionList;
    int TotalPoints = 0;
    int maxQuestions = 89;
    TextView changable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_quiz);

        // Contains ALL THE QUESTIONS
        QuestionsBigStr = getResources().getStringArray(R.array.QuestionsBig);
        // Contains ALL THE ANSWERS
        AnswersBigStr = getResources().getStringArray(R.array.AnswersBig);
        // Contains ALL THE CORRECT ANSWERS FOR CHECKING
        CorrectAnswersBigStr = getResources().getStringArray(R.array.CorrectAnswersBig);


        btn_nextQ = (Button)findViewById(R.id.btn_NextQuiz);
        btn_showQ = (Button)findViewById(R.id.btn_Show);
        btn_finishedQ = (Button)findViewById(R.id.btn_finished);
        progressq = (ProgressBar)findViewById(R.id.pb_quiz);
        progressq.setMax(maxQuestions-1);

        title = (TextView)findViewById(R.id.text_questionTitle);
        title.setText(QuestionsBigStr[mycounter]);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Can't rush it Jimmy",Toast.LENGTH_LONG).show();
            }
        });


        AnswersQ = AnswersBigStr[mycounter].split(":");
        CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");




        QuestionList = (ListView)findViewById(R.id.list_QuestionList);

        QuestionList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        QuestionList.setSelected(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listitem_question2,AnswersQ);
        //simple_list_item_multiple_choice
        QuestionList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        QuestionList.setAdapter(adapter);



        //Addint OnClickListener to the list

        /*
        QuestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    changable =(TextView) adapterView.getSelectedItem();

                    view.setSelected(false);

            }
        }); */










        btn_nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mycounter<maxQuestions-1) {
                    btn_nextQ.setVisibility(View.INVISIBLE);
                    btn_showQ.setVisibility(View.VISIBLE);
                    QuestionList.setSelected(true);
                    mycounter = mycounter + 1;
                    title.setText(QuestionsBigStr[mycounter]);
                    AnswersQ = AnswersBigStr[mycounter].split(":");
                    CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(StartQuizActivity.this, R.layout.listitem_question2,AnswersQ);
                    //simple_list_item_multiple_choice
                    QuestionList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    QuestionList.setAdapter(adapter);
                    progressq.setProgress(mycounter);
                }
                else {
                    Toast.makeText(getApplicationContext(),"If you see this, call Damjan",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_finishedQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder QuizBuilder = new AlertDialog.Builder(StartQuizActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.quizfinished, null);
                Button great = (Button) mView.findViewById(R.id.btn_great);
                TextView DEfficiency = (TextView)mView.findViewById(R.id.text_efficiency);
                TextView DPoints = (TextView)mView.findViewById(R.id.text_points);
                ImageView Gandalf = (ImageView)mView.findViewById(R.id.imageView5);

                double trouble;
                trouble = ((double)TotalPoints/maxQuestions)*100;
                DecimalFormat format = new DecimalFormat();
                format.setDecimalSeparatorAlwaysShown(false);
                DEfficiency.setText(format.format(trouble)+"%");
                DPoints.setText("Total Points: "+TotalPoints+"/"+maxQuestions);

                if (TotalPoints==maxQuestions){
                    Gandalf.setImageResource(R.drawable.ic_gandalf);
                }

                great.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartQuizActivity.super.finish();
                    }
                });

                QuizBuilder.setView(mView);
                AlertDialog QuizDialog = QuizBuilder.create();
                QuizDialog.show();

            }
        });

        btn_showQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Damjan - Give points even for partial answers 2/3
                int cntChoice = QuestionList.getCount();
                SparseBooleanArray sparseBooleanArray = QuestionList.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        for (int cnter =0; cnter<CorrectAnswers.length; cnter++) {
                            int comparison = Integer.valueOf(CorrectAnswers[cnter]);


                            if(comparison==i){
                                TotalPoints += 1;
                            }
                        }
                    }
                } */

                String[] ArrayTesting1 ={"12","15"};
                String[] ArrayTesting2 ={"12","15"};
                List<String> ArrayComparison2 = new ArrayList<String>();
                int cntChoice = QuestionList.getCount();
                SparseBooleanArray sparseBooleanArray = QuestionList.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        ArrayComparison2.add(Integer.toString(i));
                    }
                }

               if (Arrays.equals(ArrayComparison2.toArray(),CorrectAnswers)){

                    TotalPoints += 1;
                }




                if (mycounter<maxQuestions-1) {
                    btn_nextQ.setVisibility(View.VISIBLE);
                    btn_showQ.setVisibility(View.INVISIBLE);
                    QuestionList.setSelected(true);
                    title.setText(QuestionsBigStr[mycounter]);
                    AnswersQ = AnswersBigStr[mycounter].split(":");
                    CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");
                    StartQuizActivity.CorrectAnswerAdapter CA = new StartQuizActivity.CorrectAnswerAdapter();
                    QuestionList.setAdapter(CA);
                }
                else {
                    title.setText(QuestionsBigStr[mycounter]);
                    AnswersQ = AnswersBigStr[mycounter].split(":");
                    CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");
                    StartQuizActivity.CorrectAnswerAdapter CA = new StartQuizActivity.CorrectAnswerAdapter();
                    QuestionList.setAdapter(CA);
                    btn_showQ.setVisibility(View.INVISIBLE);
                    btn_finishedQ.setVisibility(View.VISIBLE);
                }
            }
        });


    }


    // Damjan Tutorial: Creating Adapter for the Question List
    // Found something better!
    class QuestionsAdapter extends BaseAdapter
    {


        @Override
        public int getCount() {
            return AnswersQ.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // Damjan Tutorial: In case we create fragment we use inflater as shown bellow
            //view = LayoutInflater.from(getContext()).inflate(R.layout.listitem_question,null);
            view = getLayoutInflater().inflate(R.layout.listitem_question2,null);
            //TextView Answer = (TextView)view.findViewById(R.id.text_answer);
            CheckedTextView Answer = (CheckedTextView)view;
            Answer.setText(AnswersQ[i]);


            return view;
        }
    }

    class CorrectAnswerAdapter extends BaseAdapter
    {


        @Override
        public int getCount() {
            return AnswersQ.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // Damjan Tutorial: In case we create fragment we use inflater as shown bellow
            //view = LayoutInflater.from(getContext()).inflate(R.layout.listitem_question,null);
            view = getLayoutInflater().inflate(R.layout.listitem_question,null);
            TextView Answer = (TextView)view.findViewById(R.id.text_answer);

            Answer.setText(AnswersQ[i]);
            for (int m=0;m<CorrectAnswers.length;m++){
                if (i==Integer.valueOf(CorrectAnswers[m])) {
                    Answer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
            return view;
        }
    }




}




