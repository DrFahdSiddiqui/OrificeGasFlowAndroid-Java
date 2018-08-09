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
 *   Source file for MainActivity Class                                      *
 *   Shows the input form, settins menu and buttons                          *
 *   Before Closure Analysis data.                                           *
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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.StrictMath.*;
import static petrosimple.orificeflow.Data.*;


// ------------------------------------------------------------------------ //
// Displays Main activity
public class MainActivity extends AppCompatActivity {
    // -------------------------------------------------------------------- //
    // Actions to perform on activity creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner sp_pipeD = (Spinner) findViewById(R.id.sp_pipeD);
        Spinner sp_orfd = (Spinner) findViewById(R.id.sp_orfd);
        Spinner sp_upP = (Spinner) findViewById(R.id.sp_upP);
        Spinner sp_diffP = (Spinner) findViewById(R.id.sp_diffP);
        Spinner sp_dsT = (Spinner) findViewById(R.id.sp_dsT);
        Spinner sp_tap = (Spinner) findViewById(R.id.sp_tap);
        Spinner sp_L1 = (Spinner) findViewById(R.id.sp_L1);
        Spinner sp_L2 = (Spinner) findViewById(R.id.sp_L2);
        EditText in_pipeD = (EditText) findViewById(R.id.in_pipeD);
        EditText in_orfd = (EditText) findViewById(R.id.in_orfd);
        EditText in_upP = (EditText) findViewById(R.id.in_upP);
        EditText in_diffP = (EditText) findViewById(R.id.in_diffP);
        EditText in_dsT = (EditText) findViewById(R.id.in_dsT);
        EditText in_sg = (EditText) findViewById(R.id.in_sg);
        EditText in_k = (EditText) findViewById(R.id.in_k);
        EditText in_L1 = (EditText) findViewById(R.id.in_L1);
        EditText in_L2 = (EditText) findViewById(R.id.in_L2);

        List<String> categories = new ArrayList<>();
        categories.add("   mm");
        categories.add("   in");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_pipeD.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   mm");
        categories.add("   in");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_orfd.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   kPa");
        categories.add("   psi");
        categories.add("   bar");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_upP.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   kPa");
        categories.add("   psi");
        categories.add("   bar");
        categories.add("   in H2O");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_diffP.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   C");
        categories.add("   F");
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_dsT.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   Flange Tappings");
        categories.add("   D and D/2 Tappings");
        categories.add("   Corner Tappings");
        categories.add("   Custom Tappings/Advanced");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tap.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   mm");
        categories.add("   in");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_L1.setAdapter(dataAdapter);

        categories = new ArrayList<>();
        categories.add("   mm");
        categories.add("   in");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_L2.setAdapter(dataAdapter);

        sp_pipeD.setSelection(SetPD);
        sp_orfd.setSelection(SetOd);
        sp_upP.setSelection(SetUpP);
        sp_diffP.setSelection(SetDp);
        sp_dsT.setSelection(SetT);
        sp_tap.setSelection(SetTap);
        sp_L1.setSelection(SetL1);
        sp_L2.setSelection(SetL2);
        in_pipeD.setText(String.format(Locale.getDefault(), "%.3f", D1));
        in_orfd.setText(String.format(Locale.getDefault(), "%.3f", D2));
        in_upP.setText(String.format(Locale.getDefault(), "%.3f", P1));
        in_diffP.setText(String.format(Locale.getDefault(), "%.2f", Pd));
        in_dsT.setText(String.format(Locale.getDefault(), "%.1f", T1));
        in_sg.setText(String.format(Locale.getDefault(), "%.4f", sg));
        in_k.setText(String.format(Locale.getDefault(), "%.4f", k));
        in_L1.setText(String.format(Locale.getDefault(), "%.4f", L1));
        in_L2.setText(String.format(Locale.getDefault(), "%.4f", L2));

