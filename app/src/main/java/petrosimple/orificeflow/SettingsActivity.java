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
 *   Source file for Settings Activity Class                                 *
 *   Displays options for selecting correlations                             *
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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static petrosimple.orificeflow.Data.*;


// ------------------------------------------------------------------------ //
// Displays Settings activity
public class SettingsActivity extends AppCompatActivity {
    // -------------------------------------------------------------------- //
    // Actions to perform on activity creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Z-Factor
        Spinner sp_Z = (Spinner) findViewById(R.id.sp_Z);
        List<String> categories = new ArrayList<>();
        categories.add("  Hall and Yarborough");
        categories.add("  Dranchuk and Abou-Kassem");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_Z.setAdapter(dataAdapter);
        sp_Z.setSelection(SetZ);

        //Viscosity
        Spinner sp_mu = (Spinner) findViewById(R.id.sp_mu);
        categories = new ArrayList<>();
        categories.add("  Lee et al");
        categories.add("  Carr et al");
        dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mu.setAdapter(dataAdapter);
        sp_mu.setSelection(SetMu);
    } // onCreate


    // -------------------------------------------------------------------- //
    // Actions to perform on save button click
    // Saves the correlations settings to be used
    public void save(View view) {
        Spinner sp_Z = (Spinner) findViewById(R.id.sp_Z);
        Spinner sp_mu = (Spinner) findViewById(R.id.sp_mu);
        SetZ = sp_Z.getSelectedItemPosition();
        SetMu = sp_mu.getSelectedItemPosition();
        option_Z = sp_Z.getSelectedItem().toString();
        option_mu = sp_mu.getSelectedItem().toString();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    } // save
}


/****************************************************************************/
