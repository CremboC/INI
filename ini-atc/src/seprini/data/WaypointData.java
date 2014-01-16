package seprini.data;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import seprini.models.Exitpoint;
import seprini.models.Waypoint;

public class WaypointData {
	public final static ArrayList<Waypoint> permanentWaypointList = new ArrayList<Waypoint>();
	public final static ArrayList<Waypoint> userWaypointList = new ArrayList<Waypoint>();
	public final static ArrayList<Waypoint> entryList = new ArrayList<Waypoint>();
	public final static ArrayList<Exitpoint> exitList = new ArrayList<Exitpoint>();
	
	public WaypointData(){
		// add entry waypoints to entryList
				entryList.add(new Waypoint(new Vector2(0, 0), true));
				entryList.add(new Waypoint(new Vector2(0, 720), true));
				entryList.add(new Waypoint(new Vector2(1080, 360), true));

				// add exit waypoints to exitList
				exitList.add(new Exitpoint(new Vector2(1080, 720)));
				exitList.add(new Exitpoint(new Vector2(1080, 0)));
				exitList.add(new Exitpoint(new Vector2(0, 420)));

				// add some waypoints
				permanentWaypointList.add(new Waypoint(new Vector2(300, 200), true));
				permanentWaypointList.add(new Waypoint(new Vector2(300, 500), true));
				permanentWaypointList.add(new Waypoint(new Vector2(700, 200), true));
				permanentWaypointList.add(new Waypoint(new Vector2(700, 500), true));				
	}
}