        sp_tap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText in_L1 = (EditText) findViewById(R.id.in_L1);
                EditText in_L2 = (EditText) findViewById(R.id.in_L2);
                Spinner sp_L1 = (Spinner) findViewById(R.id.sp_L1);
                Spinner sp_L2 = (Spinner) findViewById(R.id.sp_L2);
                Spinner sp_pipeD = (Spinner) findViewById(R.id.sp_pipeD);
                Spinner sp_orfd = (Spinner) findViewById(R.id.sp_orfd);
                EditText in_pipeD = (EditText) findViewById(R.id.in_pipeD);
                EditText in_orfd = (EditText) findViewById(R.id.in_orfd);
                in_L1.setEnabled(false);
                in_L2.setEnabled(false);
                sp_L1.setEnabled(false);
                sp_L2.setEnabled(false);
                switch (position) {
                    case 0://Flange
                        in_L1.setText(String.format(Locale.getDefault(), "1.0"));
                        in_L2.setText(String.format(Locale.getDefault(), "1.0"));
                        sp_L1.setSelection(1);
                        sp_L2.setSelection(1);
                        L1 = 25.4;
                        L2 = 25.4;
                        break;
                    case 1://D D/2
                        try {
                            D1 = Double.parseDouble(in_pipeD.getText().toString());
                        } catch (Exception e) {
                        }
                        try {
                            D2 = Double.parseDouble(in_orfd.getText().toString());
                        } catch (Exception e) {
                        }
                        switch (sp_pipeD.getSelectedItemPosition()) {
                            case 0://mm
                                D1 = D1 / 1000;
                                break;
                            case 1://in
                                D1 = D1 / 1000 * 25.4;
                                break;
                        }

                        switch (sp_orfd.getSelectedItemPosition()) {
                            case 0://mm
                                D2 = D2 / 1000;
                                break;
                            case 1://in
                                D2 = D2 / 1000 * 25.4;
                                break;
                        }
                        in_L1.setText(String.format(Locale.getDefault(), "%.1f", D1 * 1000 / 25.4));
                        in_L2.setText(String.format(Locale.getDefault(), "%.1f", D1 / 2 * 1000 / 25.4));
                        sp_L1.setSelection(1);
                        sp_L2.setSelection(1);
                        break;
                    case 2://Corner
                        in_L1.setText("0");
                        in_L2.setText("0");
                        sp_L1.setSelection(1);
                        sp_L2.setSelection(1);
                        break;
                    case 3://Custom
                        in_L1.setEnabled(true);
                        in_L2.setEnabled(true);
                        sp_L1.setEnabled(true);
                        sp_L2.setEnabled(true);
                        sp_L1.setSelection(1);
                        sp_L2.setSelection(1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });
    } // onCreate


    // -------------------------------------------------------------------- //
    // Actions to perform on options menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // onCreateOptionsMenu


    // -------------------------------------------------------------------- //
    // Actions to perform on each option click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            settings(findViewById(android.R.id.content));
            return true;
        }
        if (id == R.id.action_about) {
            about(findViewById(android.R.id.content));
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected


    // -------------------------------------------------------------------- //
    // Actions to perform on calc button click
    // Calculates z-factor from correlation, viscosity from correlation,
    // and gas flow rates from orifice equation
    public void calc(View view) {
        Spinner sp_pipeD = (Spinner) findViewById(R.id.sp_pipeD);
        Spinner sp_orfd = (Spinner) findViewById(R.id.sp_orfd);
        Spinner sp_upP = (Spinner) findViewById(R.id.sp_upP);
        Spinner sp_diffP = (Spinner) findViewById(R.id.sp_diffP);
        Spinner sp_dsT = (Spinner) findViewById(R.id.sp_dsT);
        Spinner sp_L1 = (Spinner) findViewById(R.id.sp_L1);
        Spinner sp_L2 = (Spinner) findViewById(R.id.sp_L2);

        EditText in_pipeD = (EditText) findViewById(R.id.in_pipeD);
        EditText in_orfd = (EditText) findViewById(R.id.in_orfd);
        EditText in_upP = (EditText) findViewById(R.id.in_upP);
        EditText in_diffP = (EditText) findViewById(R.id.in_diffP);
        EditText in_dsT = (EditText) findViewById(R.id.in_dsT);
        EditText in_sg = (EditText) findViewById(R.id.in_sg);
        EditText in_k = (EditText) findViewById(R.id.in_k);
        EditText in_L1 = (EditText) findViewById(R.id.in_L1);
        EditText in_L2 = (EditText) findViewById(R.id.in_L2);

        try {
            D1 = Double.parseDouble(in_pipeD.getText().toString());
        } catch (Exception e) {
        }
        try {
            D2 = Double.parseDouble(in_orfd.getText().toString());
        } catch (Exception e) {
        }
        try {
            P1 = Double.parseDouble(in_upP.getText().toString());
        } catch (Exception e) {
        }
        try {
            Pd = Double.parseDouble(in_diffP.getText().toString());
        } catch (Exception e) {
        }
        try {
            T1 = Double.parseDouble(in_dsT.getText().toString());
        } catch (Exception e) {
        }
        try {
            sg = Double.parseDouble(in_sg.getText().toString());
        } catch (Exception e) {
        }
        try {
            k = Double.parseDouble(in_k.getText().toString());
        } catch (Exception e) {
        }
        switch (sp_pipeD.getSelectedItemPosition()) {
            case 0://mm
                D1 = D1 / 1000.0;
                break;
            case 1://in
                D1 = D1 / 1000.0 * 25.4;
                break;
        }
        switch (sp_orfd.getSelectedItemPosition()) {
            case 0://mm
                D2 = D2 / 1000.0;
                break;
            case 1://in
                D2 = D2 / 1000.0 * 25.4;
                break;
        }
        switch (sp_upP.getSelectedItemPosition()) {
            case 0://kPa
                P1 = P1 * 1000;
                break;
            case 1://psi
                P1 = P1 * 6894.76;
                break;
            case 2://bar
                P1 = P1 * 100000;
                break;
        }
        switch (sp_diffP.getSelectedItemPosition()) {
            case 0://kPa
                Pd = Pd * 1000;
                break;
            case 1://psi
                Pd = Pd * 6894.76;
                break;
            case 2://bar
                Pd = Pd * 100000;
                break;
            case 3://inH20
                Pd = Pd * 248.84;
                break;
        }
        switch (sp_dsT.getSelectedItemPosition()) {
            case 0://C
                T1 = T1 + 273.15;
                break;
            case 1://F
                T1 = (T1 - 32.0) * 5.0 / 9.0 + 273.15;
                break;
        }
        if (T1 <= 0) T1 = 0.15;

        Spinner sp_tap = (Spinner) findViewById(R.id.sp_tap);
        switch (sp_tap.getSelectedItemPosition()) {
            case 0://Flange
                L1 = 0.0254;
                L2 = 0.0254;
                break;
            case 1://D D/2
                L1 = D1;
                L2 = D1 / 2;
                break;
            case 2://Corner
                L1 = 0.0;
                L2 = 0.0;
                break;
            case 3://Custom
                try {
                    L1 = Double.parseDouble(in_L1.getText().toString());
                } catch (Exception e) {
                }
                try {
                    L2 = Double.parseDouble(in_L2.getText().toString());
                } catch (Exception e) {
                }
                switch (sp_L1.getSelectedItemPosition()) {
                    case 0://mm
                        L1 = L1 / 1000;
                        break;
                    case 1://in
                        L1 = L1 / 1000 * 25.4;
                        break;
                }
                switch (sp_L2.getSelectedItemPosition()) {
                    case 0://mm
                        L2 = L2 / 1000;
                        break;
                    case 1://in
                        L2 = L2 / 1000 * 25.4;
                        break;
                }
                break;
        }
        P2 = P1 - Pd;
        mw = sg * 28.97;
        Re0 = 435643.24;
        Pr = P2 / P1;
        mu = 0.000011594219;
        Z = 1.0;
        rho = 5.6362219;
        A1 = PI * D1 * D1 / 4;
        A2 = PI * D2 * D2 / 4;
        beta = D2 / D1;
        Y = 1 - (0.351 + 0.256 * pow(beta, 4) + 0.93 * pow(beta, 8)) * (1 - pow(P2 / P1, 1 / k));
        //Using Sutton correlation
        Double Tpc = 169.2 + 349.5 * sg - 74 * pow(sg, 2);
        Double Ppc = 756.8 - 131 * sg - 3.6 * pow(sg, 2);

        M2 = 2 * (L2 / D1) / (1 - beta);
        for (int i = 0; i < 100; ++i) {
            Z = Zfactor(1.8 * T1 / Tpc, 0.000145038 * P1 / Ppc, Ppc, SetZ, 1e-6);
            rho = P1 * mw / (Z * 8.31446 * T1) / 1000;
            mu = Viscosity(1.8 * T1 / Tpc, 0.000145038 * P1 / Ppc, mw, 1.8 * T1, rho * 0.062428, sg, 0.0, 0.0, SetMu) * 0.001;
            A = pow((19000 * beta / Re0), 0.8);
            C = 0.5961 + 0.0261 * pow(beta, 2) - 0.216 * pow(beta, 8) + 0.000521 * pow(1000000 * beta / Re0, 0.7) + (0.0188 + 0.0063 * A) * pow(beta, 3.5) * pow(1000000 / Re0, 0.3) + (0.043 + 0.08 * exp(-10 * L1 / D1) - 0.123 * exp(-7 * L1 / D1)) * (1 - 0.11 * A) * (pow(beta, 4) / (1 - pow(beta, 4))) - 0.031 * (M2 - 0.8 * pow(M2, 1.1)) * pow(beta, 1.3);
            if (D1 < 2.8 * 2.54 / 100) {
                C = C + 0.011 * (0.7 - beta) * (2.8 * 2.54 / 100 - D1);
            }
            Pd = P1 - P2;
            Q = C / sqrt(1 - pow(beta, 4)) * Y * A2 * sqrt(2 * Pd * rho) / rho;
            v = Q / A1;
            Re1 = D1 * v * rho / mu;
            if (abs(Re1 - Re0) < tol) {
                break;
            }
            Re0 = Re1;
        }
        option_tap = sp_tap.getSelectedItem().toString();

        Intent intent = new Intent(this, OutputActivity.class);
        startActivity(intent);

    }// calc

    // -------------------------------------------------------------------- //
    // Calculates Z-factors based on selected correlation
    public Double Zfactor(Double Tpr, Double Ppr, Double Ppc, int pos, Double tol) {
        Double Z = 1.0;
        Double cg = 1.0;
        Double fy = 1.0;
        Double fdy = 1.0;
        Double yn = 1e-3;
        Double y = 1.0;
        switch (pos) {
            case 0://Hall and Yarborough
                Double t = 1 / Tpr;
                Double a = 0.06125 * t * exp(-1.2 * pow((1 - t), 2));
                Double A = 14.76 * t - 9.76 * pow(t, 2) + 4.58 * pow(t, 3);
                Double B = 90.7 * t - 242.2 * pow(t, 2) + 42.4 * pow(t, 3);
                Double C = 2.18 + 2.82 * t;
                //while (abs(fy)>tol) {
                for (int i = 0; (i < 1000) || (abs(fy) > tol); ++i) {
                    y = yn;
                    fy = -a * Ppr + (y + pow(y, 2) + pow(y, 3) - pow(y, 4)) / pow((1 - y), 3) - A * pow(y, 2) + B * pow(y, C);
                    fdy = (1 + 4 * y + 4 * pow(y, 2) - 4 * pow(y, 3) + pow(y, 4)) / pow((1 - y), 4) - 2 * A * y + B * C * pow(y, C);
                    yn = y - fy / fdy;
                }
                Z = a * Ppr / y;
                cg = 1 / (Ppr * Ppc) + 1 / Z * (a / Ppc * (1 / y - a * Ppr / pow(y, 2) / fdy));
                break;

            case 1://Dranchuk and Abou-Kassem
                Z = 1.0;
                cg = 1.0;
                fy = 1.0;
                yn = .5;
                y = 1.0;
                Double Rr = 0.0, C4 = 0.0, A1 = 0.3265, A2 = -1.0700, A3 = -0.5339, A4 = 0.01569, A5 = -0.05165, A6 = 0.5475, A7 = -0.7361, A8 = 0.1844, A9 = 0.1056, A10 = 0.6134, A11 = 0.7210;
                Double C1 = (A1 + A2 / Tpr + A3 / pow(Tpr, 3) + A4 / pow(Tpr, 4) + A5 / pow(Tpr, 5));
                Double C2 = (A6 + A7 / Tpr + A8 / pow(Tpr, 2));
                Double C3 = A9 * (A7 / Tpr + A8 / pow(Tpr, 2));

                //while (abs(fy)>tol) {
                for (int i = 0; (i < 1000) || (abs(fy) > tol); ++i) {
                    y = yn;
                    Rr = 0.27 * Ppr / (y * Tpr);
                    C4 = A10 * (1 + A11 * pow(Rr, 2)) * (pow(Rr, 2) / pow(Tpr, 3)) * exp(-A11 * pow(Rr, 2));
                    fy = y - (1 + C1 * Rr + C2 * pow(Rr, 2) - C3 * pow(Rr, 5) + C4);
                    fdy = 1 + C1 * Rr / y + 2 * C2 * pow(Rr, 2) / y - 5 * C3 * pow(Rr, 5) / y + 2 * A10 * (pow(Rr, 2) / pow(Tpr, 3) / y) * (1 + A11 * pow(Rr, 2) + pow((A11 * pow(Rr, 2)), 2)) * exp(-A11 * pow(Rr, 2));
                    yn = y - fy / fdy;
                }
                Z = yn;
                Double dzdRr = C1 + 2 * C2 * Rr - 5 * C3 * pow(Rr, 4) + 2 * A10 * (Rr / pow(Tpr, 3)) * (1 + A11 * pow(Rr, 2) - pow((A11 * pow(Rr, 2)), 2)) * exp(-A11 * pow(Rr, 2));
                cg = (1 / Ppr - 0.27 / pow(Z, 2) / Tpr * (dzdRr / (1 + Rr / Z * dzdRr))) / Ppc;
                break;
        }
        if (Z < 0.01) Z = 0.01;
        return Z;
    } // Zfactor


    // -------------------------------------------------------------------- //
    // Calculates gas viscosity based on selected correlation
    public Double Viscosity(Double Tpr, Double Ppr, Double M, Double T, Double rhog, Double sg, Double h2s, Double co2, int pos) {
        Double mu = 1e-3;
        switch (pos) {
            case 0:
                //Lee et al
                Double a1 = ((9.379 + .01607 * M) * pow(T, 1.5)) / (209.2 + 19.26 * M + T);
                Double a2 = 3.448 + (986.4 / T) + .01009 * M;
                Double a3 = 2.447 - .2224 * a2;
                mu = a1 * pow(10, -4) * exp(a2 * pow((rhog / 62.428), a3));
                break;
            case 1:
                //Carr et al
                Double b1 = -2.46211820, b2 = 2.970547414, b3 = -.286264054, b4 = .00805420522, b5 = 2.80860949, b6 = -3.49803305, b7 = .3603702, b8 = -.01044324, b9 = -.793385648, b10 = 1.39643306, b11 = -.149144925, b12 = .00441015512, b13 = .0839387178, b14 = -.18648848, b15 = .0203367881, b16 = -.000609579263;
                Double mu_g1 = (1.709 / 100000 - 2.062 / 1000000 * sg) * (T - 460) + 8.188 / 1000 - 6.15 / 1000 * log10(sg) + co2 * (9.08 / 1000 * log10(sg) + 6.24 / 1000) + h2s * (8.49 / 1000 * log10(sg) + 3.73 / 1000);
                Double X_1 = b1 + b2 * Ppr + b3 * pow(Ppr, 2) + b4 * pow(Ppr, 3) + (b5 + b6 * Ppr + b7 * pow(Ppr, 2) + b8 * pow(Ppr, 3)) * Tpr + (b9 + b10 * Ppr + b11 * pow(Ppr, 2) + b12 * pow(Ppr, 3)) * pow(Tpr, 2) + (b13 + b14 * Ppr + b15 * pow(Ppr, 2) + b16 * pow(Ppr, 3)) * pow(Tpr, 3);
                mu = mu_g1 * exp(X_1) / Tpr;
                break;
        }
        return mu;
    } // Viscosity


    // -------------------------------------------------------------------- //
    // Displays settings activity
    public void settings(View view) {
        Spinner sp_pipeD = (Spinner) findViewById(R.id.sp_pipeD);
        Spinner sp_orfd = (Spinner) findViewById(R.id.sp_orfd);
        Spinner sp_upP = (Spinner) findViewById(R.id.sp_upP);
        Spinner sp_diffP = (Spinner) findViewById(R.id.sp_diffP);
        Spinner sp_dsT = (Spinner) findViewById(R.id.sp_dsT);
        Spinner sp_L1 = (Spinner) findViewById(R.id.sp_L1);
        Spinner sp_L2 = (Spinner) findViewById(R.id.sp_L2);
        Spinner sp_tap = (Spinner) findViewById(R.id.sp_tap);

        EditText in_pipeD = (EditText) findViewById(R.id.in_pipeD);
        EditText in_orfd = (EditText) findViewById(R.id.in_orfd);
        EditText in_upP = (EditText) findViewById(R.id.in_upP);
        EditText in_diffP = (EditText) findViewById(R.id.in_diffP);
        EditText in_dsT = (EditText) findViewById(R.id.in_dsT);
        EditText in_sg = (EditText) findViewById(R.id.in_sg);
        EditText in_k = (EditText) findViewById(R.id.in_k);
        EditText in_L1 = (EditText) findViewById(R.id.in_L1);
        EditText in_L2 = (EditText) findViewById(R.id.in_L2);
        try {
            D1 = Double.parseDouble(in_pipeD.getText().toString());
        } catch (Exception e) {
        }
        try {
            D2 = Double.parseDouble(in_orfd.getText().toString());
        } catch (Exception e) {
        }
        try {
            P1 = Double.parseDouble(in_upP.getText().toString());
        } catch (Exception e) {
        }
        try {
            Pd = Double.parseDouble(in_diffP.getText().toString());
        } catch (Exception e) {
        }
        try {
            T1 = Double.parseDouble(in_dsT.getText().toString());
        } catch (Exception e) {
        }
        try {
            sg = Double.parseDouble(in_sg.getText().toString());
        } catch (Exception e) {
        }
        try {
            k = Double.parseDouble(in_k.getText().toString());
        } catch (Exception e) {
        }

        SetPD = sp_pipeD.getSelectedItemPosition();
        SetOd = sp_orfd.getSelectedItemPosition();
        SetUpP = sp_upP.getSelectedItemPosition();
        SetDp = sp_diffP.getSelectedItemPosition();
        SetT = sp_dsT.getSelectedItemPosition();
        SetTap = sp_tap.getSelectedItemPosition();
        SetL1 = sp_L1.getSelectedItemPosition();
        SetL2 = sp_L2.getSelectedItemPosition();
        if (SetTap == 3) {
            try {
                L1 = Double.parseDouble(in_L1.getText().toString());
            } catch (Exception e) {
            }
            try {
                L2 = Double.parseDouble(in_L2.getText().toString());
            } catch (Exception e) {
            }
        }
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    } // settings


    // -------------------------------------------------------------------- //
    // Displays chart readings activity
    public void chart(View view) {
        Spinner sp_pipeD = (Spinner) findViewById(R.id.sp_pipeD);
        Spinner sp_orfd = (Spinner) findViewById(R.id.sp_orfd);
        Spinner sp_upP = (Spinner) findViewById(R.id.sp_upP);
        Spinner sp_diffP = (Spinner) findViewById(R.id.sp_diffP);
        Spinner sp_dsT = (Spinner) findViewById(R.id.sp_dsT);
        Spinner sp_L1 = (Spinner) findViewById(R.id.sp_L1);
        Spinner sp_L2 = (Spinner) findViewById(R.id.sp_L2);
        Spinner sp_tap = (Spinner) findViewById(R.id.sp_tap);

        EditText in_pipeD = (EditText) findViewById(R.id.in_pipeD);
        EditText in_orfd = (EditText) findViewById(R.id.in_orfd);
        EditText in_upP = (EditText) findViewById(R.id.in_upP);
        EditText in_diffP = (EditText) findViewById(R.id.in_diffP);
        EditText in_dsT = (EditText) findViewById(R.id.in_dsT);
        EditText in_sg = (EditText) findViewById(R.id.in_sg);
        EditText in_k = (EditText) findViewById(R.id.in_k);
        EditText in_L1 = (EditText) findViewById(R.id.in_L1);
        EditText in_L2 = (EditText) findViewById(R.id.in_L2);
        try {
            D1 = Double.parseDouble(in_pipeD.getText().toString());
        } catch (Exception e) {
        }
        try {
            D2 = Double.parseDouble(in_orfd.getText().toString());
        } catch (Exception e) {
        }
        try {
            P1 = Double.parseDouble(in_upP.getText().toString());
        } catch (Exception e) {
        }
        try {
            Pd = Double.parseDouble(in_diffP.getText().toString());
        } catch (Exception e) {
        }
        try {
            T1 = Double.parseDouble(in_dsT.getText().toString());
        } catch (Exception e) {
        }
        try {
            sg = Double.parseDouble(in_sg.getText().toString());
        } catch (Exception e) {
        }
        try {
            k = Double.parseDouble(in_k.getText().toString());
        } catch (Exception e) {
        }

        SetPD = sp_pipeD.getSelectedItemPosition();
        SetOd = sp_orfd.getSelectedItemPosition();
        SetUpP = sp_upP.getSelectedItemPosition();
        SetDp = sp_diffP.getSelectedItemPosition();
        SetT = sp_dsT.getSelectedItemPosition();
        SetTap = sp_tap.getSelectedItemPosition();
        SetL1 = sp_L1.getSelectedItemPosition();
        SetL2 = sp_L2.getSelectedItemPosition();
        if (SetTap == 3) {
            try {
                L1 = Double.parseDouble(in_L1.getText().toString());
            } catch (Exception e) {
            }
            try {
                L2 = Double.parseDouble(in_L2.getText().toString());
            } catch (Exception e) {
            }
        }
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    } // chart


    // -------------------------------------------------------------------- //
    // Displays about activity
    public void about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    } // about
}


/****************************************************************************/
