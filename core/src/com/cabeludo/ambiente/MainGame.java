package com.cabeludo.ambiente;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cabeludo.ambiente.telas.playScreen;

public class MainGame extends Game {
	public static final int V_LARGURA = 610;
	public static final int V_ALTURA = 400;
	public static final float PPM = 100;

	public static final short CHAO_BIT = 1;
	public static final short LENY_BIT = 2;
	public static final short BLOCO_BIT = 4;
	public static final short MOEDA_BIT = 8;
	public static final short DESTROI_BIT = 16;
	public static final short OBJETO_BIT = 32;
	public static final short ITEM_BIT = 64;
	public static final short PLACA_BIT = 128;
	
	public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load("core/assets/audio/all_pokemon_game_themes_towns_cities.mp3", Music.class);
		manager.load("core/assets/audio/coin.wav", Sound.class);
		manager.finishLoading();

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
