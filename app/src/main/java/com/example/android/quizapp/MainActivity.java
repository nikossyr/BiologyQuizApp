package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    int scorePerCent = 0;
    boolean Q1checked;
    boolean Q2checked;
    boolean Q3checked;
    boolean Q4checked;
    boolean Q5checked;
    String q1;
    String q2;
    String q3;
    String q4;
    String q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            Q1checked = savedInstanceState.getBoolean(q1);
            Q2checked = savedInstanceState.getBoolean(q2);
            Q3checked = savedInstanceState.getBoolean(q3);
            Q4checked = savedInstanceState.getBoolean(q4);
            Q5checked = savedInstanceState.getBoolean(q5);
        }

        setContentView(R.layout.activity_main);
    }

    /**
     * invoked when the activity may be temporarily destroyed, save the instance state here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(q1, Q1checked);
        outState.putBoolean(q2, Q2checked);
        outState.putBoolean(q3, Q3checked);
        outState.putBoolean(q4, Q4checked);
        outState.putBoolean(q5, Q5checked);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    /**
     * This method is activated when the Submit answers button is pressed
     */
    public void submit_answers(View view) {
        EditText answer1Input = (EditText) findViewById(R.id.answer1);
        String answer1 = answer1Input.getText().toString().trim();
        if (answer1.equalsIgnoreCase("cell")) {
            Q1checked = true;
        } else if(answer1.equalsIgnoreCase("cells")){
            Q1checked = true;
        }else{
            Q1checked = false;
        }
        CheckBox checkView1 = (CheckBox) findViewById(R.id.q3_ans_a);
        boolean checkStatus1 = checkView1.isChecked();
        CheckBox checkView2 = (CheckBox) findViewById(R.id.q3_ans_b);
        boolean checkStatus2 = checkView2.isChecked();
        CheckBox checkView3 = (CheckBox) findViewById(R.id.q3_ans_c);
        boolean checkStatus3 = checkView3.isChecked();
        CheckBox checkView4 = (CheckBox) findViewById(R.id.q3_ans_d);
        boolean checkStatus4 = checkView4.isChecked();
        if ((checkStatus1) && (checkStatus3)) {
            if ((!checkStatus2) && (!checkStatus4)){
                Q3checked = true;
            }else {
                Q3checked = false;
            }
        }
        int finalScore = calculateScore(Q1checked, Q2checked, Q3checked, Q4checked, Q5checked);
        scorePerCent = finalScore * 100 / 5;
        Toast.makeText(this, "You answered " + scorePerCent + "% (" + finalScore + "/5) questions correct", Toast.LENGTH_LONG).show();
        score = 0;
        scorePerCent = 0;
    }

    /**
     * This method checks which RadioButton is checked in Question 2
     */
    public void Q2_radio_button(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.q2_ans_a:
                if (checked)
                    Q2checked = false;
                break;
            case R.id.q2_ans_b:
                if (checked)
                    Q2checked = true;
                break;
            case R.id.q2_ans_c:
                if (checked)
                    Q2checked = false;
                break;
            case R.id.q2_ans_d:
                if (checked)
                    Q2checked = false;
                break;
        }
    }

    /**
     * This method checks which RadioButton is checked in Question 3
     */
    public void Q3_checkbox(View view) {
        int subscore = 0;
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.q3_ans_a:
                if (checked)
                    subscore = subscore + 1;
                checked = false;
            case R.id.q3_ans_b:
                if (checked)
                    subscore = subscore - 1;
                checked = false;
                // Cheese me
            case R.id.q3_ans_c:
                if (checked)
                    subscore = subscore + 1;
                checked = false;
            case R.id.q3_ans_d:
                if (checked)
                    subscore = subscore - 1;
        }
        if (subscore == 2) {
            score = score + 1;
        }
    }

    /**
     * This method checks which RadioButton is checked in Question 4
     */
    public void Q4_radio_button(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.q4_ans_a:
                if (checked)
                    Q4checked = false;
                break;
            case R.id.q4_ans_b:
                if (checked)
                    Q4checked = true;
                break;
            case R.id.q4_ans_c:
                if (checked)
                    Q4checked = false;
                break;
            case R.id.q4_ans_d:
                if (checked)
                    Q4checked = false;
                break;
        }
    }

    /**
     * This method checks which RadioButton is checked in Question 5
     */
    public void Q5_radio_button(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.q5_ans_a:
                if (checked)
                    Q5checked = false;
                break;
            case R.id.q5_ans_b:
                if (checked)
                    Q5checked = false;
                break;
            case R.id.q5_ans_c:
                if (checked)
                    Q5checked = true;
                break;
            case R.id.q5_ans_d:
                if (checked)
                    Q5checked = false;
                break;
        }
    }

    /**
     * This method calculated the final score (correct answers)
     */
    public int calculateScore(boolean Q1, boolean Q2, boolean Q3, boolean Q4, boolean Q5) {
        if (Q1) {
            score++;
        }
        if (Q2) {
            score++;
        }
        if (Q3) {
            score++;
        }
        if (Q4) {
            score++;
        }
        if (Q5) {
            score++;
        }
        return score;
    }

    public void reset_answers(View view) {
        EditText Q1editext = (EditText) findViewById(R.id.answer1);
        Q1editext.setText(null);
        RadioGroup Q2radiogroup = (RadioGroup) findViewById(R.id.Q2_radiogroup);
        Q2radiogroup.clearCheck();
        RadioGroup Q4radiogroup = (RadioGroup) findViewById(R.id.Q4_radiogroup);
        Q4radiogroup.clearCheck();
        RadioGroup Q5radiogroup = (RadioGroup) findViewById(R.id.Q5_radiogroup);
        Q5radiogroup.clearCheck();

        CheckBox Q3achecked = (CheckBox) findViewById(R.id.q3_ans_a);
        Q3achecked.setChecked(false);
        CheckBox Q3bchecked = (CheckBox) findViewById(R.id.q3_ans_b);
        Q3bchecked.setChecked(false);
        CheckBox Q3cchecked = (CheckBox) findViewById(R.id.q3_ans_c);
        Q3cchecked.setChecked(false);
        CheckBox Q3dchecked = (CheckBox) findViewById(R.id.q3_ans_d);
        Q3dchecked.setChecked(false);
        Q1checked = false;
        Q2checked = false;
        Q3checked = false;
        Q4checked = false;
        Q5checked = false;
        LinearLayout headertext = (LinearLayout) findViewById(R.id.head_layout);
        ScrollView parentScrollView = (ScrollView) findViewById(R.id.parent_scroll_view);
        parentScrollView.smoothScrollTo(0, headertext.getTop());


    }
}
