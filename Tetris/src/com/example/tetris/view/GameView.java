package com.example.tetris.view;

import com.example.tetris.object.Block;
import com.example.tetris.object.GameConfig;
import com.example.tetris.object.Grid;
import com.example.tetris.service.GameService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class GameView extends ImageView implements OnGestureListener,
		OnTouchListener {
	private static final int BLOCK_TYPE_NUM = 7;// 7种类型的方块
	GestureDetector mGestureDetector;
	// 游戏逻辑的实现类
	private GameService gameService = null;
//	//当前方块与下一个方块
//	private Block curBlock=null;
//	private Block nextBlock=null;
	// 俄罗斯方块图片
	private Bitmap[] block_color;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, this);
		System.out.printf("GameView..................\n");

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		float distance = e2.getX() - e1.getX();
		if (distance > 0) {
			Toast.makeText(getContext(), "I'm onFling!---right",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getContext(), "I'm onFling!---left",
					Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), "I'm onDown!", Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), "I'm onLongPress!", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		mGestureDetector.onTouchEvent(event);

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if ((null==this.gameService)||(null==this.block_color))
			return;
		// /*

		Grid[][] board = gameService.getGrid();
//		Block curBlock=gameService.getCurBlock();
//		Block nextBlock=gameService.getNextBlock();

		Block curBlock=gameService.getCurBlock();
		/*
		if(null!=curBlock){
			for(int i=0; i<gameService.getGameConfig().getBlockHeight(); i++){
					for(int j=0; j<gameService.getGameConfig().getBlockWidth(); j++){
						if(curBlock.getBlockData()[i * gameService.getGameConfig().getBlockHeight() + j]=='1')
						canvas.drawBitmap(this.block_color[(i * gameService.getGameConfig().getBlockHeight() + j)%gameService.getGameConfig().getBlockTypeNUM()], 
								this.getLeft()+j*36, this.getTop()+i*36, null);
					}
			}
			
		}
		*/
		if(null!=curBlock){
			System.out.printf("\ncurBlock======onDraw (y,x)===(%d,%d)\n",curBlock.getIndexY(),curBlock.getIndexX());
			for(int i=0; i<gameService.getGameConfig().getBlockHeight(); i++){
					for(int j=0; j<gameService.getGameConfig().getBlockWidth(); j++){
						if(curBlock.getBlockData()[i * gameService.getGameConfig().getBlockHeight() + j]=='1')
//						canvas.drawBitmap(this.block_color[(i * gameService.getGameConfig().getBlockHeight() + j)%gameService.getGameConfig().getBlockTypeNUM()], 
//								this.getLeft()+j*36, this.getTop()+i*36, null);
							canvas.drawBitmap(
									this.block_color[curBlock.getBlockType()],
									this.getLeft()
											+ gameService.getGameConfig()
													.getBeginImageX()
											+ (curBlock.getIndexX()+j)
											* gameService.getGameConfig()
													.getImageWidth(),
									this.getTop()
											+ gameService.getGameConfig()
													.getBeginImageY()
											+ (curBlock.getIndexY()+i)
											* gameService.getGameConfig()
													.getImageHeight(), null);
					}
			}
			
		}
		
		if (null != board) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if(board[i][j].getValue()==gameService.getGameConfig().getValueOne()){
						canvas.drawBitmap(
								this.block_color[board[i][j].getBlockType()],
								this.getLeft()
										+ gameService.getGameConfig()
												.getBeginImageX()
										+ board[i][j].getIndexX()
										* gameService.getGameConfig()
												.getImageWidth(),
								this.getTop()
										+ gameService.getGameConfig()
												.getBeginImageY()
										+ board[i][j].getIndexY()
										* gameService.getGameConfig()
												.getImageHeight(), null);
					}
				}

			}
		}

		// */
		System.out.printf("on Draw++++++++++++++++++++++++++++");
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}
	
	public void setGridColor(Bitmap[] bitbmp)
	{
		this.block_color=bitbmp;
	}
}
