package com.example.tetris.board.implement;

import com.example.tetris.board.GameService;
import com.example.tetris.object.GameConfig;
import com.example.tetris.view.Block;

public class GameServiceImplement implements GameService {

	/* ����һ��Block���鱣����Ϸ����ķ�����Ϣ */
	private Block[][] board = null;

	/* ��Ϸ���ö��� */
	private GameConfig gameConfig = null;

	public GameServiceImplement(GameConfig gameConfig) {
		// TODO Auto-generated constructor stub
		this.gameConfig = gameConfig;
		this.board = init_board(this.gameConfig);
	};

	/*
	 * private Block[][] init_board(GameConfig gameConfig){ Block[][] board=new
	 * Block[gameConfig.getYSize()][gameConfig.getXSize()]; for(int y=0;
	 * y<gameConfig.getYSize(); y++){ for(int x=0; x<gameConfig.getXSize();
	 * x++){ Block block=new Block(y,x); board[y][x]=block; } } return board; }
	 */
	private Block[][] init_board(GameConfig gameConfig) {
		Block[][] board = new Block[gameConfig.getYSize()][gameConfig
				.getXSize()];
		for (int y = 0; y < gameConfig.getYSize(); y++) {
			for (int x = 0; x < gameConfig.getXSize(); x++) {
				board[y][x].setIndexYX(y, x);
			}
		}
		return board;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public Block move_left_block() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block move_right_block() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block fast_down_block() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block rotate_block() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block[][] getBlocks() {
		// TODO Auto-generated method stub
		return this.board;
	}

	public GameConfig getGameConfig() {
		return this.gameConfig;
	}
}