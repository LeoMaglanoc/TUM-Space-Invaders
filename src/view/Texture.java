package view;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet ps, gs, ps2, ts;

	private BufferedImage player_sheet = null;
	private BufferedImage player_sheetP2 = null;
	private BufferedImage gegner_sheet = null;
	private BufferedImage tastatur_sheet = null;

	public BufferedImage[] player = new BufferedImage[14];
	public BufferedImage[] playerP2 = new BufferedImage[14];
	public BufferedImage[] tastatur = new BufferedImage[1];
	public BufferedImage[] Bullet = new BufferedImage[6];
	public BufferedImage[] gegner = new BufferedImage[36];

	public Texture() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			player_sheet = loader.loadImage("/player_sheet.png"); // spieler bild
			player_sheetP2 = loader.loadImage("/player_sheetP2.png"); // spieler2 bild
			gegner_sheet = loader.loadImage("/gegner_sheet.png"); // textur gegner
			tastatur_sheet = loader.loadImage("/Tastatur.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ps = new SpriteSheet(player_sheet);
		ps2 = new SpriteSheet(player_sheetP2);
		gs = new SpriteSheet(gegner_sheet);
		ts = new SpriteSheet(tastatur_sheet);

		getTextures();
	}

	private void getTextures() {
		player[0] = ps.grabImage(1, 1, 32, 32); // Spieler Idle
		player[1] = ps.grabImage(2, 1, 32, 32); // Spieler Idle
		player[2] = ps.grabImage(3, 1, 32, 32); // Spieler Idle
		player[3] = ps.grabImage(4, 1, 32, 32); // Spieler Idle

		player[4] = ps.grabImage(1, 2, 32, 32); // Spieler Links
		player[5] = ps.grabImage(2, 2, 32, 32); // Spieler Links
		player[6] = ps.grabImage(3, 2, 32, 32); // Spieler Links
		player[7] = ps.grabImage(4, 2, 32, 32); // Spieler Links

		player[8] = ps.grabImage(1, 3, 32, 32); // Spieler rechts
		player[9] = ps.grabImage(2, 3, 32, 32); // Spieler rechts
		player[10] = ps.grabImage(3, 3, 32, 32); // Spieler rechts
		player[11] = ps.grabImage(4, 3, 32, 32); // Spieler rechts

		playerP2[0] = ps2.grabImage(1, 1, 32, 32); // Spieler2 Idle
		playerP2[1] = ps2.grabImage(2, 1, 32, 32); // Spieler2 Idle
		playerP2[2] = ps2.grabImage(3, 1, 32, 32); // Spieler2 Idle
		playerP2[3] = ps2.grabImage(4, 1, 32, 32); // Spieler2 Idle

		playerP2[4] = ps2.grabImage(1, 2, 32, 32); // Spieler2 Links
		playerP2[5] = ps2.grabImage(2, 2, 32, 32); // Spieler2 Links
		playerP2[6] = ps2.grabImage(3, 2, 32, 32); // Spieler2 Links
		playerP2[7] = ps2.grabImage(4, 2, 32, 32); // Spieler2 Links

		playerP2[8] = ps2.grabImage(1, 3, 32, 32); // Spieler2 rechts
		playerP2[9] = ps2.grabImage(2, 3, 32, 32); // Spieler2 rechts
		playerP2[10] = ps2.grabImage(3, 3, 32, 32); // Spieler2 rechts
		playerP2[11] = ps2.grabImage(4, 3, 32, 32); // Spieler2 rechts

		Bullet[0] = gs.grabImage(5, 1, 32, 32);
		Bullet[1] = gs.grabImage(6, 1, 32, 32);

		gegner[0] = gs.grabImage(1, 9, 32, 32);
		gegner[1] = gs.grabImage(1, 8, 32, 32);
		gegner[2] = gs.grabImage(1, 7, 32, 32); // Spieler Idle
		gegner[3] = gs.grabImage(1, 6, 32, 32); // Spieler animantion
		gegner[4] = gs.grabImage(1, 5, 32, 32); // Spieler animantion
		gegner[5] = gs.grabImage(1, 4, 32, 32); // Spieler animantion
		gegner[6] = gs.grabImage(1, 3, 32, 32); // Spieler animantion Alle f�r Gegner
		gegner[7] = gs.grabImage(1, 2, 32, 32); // Spieler animantion
		gegner[8] = gs.grabImage(1, 1, 32, 32); // Spieler animantion

		gegner[9] = gs.grabImage(2, 9, 32, 32);
		gegner[10] = gs.grabImage(2, 8, 32, 32);
		gegner[11] = gs.grabImage(2, 7, 32, 32); // Spieler Idle
		gegner[12] = gs.grabImage(2, 6, 32, 32); // Spieler animantion
		gegner[13] = gs.grabImage(2, 5, 32, 32); // Spieler animantion
		gegner[14] = gs.grabImage(2, 4, 32, 32); // Spieler animantion
		gegner[15] = gs.grabImage(2, 3, 32, 32); // Spieler animantion Alle f�r Gegner
		gegner[16] = gs.grabImage(2, 2, 32, 32); // Spieler animantion
		gegner[17] = gs.grabImage(2, 1, 32, 32); // Spieler animantion

		gegner[18] = gs.grabImage(3, 1, 32, 32);
		gegner[19] = gs.grabImage(3, 2, 32, 32);
		gegner[20] = gs.grabImage(3, 3, 32, 32); // Spieler Idle
		gegner[21] = gs.grabImage(3, 4, 32, 32); // Spieler animantion
		gegner[22] = gs.grabImage(3, 5, 32, 32); // Spieler animantion
		gegner[23] = gs.grabImage(3, 6, 32, 32); // Spieler animantion
		gegner[24] = gs.grabImage(3, 7, 32, 32); // Spieler animantion Alle f�r Gegner
		gegner[25] = gs.grabImage(3, 8, 32, 32); // Spieler animantion
		gegner[26] = gs.grabImage(3, 9, 32, 32); // Spieler animantion

		gegner[27] = gs.grabImage(4, 1, 32, 32);
		gegner[28] = gs.grabImage(4, 2, 32, 32);
		gegner[29] = gs.grabImage(4, 3, 32, 32); // Spieler Idle
		gegner[30] = gs.grabImage(4, 4, 32, 32); // Spieler animantion
		gegner[31] = gs.grabImage(4, 5, 32, 32); // Spieler animantion
		gegner[32] = gs.grabImage(4, 6, 32, 32); // Spieler animantion
		gegner[33] = gs.grabImage(4, 7, 32, 32); // Spieler animantion Alle f�r Gegner
		gegner[34] = gs.grabImage(4, 8, 32, 32); // Spieler animantion
		gegner[35] = gs.grabImage(4, 9, 32, 32); // Spieler animantion

		tastatur[0] = ts.grabImage(1, 1, 780, 270); // tastatur zeichen
	}
}
