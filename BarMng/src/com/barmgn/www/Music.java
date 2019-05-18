package com.barmgn.www;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	// ���� ����
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(name);

			System.out.println(file);
			
			fis = new FileInputStream(file);	// ������ ������ ���Ͻ�Ʈ������ ��´�.
			bis = new BufferedInputStream(fis); // ����(�޸�)���ٰ� �־���
			player = new Player(bis);			// ������ ��ü���ٰ� �־���
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// ���� �ð� üũ : ��Ʈ �������� Ȯ���ϱ� ���ؼ�.
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	// ���� �� ��� ���ߴ� �κ�
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {	// ����ó��
			do {
				player.play();
				fis = new FileInputStream(file);	// ������ ������ ���Ͻ�Ʈ������ ��´�.
				bis = new BufferedInputStream(fis); // ����(�޸�)���ٰ� �־���
				player = new Player(bis);			// ������ ��ü���ٰ� �־���
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}