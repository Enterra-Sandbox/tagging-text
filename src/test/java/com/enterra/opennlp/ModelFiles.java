package com.enterra.opennlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.rules.ExternalResource;

import com.enterra.opennlp.util.OpennlpModels;
import com.google.common.io.Closer;

public class ModelFiles extends ExternalResource {

    private Closer closer;
    
    @Override
    protected void before() throws Throwable {
        closer = Closer.create();
    }
    
    public InputStream openModel(String model) throws IOException {
        File file = OpennlpModels.getModelFile(model);
        InputStream is = new FileInputStream(file);
        closer.register(is);
        return is;
    }

    @Override
    protected void after() {
        try {
            closer.close();
        } catch (IOException e) {
        }
    }

}
