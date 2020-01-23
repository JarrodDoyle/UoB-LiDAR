package com.lidar.lidar;

import com.lidar.lidar.database.*;

import java.util.*;

import java.io.FileReader;
import java.io.File;

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
    TestTable tests;

    @Autowired
    LoadedFileTable loadedFiles;

    @Bean(name="fileLoader")
    public FileLoader fileLoader() {
        return new FileLoader();
    }

    public void loadFiles() {
        Iterable<LoadedFile> lFiles = loadedFiles.findAll();

        //for (final File f : "./")
    }
}