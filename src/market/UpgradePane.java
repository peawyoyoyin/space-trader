package market;

import constants.ConfigConstant;
import game.logic.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class UpgradePane extends GridPane {

	public UpgradePane() {
		super();
		// TODO Auto-generated constructor stub
		Text upgrade = new Text("Upgrade");
		upgrade.setFont(ConfigConstant.Resource.HUD_FONT);
		this.add(upgrade, 0, 0, 2, 1);
		Text hp = new Text("Upgrade HP");
		hp.setFont(ConfigConstant.Resource.HUD_FONT);
		this.add(hp, 0, 1);
		Button upHp = new Button("+ 20");
		this.add(upHp, 1, 1);
		Text dmg = new Text("Upgrade Damage");
		dmg.setFont(ConfigConstant.Resource.HUD_FONT);
		this.add(dmg, 0, 2);
		Button upDmg = new Button("+ 2");
		this.add(upDmg, 1, 2);
		Text speed = new Text("Upgrade Speed");
		speed.setFont(ConfigConstant.Resource.HUD_FONT);
		this.add(speed, 0, 3);
		Button upSpeed = new Button("+ 2");
		this.add(upSpeed, 1, 3);
		this.setAlignment(Pos.CENTER);
		Button back = new Button("Buy / Sell");
		this.add(back, 1, 4);

		this.getColumnConstraints().add(new ColumnConstraints(250));
		GridPane.setMargin(upgrade, new Insets(20, 0, 20, 0));
		GridPane.setMargin(hp, new Insets(10, 0, 10, 20));
		GridPane.setMargin(dmg, new Insets(10, 0, 10, 20));
		GridPane.setMargin(speed, new Insets(10, 0, 10, 20));
		GridPane.setMargin(back, new Insets(75, 0, 0, 0));

		upHp.setOnAction(e -> {
			Player.instance.getPlayerShip().setMaxHp(Player.instance.getPlayerShip().getMaxHp() + 20);
			Player.instance.getPlayerShip().getShipProperty("hp")
					.set(Player.instance.getPlayerShip().getShipProperty("maxHp").get());
			Player.instance.getPlayerShip().heal();
		});
		upDmg.setOnAction(e -> {
			Player.instance.setBulletDamage(Player.instance.getBulletDamage() + 2);
		});
		upSpeed.setOnAction(e -> {
			Player.instance.getPlayerShip().setMaxSpeed(Player.instance.getPlayerShip().getSpeedProperty().get() + 2);
		});

		back.setOnAction(e -> {
			TraderScreen.togglePane();
		});
	}

}
