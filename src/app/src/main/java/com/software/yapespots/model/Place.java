package com.software.yapespots.model;

import com.software.yapespots.model.local.FavoritePlace;

import java.util.ArrayList;

public class Place {
    private String id;
    private String lat;
    private String lng;
    private String name;
    private ArrayList<String> type;
    private Boolean opennow;
    public Place(){}

    public Place(FavoritePlace favorite){
        this.id = favorite.id;
        this.lat = String.valueOf(favorite.lat);
        this.lng = String.valueOf(favorite.lng);
        this.name = favorite.name;
        this.type = new ArrayList<String>();
        type.add(favorite.type);
        this.opennow = favorite.opennow;

    }

    public Boolean getOpennow(){ return opennow;}

    public void setOpennow(Boolean open){  opennow = open;}

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type=type;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public boolean searchType(String tipo){
        boolean exist = false;
        for(int i=0;i<type.size();i++){
            if(tipo.equals(type.get(i))){
                exist = true;
                break;
            }
        }
        return exist;
    }
        /*public DetailPlace getDetailPlace() {
            return detailPlace;
        }

        public void setDetailPlace(DetailPlace newDetailPlace) {
            this.detailPlace = newDetailPlace;
        }*/
}