package com.enterra.opennlp;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;

import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import org.junit.Rule;
import org.junit.Test;

public class SentenceDetectionTest {
    final String testString = "This is a sentence.  It has fruits, vegetables,"
            + " etc. but does not have meat.  Mr. Smith went to Washington.";

    @Rule
    public final ModelFiles modelFiles = new ModelFiles();

    @Test
    public void testJavasBreakIterator() throws Exception {
        BreakIterator sentIterator = BreakIterator.getSentenceInstance(Locale.US);
        sentIterator.setText(testString);
        int end;
        List<String> sentences = newArrayList();
        System.out.println("BreakIterator sentences");
        for (int start = sentIterator.first(); (end = sentIterator.next()) != BreakIterator.DONE; start = end) {
            String sentence = testString.substring(start, end);
            sentences.add(sentence);
            System.out.printf("Sentence: %s%n", sentence);
        }
        assertEquals(4, sentences.size());
    }

    @Test
    public void testOpennlp() throws Exception {
        SentenceModel model = new SentenceModel(modelFiles.openModel("en-sent.bin"));
        SentenceDetector detector = new SentenceDetectorME(model);
        List<String> sentences = newArrayList(detector.sentDetect(testString));
        System.out.println("OpenNLP sentences");
        for (String sentence : sentences) {
            System.out.printf("Sentence: %s%n", sentence);
        }
        assertEquals(3, sentences.size());
    }
}
