package com.cabeludo.ambiente.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cabeludo.ambiente.MainGame;

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;

    
    Label countdownLabel;
    static Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label lenyLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 0;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(MainGame.V_LARGURA, MainGame.V_ALTURA, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lenyLabel = new Label("LENY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(lenyLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    public static void addScore(int i) {
        score += i;
        scoreLabel.setText(String.format("%06d", score));
    }
    public void addTime(int i) {
        worldTimer += i;
        countdownLabel.setText(String.format("%06d", worldTimer));
    }

    @Override
    public void dispose() {
        stage.dispose();
        
    }
}
