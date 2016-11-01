package quizapp.jd.com.quizapp;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {


    private static final String TAG = "QUIZZAPP";

    // QUESTION  1 radios...
    RadioButton question1A;
    RadioButton question1B;
    RadioButton question1C;
    RadioButton question1D;
    RadioGroup question1RadioGroup;

    // QUESTION 2 box
    CheckBox question2A;
    CheckBox question2B;
    CheckBox question2C;

    // QUESTION 3
    EditText question3Text;
    EditText question4Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect question 1 views
        question1A = (RadioButton) this.findViewById(R.id.question1_radio_A);
        question1B = (RadioButton) this.findViewById(R.id.question1_radio_B);
        question1C = (RadioButton) this.findViewById(R.id.question1_radio_C);
        question1D = (RadioButton) this.findViewById(R.id.question1_radio_D);
        question1RadioGroup = (RadioGroup) this.findViewById(R.id.question1_radio_group);

        // connect question 2 views
        question2A = (CheckBox) this.findViewById(R.id.question2_chkbox_A);
        question2B = (CheckBox) this.findViewById(R.id.question2_chkbox_B);
        question2C = (CheckBox) this.findViewById(R.id.question2_chkbox_C);

        // question 3
        question3Text = (EditText) this.findViewById(R.id.question3_answer_text);

        // question 4
        question4Text = (EditText) this.findViewById(R.id.question4_answer_text);

        question3Text.clearFocus();
        question4Text.clearFocus();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.submit_btn:
                checkAllAnswers();
                break;
            case R.id.reset_btn:
                resetAllAnswers();
                break;
            default:
                Log.d(TAG,"Should not get here. Strange Error");
        }

    }

    /**
     * verify that the answer is correct for each question
     */
    private void checkAllAnswers()
    {
            // show answer also
            boolean resQ1 = verifyQuestion1();
            boolean resQ2 = verifyQuestion2();
            boolean resQ3 = verifyEditTextQuestion(question3Text,
                    getString(R.string.question3_text_answer));
            boolean resQ4 =  verifyEditTextQuestion(question4Text,
                    getString(R.string.question4_text_answer));

            if (!resQ1)
            {
                //question1RadioGroup.clearCheck();
                question1B.setBackgroundColor(
                        ContextCompat.getColor(this,R.color.colorCorrectAnswer));
            }

            if (!resQ2)
            {
                question2B.setBackgroundColor(
                        ContextCompat.getColor(this,R.color.colorCorrectAnswer));
                question2A.setBackgroundColor(
                        ContextCompat.getColor(this,R.color.colorCorrectAnswer));

            }

            if (!resQ3)
            {
                question3Text.setText(getString(R.string.question3_text_answer));
                question3Text.setTextColor(ContextCompat.getColor(this,R.color.colorCorrectAnswer));
            }

            if (!resQ4)
            {
                question4Text.setText(getString(R.string.question4_text_answer));
                question4Text.setTextColor(ContextCompat.getColor(this,R.color.colorCorrectAnswer));
            }

           if (resQ1 && resQ2 && resQ3 && resQ4)
           {
               Toast.makeText(this, getString(R.string.congrats_txt), Toast.LENGTH_LONG).show();
           }

    }


    /**
     * @return true if and only if B is selected
     */
    private boolean verifyQuestion1()
    {
        return (question1B.isChecked());
    }

    /**
     * @return true if and only if A and B selected and C not selected
     */
    private boolean verifyQuestion2()
    {
        return (question2A.isChecked() && question2B.isChecked() && (!question2C.isChecked()));
    }


    /**
     *
     * @return true if and only if the string match with the answer string
     * from the string resources file
     */
    private boolean verifyEditTextQuestion(EditText textView, String answer)
    {
        String str_answer = textView.getText().toString();
        if (str_answer == null)
            return false;

        return str_answer.equalsIgnoreCase(answer);

    }



    /**
     * uncheck all radio buttons and check box as well as clearing text answer
     */
    private void resetAllAnswers()
    {
        question1RadioGroup.clearCheck();
        question1B.setBackgroundColor(Color.TRANSPARENT);

        question2A.setChecked(false);
        question2B.setChecked(false);
        question2C.setChecked(false);
        question2A.setBackgroundColor(Color.TRANSPARENT);
        question2B.setBackgroundColor(Color.TRANSPARENT);

        question3Text.setText("");
        question3Text.clearFocus();
        question3Text.setHint(getString(R.string.question3_text_hint));

        question4Text.setText("");
        question4Text.clearFocus();
        question4Text.setHint(getString(R.string.question4_text_hint));


    }


}
