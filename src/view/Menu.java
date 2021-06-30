package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;

import controller.Game;
import controller.Game.STATE;
import model.Entity;
import profiles.ProfileSubsystemFacade;

public class Menu extends MouseAdapter {

	private Game game;
	private Window window;
	private JTextField eingabe;
	private ProfileSubsystemFacade profil;
	private Animation Gegner3;
	static Texture tex;
	private static Path directory = Paths.get("res");
	private boolean textfieldon = false;

	public Menu(Game game, Window window) {
		this.game = game;
		this.window = window;
		tex = new Texture();
		Gegner3 = new Animation(7, tex.gegner[27], tex.gegner[28], tex.gegner[29], tex.gegner[30], tex.gegner[31],
				tex.gegner[32], tex.gegner[33], tex.gegner[34], tex.gegner[35]);

		profil = new ProfileSubsystemFacade();
	}

	public void tick() {
		Gegner3.runAnimation();
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.menu) {
			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 1, 50);
			Font fnt4 = new Font("arial", 1, 50);
			Font fnt5 = new Font("arial", 1, 45);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 320, 100);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(80, 180, 270, 90);
			g.drawString("Play!", 160, 245);

			g.setFont(fnt5);
			g.setColor(Color.white);
			g.drawRect(450, 180, 270, 90);
			g.drawString("Multiplayer", 465, 240);

			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawRect(280, 330, 270, 90);
			g.drawString("Controls", 312, 393);

			g.setFont(fnt4);
			g.setColor(Color.white);
			g.drawRect(280, 630, 270, 90);
			g.drawString("Credits", 330, 693);

			g.setFont(fnt5);
			g.setColor(Color.white);
			g.drawRect(280, 480, 270, 90);
			g.drawString("Highscores", 295, 543);

		}
		if (game.gameState == STATE.Help) {

			Font fnt = new Font("arial", 1, 90);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Controls", 215, 120);

			Font fnt2 = new Font("arial", 1, 50);
			g.setFont(fnt2);
			g.drawRect(580, 680, 190, 90);
			g.drawString("Back", 615, 740);

			Font fnt3 = new Font("arial", 1, 20);
			g.setFont(fnt3);

			g.drawString("Du hast 3 Leben! wirst du getroffen verlierst du eins", 50, 260);
			g.drawString("Besiege alle Gegner bevor sie dich erreichen !", 50, 200);
			g.drawString("Sind alle Gegner besiegt, Startet ein neues schwierigeres Level", 50, 230);

			g.drawString("Spieler 1 :", 30, 320);
			g.drawString("Benutze die [A] Taste für Links, und die [D] Taste für Rechts", 30, 350);
			g.drawString("Drücke die SPACE Taste zum Schießen", 30, 380);

			g.drawString("Spieler 2:", 30, 420);
			g.drawString("Benutze [NUM 4] oder [<-] für Links, und [NUM 6] oder [->] für Rechts ", 30, 450);
			g.drawString("Drücke [UP] oder [DOWN] um zum Schießen", 30, 480);

			g.drawString("Benutze die [ESCAPE] Taste um das Spiel zu pausieren", 30, 520);

			g.drawImage(tex.tastatur[0], 10, 585, 563, 195, null);

		}
		if (game.gameState == STATE.Credits) {
			g.setColor(Color.white);
			Font fnt2 = new Font("arial", 1, 50);
			g.setFont(fnt2);
			g.drawRect(50, 650, 270, 90);

			g.drawString("Back", 125, 713);

			Font fnt = new Font("arial", 1, 90);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Credits", 250, 120);

			Font fnt3 = new Font("calibri", 3, 40);
			g.setFont(fnt3);

			g.drawString("Louis Micke", 50, 260);
			g.drawString("Leonardo Maglanoc", 50, 310);
			g.drawString("Vicente Kiekebusch", 50, 360);
			g.drawString("Thekla Meier", 50, 410);

			Gegner3.drawAnimation(g, 520, 250, 180, 180);

		}
		if (game.gameState == STATE.Profile) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Profile", 310, 100);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 180, 270, 90);
			g.drawString("Add", 365, 245);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 330, 270, 90);
			g.drawString("Remove", 315, 393);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 630, 270, 90);
			g.drawString("Back", 355, 693);

		}

		if (game.gameState == STATE.Add) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 2, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Add Profile", 220, 100);

			g.setFont(fnt3);
			g.setColor(Color.white);

			g.drawString("Enter Name", 265, 285);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(180, 320, 430, 90);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(335, 630, 150, 90);
			g.drawString("OK", 370, 693);

		}
		if (game.gameState == STATE.Remove) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 2, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Remove Profile", 150, 100);

			g.setFont(fnt3);
			g.setColor(Color.white);

			g.drawString("Enter Name", 265, 285);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(185, 320, 430, 90);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(220, 630, 150, 90);

			g.drawString("OK", 260, 693);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(430, 630, 150, 90);
			g.drawString("Back", 445, 693);

		}

		if (game.gameState == STATE.Scores) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 42);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Highscores", 220, 100);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(75, 205, 270, 90);
			g.drawString("View Top", 120, 265);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(450, 205, 270, 90);
			g.drawString("View Recent", 460, 265);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 350, 270, 90);
			g.drawString("Reset", 360, 410);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 630, 270, 90);
			g.drawString("Back", 365, 693);

		}
		if (game.gameState == STATE.HighSc) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 1, 40);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Top Scores", 230, 100);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(280, 630, 270, 90);
			g.drawString("Back", 355, 693);

			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString(profil.getTopScore(0), 220, 250);
			g.drawString(profil.getTopScore(1), 220, 310);
			g.drawString(profil.getTopScore(2), 220, 370);
			g.drawString(profil.getTopScore(3), 220, 430);
			g.drawString(profil.getTopScore(4), 220, 490);

		}
		if (game.gameState == STATE.RecSc) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 50);
			Font fnt3 = new Font("arial", 1, 40);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Recent Scores", 160, 100);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(270, 630, 270, 90);
			g.drawString("Back", 345, 693);

			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString(profil.getRecentScore(0), 220, 250);
			g.drawString(profil.getRecentScore(1), 220, 310);
			g.drawString(profil.getRecentScore(2), 220, 370);
			g.drawString(profil.getRecentScore(3), 220, 430);
			g.drawString(profil.getRecentScore(4), 220, 490);

		}
		if (game.gameState == STATE.End) {

			Font fnt = new Font("arial", 1, 70);
			Font fnt2 = new Font("arial", 1, 42);
			Font fnt3 = new Font("arial", 2, 40);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 220, 110);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Enter your name", 240, 280);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(370, 430, 70, 70);
			g.drawString("OK", 372, 481);

			g.setFont(fnt3);
			g.setColor(Color.white);

			g.drawRect(270, 330, 270, 70);
			g.drawString("> Click here <", 280, 380);

			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Score:" + Entity.getScore(), 300, 650);
		}

	}

	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		////////////////////////// Play Game
		if (game.gameState == STATE.menu) {
			if (mouseOver(mx, my, 80, 180, 270, 90)) {
				game.gameState = STATE.Game;
				game.init();
			}
		}

		////////////////////////// Play Multiplayer
		if (game.gameState == STATE.menu) {
			if (mouseOver(mx, my, 450, 180, 270, 90)) {
				game.gameState = STATE.Game2P;
				game.init();
			}
		}

		////////////////////////// Credits
		if (game.gameState == STATE.menu) {
			if (mouseOver(mx, my, 280, 630, 270, 90)) {
				game.gameState = STATE.Credits;
			}
		}
		///////////////////////// Back Buton for credits
		if (game.gameState == STATE.Credits && (mouseOver(mx, my, 50, 650, 270, 90))) {

			game.gameState = STATE.menu;

		}

		///////////////////////// Back Buton for Profile
		if (game.gameState == STATE.Profile && (mouseOver(mx, my, 280, 630, 270, 90))) {

			game.gameState = STATE.menu;

		}

		///////////////////////// Add Buton for Profile
		if (game.gameState == STATE.Profile && (mouseOver(mx, my, 280, 180, 270, 90))) {

			game.gameState = STATE.Add;

		}
		///////////////////////// Remove Buton for Profile
		if (game.gameState == STATE.Profile && (mouseOver(mx, my, 280, 330, 270, 90))) {

			game.gameState = STATE.Remove;

		}
		///////////////////////// OK Button von Profile Add
		if (game.gameState == STATE.Add && (mouseOver(mx, my, 335, 630, 150, 90))) {

			// profile.addName();
			game.gameState = STATE.Profile;

		}
		///////////////////////// Back Button von Profile Remove
		if (game.gameState == STATE.Remove && (mouseOver(mx, my, 430, 630, 150, 90))) {

			game.gameState = STATE.Profile;
		}
		///////////////////////// OK Button von Profile Remove
		if (game.gameState == STATE.Remove && (mouseOver(mx, my, 220, 630, 150, 90))) {

			// profile.removeName();

		}
		///////////////////////// Button von Highscores
		if (game.gameState == STATE.menu && (mouseOver(mx, my, 280, 480, 270, 90))) {

			game.gameState = STATE.Scores;

		}

		///////////////////////// Button von Highscores ViewTOP
		if (game.gameState == STATE.Scores && (mouseOver(mx, my, 75, 205, 270, 90))) {

			game.gameState = STATE.HighSc;

		}
		///////////////////////// Button von Highscores ViewRecent
		if (game.gameState == STATE.Scores && (mouseOver(mx, my, 450, 205, 270, 90))) {

			game.gameState = STATE.RecSc;

		}
		///////////////////////// Button von Highscores RESET
		if (game.gameState == STATE.Scores && (mouseOver(mx, my, 280, 350, 270, 90))) {
			profil.resetScores();
			// Reset all scores
			try {
				BufferedWriter writerProfiles_buf = Files.newBufferedWriter(directory.resolve(Paths.get("profiles.txt")));
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			try {
				BufferedWriter writerHighscores_buf = Files.newBufferedWriter(directory.resolve(Paths.get("highscores.txt")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
			//Speichert Scores

		}
		///////////////////////// Button von Highscores Back
		if (game.gameState == STATE.Scores && (mouseOver(mx, my, 280, 630, 270, 90))) {

			game.gameState = STATE.menu;

		}

		///////////////////////// Button von Highscores TOPscores Back
		if (game.gameState == STATE.HighSc && (mouseOver(mx, my, 280, 630, 270, 90))) {

			game.gameState = STATE.Scores;

		}

		///////////////////////// Button von Highscores RECENTscores Back
		if (game.gameState == STATE.RecSc && (mouseOver(mx, my, 270, 630, 270, 90))) {

			game.gameState = STATE.Scores;

		}

		////////////////////////// Controls
		if (game.gameState == STATE.menu) {
			if (mouseOver(mx, my, 280, 330, 270, 90)) {
				game.gameState = STATE.Help;

			}
		}
		///////////////////////// Back Buton for help
		if (game.gameState == STATE.Help && (mouseOver(mx, my, 580, 680, 190, 90))) {

			game.gameState = STATE.menu;
		}

		///////////////////////// OK Button von Game Over
		if (game.gameState == STATE.End && (mouseOver(mx, my, 370, 430, 70, 70))) {
			if (!textfieldon) {
				profil.addScore("unknown", Entity.getScore());
			} else {
				try {
					profil.addScore(eingabe.getText(), Entity.getScore());
				} catch (NullPointerException e1) {
					profil.addScore("unknown", Entity.getScore());
				}
			}
			profil.saveToFile();
			window.getFrame().dispose();
			game.stop();
			game.main(null);

			game.gameState = STATE.menu;

		}
		if (game.gameState == STATE.End && (mouseOver(mx, my, 270, 330, 270, 70))) {
			if (textfieldon == false) {
				eingabe = new JTextField();
				eingabe.setBounds(270, 330, 270, 70);
				eingabe.setEditable(true);
				window.getPane().add(eingabe);
				textfieldon = true;
			} else {
				System.out.println("test");
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
