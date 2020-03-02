package com.lidar.lidar;

import com.lidar.lidar.database.*;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    /*@Bean(name="fileLoader")
    public FileLoader fileLoader() {
        return new FileLoader();
    }*/

    public void loadFiles() {
        Iterable<LoadedFile> lFiles = loadedFiles.findAll();

        for (final File f : new File("..").listFiles()) {
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
                            String serial = reader.readLine();
                            System.out.println(serial);
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
}