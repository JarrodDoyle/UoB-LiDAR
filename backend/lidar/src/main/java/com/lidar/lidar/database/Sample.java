package com.lidar.lidar.database;

import javax.persistence.*;

@Entity @Table(name = "samples")
public class Sample {
    @Id @GeneratedValue @Column(name = "id")
    Long id;

    @Column(name = "direction30m")
    Double direction30m;
    @Column(name = "direction40m")
    Double direction40m;
    @Column(name = "direction60m")
    Double direction60m;
    @Column(name = "direction80m")
    Double direction80m;
    @Column(name = "direction100m")
    Double direction100m;

    @Column(name = "speed30m")
    Double speed30m;
    @Column(name = "speed40m")
    Double speed40m;
    @Column(name = "speed60m")
    Double speed60m;
    @Column(name = "speed80m")
    Double speed80m;
    @Column(name = "speed100m")
    Double speed100m;

    @Column(name = "ti30m")
    Double ti30m;
    @Column(name = "ti40m")
    Double ti40m;
    @Column(name = "ti60m")
    Double ti60m;
    @Column(name = "ti80m")
    Double ti80m;
    @Column(name = "ti100m")
    Double ti100m;

    public Sample() {
        direction30m = 0d;
        direction40m = 0d;
        direction60m = 0d;
        direction80m = 0d;
        direction100m = 0d;

        speed30m = 0d;
        speed40m = 0d;
        speed60m = 0d;
        speed80m = 0d;
        speed100m = 0d;

        ti30m = 0d;
        ti40m = 0d;
        ti60m = 0d;
        ti80m = 0d;
        ti100m = 0d;
    }
}
