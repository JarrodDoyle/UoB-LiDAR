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
    MastTable masts;
    
    @Autowired
    MastSampleTable mastSamples;

    @Autowired
    BuoyTable buoys;

    @Autowired
    BuoySampleTable buoySamples;

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
                    loadedFiles.save(nlf);
                    saveEntries(f);
                }
            }
        }

        System.out.println("DONE");
    }

    private void saveEntries(File file) {
        try {
            Scanner sc = new Scanner(file);

            sc.useDelimiter(",|\\n");

            String serial = "";

            Boolean isMast = false;
            Mast mast = null;
            Buoy buoy = null;

            Integer i = 0;
            while (sc.hasNext()) {
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
                else if (i == 1) {
                    serial = timestamp.split(": ")[1];
                    Optional<Buoy> maybeBuoy = buoys.findById(serial);
                    Optional<Mast> maybeMast = masts.findById(serial);
                    if (maybeBuoy.isPresent()) {
                        isMast = false;
                        buoy = maybeBuoy.get();
                    }
                    else if (maybeMast.isPresent()) {
                        isMast = true;
                        mast = maybeMast.get();
                    }
                    else {
                        System.out.println("Device not registered with system.");
                        return;
                    }
                }
                else if (i > 3) {
                    if (isMast) {
                        MastSample sample = new MastSample();
                        sample.setMast(mast);
                        sample.setTimestamp(timestamp);
                        try {
                            sample.setDirection30m(Double.parseDouble(direction30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection40m(Double.parseDouble(direction40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection60m(Double.parseDouble(direction60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection80m(Double.parseDouble(direction80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection100m(Double.parseDouble(direction100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed30m(Double.parseDouble(speed30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed40m(Double.parseDouble(speed40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed60m(Double.parseDouble(speed60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed80m(Double.parseDouble(speed80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed100m(Double.parseDouble(speed100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi30m(Double.parseDouble(ti30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi40m(Double.parseDouble(ti40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi60m(Double.parseDouble(ti60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi80m(Double.parseDouble(ti80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi100m(Double.parseDouble(ti100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        mastSamples.save(sample);
                    }
                    else {
                        BuoySample sample = new BuoySample();
                        sample.setBuoy(buoy);
                        sample.setTimestamp(timestamp);
                        try {
                            sample.setDirection30m(Double.parseDouble(direction30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection40m(Double.parseDouble(direction40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection60m(Double.parseDouble(direction60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection80m(Double.parseDouble(direction80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setDirection100m(Double.parseDouble(direction100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed30m(Double.parseDouble(speed30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed40m(Double.parseDouble(speed40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed60m(Double.parseDouble(speed60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed80m(Double.parseDouble(speed80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setSpeed100m(Double.parseDouble(speed100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi30m(Double.parseDouble(ti30m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi40m(Double.parseDouble(ti40m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi60m(Double.parseDouble(ti60m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi80m(Double.parseDouble(ti80m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        try {
                            sample.setTi100m(Double.parseDouble(ti100m));
                        }
                        catch (NumberFormatException ex) {
                            
                        }
                        buoySamples.save(sample);
                    }
                }
                i++;
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error opening file.");
        }
    }
}