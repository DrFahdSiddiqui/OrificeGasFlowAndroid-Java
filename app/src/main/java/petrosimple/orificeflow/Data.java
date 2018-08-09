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
 *   Source file for data objects Class                                      *
 *   Last updated 08/08/2018                                                 *
 ****************************************************************************/

/*****************************************************************************
 * TODO                                                                      *
 *   Better way of coding (this looks like a hacky way)                      *
 ****************************************************************************/


/****************************************************************************/


package petrosimple.orificeflow;


// ------------------------------------------------------------------------ //
// Class for holding data pieces for sharing between activities
public class Data {
    public static Data mInstance = null;

    static Double D1 = 5.0, D2 = 3.5, sg = 15.996 / 28.97, L1 = 1.0, L2 = 1.0, P1 = 130.0, Pd = 25.3, P2 = P1 - Pd, T1 = 100.0, k = 1.3023845, tol = 0.001;
    static Double mw = sg * 28.97, Re0 = 435643.24, Pr = P2 / P1, mu = 0.000011594219, Z = 1.0, rho = 5.6362219;
    static Double A1, A2, beta, Y, M2, A, C, Re1, Q, v;
    static int SetZ = 0, SetMu = 0, j = 1;
    static int SetPD = 1, SetOd = 1, SetUpP = 1, SetDp = 1, SetT = 1, SetTap = 0, SetL1 = 0, SetL2 = 0;
    static String option_tap, option_Z = "  Hall and Yarborough", option_mu = "  Lee et al";
    static Double btr = 2000.0, dpr = 200.0, S = 1.0, DP = 1.0, T = 1.0;
    static int chart = 0;

    public static Data ta() {
        if (mInstance == null) {
            mInstance = new Data();
        }
        return mInstance;
    }
}


/****************************************************************************/
