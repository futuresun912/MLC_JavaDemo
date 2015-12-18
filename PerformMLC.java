/**
 * Created by sunlu on 12/9/15.
 */

import meka.classifiers.multilabel.*;


public class PerformMLC {

    // ***********************************
    // Specify the setting of experiments
    // ***********************************
    static String n = "5";                       // n-fold CV
    static String percent = "75.0";              // split percentage
    static String outputType = "2";              // 1, 2, 3, 4, 5, 6
    static String baseline = "Logistic";         // Logistic, NaiveBayes, SMO...
    static boolean flagCV = false;               // use CV if true
    static boolean flagDebug = false;            // output debug infor. if true
    // ***********************************
    static boolean flagMulan = false;            // Use mulan if true
    static String[] options = new String[14];    // specified options
    static String mulanMehtod;                   // set the method of mulan
    static String dataList[] = {
            "emotions",     // 0
            "scene",        // 1
            "flags",        // 2
            "yeast",        // 3
            "birds",        // 4
            "genbase",      // 5
            "medical",      // 6
            "enron",        // 7
            "languagelog",  // 8
            "bibtex",       // 9
            "Corel5k",      // 10
            "mediamill",    // 11
            "CAL500",       // 12
            "rcv1subset1",  // 13
            "rcv1subset3",  // 14
            "delicious"     // 15
    };

    public static void main(String[] args) throws Exception {

        // **********************************
        // Select a dataset and MLC method.
        // **********************************
        String filename = dataList[0];
        String mlMetond = "homer";
        // **********************************

        switch (mlMetond) {
            case "br":
                BR br = new BR();
                setTestOptions(filename);
                Evaluation.runExperiment(br, options);
                break;
            case "cc":
                CC cc = new CC();
                setTestOptions(filename);
                Evaluation.runExperiment(cc, options);
                break;
            case "mlknn":
                flagMulan = true;
                mulanMehtod = "mlknn";
                setTestOptions(filename);
                MULAN mlknn = new MULAN();
                Evaluation.runExperiment(mlknn, options);
                break;
            case "homer":
                flagMulan = true;
                mulanMehtod = "homer";
                MULAN homer = new MULAN();
                setTestOptions(filename);
                Evaluation.runExperiment(homer, options);
                break;
            case "rakel":
                flagMulan = true;
                mulanMehtod = "rakel";
                MULAN rakel = new MULAN();
                setTestOptions(filename);
                Evaluation.runExperiment(rakel, options);
                break;
            case "paccldf":
                setTestOptions(filename);
                PACCLDF paccldf = new PACCLDF();
                Evaluation.runExperiment(paccldf, options);
                break;
        }
    }

    public static void setTestOptions(String arffname) {

        // select a data set
        options[0] = "-t";
        options[1] = "/home/pipi/workspace/data/" + arffname + ".arff";

        if (flagCV) {
            // use n-fold cross validation
            options[2] = "-x";
            options[3] = n;
        } else {
            // split train/test in percent%
            options[2] = "-split-percentage";
            options[3] = percent;
        }

        // seed for randomizing
        options[4] = "-s";
        options[5] = "1";
        options[6] = "-R";

        // output type
        options[7] = "-verbosity";
        options[8] = outputType;

        // choose the baseline classifier
        options[9] = "-W";
        if (baseline == "NaiveBayes")
            options[10] = "weka.classifiers.bayes.NaiveBayes";
        else
            options[10] = "weka.classifiers.functions." + baseline;

        // output debug information
        if (flagDebug)
            options[11] = "-output-debug-info";
        else
            options[11] = "";

        if (flagMulan) {
            options[12] = "-S";
            if (mulanMehtod == "mlknn")
                options[13] = "MLkNN"; // default: MLkNN
            else if (mulanMehtod == "rakel") {
                options[10] = "weka.classifiers.trees.J48";
                options[13] = "RAkEL2";
            }
            else if (mulanMehtod == "homer")
                options[13] = "HOMER.BalancedClustering.4.ClassifierChain";
            else
                System.out.println("Please use a method in Mulan.");
        } else {
            options[12] = "";
            options[13] = "";
        }
    }
}



