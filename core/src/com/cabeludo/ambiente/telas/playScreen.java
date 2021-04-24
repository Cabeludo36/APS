package com.cabeludo.ambiente.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.scenes.Hud;
import com.cabeludo.ambiente.sprites.Caixa;
import com.cabeludo.ambiente.sprites.Leny;
import com.cabeludo.ambiente.tools.B2WorldCreator;
import com.cabeludo.ambiente.tools.WorldContactListener;

public class playScreen implements Screen{

    private MainGame game;

    private TextureAtlas atlas;

    //testura
    Texture texture;
    //camera e viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    //hud
    private Hud hud;

    //mapa
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2D
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    //player
    private Leny player;

    //objetos
    private Caixa caixa;

    public playScreen(MainGame game) {

        atlas = new TextureAtlas("G:/programas/Java/APS/core/assets/lenyAni.atlas");
        
        this.game = game;
        //camera e viewport
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MainGame.V_LARGURA/MainGame.PPM, MainGame.V_ALTURA/MainGame.PPM, gameCam);
        //load da hud
        hud = new Hud(game.batch);
        //load do mapa
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("core/assets/jogo.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/MainGame.PPM);
        //posicao da camera
        gameCam.position.set(gamePort.getWorldHeight() /2 , gamePort.getWorldHeight() /2, 0);

        world = new World(new Vector2(0, -10), true);

        b2dr = new Box2DDebugRenderer();

        player = new Leny(world, this);

        world.setContactListener(new WorldContactListener());

        creator = new B2WorldCreator(this);
    }

    public void update(float dt) {
        pegaInput(dt);

        gameCam.update();
        renderer.setView(gameCam);

        //gameCam.position.x = player.b2body.getPosition().x;
        gameCam.position.y = player.b2body.getPosition().y;

        world.step(1/60f,6,2);

        player.update(dt);

    }
    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void pegaInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float dt) {
        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //Linhas de Debug
        b2dr.render(world, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();

        player.draw(game.batch);

        game.batch.end();

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        
    }

    public World getWorld() {
        return world;
    }
    public TiledMap getMap() {
        return map;
    }
    
}