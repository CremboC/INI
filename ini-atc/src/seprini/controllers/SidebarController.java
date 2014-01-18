package seprini.controllers;

import java.util.HashMap;

import seprini.data.Art;
import seprini.data.Config;
import seprini.data.State;
import seprini.models.Aircraft;
import seprini.screens.GameScreen;
import seprini.screens.MenuScreen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.tablelayout.Cell;

/**
 * Controls the sidebar in the GameScreen
 * 
 * @author Paulius, Miguel
 * 
 */
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

	private Table aircraftControls, waypointWrapper, bottomButtons;

	/**
	 * 
	 * 
	 * @param sidebar
	 *            the sidebar layout, so the controller can add all of the
	 *            buttons
	 * @param aircrafts
	 *            so this can get the selected aircraft
	 * @param screen
	 *            for changing screens once Menu or Pause have been clicked
	 */
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

		// wrapper for aicraft controls
		aircraftControls = new Table();
		aircraftControls.setFillParent(true);

		if (Config.DEBUG_UI)
			aircraftControls.debug();

		aircraftControls.top();
		sidebar.addActor(aircraftControls);

		// wrapper for waypoint list
		waypointWrapper = new Table();
		waypointWrapper.setFillParent(true);

		sidebar.addActor(waypointWrapper);

		// wrapper for bottom buttons
		bottomButtons = new Table();

		bottomButtons.setFillParent(true);

		if (Config.DEBUG_UI)
			aircraftControls.debug();

		bottomButtons.bottom();
		sidebar.addActor(bottomButtons);

		// adding labels to aircraft controls
		createLabel("speed", " Speed: ", aircraftControls).width(100);
		createLabel("altitude", "Altitude: ", aircraftControls).width(100);

		aircraftControls.row().width(100);

		// adding buttons to aircraft controls
		createButton("createWaypoint", " Create Waypoint", aircraftControls)
				.width(100);
		createButton("assignWaypoint", " Assign Waypoint", aircraftControls)
				.width(100);

		aircraftControls.row().spaceTop(50);

		createButton("accelerate", " Accelerate", aircraftControls).width(200)
				.colspan(2);
		// createButton("takeOff", "Take Off", aircraftControls).width(100);

		aircraftControls.row();

		createButton("decelerate", " Decelerate", aircraftControls).width(200)
				.colspan(2);
		// createButton("land", "Land", aircraftControls).width(100);

		aircraftControls.row().spaceTop(100);

		createButton("up", " Up", aircraftControls).width(100).colspan(2);

		aircraftControls.row();

		createButton("left", " Left", aircraftControls).width(100);
		createButton("right", "Right", aircraftControls).width(100);

		aircraftControls.row();

		createButton("down", "Down", aircraftControls).width(100).colspan(2);

		aircraftControls.row();

		createLabel("", "Time:").width(100);
		createLabel("timer", "..").width(100);

		// adding buttons to bottom
		createButton("menu", " Menu", bottomButtons).width(100);
		createButton("pause", " Pause", bottomButtons).width(100);
	}

	/**
	 * Update the sidebar according to changes in the AircraftController
	 */
	public void update() {
		// update timer
		labels.get("timer").setText("" + Math.round(State.time()));

		// if there is no selected aircraft, return immediately to avoid errors
		// otherwise set it to the local selectedAircraft variable and update
		// the text
		if ((selectedAircraft = aircrafts.getSelectedAircraft()) == null)
			return;

		// update aircraft altitude text
		labels.get("altitude").setText(
				"Altitude: " + selectedAircraft.getAltitude() + "m");

		// update aircraft speed text
		labels.get("speed").setText(
				"Speed: "
						+ Math.round(selectedAircraft.getSpeed()
								* Config.AIRCRAFT_SPEED_MULTIPLIER) + "km/h");
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
	private Cell<?> createButton(String name, String text, Table parent) {
		TextButton button = new TextButton(text, Art.getSkin());
		button.pad(3);
		button.addListener(this);

		buttons.put(name, button);

		return parent.add(button);
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

	/**
	 * Convinience method to create labels and add them to the sidebar
	 * 
	 * @param name
	 * @param text
	 * @return
	 */
	private Cell<?> createLabel(String name, String text, Table parent) {
		Label label = new Label(text, Art.getSkin());

		labels.put(name, label);

		return parent.add(label);
	}

	@Override
	public void changed(ChangeEvent event, Actor actor) {

		if (actor.equals(buttons.get("createWaypoint")))
			createWaypointClicked();

		if (actor.equals(buttons.get("assignWaypoint")))
			assignWaypointClicked();

		if (selectedAircraft != null) {
			if (actor.equals(buttons.get("left")))
				selectedAircraft.turnLeft();

			if (actor.equals(buttons.get("right")))
				selectedAircraft.turnRight();

			if (actor.equals(buttons.get("up")))
				selectedAircraft.increaseAltitude();

			if (actor.equals(buttons.get("down")))
				selectedAircraft.decreaseAltitude();

			if (actor.equals(buttons.get("accelerate")))
				selectedAircraft.increaseSpeed();

			if (actor.equals(buttons.get("decelerate")))
				selectedAircraft.decreaseSpeed();
		}

		if (actor.equals(buttons.get("menu"))) {
			Art.getSound("ambience").stop();
			screen.setScreen(new MenuScreen());
		}
	}

	/**
	 * This is true when the button to create new waypoints has clicked, false
	 * otherwise
	 * 
	 * @return whether the create waypoint button has been clicked
	 */
	public boolean allowNewWaypoints() {
		return allowNewWaypoints;
	}

	/**
	 * True when then button to redirect aircraft has been clicked, false
	 * otherwise
	 * 
	 * @return whether the button as be clicked or not
	 */
	public boolean allowRedirection() {
		return allowRedirection;
	}

}
