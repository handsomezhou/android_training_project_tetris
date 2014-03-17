package com.example.tetris.service.implement;

import android.widget.Toast;
import com.example.tetris.object.Block;
import com.example.tetris.object.GameConfig;
import com.example.tetris.object.GameConfig.BlockType;
import com.example.tetris.object.GameConfig.GameStatus;
import com.example.tetris.object.Grid;
import com.example.tetris.service.GameService;

public class GameServiceImplement implements GameService, Cloneable {
	private enum Direction{
		DIR_LEFT,
		DIR_RIGHT,
		DIR_DOWN,
	}
	/* ����һ��Grid���鱣����Ϸ����ķ�����Ϣ */
	private Grid[][] board = null;

	/* ��Ϸ���ö��� */
	private GameConfig gameConfig = null;
	/* ��ǰ��ʾ�ķ�������һ������ */
	private Block curBlock = null;
	private Block nextBlock = null;

	public GameServiceImplement(GameConfig gameConfig) {
		// TODO Auto-generated constructor stub
		this.gameConfig = gameConfig;
		this.board = init_board(this.gameConfig);
		this.curBlock = new Block(0, 0);
		this.nextBlock = new Block(0, 0);
		produceCurBlock(0);
		produceNextBlock(getCurBlock());
		produceCurBlock(1);

	};

	private Grid[][] init_board(GameConfig gameConfig) {
		Grid[][] board = new Grid[gameConfig.getYSize()][gameConfig.getXSize()];
		for (int y = 0; y < gameConfig.getYSize(); y++) {
			for (int x = 0; x < gameConfig.getXSize(); x++) {
				Grid grid = new Grid(y, x);
				grid.setBlockType(gameConfig.getBlockInitType());
				grid.setValue(gameConfig.getValueZero());
				board[y][x] = grid;
				// System.out.printf("(%d,%d)-[%d]-[%c]\n",board[y][x].getIndexY(),board[y][x].getIndexX(),board[y][x].getBlockType(),board[y][x].getValue());
			}
			// System.out.printf("\n");
		}
		return board;
	}

	public Block produceCurBlock(int i) {
		curBlock.setIndexYX(0, 0);
		curBlock.setBlockType(BlockType.BLOCK_I);
		curBlock.setBlockNumber(0);
		curBlock.setBlockData(gameConfig.getBlocks(i));
		// System.out.printf("produceCurBlock==[%s]",curBlock);
		return this.curBlock;
	}

	public Block produceNextBlock(Block block) {
		//this.nextBlock = block;

		try {
			this.nextBlock = (Block) block.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			System.out
					.printf(".......................................��ȡ��һ����������ʧ�ܣ�\n");
			e.printStackTrace();
		}

		return this.nextBlock;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		gameConfig.setGameStatus(GameStatus.STATUS_PLAYING);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm pauseContinueButton!",
				Toast.LENGTH_SHORT).show();
		gameConfig.setGameStatus(GameStatus.STATUS_PAUSE);
	}

	@Override
	public void resume_playing() {
		// TODO Auto-generated method stub
		gameConfig.setGameStatus(GameStatus.STATUS_PLAYING);
	}

	@Override
	public void set_level(int level) {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm levelIncreasesButton!",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public Block move_left_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm leftButton!",
				Toast.LENGTH_SHORT).show();
		
		return moveBlock(Direction.DIR_LEFT);
	}

	@Override
	public Block move_right_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm rightButton...!",
				Toast.LENGTH_SHORT).show();
		System.out.printf("I'm move_right_block()\n");
		return moveBlock(Direction.DIR_RIGHT);
	}

	@Override
	public Block move_down_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm move down!",
				Toast.LENGTH_SHORT).show();
		return moveBlock(Direction.DIR_DOWN);
	}

	@Override
	public Block fast_down_block() {
		// TODO Auto-generated method stub
		Toast.makeText(gameConfig.getContext(), "I'm downButton!",
				Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public Block rotate_block() {
		// TODO Auto-generated method stub

		Toast.makeText(gameConfig.getContext(), "I'm upButton!",
				Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public Grid[][] getGrid() {
		// TODO Auto-generated method stub
		return this.board;
	}

	@Override
	public GameConfig getGameConfig() {
		return this.gameConfig;
	}

	@Override
	public Block getCurBlock() {
		// TODO Auto-generated method stub
		return this.curBlock;
	}

	@Override
	public Block getNextBlock() {
		// TODO Auto-generated method stub
		return this.nextBlock;
	}

	private Block moveBlock(Direction dir){
		switch(dir){
		case DIR_LEFT:
			if(true==canMoveBlock(curBlock.getIndexY(),curBlock.getIndexX()-1)){
				//curBlock.setIndexYX(curBlock.getIndexY(), curBlock.getIndexX()-1);
				curBlock.setIndexX(curBlock.getIndexX()-1);
				return curBlock;
			}
			break;
		case DIR_RIGHT:
			if(true==canMoveBlock(curBlock.getIndexY(), curBlock.getIndexX()+1)){
				//curBlock.setIndexYX(curBlock.getIndexY(),curBlock.getIndexX()+1);
				curBlock.setIndexX(curBlock.getIndexX()+1);
				System.out.printf("I'm DIR_RIGHT()  x=%d\n",curBlock.getIndexX());
				return curBlock;
			}
			break;
		case DIR_DOWN:
			if(true==canMoveBlock(curBlock.getIndexY()+1, curBlock.getIndexX())){
				curBlock.setIndexY(curBlock.getIndexY()+1);
				System.out.printf("DIR_DOWN====y==%d\n",curBlock.getIndexY());
				return curBlock;
			}
			break;
		default:
			break;
			
		}
		return null;
	}
	
	private boolean canMoveBlock(int y, int x){
		Block tmp=new Block(0, 0);
		
		try {
			tmp=(Block) curBlock.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tmp.setIndexYX(y, x);
		
		switch(curBlock.getBlockType()){
		//case GameConfig.BLOCK_I:
			//break;
		}
		return true;
	}
}