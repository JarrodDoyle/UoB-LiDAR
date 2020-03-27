package com.lidar.lidar;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.BuoySampleFactory;
import com.lidar.lidar.samples.MastSampleFactory;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    SystemManager systemManager;

    @Autowired
    BuoyTable buoyTable;

    @Autowired
    MastTable mastTable;

    /*@Bean(name="fileLoader")
    public FileLoader fileLoader() {
        return new FileLoader();
    }*/

    public void loadFiles() {
        Iterable<LoadedFile> lFiles = loadedFiles.findAll();

        for (final File f : sortFiles(new File("../../data").listFiles())) {
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
                    System.out.println("Saved: " + nlf.toString() + "\n");

                    try {
                        if (f.canRead()) {
                            BufferedReader reader = new BufferedReader(new FileReader(f));
                            reader.readLine();
                            reader.skip(15);
                            String serial = reader.readLine().replace(",", "").replaceAll("^[ \t]+|[ \t]+$", "");
                            reader.readLine();
                            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                if (buoyTable.existsById(serial)) {
                                    try {
                                        systemManager.addBuoySample(BuoySampleFactory.fromCSVLine(serial, line));
                                    }
                                    catch (IllegalArgumentException e) {
                                        
                                    }
                                }
                                else if (mastTable.existsById(serial)) {
                                    try {
                                        systemManager.addMastSample(MastSampleFactory.fromCSVLine(serial, line));
                                    }
                                    catch (IllegalArgumentException e) {
        
                                    }
                                }
                            }
                            reader.close();
                        }
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("File not found: " + f.getName());
                        System.out.println("May have been deleted while loading data.");
                    }
                    catch (IOException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }
    }

    private List<File> sortFiles(File[] files) {
        List<File> sortedFiles = new ArrayList<File>();
        
        for (File file : files) {
            Boolean added = false;
            for (Integer i = 0; i < sortedFiles.size(); i++) {
                Instant newTime = getFileTime(file);
                Instant time = getFileTime(sortedFiles.get(i));
                if (newTime.compareTo(time) < 0) {
                    added = true;
                    sortedFiles.add(i, file);
                    break;
                }
            }
            if (!added) {
                sortedFiles.add(file);
            }
        }

        return sortedFiles;
    }

    private Instant getFileTime(File file) {
        String name = file.getName();
        Integer start = name.length() - 14;
        Integer end = name.length() - 4;
        String timestamp = name.substring(start, end) + "T00:00:00Z";
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(timestamp));
    }
}