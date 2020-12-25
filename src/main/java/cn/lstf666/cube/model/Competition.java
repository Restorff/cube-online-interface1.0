package cn.lstf666.cube.model;

import java.io.Serializable;

/**
 * @Author liaotao
 * @Date 2020/10/3 15:28
 * 比赛实体类
 */
public class Competition implements Serializable {

    private int cId;
    private String name;
    private String time;
    private String location;
    private int maxNum;
    private int nowNum;
    private int e222;
    private int e333;
    private int e444;
    private int e555;
    private int e666;
    private int e777;
    private int e333bf;
    private int e333oh;
    private int e333fm;
    private int eclock;
    private int eminx;
    private int epyram;
    private int eskewb;
    private int esq1;
    private int e444bf;
    private int e555bf;
    private int e333mbf;
    private String schedule;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public int getE222() {
        return e222;
    }

    public void setE222(int e222) {
        this.e222 = e222;
    }

    public int getE333() {
        return e333;
    }

    public void setE333(int e333) {
        this.e333 = e333;
    }

    public int getE444() {
        return e444;
    }

    public void setE444(int e444) {
        this.e444 = e444;
    }

    public int getE555() {
        return e555;
    }

    public void setE555(int e555) {
        this.e555 = e555;
    }

    public int getE666() {
        return e666;
    }

    public void setE666(int e666) {
        this.e666 = e666;
    }

    public int getE777() {
        return e777;
    }

    public void setE777(int e777) {
        this.e777 = e777;
    }

    public int getE333bf() {
        return e333bf;
    }

    public void setE333bf(int e333bf) {
        this.e333bf = e333bf;
    }

    public int getE333oh() {
        return e333oh;
    }

    public void setE333oh(int e333oh) {
        this.e333oh = e333oh;
    }

    public int getE333fm() {
        return e333fm;
    }

    public void setE333fm(int e333fm) {
        this.e333fm = e333fm;
    }

    public int getEclock() {
        return eclock;
    }

    public void setEclock(int eclock) {
        this.eclock = eclock;
    }

    public int getEpyram() {
        return epyram;
    }

    public void setEpyram(int epyram) {
        this.epyram = epyram;
    }

    public int getEskewb() {
        return eskewb;
    }

    public void setEskewb(int eskewb) {
        this.eskewb = eskewb;
    }

    public int getEsq1() {
        return esq1;
    }

    public void setEsq1(int esq1) {
        this.esq1 = esq1;
    }

    public int getE444bf() {
        return e444bf;
    }

    public void setE444bf(int e444bf) {
        this.e444bf = e444bf;
    }

    public int getE555bf() {
        return e555bf;
    }

    public void setE555bf(int e555bf) {
        this.e555bf = e555bf;
    }

    public int getE333mbf() {
        return e333mbf;
    }

    public void setE333mbf(int e333mbf) {
        this.e333mbf = e333mbf;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getEminx() {
        return eminx;
    }

    public void setEminx(int eminx) {
        this.eminx = eminx;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "cId=" + cId +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", maxNum=" + maxNum +
                ", nowNum=" + nowNum +
                ", e222=" + e222 +
                ", e333=" + e333 +
                ", e444=" + e444 +
                ", e555=" + e555 +
                ", e666=" + e666 +
                ", e777=" + e777 +
                ", e333bf=" + e333bf +
                ", e333oh=" + e333oh +
                ", e333fm=" + e333fm +
                ", eclock=" + eclock +
                ", eminz=" + eminx +
                ", ephram=" + epyram +
                ", eskewb=" + eskewb +
                ", esq1=" + esq1 +
                ", e444bf=" + e444bf +
                ", e555bf=" + e555bf +
                ", e333mbf=" + e333mbf +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}

