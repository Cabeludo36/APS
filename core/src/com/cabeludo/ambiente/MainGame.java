package com.cabeludo.ambiente;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cabeludo.ambiente.telas.playScreen;

public class MainGame extends Game {
	public static final int V_LARGURA = 400;
	public static final int V_ALTURA = 208;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new playScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
