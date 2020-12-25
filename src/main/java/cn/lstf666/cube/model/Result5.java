package cn.lstf666.cube.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author liaotao
 * @Date 2020/10/5 19:28
 * 五次成绩对应实体类
 */
public class Result5
{

    private int rId;
    private int cId;
    private int pId;
    private String name;
    private String event;
    private int rounds;
    private double t1;
    private double t2;
    private double t3;
    private double t4;
    private double t5;
    private double single;
    private double avg;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public double getT3() {
        return t3;
    }

    public void setT3(double t3) {
        this.t3 = t3;
    }

    public double getT4() {
        return t4;
    }

    public void setT4(double t4) {
        this.t4 = t4;
    }

    public Result5(int cId, int pId, String event, int rounds, double t1, double t2, double t3, double t4, double t5) {
        this.cId = cId;
        this.pId = pId;
        this.event = event;
        this.rounds = rounds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
    }

    public Result5(int cId, String name, String event, int rounds, double t1, double t2, double t3, double t4, double t5) {
        this.cId = cId;
        this.name = name;
        this.event = event;
        this.rounds = rounds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
    }

    public double getT5() {
        return t5;
    }

    public Result5() {
    }

    public Result5(int rId, int cId, int pId, String name, String event, int rounds, double t1, double t2, double t3, double t4, double t5, double single, double avg) {
        this.rId = rId;
        this.cId = cId;
        this.pId = pId;
        this.name = name;
        this.event = event;
        this.rounds = rounds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.single = single;
        this.avg = avg;
    }

    public Result5(int cId, int pId, String name, String event, int rounds, double t1, double t2, double t3, double t4, double t5, double single, double avg) {

        this.cId = cId;
        this.pId = pId;
        this.name = name;
        this.event = event;
        this.rounds = rounds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.single = single;
        this.avg = avg;
    }

    public Result5(int cId, int pId, String name, String event, int rounds, double t1, double t2, double t3, double t4, double t5) {
        this.cId = cId;
        this.pId = pId;
        this.name = name;
        this.event = event;
        this.rounds = rounds;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
    }

    public void count(){

        List<Double> list = new ArrayList<Double>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        this.single= Collections.min(list);
        double max = Collections.max(list);
        // 保留三位小数
        BigDecimal b = new BigDecimal((t1+t2+t3+t4+t5-max-this.single)/3.0);
        this.avg = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

    }
    public void setT5(double t5) {
        this.t5 = t5;
    }

    public double getSingle() {
        return single;
    }

    public void setSingle(double single) {
        this.single = single;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Result5{" +
                "rId=" + rId +
                ", cId=" + cId +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", event=" + event +
                ", rounds=" + rounds +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                ", t4=" + t4 +
                ", t5=" + t5 +
                ", single=" + single +
                ", avg=" + avg +
                '}';
    }
}
