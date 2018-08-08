package com.example.android.volleyballscoring;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int scoreA, scoreB, setsA, setsB;
    private TextView scoreViewA, scoreViewB, setsViewA, setsViewB;
    private Button reset_Button, pointA_Button, pointB_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset_Button = (Button) findViewById(R.id.reset_button);
        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        setsViewA = (TextView) findViewById(R.id.team_a_sets);
        pointA_Button = (Button) findViewById(R.id.point_a_button);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
        setsViewB = (TextView) findViewById(R.id.team_b_sets);
        pointB_Button = (Button) findViewById(R.id.point_b_button);

        reset_Button.setOnClickListener(this);
        pointA_Button.setOnClickListener(this);
        pointB_Button.setOnClickListener(this);

        scoreA = scoreB = setsA = setsB = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.reset_button): {
                resetSets();
                resetScores();
                break;
            }
            case (R.id.point_a_button): {
                scoreA++;
                displayForTeam(scoreViewA, scoreA);
                if (scoreA >= 25 && (scoreA - scoreB) >= 2) {
                    new CountDownTimer(250, 500) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            Context context = getApplicationContext();
                            CharSequence text = "Team A wins this set!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            setsA++;
                            displayForTeam(setsViewA, setsA);
                            resetScores();
                        }
                    }.start();
                }
                break;
            }
            case (R.id.point_b_button): {
                scoreB++;
                displayForTeam(scoreViewB, scoreB);
                if (scoreB >= 25 && (scoreB - scoreA) >= 2) {
                    new CountDownTimer(250, 500) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            Context context = getApplicationContext();
                            CharSequence text = "Team B wins this set!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            setsB++;
                            displayForTeam(setsViewB, setsB);
                            resetScores();
                        }
                    }.start();
                }
                break;
            }
            default:
                break;
        }
    }

    /* ------------------------------------------------------------------------------------- */

    public void displayForTeam(TextView view, int num) {
        view.setText(String.valueOf(num));
    }

    private void resetSets() {
        setsA = setsB = 0;
        displayForTeam(setsViewA, setsA);
        displayForTeam(setsViewB, setsB);
    }

    private void resetScores() {
        scoreA = scoreB = 0;
        displayForTeam(scoreViewA, scoreA);
        displayForTeam(scoreViewB, scoreB);
    }

}
