package com.cabeludo.ambiente.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.scenes.Hud;

public class playScreen implements Screen{

    private MainGame game;
    Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    Hud hud;

    public playScreen(MainGame game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MainGame.V_LARGURA, MainGame.V_ALTURA, gameCam);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
}
