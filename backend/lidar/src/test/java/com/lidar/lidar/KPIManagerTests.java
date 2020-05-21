package com.lidar.lidar;

import com.lidar.lidar.database.*;
import com.lidar.lidar.samples.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;

import javassist.tools.reflect.Sample;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class KPIManagerTests {
	@Autowired
	private KPIManager kpiManager;

	@Autowired   
    BuoyTable buoys;

	@Autowired   
    MastTable masts;

    @Autowired
    SpeedHeightTable speedHeights;

    @Autowired
    DirHeightTable dirHeights;

	@Test   
	public void testAddSample() throws Exception {
        Mast mast = masts.save(new Mast("TESTMAST"));
        buoys.save(new Buoy("TBUOYA", mast, speedHeights, dirHeights));
        buoys.save(new Buoy("TBUOYB", mast, speedHeights, dirHeights));

        BuoySample buoySample = TestSampleFactory.buoySample("2016-06-05T00:00:00Z");
        MastSample mastSample = TestSampleFactory.mastSample("2016-06-05T00:00:00Z");

        kpiManager.reloadBuoys();

        kpiManager.addBuoySample("TBUOYA", buoySample);
        kpiManager.addMastSample("TESTMAST", mastSample);
        kpiManager.addBuoySample("TBUOYB", buoySample);

        Optional<Buoy> tBuoyA = buoys.findById("TBUOYA");
        Optional<Buoy> tBuoyB = buoys.findById("TBUOYB");

        //  buoy speed 3, mast speed 2
        assertEquals(0l, tBuoyA.get().getSh40().getCounta(), "Wrong number of 40m speed A samples received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeeda(), "Wrong value of 40m speed A received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getMastSpeeda(), "Wrong value of 40m mast speed A received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeedaSqr(), "Wrong value of 40m speed A square received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getMastSpeedaSqr(), "Wrong value of 40m mast speed A square received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeedaProd(), "Wrong value of 40m speed A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getSh40().getCountb(), "Wrong number of 40m speed B samples received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getSh40().getSpeedb(), "Wrong value of 40m speed B received for buoy A.");
        assertEquals(2.0, tBuoyA.get().getSh40().getMastSpeedb(), "Wrong value of 40m mast speed B received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh40().getSpeedbSqr(), "Wrong value of 40m speed B square received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getSh40().getMastSpeedbSqr(), "Wrong value of 40m mast speed B square received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getSh40().getSpeedbProd(), "Wrong value of 40m speed B product received for buoy A.");
        
        //  buoy speed 5, mast speed 3
        assertEquals(1l, tBuoyA.get().getSh60().getCounta(), "Wrong number of 60m speed A samples received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getSh60().getSpeeda(), "Wrong value of 60m speed A received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getSh60().getMastSpeeda(), "Wrong value of 60m mast speed A received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getSh60().getSpeedaSqr(), "Wrong value of 60m speed A square received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh60().getMastSpeedaSqr(), "Wrong value of 60m mast speed A square received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh60().getSpeedaProd(), "Wrong value of 60m speed A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getSh60().getCountb(), "Wrong number of 60m speed B samples received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getSh60().getSpeedb(), "Wrong value of 60m speed B received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getSh60().getMastSpeedb(), "Wrong value of 60m mast speed B received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getSh60().getSpeedbSqr(), "Wrong value of 60m speed B square received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh60().getMastSpeedbSqr(), "Wrong value of 60m mast speed B square received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh60().getSpeedbProd(), "Wrong value of 60m speed B product received for buoy A.");
        
        //  buoy speed 6, mast speed 4
        assertEquals(1l, tBuoyA.get().getSh80().getCounta(), "Wrong number of 80m speed A samples received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getSh80().getSpeeda(), "Wrong value of 80m speed A received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getSh80().getMastSpeeda(), "Wrong value of 80m mast speed A received for buoy A.");
        assertEquals(36.0, tBuoyA.get().getSh80().getSpeedaSqr(), "Wrong value of 80m speed A square received for buoy A.");
        assertEquals(16.0, tBuoyA.get().getSh80().getMastSpeedaSqr(), "Wrong value of 80m mast speed A square received for buoy A.");
        assertEquals(24.0, tBuoyA.get().getSh80().getSpeedaProd(), "Wrong value of 80m speed A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getSh80().getCountb(), "Wrong number of 80m speed B samples received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getSh80().getSpeedb(), "Wrong value of 80m speed B received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getSh80().getMastSpeedb(), "Wrong value of 80m mast speed B received for buoy A.");
        assertEquals(36.0, tBuoyA.get().getSh80().getSpeedbSqr(), "Wrong value of 80m speed B square received for buoy A.");
        assertEquals(16.0, tBuoyA.get().getSh80().getMastSpeedbSqr(), "Wrong value of 80m mast speed B square received for buoy A.");
        assertEquals(24.0, tBuoyA.get().getSh80().getSpeedbProd(), "Wrong value of 80m speed B product received for buoy A.");
        
        //  buoy speed 7, mast speed 5
        assertEquals(1l, tBuoyA.get().getSh100().getCounta(), "Wrong number of 100m speed A samples received for buoy A.");
        assertEquals(7.0, tBuoyA.get().getSh100().getSpeeda(), "Wrong value of 100m speed A received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getSh100().getMastSpeeda(), "Wrong value of 100m mast speed A received for buoy A.");
        assertEquals(49.0, tBuoyA.get().getSh100().getSpeedaSqr(), "Wrong value of 100m speed A square received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getSh100().getMastSpeedaSqr(), "Wrong value of 100m mast speed A square received for buoy A.");
        assertEquals(35.0, tBuoyA.get().getSh100().getSpeedaProd(), "Wrong value of 100m speed A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getSh100().getCountb(), "Wrong number of 100m speed B samples received for buoy A.");
        assertEquals(7.0, tBuoyA.get().getSh100().getSpeedb(), "Wrong value of 100m speed B received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getSh100().getMastSpeedb(), "Wrong value of 100m mast speed B received for buoy A.");
        assertEquals(49.0, tBuoyA.get().getSh100().getSpeedbSqr(), "Wrong value of 100m speed B square received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getSh100().getMastSpeedbSqr(), "Wrong value of 100m mast speed B square received for buoy A.");
        assertEquals(35.0, tBuoyA.get().getSh100().getSpeedbProd(), "Wrong value of 100m speed B product received for buoy A.");
        
        //  buoy direction 3, mast direction 2
        assertEquals(1l, tBuoyA.get().getDh40().getCounta(), "Wrong number of 40m direction A samples received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getDh40().getDira(), "Wrong value of 40m direction A received for buoy A.");
        assertEquals(2.0, tBuoyA.get().getDh40().getMastDira(), "Wrong value of 40m mast direction A received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh40().getDiraSqr(), "Wrong value of 40m direction A square received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getDh40().getMastDiraSqr(), "Wrong value of 40m mast direction A square received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh40().getDiraProd(), "Wrong value of 40m direction A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getDh40().getCountb(), "Wrong number of 40m direction B samples received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getDh40().getDirb(), "Wrong value of 40m direction B received for buoy A.");
        assertEquals(2.0, tBuoyA.get().getDh40().getMastDirb(), "Wrong value of 40m mast direction B received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh40().getDirbSqr(), "Wrong value of 40m direction B square received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getDh40().getMastDirbSqr(), "Wrong value of 40m mast direction B square received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh40().getDirbProd(), "Wrong value of 40m direction B product received for buoy A.");
        
        //  buoy direction 5, mast direction 3
        assertEquals(1l, tBuoyA.get().getDh60().getCounta(), "Wrong number of 60m direction A samples received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getDh60().getDira(), "Wrong value of 60m direction A received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getDh60().getMastDira(), "Wrong value of 60m mast direction A received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getDh60().getDiraSqr(), "Wrong value of 60m direction A square received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh60().getMastDiraSqr(), "Wrong value of 60m mast direction A square received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh60().getDiraProd(), "Wrong value of 60m direction A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getDh60().getCountb(), "Wrong number of 60m direction B samples received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getDh60().getDirb(), "Wrong value of 60m direction B received for buoy A.");
        assertEquals(3.0, tBuoyA.get().getDh60().getMastDirb(), "Wrong value of 60m mast direction B received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getDh60().getDirbSqr(), "Wrong value of 60m direction B square received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh60().getMastDirbSqr(), "Wrong value of 60m mast direction B square received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh60().getDirbProd(), "Wrong value of 60m direction B product received for buoy A.");
        
        //  buoy direction 6, mast direction 4
        assertEquals(1l, tBuoyA.get().getDh80().getCounta(), "Wrong number of 80m direction A samples received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh80().getDira(), "Wrong value of 80m direction A received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getDh80().getMastDira(), "Wrong value of 80m mast direction A received for buoy A.");
        assertEquals(36.0, tBuoyA.get().getDh80().getDiraSqr(), "Wrong value of 80m direction A square received for buoy A.");
        assertEquals(16.0, tBuoyA.get().getDh80().getMastDiraSqr(), "Wrong value of 80m mast direction A square received for buoy A.");
        assertEquals(24.0, tBuoyA.get().getDh80().getDiraProd(), "Wrong value of 80m direction A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getDh80().getCountb(), "Wrong number of 80m direction B samples received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh80().getDirb(), "Wrong value of 80m direction B received for buoy A.");
        assertEquals(4.0, tBuoyA.get().getDh80().getMastDirb(), "Wrong value of 80m mast direction B received for buoy A.");
        assertEquals(36.0, tBuoyA.get().getDh80().getDirbSqr(), "Wrong value of 80m direction B square received for buoy A.");
        assertEquals(16.0, tBuoyA.get().getDh80().getMastDirbSqr(), "Wrong value of 80m mast direction B square received for buoy A.");
        assertEquals(24.0, tBuoyA.get().getDh80().getDirbProd(), "Wrong value of 80m direction B product received for buoy A.");
        
        //  buoy direction 7, mast direction 5
        assertEquals(1l, tBuoyA.get().getDh100().getCounta(), "Wrong number of 100m direction A samples received for buoy A.");
        assertEquals(7.0, tBuoyA.get().getDh100().getDira(), "Wrong value of 100m direction A received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getDh100().getMastDira(), "Wrong value of 100m mast direction A received for buoy A.");
        assertEquals(49.0, tBuoyA.get().getDh100().getDiraSqr(), "Wrong value of 100m direction A square received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getDh100().getMastDiraSqr(), "Wrong value of 100m mast direction A square received for buoy A.");
        assertEquals(35.0, tBuoyA.get().getDh100().getDiraProd(), "Wrong value of 100m direction A product received for buoy A.");
        assertEquals(1l, tBuoyA.get().getDh100().getCountb(), "Wrong number of 100m direction B samples received for buoy A.");
        assertEquals(7.0, tBuoyA.get().getDh100().getDirb(), "Wrong value of 100m direction B received for buoy A.");
        assertEquals(5.0, tBuoyA.get().getDh100().getMastDirb(), "Wrong value of 100m mast direction B received for buoy A.");
        assertEquals(49.0, tBuoyA.get().getDh100().getDirbSqr(), "Wrong value of 100m direction B square received for buoy A.");
        assertEquals(25.0, tBuoyA.get().getDh100().getMastDirbSqr(), "Wrong value of 100m mast direction B square received for buoy A.");
        assertEquals(35.0, tBuoyA.get().getDh100().getDirbProd(), "Wrong value of 100m direction B product received for buoy A.");
        
        //  buoy speed 3, mast speed 2
        assertEquals(0l, tBuoyB.get().getSh40().getCounta(), "Wrong number of 40m speed A samples received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeeda(), "Wrong value of 40m speed A received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getMastSpeeda(), "Wrong value of 40m mast speed A received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeedaSqr(), "Wrong value of 40m speed A square received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getMastSpeedaSqr(), "Wrong value of 40m mast speed A square received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeedaProd(), "Wrong value of 40m speed A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getSh40().getCountb(), "Wrong number of 40m speed B samples received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getSh40().getSpeedb(), "Wrong value of 40m speed B received for buoy B.");
        assertEquals(2.0, tBuoyB.get().getSh40().getMastSpeedb(), "Wrong value of 40m mast speed B received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh40().getSpeedbSqr(), "Wrong value of 40m speed B square received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getSh40().getMastSpeedbSqr(), "Wrong value of 40m mast speed B square received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getSh40().getSpeedbProd(), "Wrong value of 40m speed B product received for buoy B.");
        
        //  buoy speed 5, mast speed 3
        assertEquals(1l, tBuoyB.get().getSh60().getCounta(), "Wrong number of 60m speed A samples received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getSh60().getSpeeda(), "Wrong value of 60m speed A received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getSh60().getMastSpeeda(), "Wrong value of 60m mast speed A received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getSh60().getSpeedaSqr(), "Wrong value of 60m speed A square received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh60().getMastSpeedaSqr(), "Wrong value of 60m mast speed A square received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh60().getSpeedaProd(), "Wrong value of 60m speed A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getSh60().getCountb(), "Wrong number of 60m speed B samples received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getSh60().getSpeedb(), "Wrong value of 60m speed B received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getSh60().getMastSpeedb(), "Wrong value of 60m mast speed B received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getSh60().getSpeedbSqr(), "Wrong value of 60m speed B square received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh60().getMastSpeedbSqr(), "Wrong value of 60m mast speed B square received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh60().getSpeedbProd(), "Wrong value of 60m speed B product received for buoy B.");
        
        //  buoy speed 6, mast speed 4
        assertEquals(1l, tBuoyB.get().getSh80().getCounta(), "Wrong number of 80m speed A samples received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getSh80().getSpeeda(), "Wrong value of 80m speed A received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getSh80().getMastSpeeda(), "Wrong value of 80m mast speed A received for buoy B.");
        assertEquals(36.0, tBuoyB.get().getSh80().getSpeedaSqr(), "Wrong value of 80m speed A square received for buoy B.");
        assertEquals(16.0, tBuoyB.get().getSh80().getMastSpeedaSqr(), "Wrong value of 80m mast speed A square received for buoy B.");
        assertEquals(24.0, tBuoyB.get().getSh80().getSpeedaProd(), "Wrong value of 80m speed A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getSh80().getCountb(), "Wrong number of 80m speed B samples received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getSh80().getSpeedb(), "Wrong value of 80m speed B received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getSh80().getMastSpeedb(), "Wrong value of 80m mast speed B received for buoy B.");
        assertEquals(36.0, tBuoyB.get().getSh80().getSpeedbSqr(), "Wrong value of 80m speed B square received for buoy B.");
        assertEquals(16.0, tBuoyB.get().getSh80().getMastSpeedbSqr(), "Wrong value of 80m mast speed B square received for buoy B.");
        assertEquals(24.0, tBuoyB.get().getSh80().getSpeedbProd(), "Wrong value of 80m speed B product received for buoy B.");
        
        //  buoy speed 7, mast speed 5
        assertEquals(1l, tBuoyB.get().getSh100().getCounta(), "Wrong number of 100m speed A samples received for buoy B.");
        assertEquals(7.0, tBuoyB.get().getSh100().getSpeeda(), "Wrong value of 100m speed A received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getSh100().getMastSpeeda(), "Wrong value of 100m mast speed A received for buoy B.");
        assertEquals(49.0, tBuoyB.get().getSh100().getSpeedaSqr(), "Wrong value of 100m speed A square received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getSh100().getMastSpeedaSqr(), "Wrong value of 100m mast speed A square received for buoy B.");
        assertEquals(35.0, tBuoyB.get().getSh100().getSpeedaProd(), "Wrong value of 100m speed A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getSh100().getCountb(), "Wrong number of 100m speed B samples received for buoy B.");
        assertEquals(7.0, tBuoyB.get().getSh100().getSpeedb(), "Wrong value of 100m speed B received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getSh100().getMastSpeedb(), "Wrong value of 100m mast speed B received for buoy B.");
        assertEquals(49.0, tBuoyB.get().getSh100().getSpeedbSqr(), "Wrong value of 100m speed B square received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getSh100().getMastSpeedbSqr(), "Wrong value of 100m mast speed B square received for buoy B.");
        assertEquals(35.0, tBuoyB.get().getSh100().getSpeedbProd(), "Wrong value of 100m speed B product received for buoy B.");
        
        //  buoy direction 3, mast direction 2
        assertEquals(1l, tBuoyB.get().getDh40().getCounta(), "Wrong number of 40m direction A samples received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getDh40().getDira(), "Wrong value of 40m direction A received for buoy B.");
        assertEquals(2.0, tBuoyB.get().getDh40().getMastDira(), "Wrong value of 40m mast direction A received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh40().getDiraSqr(), "Wrong value of 40m direction A square received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getDh40().getMastDiraSqr(), "Wrong value of 40m mast direction A square received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh40().getDiraProd(), "Wrong value of 40m direction A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getDh40().getCountb(), "Wrong number of 40m direction B samples received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getDh40().getDirb(), "Wrong value of 40m direction B received for buoy B.");
        assertEquals(2.0, tBuoyB.get().getDh40().getMastDirb(), "Wrong value of 40m mast direction B received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh40().getDirbSqr(), "Wrong value of 40m direction B square received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getDh40().getMastDirbSqr(), "Wrong value of 40m mast direction B square received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh40().getDirbProd(), "Wrong value of 40m direction B product received for buoy B.");
        
        //  buoy direction 5, mast direction 3
        assertEquals(1l, tBuoyB.get().getDh60().getCounta(), "Wrong number of 60m direction A samples received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getDh60().getDira(), "Wrong value of 60m direction A received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getDh60().getMastDira(), "Wrong value of 60m mast direction A received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getDh60().getDiraSqr(), "Wrong value of 60m direction A square received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh60().getMastDiraSqr(), "Wrong value of 60m mast direction A square received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh60().getDiraProd(), "Wrong value of 60m direction A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getDh60().getCountb(), "Wrong number of 60m direction B samples received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getDh60().getDirb(), "Wrong value of 60m direction B received for buoy B.");
        assertEquals(3.0, tBuoyB.get().getDh60().getMastDirb(), "Wrong value of 60m mast direction B received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getDh60().getDirbSqr(), "Wrong value of 60m direction B square received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh60().getMastDirbSqr(), "Wrong value of 60m mast direction B square received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh60().getDirbProd(), "Wrong value of 60m direction B product received for buoy B.");
        
        //  buoy direction 6, mast direction 4
        assertEquals(1l, tBuoyB.get().getDh80().getCounta(), "Wrong number of 80m direction A samples received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh80().getDira(), "Wrong value of 80m direction A received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getDh80().getMastDira(), "Wrong value of 80m mast direction A received for buoy B.");
        assertEquals(36.0, tBuoyB.get().getDh80().getDiraSqr(), "Wrong value of 80m direction A square received for buoy B.");
        assertEquals(16.0, tBuoyB.get().getDh80().getMastDiraSqr(), "Wrong value of 80m mast direction A square received for buoy B.");
        assertEquals(24.0, tBuoyB.get().getDh80().getDiraProd(), "Wrong value of 80m direction A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getDh80().getCountb(), "Wrong number of 80m direction B samples received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh80().getDirb(), "Wrong value of 80m direction B received for buoy B.");
        assertEquals(4.0, tBuoyB.get().getDh80().getMastDirb(), "Wrong value of 80m mast direction B received for buoy B.");
        assertEquals(36.0, tBuoyB.get().getDh80().getDirbSqr(), "Wrong value of 80m direction B square received for buoy B.");
        assertEquals(16.0, tBuoyB.get().getDh80().getMastDirbSqr(), "Wrong value of 80m mast direction B square received for buoy B.");
        assertEquals(24.0, tBuoyB.get().getDh80().getDirbProd(), "Wrong value of 80m direction B product received for buoy B.");
        
        //  buoy direction 7, mast direction 5
        assertEquals(1l, tBuoyB.get().getDh100().getCounta(), "Wrong number of 100m direction A samples received for buoy B.");
        assertEquals(7.0, tBuoyB.get().getDh100().getDira(), "Wrong value of 100m direction A received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getDh100().getMastDira(), "Wrong value of 100m mast direction A received for buoy B.");
        assertEquals(49.0, tBuoyB.get().getDh100().getDiraSqr(), "Wrong value of 100m direction A square received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getDh100().getMastDiraSqr(), "Wrong value of 100m mast direction A square received for buoy B.");
        assertEquals(35.0, tBuoyB.get().getDh100().getDiraProd(), "Wrong value of 100m direction A product received for buoy B.");
        assertEquals(1l, tBuoyB.get().getDh100().getCountb(), "Wrong number of 100m direction B samples received for buoy B.");
        assertEquals(7.0, tBuoyB.get().getDh100().getDirb(), "Wrong value of 100m direction B received for buoy B.");
        assertEquals(5.0, tBuoyB.get().getDh100().getMastDirb(), "Wrong value of 100m mast direction B received for buoy B.");
        assertEquals(49.0, tBuoyB.get().getDh100().getDirbSqr(), "Wrong value of 100m direction B square received for buoy B.");
        assertEquals(25.0, tBuoyB.get().getDh100().getMastDirbSqr(), "Wrong value of 100m mast direction B square received for buoy B.");
        assertEquals(35.0, tBuoyB.get().getDh100().getDirbProd(), "Wrong value of 100m direction B product received for buoy B.");
    }

	@Test   
	public void testAddSamples() throws Exception {
        Mast mast = masts.save(new Mast("TESTMAST"));
        buoys.save(new Buoy("TBUOYA", mast, speedHeights, dirHeights));
        buoys.save(new Buoy("TBUOYB", mast, speedHeights, dirHeights));

        List<BuoySample> buoySamples = new ArrayList<BuoySample>();
        buoySamples.add(TestSampleFactory.buoySample("2016-06-05T00:00:00Z"));
        buoySamples.add(TestSampleFactory.buoySample("2016-06-05T00:10:00Z"));
        buoySamples.add(TestSampleFactory.buoySample("2016-06-05T00:20:00Z"));
        List<MastSample> mastSamples = new ArrayList<MastSample>();
        mastSamples.add(TestSampleFactory.mastSample("2016-06-05T00:00:00Z"));
        mastSamples.add(TestSampleFactory.mastSample("2016-06-05T00:10:00Z"));
        mastSamples.add(TestSampleFactory.mastSample("2016-06-05T00:20:00Z"));

        kpiManager.reloadBuoys();

        kpiManager.addBuoySamples("TBUOYA", buoySamples);
        kpiManager.addMastSamples("TESTMAST", mastSamples);
        kpiManager.addBuoySamples("TBUOYB", buoySamples);

        Optional<Buoy> tBuoyA = buoys.findById("TBUOYA");
        Optional<Buoy> tBuoyB = buoys.findById("TBUOYB");

        //  buoy speed 3, mast speed 2
        assertEquals(0l, tBuoyA.get().getSh40().getCounta(), "Wrong number of 40m speed A samples received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeeda(), "Wrong value of 40m speed A received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getMastSpeeda(), "Wrong value of 40m mast speed A received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeedaSqr(), "Wrong value of 40m speed A square received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getMastSpeedaSqr(), "Wrong value of 40m mast speed A square received for buoy A.");
        assertEquals(0.0, tBuoyA.get().getSh40().getSpeedaProd(), "Wrong value of 40m speed A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getSh40().getCountb(), "Wrong number of 40m speed B samples received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh40().getSpeedb(), "Wrong value of 40m speed B received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getSh40().getMastSpeedb(), "Wrong value of 40m mast speed B received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getSh40().getSpeedbSqr(), "Wrong value of 40m speed B square received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getSh40().getMastSpeedbSqr(), "Wrong value of 40m mast speed B square received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getSh40().getSpeedbProd(), "Wrong value of 40m speed B product received for buoy A.");
        
        //  buoy speed 5, mast speed 3
        assertEquals(3l, tBuoyA.get().getSh60().getCounta(), "Wrong number of 60m speed A samples received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh60().getSpeeda(), "Wrong value of 60m speed A received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh60().getMastSpeeda(), "Wrong value of 60m mast speed A received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getSh60().getSpeedaSqr(), "Wrong value of 60m speed A square received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getSh60().getMastSpeedaSqr(), "Wrong value of 60m mast speed A square received for buoy A.");
        assertEquals(45.0, tBuoyA.get().getSh60().getSpeedaProd(), "Wrong value of 60m speed A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getSh60().getCountb(), "Wrong number of 60m speed B samples received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh60().getSpeedb(), "Wrong value of 60m speed B received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getSh60().getMastSpeedb(), "Wrong value of 60m mast speed B received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getSh60().getSpeedbSqr(), "Wrong value of 60m speed B square received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getSh60().getMastSpeedbSqr(), "Wrong value of 60m mast speed B square received for buoy A.");
        assertEquals(45.0, tBuoyA.get().getSh60().getSpeedbProd(), "Wrong value of 60m speed B product received for buoy A.");
        
        //  buoy speed 6, mast speed 4
        assertEquals(3l, tBuoyA.get().getSh80().getCounta(), "Wrong number of 80m speed A samples received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getSh80().getSpeeda(), "Wrong value of 80m speed A received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getSh80().getMastSpeeda(), "Wrong value of 80m mast speed A received for buoy A.");
        assertEquals(108.0, tBuoyA.get().getSh80().getSpeedaSqr(), "Wrong value of 80m speed A square received for buoy A.");
        assertEquals(48.0, tBuoyA.get().getSh80().getMastSpeedaSqr(), "Wrong value of 80m mast speed A square received for buoy A.");
        assertEquals(72.0, tBuoyA.get().getSh80().getSpeedaProd(), "Wrong value of 80m speed A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getSh80().getCountb(), "Wrong number of 80m speed B samples received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getSh80().getSpeedb(), "Wrong value of 80m speed B received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getSh80().getMastSpeedb(), "Wrong value of 80m mast speed B received for buoy A.");
        assertEquals(108.0, tBuoyA.get().getSh80().getSpeedbSqr(), "Wrong value of 80m speed B square received for buoy A.");
        assertEquals(48.0, tBuoyA.get().getSh80().getMastSpeedbSqr(), "Wrong value of 80m mast speed B square received for buoy A.");
        assertEquals(72.0, tBuoyA.get().getSh80().getSpeedbProd(), "Wrong value of 80m speed B product received for buoy A.");
        
        //  buoy speed 7, mast speed 5
        assertEquals(3l, tBuoyA.get().getSh100().getCounta(), "Wrong number of 100m speed A samples received for buoy A.");
        assertEquals(21.0, tBuoyA.get().getSh100().getSpeeda(), "Wrong value of 100m speed A received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh100().getMastSpeeda(), "Wrong value of 100m mast speed A received for buoy A.");
        assertEquals(147.0, tBuoyA.get().getSh100().getSpeedaSqr(), "Wrong value of 100m speed A square received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getSh100().getMastSpeedaSqr(), "Wrong value of 100m mast speed A square received for buoy A.");
        assertEquals(105.0, tBuoyA.get().getSh100().getSpeedaProd(), "Wrong value of 100m speed A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getSh100().getCountb(), "Wrong number of 100m speed B samples received for buoy A.");
        assertEquals(21.0, tBuoyA.get().getSh100().getSpeedb(), "Wrong value of 100m speed B received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getSh100().getMastSpeedb(), "Wrong value of 100m mast speed B received for buoy A.");
        assertEquals(147.0, tBuoyA.get().getSh100().getSpeedbSqr(), "Wrong value of 100m speed B square received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getSh100().getMastSpeedbSqr(), "Wrong value of 100m mast speed B square received for buoy A.");
        assertEquals(105.0, tBuoyA.get().getSh100().getSpeedbProd(), "Wrong value of 100m speed B product received for buoy A.");
        
        //  buoy direction 3, mast direction 2
        assertEquals(3l, tBuoyA.get().getDh40().getCounta(), "Wrong number of 40m direction A samples received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh40().getDira(), "Wrong value of 40m direction A received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh40().getMastDira(), "Wrong value of 40m mast direction A received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getDh40().getDiraSqr(), "Wrong value of 40m direction A square received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getDh40().getMastDiraSqr(), "Wrong value of 40m mast direction A square received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getDh40().getDiraProd(), "Wrong value of 40m direction A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getDh40().getCountb(), "Wrong number of 40m direction B samples received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh40().getDirb(), "Wrong value of 40m direction B received for buoy A.");
        assertEquals(6.0, tBuoyA.get().getDh40().getMastDirb(), "Wrong value of 40m mast direction B received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getDh40().getDirbSqr(), "Wrong value of 40m direction B square received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getDh40().getMastDirbSqr(), "Wrong value of 40m mast direction B square received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getDh40().getDirbProd(), "Wrong value of 40m direction B product received for buoy A.");
        
        //  buoy direction 5, mast direction 3
        assertEquals(3l, tBuoyA.get().getDh60().getCounta(), "Wrong number of 60m direction A samples received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh60().getDira(), "Wrong value of 60m direction A received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh60().getMastDira(), "Wrong value of 60m mast direction A received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getDh60().getDiraSqr(), "Wrong value of 60m direction A square received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getDh60().getMastDiraSqr(), "Wrong value of 60m mast direction A square received for buoy A.");
        assertEquals(45.0, tBuoyA.get().getDh60().getDiraProd(), "Wrong value of 60m direction A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getDh60().getCountb(), "Wrong number of 60m direction B samples received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh60().getDirb(), "Wrong value of 60m direction B received for buoy A.");
        assertEquals(9.0, tBuoyA.get().getDh60().getMastDirb(), "Wrong value of 60m mast direction B received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getDh60().getDirbSqr(), "Wrong value of 60m direction B square received for buoy A.");
        assertEquals(27.0, tBuoyA.get().getDh60().getMastDirbSqr(), "Wrong value of 60m mast direction B square received for buoy A.");
        assertEquals(45.0, tBuoyA.get().getDh60().getDirbProd(), "Wrong value of 60m direction B product received for buoy A.");
        
        //  buoy direction 6, mast direction 4
        assertEquals(3l, tBuoyA.get().getDh80().getCounta(), "Wrong number of 80m direction A samples received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getDh80().getDira(), "Wrong value of 80m direction A received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getDh80().getMastDira(), "Wrong value of 80m mast direction A received for buoy A.");
        assertEquals(108.0, tBuoyA.get().getDh80().getDiraSqr(), "Wrong value of 80m direction A square received for buoy A.");
        assertEquals(48.0, tBuoyA.get().getDh80().getMastDiraSqr(), "Wrong value of 80m mast direction A square received for buoy A.");
        assertEquals(72.0, tBuoyA.get().getDh80().getDiraProd(), "Wrong value of 80m direction A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getDh80().getCountb(), "Wrong number of 80m direction B samples received for buoy A.");
        assertEquals(18.0, tBuoyA.get().getDh80().getDirb(), "Wrong value of 80m direction B received for buoy A.");
        assertEquals(12.0, tBuoyA.get().getDh80().getMastDirb(), "Wrong value of 80m mast direction B received for buoy A.");
        assertEquals(108.0, tBuoyA.get().getDh80().getDirbSqr(), "Wrong value of 80m direction B square received for buoy A.");
        assertEquals(48.0, tBuoyA.get().getDh80().getMastDirbSqr(), "Wrong value of 80m mast direction B square received for buoy A.");
        assertEquals(72.0, tBuoyA.get().getDh80().getDirbProd(), "Wrong value of 80m direction B product received for buoy A.");
        
        //  buoy direction 7, mast direction 5
        assertEquals(3l, tBuoyA.get().getDh100().getCounta(), "Wrong number of 100m direction A samples received for buoy A.");
        assertEquals(21.0, tBuoyA.get().getDh100().getDira(), "Wrong value of 100m direction A received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh100().getMastDira(), "Wrong value of 100m mast direction A received for buoy A.");
        assertEquals(147.0, tBuoyA.get().getDh100().getDiraSqr(), "Wrong value of 100m direction A square received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getDh100().getMastDiraSqr(), "Wrong value of 100m mast direction A square received for buoy A.");
        assertEquals(105.0, tBuoyA.get().getDh100().getDiraProd(), "Wrong value of 100m direction A product received for buoy A.");
        assertEquals(3l, tBuoyA.get().getDh100().getCountb(), "Wrong number of 100m direction B samples received for buoy A.");
        assertEquals(21.0, tBuoyA.get().getDh100().getDirb(), "Wrong value of 100m direction B received for buoy A.");
        assertEquals(15.0, tBuoyA.get().getDh100().getMastDirb(), "Wrong value of 100m mast direction B received for buoy A.");
        assertEquals(147.0, tBuoyA.get().getDh100().getDirbSqr(), "Wrong value of 100m direction B square received for buoy A.");
        assertEquals(75.0, tBuoyA.get().getDh100().getMastDirbSqr(), "Wrong value of 100m mast direction B square received for buoy A.");
        assertEquals(105.0, tBuoyA.get().getDh100().getDirbProd(), "Wrong value of 100m direction B product received for buoy A.");

        //  buoy speed 3, mast speed 2
        assertEquals(0l, tBuoyB.get().getSh40().getCounta(), "Wrong number of 40m speed A samples received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeeda(), "Wrong value of 40m speed A received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getMastSpeeda(), "Wrong value of 40m mast speed A received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeedaSqr(), "Wrong value of 40m speed A square received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getMastSpeedaSqr(), "Wrong value of 40m mast speed A square received for buoy B.");
        assertEquals(0.0, tBuoyB.get().getSh40().getSpeedaProd(), "Wrong value of 40m speed A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getSh40().getCountb(), "Wrong number of 40m speed B samples received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh40().getSpeedb(), "Wrong value of 40m speed B received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getSh40().getMastSpeedb(), "Wrong value of 40m mast speed B received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getSh40().getSpeedbSqr(), "Wrong value of 40m speed B square received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getSh40().getMastSpeedbSqr(), "Wrong value of 40m mast speed B square received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getSh40().getSpeedbProd(), "Wrong value of 40m speed B product received for buoy B.");
        
        //  buoy speed 5, mast speed 3
        assertEquals(3l, tBuoyB.get().getSh60().getCounta(), "Wrong number of 60m speed A samples received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh60().getSpeeda(), "Wrong value of 60m speed A received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh60().getMastSpeeda(), "Wrong value of 60m mast speed A received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getSh60().getSpeedaSqr(), "Wrong value of 60m speed A square received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getSh60().getMastSpeedaSqr(), "Wrong value of 60m mast speed A square received for buoy B.");
        assertEquals(45.0, tBuoyB.get().getSh60().getSpeedaProd(), "Wrong value of 60m speed A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getSh60().getCountb(), "Wrong number of 60m speed B samples received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh60().getSpeedb(), "Wrong value of 60m speed B received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getSh60().getMastSpeedb(), "Wrong value of 60m mast speed B received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getSh60().getSpeedbSqr(), "Wrong value of 60m speed B square received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getSh60().getMastSpeedbSqr(), "Wrong value of 60m mast speed B square received for buoy B.");
        assertEquals(45.0, tBuoyB.get().getSh60().getSpeedbProd(), "Wrong value of 60m speed B product received for buoy B.");
        
        //  buoy speed 6, mast speed 4
        assertEquals(3l, tBuoyB.get().getSh80().getCounta(), "Wrong number of 80m speed A samples received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getSh80().getSpeeda(), "Wrong value of 80m speed A received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getSh80().getMastSpeeda(), "Wrong value of 80m mast speed A received for buoy B.");
        assertEquals(108.0, tBuoyB.get().getSh80().getSpeedaSqr(), "Wrong value of 80m speed A square received for buoy B.");
        assertEquals(48.0, tBuoyB.get().getSh80().getMastSpeedaSqr(), "Wrong value of 80m mast speed A square received for buoy B.");
        assertEquals(72.0, tBuoyB.get().getSh80().getSpeedaProd(), "Wrong value of 80m speed A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getSh80().getCountb(), "Wrong number of 80m speed B samples received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getSh80().getSpeedb(), "Wrong value of 80m speed B received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getSh80().getMastSpeedb(), "Wrong value of 80m mast speed B received for buoy B.");
        assertEquals(108.0, tBuoyB.get().getSh80().getSpeedbSqr(), "Wrong value of 80m speed B square received for buoy B.");
        assertEquals(48.0, tBuoyB.get().getSh80().getMastSpeedbSqr(), "Wrong value of 80m mast speed B square received for buoy B.");
        assertEquals(72.0, tBuoyB.get().getSh80().getSpeedbProd(), "Wrong value of 80m speed B product received for buoy B.");
        
        //  buoy speed 7, mast speed 5
        assertEquals(3l, tBuoyB.get().getSh100().getCounta(), "Wrong number of 100m speed A samples received for buoy B.");
        assertEquals(21.0, tBuoyB.get().getSh100().getSpeeda(), "Wrong value of 100m speed A received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh100().getMastSpeeda(), "Wrong value of 100m mast speed A received for buoy B.");
        assertEquals(147.0, tBuoyB.get().getSh100().getSpeedaSqr(), "Wrong value of 100m speed A square received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getSh100().getMastSpeedaSqr(), "Wrong value of 100m mast speed A square received for buoy B.");
        assertEquals(105.0, tBuoyB.get().getSh100().getSpeedaProd(), "Wrong value of 100m speed A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getSh100().getCountb(), "Wrong number of 100m speed B samples received for buoy B.");
        assertEquals(21.0, tBuoyB.get().getSh100().getSpeedb(), "Wrong value of 100m speed B received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getSh100().getMastSpeedb(), "Wrong value of 100m mast speed B received for buoy B.");
        assertEquals(147.0, tBuoyB.get().getSh100().getSpeedbSqr(), "Wrong value of 100m speed B square received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getSh100().getMastSpeedbSqr(), "Wrong value of 100m mast speed B square received for buoy B.");
        assertEquals(105.0, tBuoyB.get().getSh100().getSpeedbProd(), "Wrong value of 100m speed B product received for buoy B.");
        
        //  buoy direction 3, mast direction 2
        assertEquals(3l, tBuoyB.get().getDh40().getCounta(), "Wrong number of 40m direction A samples received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh40().getDira(), "Wrong value of 40m direction A received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh40().getMastDira(), "Wrong value of 40m mast direction A received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getDh40().getDiraSqr(), "Wrong value of 40m direction A square received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getDh40().getMastDiraSqr(), "Wrong value of 40m mast direction A square received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getDh40().getDiraProd(), "Wrong value of 40m direction A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getDh40().getCountb(), "Wrong number of 40m direction B samples received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh40().getDirb(), "Wrong value of 40m direction B received for buoy B.");
        assertEquals(6.0, tBuoyB.get().getDh40().getMastDirb(), "Wrong value of 40m mast direction B received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getDh40().getDirbSqr(), "Wrong value of 40m direction B square received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getDh40().getMastDirbSqr(), "Wrong value of 40m mast direction B square received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getDh40().getDirbProd(), "Wrong value of 40m direction B product received for buoy B.");
        
        //  buoy direction 5, mast direction 3
        assertEquals(3l, tBuoyB.get().getDh60().getCounta(), "Wrong number of 60m direction A samples received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh60().getDira(), "Wrong value of 60m direction A received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh60().getMastDira(), "Wrong value of 60m mast direction A received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getDh60().getDiraSqr(), "Wrong value of 60m direction A square received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getDh60().getMastDiraSqr(), "Wrong value of 60m mast direction A square received for buoy B.");
        assertEquals(45.0, tBuoyB.get().getDh60().getDiraProd(), "Wrong value of 60m direction A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getDh60().getCountb(), "Wrong number of 60m direction B samples received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh60().getDirb(), "Wrong value of 60m direction B received for buoy B.");
        assertEquals(9.0, tBuoyB.get().getDh60().getMastDirb(), "Wrong value of 60m mast direction B received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getDh60().getDirbSqr(), "Wrong value of 60m direction B square received for buoy B.");
        assertEquals(27.0, tBuoyB.get().getDh60().getMastDirbSqr(), "Wrong value of 60m mast direction B square received for buoy B.");
        assertEquals(45.0, tBuoyB.get().getDh60().getDirbProd(), "Wrong value of 60m direction B product received for buoy B.");
        
        //  buoy direction 6, mast direction 4
        assertEquals(3l, tBuoyB.get().getDh80().getCounta(), "Wrong number of 80m direction A samples received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getDh80().getDira(), "Wrong value of 80m direction A received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getDh80().getMastDira(), "Wrong value of 80m mast direction A received for buoy B.");
        assertEquals(108.0, tBuoyB.get().getDh80().getDiraSqr(), "Wrong value of 80m direction A square received for buoy B.");
        assertEquals(48.0, tBuoyB.get().getDh80().getMastDiraSqr(), "Wrong value of 80m mast direction A square received for buoy B.");
        assertEquals(72.0, tBuoyB.get().getDh80().getDiraProd(), "Wrong value of 80m direction A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getDh80().getCountb(), "Wrong number of 80m direction B samples received for buoy B.");
        assertEquals(18.0, tBuoyB.get().getDh80().getDirb(), "Wrong value of 80m direction B received for buoy B.");
        assertEquals(12.0, tBuoyB.get().getDh80().getMastDirb(), "Wrong value of 80m mast direction B received for buoy B.");
        assertEquals(108.0, tBuoyB.get().getDh80().getDirbSqr(), "Wrong value of 80m direction B square received for buoy B.");
        assertEquals(48.0, tBuoyB.get().getDh80().getMastDirbSqr(), "Wrong value of 80m mast direction B square received for buoy B.");
        assertEquals(72.0, tBuoyB.get().getDh80().getDirbProd(), "Wrong value of 80m direction B product received for buoy B.");
        
        //  buoy direction 7, mast direction 5
        assertEquals(3l, tBuoyB.get().getDh100().getCounta(), "Wrong number of 100m direction A samples received for buoy B.");
        assertEquals(21.0, tBuoyB.get().getDh100().getDira(), "Wrong value of 100m direction A received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh100().getMastDira(), "Wrong value of 100m mast direction A received for buoy B.");
        assertEquals(147.0, tBuoyB.get().getDh100().getDiraSqr(), "Wrong value of 100m direction A square received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getDh100().getMastDiraSqr(), "Wrong value of 100m mast direction A square received for buoy B.");
        assertEquals(105.0, tBuoyB.get().getDh100().getDiraProd(), "Wrong value of 100m direction A product received for buoy B.");
        assertEquals(3l, tBuoyB.get().getDh100().getCountb(), "Wrong number of 100m direction B samples received for buoy B.");
        assertEquals(21.0, tBuoyB.get().getDh100().getDirb(), "Wrong value of 100m direction B received for buoy B.");
        assertEquals(15.0, tBuoyB.get().getDh100().getMastDirb(), "Wrong value of 100m mast direction B received for buoy B.");
        assertEquals(147.0, tBuoyB.get().getDh100().getDirbSqr(), "Wrong value of 100m direction B square received for buoy B.");
        assertEquals(75.0, tBuoyB.get().getDh100().getMastDirbSqr(), "Wrong value of 100m mast direction B square received for buoy B.");
        assertEquals(105.0, tBuoyB.get().getDh100().getDirbProd(), "Wrong value of 100m direction B product received for buoy B.");
    }

}
