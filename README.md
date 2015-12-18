# MLC_JavaDemo
This is a demo program in java for multi-label classification.

The program is built based on:
weka-3.7.12 (URL: http://sourceforge.net/projects/weka/files/weka-3-7/3.7.12/); 
mulan-1.4.0 (URL: http://sourceforge.net/projects/mulan/files/mulan-1-4/mulan-1.4.0.zip/download); 
meka-1.7.7 (URL: http://sourceforge.net/projects/meka/files/meka-1.7.7/); mst (URL: http://algs4.cs.princeton.edu/code/algs4.jar).

The program includes:
1. PerformMLC.java
the main function;
2. PACCLDF.java
polytree-augmented classifier chains with label-dependent features;
3. MLFeaSelect.java
two-stage label-dependent feature selection;
4. polytree.java
build the polytree structure in label space;
5. StatUtilsPro.java
Statistical functions for MLC;
6. GreedyCC
greedy search method with specified start set.
