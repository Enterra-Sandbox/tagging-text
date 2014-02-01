package com.enterra.opennlp.util;

import java.io.File;

public class OpennlpModels {
    private static final File OPENNLP_MODEL_DIR;
    static {
        String opennlpModelPath = System.getProperty("opennlp.models", "opennlp-models");
        OPENNLP_MODEL_DIR = new File(opennlpModelPath);
        if (!OPENNLP_MODEL_DIR.isDirectory()) {
            throw new IllegalStateException(OPENNLP_MODEL_DIR + " is not a directory");
        }
    }
    
    public static File getModelFile(String filename) {
        File model = new File(OPENNLP_MODEL_DIR, filename);
        if (!model.exists()) {
            throw new IllegalArgumentException(filename + ": no such opennlp model in " + OPENNLP_MODEL_DIR);
        }
        return model;
    }
}
