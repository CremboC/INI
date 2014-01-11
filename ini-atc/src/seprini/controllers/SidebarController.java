package seprini.controllers;

import java.util.HashMap;

import seprini.data.Art;
import seprini.models.Aircraft;
import seprini.screens.GameScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.tablelayout.Cell;

public final class SidebarController extends ChangeListener implements
		Controller {

	private final Table sidebar;

	private final AircraftController aircrafts;

	private Aircraft selectedAircraft;

	private final HashMap<String, TextButton> buttons = new HashMap<String, TextButton>();
	private final HashMap<String, Label> labels = new HashMap<String, Label>();

	private boolean allowNewWaypoints = false;
	private boolean allowRedirection = false;

	private final GameScreen screen;

	public SidebarController(Table sidebar, AircraftController aircrafts,
			GameScreen screen) {
		this.sidebar = sidebar;
		this.aircrafts = aircrafts;
		this.screen = screen;
	}

	/**
	 * Initialise all the buttons and labels
	 */

	public void init() {
		createLabel("", "Speed").width(100);
		createLabel("", "Heading").width(100);
		sidebar.row();
		createButton("createWaypoint", "Create Waypoint").width(100);
		createButton("assignWaypoint", "Assign Waypoint").width(100);
		sidebar.row();
		createButton("", "Accelerate: ").width(100);
		createButton("", "Take Off").width(100);
		sidebar.row();
		createButton("", "Decelerate: ").width(100);
		createButton("", "Land").width(100);
		sidebar.row();
		createButton("", "Up").width(100);
		createButton("", "Up").width(100);
		sidebar.row();
		createButton("", "Left").width(100);
		createButton("", "Right").width(100);
		sidebar.row();
		createButton("", "Down").width(100);
		createButton("", "Down").width(100);
		sidebar.row();
		createLabel("", "Timer").width(100);
		sidebar.row();
		createButton("", "Menu").width(100);
		createButton("", "Pause").width(100);
		/**
		 * createLabel("aircraftCoordsLabel", "Coords X/Y: ").width(100);
		 * createLabel("aircraftCoordsText", "0").width(100);
		 */
	}

	/**
	 * Update the sidebar according to changes in the AircraftController
	 */
	public void update() {
		if ((selectedAircraft = aircrafts.getSelectedAircraft()) == null)
			return;

		labels.get("aircraftText").setText(selectedAircraft.toString());
		labels.get("aircraftCoordsText").setText(
				Float.toString(Math.round(selectedAircraft.getX())) + " "
						+ Float.toString(Math.round(selectedAircraft.getY())));
	}

	/**
	 * Handles what happens after the 'create waypoint' button has been clicked
	 */
	private void createWaypointClicked() {
		allowNewWaypoints = (allowNewWaypoints) ? false : true;
	}

	/**
	 * Handles what happens after the 'assign waypoint' button has been clicked
	 */
	private void assignWaypointClicked() {
		allowRedirection = (allowRedirection) ? false : true;
	}

	/**
	 * Convinience method to create buttons and add them to the sidebar
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	private Cell<?> createButton(String name, String text) {
		TextButton button = new TextButton(text, Art.getSkin());
		button.addListener(this);

		buttons.put(name, button);

		return sidebar.add(button);
	}

	/**
	 * Convinience method to create labels and add them to the sidebar
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	private Cell<?> createLabel(String name, String text) {
		Label label = new Label(text, Art.getSkin());

		labels.put(name, label);

		return sidebar.add(label);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		if (actor.equals(buttons.get("createWaypoint")))
			createWaypointClicked();
		else if (actor.equals(buttons.get("assignWaypoint")))
			assignWaypointClicked();

	}

	public boolean allowNewWaypoints() {
		return allowNewWaypoints;
	}

	public boolean isAllowRedirection() {
		return allowRedirection;
	}

}
