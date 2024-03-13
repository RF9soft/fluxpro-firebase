package com.rf9.tips.model;

public class DataModel {

    String league, team, odds, tips, date, vs;

    public DataModel() {
    }

    public DataModel(String league, String team, String odds, String tips, String date, String vs) {
        this.league = league;
        this.team = team;
        this.odds = odds;
        this.tips = tips;
        this.date = date;
        this.vs = vs;
    }

    public String getLeague() {
        return league;
    }

    public String getTeam() {
        return team;
    }

    public String getOdds() {
        return odds;
    }

    public String getTips() {
        return tips;
    }

    public String getDate() {
        return date;
    }

    public String getVs() {
        return vs;
    }
}
