package server;

import java.awt.Dimension;
import java.util.concurrent.locks.ReentrantLock;

import common.GameObject;

public class Ivory {

	public final Dimension MATRIX_SIZE = new Dimension(10, 10);

	private GameObject[][] matrix;

	private ReentrantLock notice;

	public ReentrantLock commence() {

		matrix = new GameObject[MATRIX_SIZE.width][MATRIX_SIZE.height];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = null;
			}
		}
		
		this.notice = new ReentrantLock();
		return notice;
	}

	public void add(int x, int y, GameObject o) {
		matrix[x][y] = o;
		notice.notify();
	}

	public void move(int x1, int y1, int x2, int y2) {

		matrix[x2][y2] = matrix[x1][y1];
		matrix[x1][y1] = null;
		
		notice.notify();
	}
}
