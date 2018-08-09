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
 *   Source file for Text Activity Class                                     *
 *   Shows the output result as a text report for emailing and sharing etc.  *
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
// Displays Text activity
public class TextActivity extends AppCompatActivity {
    // -------------------------------------------------------------------- //
    // Actions to perform on activity creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textonly);

        TextView op_result = (TextView) findViewById(R.id.eEditTex1t);
        op_result.setText("");
        switch (j) {
            case 0:
                op_result.append(""
                        + "###########################################" + "\n"
                        + "INPUT DATA" + "\n"
                        + "###########################################");

                op_result.append(String.format(Locale.getDefault(),""
                                + "\nPipe Diameter= " + "%.3f" + " in"
                                + "\nOrifice Diameter= " + "%.3f" + " in"
                                + "\nUpstream Pressure= " + "%.1f" + " psi"
                                + "\nDownstream Pressure= " + "%.1f" + " psi"
                                + "\nDifferential Pressure= " + "%.1f" + " psi"
                                + "\nDownstream Temperature= " + "%.1f" + " F"
                                + "\nGas Specific Gravity= " + "%.4f" + " [air=1]"
                                + "\nIsentropic Expansion Coeff= " + "%.3f" + " "
                        , D1 *39.3701, D2 *39.3701, P1 *0.000145038, P2*0.000145038, Pd*0.000145038, 9.0/5.0*(T1 - 273.15)+32.0, sg, k));

                if (chart==1) {
                    op_result.append(String.format(Locale.getDefault(),"\n\nCHART READINGS"
                                    + "\n BTR= %.1f"
                                    + "\n DPR= %.1f"
                                    + "\n Static Pressure Reading= %.2f"
                                    + "\n Differential Pressure Reading= %.2f"
                                    + "\n Temperature Reading= %.2f"
                            , btr, dpr, S, DP, T));
                }

                op_result.append(""
                                    + "\n\n" + option_tap);
                op_result.append(String.format(Locale.getDefault(),""
                                    + "\n Upstream Tap Distance= " + "%.2f" + " in"
                                    + "\n Downstream Tap Distance= " + "%.2f" + " in"
                        , L1 *39.3701, L2 *39.3701));

                op_result.append("\n\n"
                        + "###########################################" + "\n"
                        + "INTERMEDIATE CALCULATIONS" + "\n"
                        + "###########################################");

                op_result.append(String.format(Locale.getDefault(),""
                                + "\nGas Z-Factor= " + "%.4f" + " " + " (" + option_Z + ")"
                                + "\nGas Viscosity= " + "%.3e" + " cp" + " (" + option_mu + ")"
                                + "\nGas Density= " + "%.3f" + " lb/ft3"
                                + "\nGas Molecular Wt= " + "%.1f" + " lb/lb-mol"
                                + "\nExpansion Factor= " + "%.4f" + " "
                                + "\nDiameter Ratio= " + "%.2f" + " "
                                + "\nPipe Area= " + "%.3e" + " ft2"
                                + "\nOrifice Area= " + "%.3e" + " ft2"
                                + "\nReynolds Number= " + "%.4e" + " "
                        , Z, mu*1000.0, rho*0.062428, mw, Y, beta, A1*10.7639, A2*10.7639, Re1));

                op_result.append("\n\n"
                        + "###########################################" + "\n"
                        + "FLOW RATES" + "\n"
                        + "###########################################");
                op_result.append(String.format(Locale.getDefault(),""
                                + "\nVolumetric Rate at 0C and 1 atm= " + "%.3f" + " MMScfD"
                                + "\nVolumetric Rate at %.1f F and %.1f psi= " + "%.4f" + " MMcfD"
                                + "\nMass Flow Rate= " + "%.2f" + " lb/s"
                                + "\nMolar Flow Rate= " + "%.3f" + " lb-mol/s"
                                + "\nPipe Velocity= " + "%.1f" + " ft/s"
                                + "\nOrifice Velocity= " + "%.1f" + " ft/s"
                        , Q * rho / mw * 22.4 / Z*3.051/492*520, 9.0/5.0*(T1 - 273.15)+32.0, P1 *0.000145038, Q*3.051, Q * rho*2.20462, Q *2.20462 * rho / mw, Q / A1*3.28084, Q / A2*3.28084));
                break;
            case 1:
                op_result.append(""
                        + "###########################################" + "\n"
                        + "INPUT DATA" + "\n"
                        + "###########################################");

                op_result.append(String.format(Locale.getDefault(),""
                                + "\nPipe Diameter= " + "%.2f" + " mm"
                                + "\nOrifice Diameter= " + "%.2f" + " mm"
                                + "\nUpstream Pressure= " + "%.1f" + " kPa"
                                + "\nDownstream Pressure= " + "%.1f" + " kPa"
                                + "\nDifferential Pressure= " + "%.1f" + " kPa"
                                + "\nDownstream Temperature= " + "%.1f" + " C"
                                + "\nGas Specific Gravity= " + "%.4f" + " [air=1]"
                                + "\nIsentropic Expansion Coeff= " + "%.3f" + " "
                        , D1 * 1000.0, D2 * 1000.0, P1 / 1000.0, P2 / 1000.0, Pd / 1000.0, T1 - 273.15, sg, k));

                op_result.append(""
                        + "\n" + option_tap);
                op_result.append(String.format(Locale.getDefault(),""
                                + "\nUpstream Tap Distance= " + "%.1f" + " mm"
                                + "\nDownstream Tap Distance= " + "%.1f" + " mm"
                        , L1 * 1000, L2 * 1000));

                op_result.append("\n\n"
                        + "###########################################" + "\n"
                        + "INTERMEDIATE CALCULATIONS" + "\n"
                        + "###########################################");

                op_result.append(String.format(Locale.getDefault(),""
                                + "\nGas Z-Factor= " + "%.4f" + " " + " (" + option_Z + ")"
                                + "\nGas Viscosity= " + "%.3e" + " Pa.s" + " (" + option_mu + ")"
                                + "\nGas Density= " + "%.3f" + " kg/m3"
                                + "\nGas Molecular Wt= " + "%.1f" + " g/g-mol"
                                + "\nExpansion Factor= " + "%.4f" + " "
                                + "\nDiameter Ratio= " + "%.2f" + " "
                                + "\nPipe Area= " + "%.3e" + " m2"
                                + "\nOrifice Area= " + "%.3e" + " m2"
                                + "\nReynolds Number= " + "%.4e" + " "
                        , Z, mu, rho, mw, Y, beta, A1, A2, Re1));

                op_result.append("\n\n"
                        + "###########################################" + "\n"
                        + "FLOW RATES" + "\n"
                        + "###########################################");
                op_result.append(String.format(Locale.getDefault(),""
                                + "\nVolumetric Rate at 0C and 1 atm= " + "%.3f" + " Std m3/s"
                                + "\nVolumetric Rate at %.1f C and %.1f kPa= " + "%.4f" + " m3/s"
                                + "\nMass Flow Rate= " + "%.3f" + " kg/s"
                                + "\nMolar Flow Rate= " + "%.1f" + " g-mol/s"
                                + "\nPipe Velocity= " + "%.1f" + " m/s"
                                + "\nOrifice Velocity= " + "%.1f" + " m/s"
                        , Q * rho / mw * 22.4 / Z, T1 - 273.15, P1 / 1000, Q, Q * rho, Q * rho * 1000 / mw , Q / A1, Q / A2));
                break;
        }
    } // onCreate


    // -------------------------------------------------------------------- //
    // Actions to perform on email button click
    // Opens a sharing dialog box for forwarding the report text.
    public void email(View view) {
        TextView op_result = (TextView) findViewById(R.id.eEditTex1t);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Orifice Gas Flow Rate Report from PetroSimple App");
        intent.putExtra(Intent.EXTRA_TEXT, op_result.getText());
        startActivity(Intent.createChooser(intent, "Send Email"));
    } // email
}


/****************************************************************************/
