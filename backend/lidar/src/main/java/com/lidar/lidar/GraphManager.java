package com.lidar.lidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.*;

import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.time.*;
import java.time.format.DateTimeFormatter;

import com.lidar.lidar.samples.*;
import com.lidar.lidar.database.*;

@Controller
public class GraphManager implements InitializingBean {
    @Autowired
    BuoyTable buoys;

    @Autowired
    MastTable masts;

    @Autowired
    BuoyGraphTable buoyGraphs;

    @Autowired
    MastGraphTable mastGraphs;

    Map<String, Map<String, BufferedWriter>> files;
    Map<String, Map<String, List<String>>> filenames;

    public void afterPropertiesSet() {
        files = new HashMap<String, Map<String, BufferedWriter>>();
        filenames = new HashMap<String, Map<String, List<String>>>();

        File graphs = new File("../../graphs");

        for (final File serialFile : graphs.listFiles()) {
            Map<String, BufferedWriter> serialMap = new HashMap<String, BufferedWriter>();
            Map<String, List<String>> serialNameMap = new HashMap<String, List<String>>();

            for (final File nameFile : serialFile.listFiles()) {
                Instant latestTime = null;
                
                for (final File timeFile : nameFile.listFiles()) {
                    Instant thisTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(timeFile.getName()));

                    if (latestTime == null || thisTime.isAfter(latestTime)) {
                        latestTime = thisTime;
                    }
                }

                try {
                    if (latestTime != null) {
                        serialMap.put(nameFile.getName(), new BufferedWriter(new FileWriter(new File(nameFile, latestTime.toString()))));
                        List<String> nameFilenames = new ArrayList<String>();
                        nameFilenames.add(latestTime.toString());
                        serialNameMap.put(nameFile.getName(), nameFilenames);
                    }
                }
                catch (IOException e) {

                }
            }

            files.put(serialFile.getName(), serialMap);
            filenames.put(serialFile.getName(), serialNameMap);
        }
    }

    public void AddBuoySample(String serial, BuoySample sample) throws IllegalArgumentException {
        Optional<Buoy> maybeBuoy = buoys.findById(serial);
        if (maybeBuoy.isPresent()) {
            for (BuoyGraph graph : buoyGraphs.findByBuoy(maybeBuoy.get())) {
                BuoySample entry = graph.addSample(sample);

                if (entry != null) {
                    SaveBuoyEntry(serial, graph.getConfig().getName(), graph.getPageStart(), entry);
                }

                buoyGraphs.save(graph);
            }
        }
        else {
            throw new IllegalArgumentException("Serial doesn't match a buoy.");
        }
    }

    void SaveBuoyEntry(String serial, String name, String time, BuoySample entry) {
        try {
            if (!files.containsKey(serial)) {
                NewBuoyPage(serial, name, time);
            }
            else if (!files.get(serial).containsKey(name)) {
                NewBuoyPage(serial, name, time);
            }
            else if (!filenames.get(serial).get(name).get(filenames.get(serial).get(name).size() - 1).equals(time)) {
                NewBuoyPage(serial, name, time);
            }

            BufferedWriter fw = files.get(serial).get(name);

            fw.append(entry.toCSVLine() + "\n");
        }
        catch (IOException e) {

        }
    }

    void NewBuoyPage(String serial, String name, String time) {
        if (!files.containsKey(serial)) {
            new File("../../graphs/" + serial).mkdir();
            files.put(serial, new HashMap<String, BufferedWriter>());
            filenames.put(serial, new HashMap<String, List<String>>());
        }

        Map<String, BufferedWriter> serialMap = files.get(serial);
        Map<String, List<String>> serialNameMap = filenames.get(serial);

        if (!serialNameMap.containsKey(name)) {
            new File("../../graphs/" + serial + "/" + name).mkdir();
            serialNameMap.put(name, new ArrayList<String>());
        }
            
        File f = new File("../../graphs/" + serial + "/" + name + "/" + time);

        try {
            f.createNewFile();
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));

            fw.append("LOCATION: \n" + 
                    "SYSTEM SERIAL: " + serial + "\n" +
                    "TIMESTAMP (ISO-8601) UTC,WindDir004m deg,WindDir030m deg,WindDir040m deg,WindDir040m_ref deg," + 
                    "WindDir060m deg,WindDir080m deg,WindDir100m deg,WindDir120m deg,WindDir140m deg," + 
                    "WindDir160m deg,WindDir180m deg,WindDir200m deg,WindGust004m m/s,WindSpeed004m m/s," + 
                    "WindSpeed030mh m/s,WindSpeed040mh m/s,WindSpeed040m_refh m/s,WindSpeed060mh m/s,WindSpeed080mh m/s," + 
                    "WindSpeed100mh m/s,WindSpeed120mh m/s,WindSpeed140mh m/s,WindSpeed160mh m/s,WindSpeed180mh m/s," + 
                    "WindSpeed200mh m/s,TI 030m,TI 040m,TI 040m ref,TI 060m," + 
                    "TI 080m,TI 100m,TI 120m,TI 140m,TI 160m," +
                    "TI 180m,TI 200m\n");

            if (serialMap.containsKey(name)) {
                serialMap.get(name).close();
            }

            serialMap.put(name, fw);
            serialNameMap.get(name).add(time);
        }
        catch (IOException e) {

        }
    }

    public void AddMastSample(String serial, MastSample sample) throws IllegalArgumentException {
        Optional<Mast> maybeMast = masts.findById(serial);
        if (maybeMast.isPresent()) {
            for (MastGraph graph : mastGraphs.findByMast(maybeMast.get())) {
                MastSample entry = graph.addSample(sample);

                if (entry != null) {
                    SaveMastEntry(serial, graph.getConfig().getName(), graph.getPageStart(), entry);
                }

                mastGraphs.save(graph);
            }
        }
        else {
            throw new IllegalArgumentException("Serial doesn't match a mast.");
        }
    }

    void SaveMastEntry(String serial, String name, String time, MastSample entry) {
        try {
            if (!files.containsKey(serial)) {
                NewMastPage(serial, name, time);
            }
            else if (!files.get(serial).containsKey(name)) {
                NewMastPage(serial, name, time);
            }
            else if (!filenames.get(serial).get(name).get(filenames.get(serial).get(name).size() - 1).equals(time)) {
                NewMastPage(serial, name, time);
            }

            BufferedWriter fw = files.get(serial).get(name);

            fw.append(entry.toCSVLine() + "\n");
        }
        catch (IOException e) {

        }
    }

    void NewMastPage(String serial, String name, String time) {
        if (!files.containsKey(serial)) {
            new File("../../graphs/" + serial).mkdir();
            files.put(serial, new HashMap<String, BufferedWriter>());
            filenames.put(serial, new HashMap<String, List<String>>());
        }

        Map<String, BufferedWriter> serialMap = files.get(serial);
        Map<String, List<String>> serialNameMap = filenames.get(serial);

        if (!serialNameMap.containsKey(name)) {
            new File("../../graphs/" + serial + "/" + name).mkdir();
            serialNameMap.put(name, new ArrayList<String>());
        }
            
        File f = new File("../../graphs/" + serial + "/" + name + "/" + time);

        try {
            f.createNewFile();
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));
    
            fw.append("LOCATION: \n" + 
                    "SYSTEM SERIAL: " + serial + "\n" +
                    "TIMESTAMP (ISO-8601) UTC,WindDir030m deg,WindDir040m deg,WindDir060m deg,WindDir080m deg," +
                    "WindDir100m deg,WindSpeed030mh m/s,WindSpeed040mh m/s,WindSpeed060mh m/s,WindSpeed080mh m/s," +
                    "WindSpeed100mh m/s,TI 030m,TI 040m,TI 060m,TI 080m,TI 100m\n");

            if (serialMap.containsKey(name)) {
                serialMap.get(name).close();
            }

            serialMap.put(name, fw);
            serialNameMap.get(name).add(time);
        }
        catch (IOException e) {
    
        }
    }
}