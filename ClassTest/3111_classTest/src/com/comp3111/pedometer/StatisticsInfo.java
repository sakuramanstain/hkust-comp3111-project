package com.comp3111.pedometer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;

/**
 * Class for calculating statistical information
 * @author Henry Chan
 *
 */
public class StatisticsInfo {

	public static enum stepSize {
		LARGE, MEDIUM, SMALL
	};
	
	// pace distribution graph data
	//public ArrayList<GraphViewData> pace_dist = new ArrayList<GraphViewData>();

	public stepSize stepSizeVariable = stepSize.MEDIUM;
	public int stepLength = 80; // in cm
	public double metValue = 0.2;
	public double distanceTravelled;
	public double weight = 65;
	public double stepDuration;
	public int totalSteps;
	public double timeLasted = 0.00001;// second
	public String recordDateString;
	public String journeyType;		// goals title
	public String journeyTime;		// time elapsed / steps / etc 
	
	public StatisticsInfo(double kilogram){
		setWeight(kilogram);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		recordDateString = df.format(new Date());
		//Log.i("StatisticsInfo", "New record created with DateStr: " + recordDateString);
	}
	
	public void addTime(double unit){
		timeLasted += unit;
	}

	public void setWeight(double kilogram) {
		weight = kilogram;
	}


	public void setStepSize(stepSize sS) {
		stepSizeVariable = sS;
		if(sS == stepSize.SMALL){
			stepLength = 40;
		}else if(sS == stepSize.MEDIUM){
			stepLength = 80;
		}else{
			stepLength = 160;
		}
	}
	
	public void setTotalSteps(int st){
		totalSteps = st;
	}
	
	public int getTotalSteps(){
		return totalSteps;
	}

	public double getWeight() {
		return weight;
	}

	public stepSize getStepsize() {
		return stepSizeVariable;
	}

	public double getCaloriesBurn() {
		return (double) metValue * weight * (totalSteps / 360.0);
	}

	public double getDistanceTravelled() {
		// 1 mile ~= 160 cm
		distanceTravelled = (stepLength * totalSteps) / 160.0;	// in miles 
		return distanceTravelled;
	}

	public double getStepPerTime() {
		double stepPerTime = totalSteps / (timeLasted / 60);	// per min
		return stepPerTime;
	}

	public double getDistancePerTime() {
		double distancePerTime = distanceTravelled / (timeLasted / 3600);	// per hour
		return distancePerTime;
	}

	public double getTimeLasted() {
		return timeLasted;
	}
	
	public String getDateString() {
		return recordDateString;
	}
	
	public String toJSON(){
		String json = new Gson().toJson(this);
		//Log.i("JSON record", json);
		return json;
	}

}