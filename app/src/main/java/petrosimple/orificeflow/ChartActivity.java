/*****************************************************************************
 *                                                                           *
 *                 ORIFICE GAS FLOW RATE CALCULATION PROGRAM                 *
 *                                Version 2.1                                *
 *        Written for Java/Android by : Fahd Siddiqui and Aqsa Qureshi       *
 *        https://github.com/DrFahdSiddiqui/OrificeGasFlowAndroid-Java       *
 *                                                                           *
 * ------------------------------------------------------------------------- *
 * LICENSE: MOZILLA 2.0                                                      *
 *   This Source Code Form is subject to the terms of the Mozilla Public     *
 *   License, v. 2.0. If a copy of the MPL was not distributed with this     *
 *   file, You can obtain one at http://mozilla.org/MPL/2.0/.                *
 ****************************************************************************/

/*****************************************************************************
 * DOCUMENTATION                                                             *
 *   Source file for ChartActivity Class                                      *
 *   Takes barton chart readings and converts them to pressure, temperature, *
 *   and differential pressure                                               *
 *   Last updated 08/08/2018                                                 *
 ****************************************************************************/

/*****************************************************************************
 * TODO                                                                      *
 *   Code cleanup and re-usability                                           *
 ****************************************************************************/


/****************************************************************************/


package petrosimple.orificeflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import static java.lang.StrictMath.pow;
import static petrosimple.orificeflow.Data.*;


// ------------------------------------------------------------------------ //
// Displays Chart Readings activity
public class ChartActivity extends AppCompatActivity {
    // -------------------------------------------------------------------- //
    // Actions to perform on activity creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        EditText in_btr = (EditText) findViewById(R.id.in_btr);
        EditText in_dpr = (EditText) findViewById(R.id.in_dpr);
        EditText in_S = (EditText) findViewById(R.id.in_S);
        EditText in_DP = (EditText) findViewById(R.id.in_DP);
        EditText in_T = (EditText) findViewById(R.id.in_T);

        in_btr.setText(String.format(Locale.getDefault(), "%.1f", btr));
        in_dpr.setText(String.format(Locale.getDefault(), "%.1f", dpr));
        in_S.setText(String.format(Locale.getDefault(), "%.2f", S));
        in_DP.setText(String.format(Locale.getDefault(), "%.2f", DP));
        in_T.setText(String.format(Locale.getDefault(), "%.2f", T));
    } // onCreate


    // -------------------------------------------------------------------- //
    // Actions to perform on calc button click
    // Converts the barton chart readings to respecting pressure, temperature,
    // and differential pressure values
    public void calc(View view) {
        EditText in_btr = (EditText) findViewById(R.id.in_btr);
        EditText in_dpr = (EditText) findViewById(R.id.in_dpr);
        EditText in_S = (EditText) findViewById(R.id.in_S);
        EditText in_DP = (EditText) findViewById(R.id.in_DP);
        EditText in_T = (EditText) findViewById(R.id.in_T);
        TextView op_P = (TextView) findViewById(R.id.op_P);
        TextView op_DP = (TextView) findViewById(R.id.op_DP);
        TextView op_T = (TextView) findViewById(R.id.op_T);

        try {
            btr = Double.parseDouble(in_btr.getText().toString());
        } catch (Exception e) {
        }
        try {
            dpr = Double.parseDouble(in_dpr.getText().toString());
        } catch (Exception e) {
        }
        try {
            S = Double.parseDouble(in_S.getText().toString());
        } catch (Exception e) {
        }
        try {
            DP = Double.parseDouble(in_DP.getText().toString());
        } catch (Exception e) {
        }
        try {
            T = Double.parseDouble(in_T.getText().toString());
        } catch (Exception e) {
        }
        P1 = pow(S, 2) * btr / 100 + 14.7;
        Pd = pow(DP, 2) * dpr / 100;
        T1 = pow(T, 2) * 9.0 / 5.0 + 32.0;
        op_P.setText(String.format(Locale.getDefault(), "%.2f", P1));
        op_DP.setText(String.format(Locale.getDefault(), "%.3f", Pd));
        op_T.setText(String.format(Locale.getDefault(), "%.2f", T1));
        SetUpP = 1;
        SetDp = 3;
        SetT = 1;
    } // calc


    // -------------------------------------------------------------------- //
    // Actions to perform on save button click
    // Sets the converted values to main activity form
    public void save(View view) {
        calc(findViewById(android.R.id.content));
        chart = 1;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    } // save
}


/****************************************************************************/

