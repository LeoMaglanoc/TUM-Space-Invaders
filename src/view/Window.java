package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import controller.Game;

public class Window {
	JFrame frame;
	JLayeredPane pane;

	public Window(int w, int h, String title, Game game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));

		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		pane = frame.getLayeredPane();
		game.start();
	}

	public JFrame getFrame() {
		return frame;
	}

	public JLayeredPane getPane() {
		return pane;
	}
}
