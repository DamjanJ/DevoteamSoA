package org.zerocode.soawizard;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

/**
 * Created by Damjan on 29-Jun-18.
 */

public class QuestionsActivity extends AppCompatActivity{


    String[] Example = {"Damjan","Jelic","Software","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer","Engineer"};
    String[] AnswersQ = {"If you", "See this","Call","Damjan"};
    String[] QuestionsBigStr;
    String[] AnswersBigStr;
    String[] CorrectAnswersBigStr;
    String[] CorrectAnswers;
    Button btn_nextQ;
    ProgressBar progress;
    Button btn_previousQ;
    int mycounter = 0;
    int jumpedFlag = 0;
    TextView title;
    ListView QuestionList;
    int maxQuestions = 89;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // Contains ALL THE QUESTIONS
        QuestionsBigStr = getResources().getStringArray(R.array.QuestionsBig);
        // Contains ALL THE ANSWERS
        AnswersBigStr = getResources().getStringArray(R.array.AnswersBig);
        // Contains ALL THE CORRECT ANSWERS FOR CHECKING
        CorrectAnswersBigStr = getResources().getStringArray(R.array.CorrectAnswersBig);


        btn_nextQ = (Button)findViewById(R.id.btn_Next);
        btn_previousQ = (Button)findViewById(R.id.btn_Previous);
        progress = (ProgressBar)findViewById(R.id.pb_questions);
        progress.setMax(maxQuestions-1);



        AnswersQ = AnswersBigStr[mycounter].split(";");
        CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");


        btn_previousQ.setVisibility(View.INVISIBLE);


        title = (TextView)findViewById(R.id.text_questionTitle);
        title.setText(QuestionsBigStr[mycounter]);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NumbPad np = new NumbPad();
                np.show(QuestionsActivity.this, "Go to question:", NumbPad.NO_DECIMAL,
                        new NumbPad.numbPadInterface() {
                            Integer tempvalue = mycounter;
                            // This is called when the user click the 'Ok' button on the dialog
                            // value is the captured input from the dialog.
                            public String numPadInputValue(String value) {

                                tempvalue = Integer.valueOf(value)-1;
                                    if (tempvalue>=0 && tempvalue <maxQuestions) {
                                        mycounter = tempvalue;
                                        if(mycounter==0) {
                                            btn_previousQ.setVisibility(View.INVISIBLE);
                                        } else
                                        {
                                            btn_previousQ.setVisibility(View.VISIBLE);
                                        }
                                        title.setText(QuestionsBigStr[mycounter]);
                                        AnswersQ = AnswersBigStr[mycounter].split(";");
                                        CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");
                                        QuestionsAdapter QA = new QuestionsAdapter();
                                        QuestionList.setAdapter(QA);
                                        progress.setProgress(mycounter);

                                    }

                                    else {
                                     Toast.makeText(QuestionsActivity.this,"No Such Question",Toast.LENGTH_LONG).show();
                                    }

                                return null;
                            }

                            // This is called when the user clicks the 'Cancel' button on the dialog
                            public String numPadCanceled() {
                                // generate a toast message to inform the user that
                                // capture was canceled
                                return null;
                            }
                        });


            }
        });





        QuestionList = (ListView)findViewById(R.id.list_QuestionList);
        QuestionsAdapter QA = new QuestionsAdapter();
        QuestionList.setAdapter(QA);

        btn_nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mycounter<maxQuestions-1) {
                    mycounter = mycounter + 1;
                        if(btn_previousQ.getVisibility()==View.INVISIBLE)
                        {
                            btn_previousQ.setVisibility(View.VISIBLE);
                        }
                    title.setText(QuestionsBigStr[mycounter]);
                    AnswersQ = AnswersBigStr[mycounter].split(";");
                    CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");
                    QuestionsAdapter QA = new QuestionsAdapter();
                    QuestionList.setAdapter(QA);
                    progress.setProgress(mycounter);
                }
                else {
                    Toast.makeText(getApplicationContext(),"This is the End.. my only friend, the end!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_previousQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mycounter>0) {
                    mycounter = mycounter - 1;
                        if(mycounter==0) {
                            btn_previousQ.setVisibility(View.INVISIBLE);
                        }
                    title.setText(QuestionsBigStr[mycounter]);
                    AnswersQ = AnswersBigStr[mycounter].split(";");
                    CorrectAnswers = CorrectAnswersBigStr[mycounter].split(",");
                    QuestionsAdapter QA = new QuestionsAdapter();
                    QuestionList.setAdapter(QA);
                    progress.setProgress(mycounter);
                }
                else {
                    Toast.makeText(getApplicationContext(),"You are at the Start",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    // Damjan Tutorial: Creating Adapter for the Question List
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
            view = getLayoutInflater().inflate(R.layout.listitem_question,null);
            TextView Answer = (TextView)view.findViewById(R.id.text_answer);

            Answer.setText(AnswersQ[i]);
            for (int m=0;m<CorrectAnswers.length;m++){
                if (i==Integer.valueOf(CorrectAnswers[m])) {
                    Answer.setTextColor(getResources().getColor(R.color.colorDevoBlueDark));
                }
            }
            return view;
        }
    }



}




