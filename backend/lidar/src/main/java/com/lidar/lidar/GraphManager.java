package com.lidar.lidar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.*;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
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

            for (final File configFile : serialFile.listFiles()) {
                Instant latestTime = null;
                List<String> configFilenames = new ArrayList<String>();
                
                for (final File timeFile : configFile.listFiles()) {
                    Instant thisTime = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(timeFile.getName()));
                    configFilenames.add(timeFile.getName());
                    if (latestTime == null || thisTime.isAfter(latestTime)) {
                        latestTime = thisTime;
                    }
                }

                configFilenames.sort(null);

                try {

                    if (latestTime != null) {
                        serialMap.put(configFile.getName(), new BufferedWriter(new FileWriter(new File(configFile, latestTime.toString()), true)));
                        serialNameMap.put(configFile.getName(), configFilenames);
                    }
                }
                catch (IOException e) {

                }
            }

            files.put(serialFile.getName(), serialMap);
            filenames.put(serialFile.getName(), serialNameMap);
        }
    }

    public void processSamples() {
        for (Map<String, BufferedWriter> serialMap : files.values()) {
            for (BufferedWriter file : serialMap.values()) {
                try {
                    file.flush();
                }
                catch (IOException e) {

                }
            }
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

    void SaveBuoyEntry(String serial, String config, String time, BuoySample entry) {
        try {
            if (!files.containsKey(serial)) {
                NewBuoyPage(serial, config, time);
            }
            else if (!files.get(serial).containsKey(config)) {
                NewBuoyPage(serial, config, time);
            }
            else if (!filenames.get(serial).get(config).get(filenames.get(serial).get(config).size() - 1).equals(time)) {
                NewBuoyPage(serial, config, time);
            }

            BufferedWriter fw = files.get(serial).get(config);

            fw.append("\n" + entry.toCSVLine());
        }
        catch (IOException e) {

        }
    }

    void NewBuoyPage(String serial, String config, String time) {
        if (!files.containsKey(serial)) {
            new File("../../graphs/" + serial).mkdir();
            files.put(serial, new HashMap<String, BufferedWriter>());
            filenames.put(serial, new HashMap<String, List<String>>());
        }

        Map<String, BufferedWriter> serialMap = files.get(serial);
        Map<String, List<String>> serialNameMap = filenames.get(serial);

        if (!serialNameMap.containsKey(config)) {
            new File("../../graphs/" + serial + "/" + config).mkdir();
            serialNameMap.put(config, new ArrayList<String>());
        }
            
        File f = new File("../../graphs/" + serial + "/" + config + "/" + time);

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
                    "TI 180m,TI 200m");

            if (serialMap.containsKey(config)) {
                serialMap.get(config).close();
            }

            serialMap.put(config, fw);
            serialNameMap.get(config).add(time);
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

    void SaveMastEntry(String serial, String config, String time, MastSample entry) {
        try {
            if (!files.containsKey(serial)) {
                NewMastPage(serial, config, time);
            }
            else if (!files.get(serial).containsKey(config)) {
                NewMastPage(serial, config, time);
            }
            else if (!filenames.get(serial).get(config).get(filenames.get(serial).get(config).size() - 1).equals(time)) {
                NewMastPage(serial, config, time);
            }

            BufferedWriter fw = files.get(serial).get(config);

            fw.append("\n" + entry.toCSVLine());
        }
        catch (IOException e) {

        }
    }

    void NewMastPage(String serial, String config, String time) {
        if (!files.containsKey(serial)) {
            new File("../../graphs/" + serial).mkdir();
            files.put(serial, new HashMap<String, BufferedWriter>());
            filenames.put(serial, new HashMap<String, List<String>>());
        }

        Map<String, BufferedWriter> serialMap = files.get(serial);
        Map<String, List<String>> serialNameMap = filenames.get(serial);

        if (!serialNameMap.containsKey(config)) {
            new File("../../graphs/" + serial + "/" + config).mkdir();
            serialNameMap.put(config, new ArrayList<String>());
        }
            
        File f = new File("../../graphs/" + serial + "/" + config + "/" + time);

        try {
            f.createNewFile();
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));
    
            fw.append("LOCATION: \n" + 
                    "SYSTEM SERIAL: " + serial + "\n" +
                    "TIMESTAMP (ISO-8601) UTC,WindDir030m deg,WindDir040m deg,WindDir060m deg,WindDir080m deg," +
                    "WindDir100m deg,WindSpeed030mh m/s,WindSpeed040mh m/s,WindSpeed060mh m/s,WindSpeed080mh m/s," +
                    "WindSpeed100mh m/s,TI 030m,TI 040m,TI 060m,TI 080m,TI 100m");

            if (serialMap.containsKey(config)) {
                serialMap.get(config).close();
            }

            serialMap.put(config, fw);
            serialNameMap.get(config).add(time);
        }
        catch (IOException e) {
    
        }
    }

    public String getSamplesBetween(String serial, String config, String start, String end) {
        try {
            StringBuilder samples = new StringBuilder();
    
            final List<String> configFilenames = filenames.get(serial).get(config);
    
            if (configFilenames.size() > 0) {
                Integer startIndex = 0;
                Integer endIndex = 0;
    
                while (startIndex < configFilenames.size() && configFilenames.get(startIndex).compareTo(start) <= 0) {
                    startIndex++;
                    endIndex++;
                }
                startIndex--;
                
                while (endIndex < configFilenames.size() && configFilenames.get(endIndex).compareTo(end) <= 0) {
                    endIndex++;
                }
                endIndex--;
    
                if (startIndex.equals(endIndex)) {
                    BufferedReader fr = new BufferedReader(new FileReader("../../graphs/" + serial + "/" + config + "/" + configFilenames.get(startIndex)));
                    String fileSamples = getSamplesWithin(fr, start, end);
                    if (fileSamples != null) samples.append(fileSamples);
                    fr.close();
                }
                else {
                    if (startIndex >= 0) {
                        BufferedReader fr = new BufferedReader(new FileReader("../../graphs/" + serial + "/" + config + "/" + configFilenames.get(startIndex)));
            
                        String fileSamples = getSamplesAfter(fr, start);
                        if (fileSamples != null) samples.append(fileSamples);
                        fr.close();
                    }
        
                    for (Integer fileIndex = startIndex + 1; fileIndex < endIndex; fileIndex++) {
                        BufferedReader fr = new BufferedReader(new FileReader("../../graphs/" + serial + "/" + config + "/" + configFilenames.get(fileIndex)));
                        fr.readLine();
                        fr.readLine();
                        fr.readLine();
                        samples.append(readAll(fr));
                        fr.close();
                    }
                    
                    if (endIndex < configFilenames.size()) {
                        BufferedReader fr = new BufferedReader(new FileReader("../../graphs/" + serial + "/" + config + "/" + configFilenames.get(endIndex)));
                        String fileSamples = getSamplesBefore(fr, end);
                        if (fileSamples != null) samples.append(fileSamples);
                        fr.close();
                    }
                }
            }
    
            return samples.toString();
        }
        catch (Exception e) {
            return "fuck";
        }
    }

    String getSamplesWithin(BufferedReader fr, String start, String end) throws IOException {
        String sample = fr.readLine();
        while (!(sample.charAt(0) >= '0' && sample.charAt(0) <= '9') || sample.subSequence(0, 20).toString().compareTo(start) < 0) {
            sample = fr.readLine();
            if (sample == null) {
                return null;
            }
        }
        
        String samples = sample;
        if (sample.subSequence(0, 20).toString().compareTo(end) > 0) {
            return null;
        }
        sample = fr.readLine();
        
        while (sample != null && sample.subSequence(0, 20).toString().compareTo(end) <= 0) {
            samples += "\n" + sample;
            sample = fr.readLine();
        }

        return samples;
    }

    String getSamplesAfter(BufferedReader fr, String start) throws IOException {
        String sample = fr.readLine();
        while (!(sample.charAt(0) >= '0' && sample.charAt(0) <= '9') || sample.subSequence(0, 20).toString().compareTo(start) < 0) {
            sample = fr.readLine();
            if (sample == null) {
                return null;
            }
        }

        return sample + "\n" + readAll(fr);
    }

    String getSamplesBefore(BufferedReader fr, String end) throws IOException {
        String sample = fr.readLine();
        while (!(sample.charAt(0) >= '0' && sample.charAt(0) <= '9')) {
            sample = fr.readLine();
            if (sample == null) {
                return null;
            }
        }
        
        String samples = sample;
        if (sample.subSequence(0, 20).toString().compareTo(end) > 0) {
            return null;
        }
        sample = fr.readLine();
        
        while (sample != null && sample.subSequence(0, 20).toString().compareTo(end) <= 0) {
            samples += "\n" + sample;
            sample = fr.readLine();
        }

        return samples;
    }

    String readAll(BufferedReader fr) throws IOException {
        StringBuilder output = new StringBuilder();

        int character = fr.read();
        while (character != -1) {
            output.append((char)character);
            character = fr.read();
        }

        return output.toString();
    }

    public void resetAll() {
        for (File serialFile : new File("../../graphs").listFiles()) {
            for (File configFile : serialFile.listFiles()) {
                for (File dataFile : configFile.listFiles()) {
                    dataFile.delete();
                }
            }
        }
    }
    
    public void reset(String serial) {
        File serialFile = new File("../../graphs/" + serial);
        if (serialFile.exists() && serialFile.isDirectory()) {
            for (File configFile : serialFile.listFiles()) {
                for (File dataFile : configFile.listFiles()) {
                    dataFile.delete();
                }
            }
        }
    }
}