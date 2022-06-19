package com.company;


public class Msg  {
    public int from;
    public int[] to;
    public String testo;

    public Msg(int from,int[] to,String testo){
        this.from=from;
        this.to=to;
        this.testo=testo;
    }
}
