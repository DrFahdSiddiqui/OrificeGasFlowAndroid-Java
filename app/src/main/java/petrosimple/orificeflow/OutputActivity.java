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
 *   Source file for Output Activity Class                                   *
 *   Shows the output result as a form, with an option to change units       *
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
import android.widget.TextView;

import java.util.Locale;

import static petrosimple.orificeflow.Data.*;


// ------------------------------------------------------------------------ //
// Displays Output activity
public class OutputActivity extends AppCompatActivity {
    // -------------------------------------------------------------------- //
    // Actions to perform on activity creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        j = 1;
        units(findViewById(android.R.id.content));
    } // onCreate


    // -------------------------------------------------------------------- //
    // Actions to perform on units button click
    // Convert units from field to metric and vice-versa for displaying
    public void units(View view) {
        TextView op_QS = (TextView) findViewById(R.id.op_QS);
        TextView op_QV = (TextView) findViewById(R.id.op_QV);
        TextView op_QM = (TextView) findViewById(R.id.op_QM);
        TextView op_VP = (TextView) findViewById(R.id.op_VP);
        TextView op_VO = (TextView) findViewById(R.id.op_VO);

        TextView op_Re = (TextView) findViewById(R.id.op_Re);
        TextView op_P2 = (TextView) findViewById(R.id.op_P2);
        TextView op_C = (TextView) findViewById(R.id.op_C);
        TextView op_Z = (TextView) findViewById(R.id.op_Z);
        TextView op_mu = (TextView) findViewById(R.id.op_mu);

        TextView ut_QS = (TextView) findViewById(R.id.ut_QS);
        TextView ut_QV = (TextView) findViewById(R.id.ut_QV);
        TextView ut_QM = (TextView) findViewById(R.id.ut_QM);
        TextView ut_VP = (TextView) findViewById(R.id.ut_VP);
        TextView ut_VO = (TextView) findViewById(R.id.ut_VO);

        TextView ut_P2 = (TextView) findViewById(R.id.ut_P2);
        TextView ut_mu = (TextView) findViewById(R.id.ut_mu);
        switch (j) {
            case 0://SI Units
                op_QV.setText(String.format(Locale.getDefault(), "%.4f", Q));
                op_QM.setText(String.format(Locale.getDefault(), "%.3f", Q * rho));
                op_QS.setText(String.format(Locale.getDefault(), "%.3f", Q * rho / mw * 22.4 / Z));
                op_VP.setText(String.format(Locale.getDefault(), "%.2f", Q / A1));
                op_VO.setText(String.format(Locale.getDefault(), "%.1f", Q / A2));

                op_P2.setText(String.format(Locale.getDefault(), "%.1f", P2 / 1000));
                op_Re.setText(String.format(Locale.getDefault(), "%.3e", Re1));
                op_C.setText(String.format(Locale.getDefault(), "%.4f", C));
                op_mu.setText(String.format(Locale.getDefault(), "%.3e", mu));
                op_Z.setText(String.format(Locale.getDefault(), "%.4f", Z));

                ut_QV.setText(" m3/s");
                ut_QM.setText(" kg/s");
                ut_QS.setText(" sm3/s");
                ut_VP.setText(" m/s");
                ut_VO.setText(" m/s");

                ut_P2.setText(" kPa");
                ut_mu.setText(" Pa.s");

                j = 1;
                break;
            case 1://Field Units
                op_QV.setText(String.format(Locale.getDefault(), "%.3f", Q * 3.051));
                op_QM.setText(String.format(Locale.getDefault(), "%.2f", Q * rho * 2.20462));
                op_QS.setText(String.format(Locale.getDefault(), "%.2f", Q * rho / mw * 22.4 / Z * 3.051 / 492 * 520));
                op_VP.setText(String.format(Locale.getDefault(), "%.1f", Q / A1 * 3.28084));
                op_VO.setText(String.format(Locale.getDefault(), "%.1f", Q / A2 * 3.28084));

                op_P2.setText(String.format(Locale.getDefault(), "%.1f", P2 * 0.000145038));
                op_Re.setText(String.format(Locale.getDefault(), "%.3e", Re1));
                op_C.setText(String.format(Locale.getDefault(), "%.4f", C));
                op_mu.setText(String.format(Locale.getDefault(), "%.3e", mu * 1000));
                op_Z.setText(String.format(Locale.getDefault(), "%.4f", Z));
                ut_QV.setText(" MMcfD");
                ut_QM.setText(" lb/s");
                ut_QS.setText(" MMScfD");
                ut_VP.setText(" ft/s");
                ut_VO.setText(" ft/s");

                ut_P2.setText(" psi");
                ut_mu.setText(" cp");
                j = 0;
                break;
        }
    } // units


    // -------------------------------------------------------------------- //
    // Actions to perform on calc button click
    // Make a text report for emailing or sharing etc.
    public void report(View view) {
        Intent intent2 = new Intent(this, TextActivity.class);
        startActivity(intent2);
    }

    // -------------------------------------------------------------------- //
    // Actions to perform on resetting
    // Resets the variables for next usage
    public void onDestroy() {
        D1 = 5.0;
        D2 = 3.5;
        sg = 15.996 / 28.97;
        L1 = 1.0;
        L2 = 1.0;
        P1 = 130.0;
        Pd = 25.3;
        P2 = P1 - Pd;
        T1 = 100.0;
        k = 1.3023845;
        tol = 0.001;
        chart = 0;
        super.onDestroy();
    } // onDestroy
}


/****************************************************************************/
