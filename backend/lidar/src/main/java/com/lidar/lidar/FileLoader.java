package com.lidar.lidar;

import com.lidar.lidar.database.*;

import java.util.*;

import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class FileLoader {
    @Autowired
    LoadedFileTable loadedFiles;

    @Autowired
    MastSampleTable mastSamples;

    public void loadFiles() {
        Iterable<LoadedFile> lFiles = loadedFiles.findAll();
        Boolean out = false;

        for (final File f : new File("../../processedData").listFiles()) {
            if (f.isFile()) {
                Boolean loaded = false;
                for (LoadedFile lf : lFiles) {
                    if (f.getName().equals(lf.getName())) {
                        loaded = true;
                    }
                }

                if (!loaded) {
                    LoadedFile nlf = new LoadedFile(f.getName());
                    //loadedFiles.save(nlf);
                    if (!out) {
                        saveEntries(f);
                        out = true;
                    }
                }
            }
        }
    }

    private void saveEntries(File file) {
        try {
            Scanner sc = new Scanner(file);

            sc.useDelimiter(",|\\n");

            Integer i = 0;
            while (sc.hasNext() && i < 50) {
                String timestamp = sc.next();
                String direction30m = sc.next();
                String direction40m = sc.next();
                String direction60m = sc.next();
                String direction80m = sc.next();
                String direction100m = sc.next();
                String speed30m = sc.next();
                String speed40m = sc.next();
                String speed60m = sc.next();
                String speed80m = sc.next();
                String speed100m = sc.next();
                String ti30m = sc.next();
                String ti40m = sc.next();
                String ti60m = sc.next();
                String ti80m = sc.next();
                String ti100m = sc.next();
                if (i == 0) {
                    if (!timestamp.startsWith("LOCATION")) return;
                }
                else if (i > 3) {
                    MastSample sample = new MastSample();
                    sample.setTimestamp(timestamp);
                    sample.setDirection30m(Double.parseDouble(direction30m));
                    sample.setDirection40m(Double.parseDouble(direction40m));
                    sample.setDirection60m(Double.parseDouble(direction60m));
                    sample.setDirection80m(Double.parseDouble(direction80m));
                    sample.setDirection100m(Double.parseDouble(direction100m));
                    sample.setSpeed30m(Double.parseDouble(speed30m));
                    sample.setSpeed40m(Double.parseDouble(speed40m));
                    sample.setSpeed60m(Double.parseDouble(speed60m));
                    sample.setSpeed80m(Double.parseDouble(speed80m));
                    sample.setSpeed100m(Double.parseDouble(speed100m));
                    sample.setTi30m(Double.parseDouble(ti30m));
                    sample.setTi40m(Double.parseDouble(ti40m));
                    sample.setTi60m(Double.parseDouble(ti60m));
                    sample.setTi80m(Double.parseDouble(ti80m));
                    sample.setTi100m(Double.parseDouble(ti100m));

                    System.out.println(sample.toString());
                }
                i++;
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error opening file.");
        }
    }
}