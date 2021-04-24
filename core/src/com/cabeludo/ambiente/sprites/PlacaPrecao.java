package com.cabeludo.ambiente.sprites;

import com.badlogic.gdx.maps.MapObject;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.scenes.Hud;
import com.cabeludo.ambiente.telas.playScreen;

public class PlacaPrecao extends InteractiveTileObject {

    public PlacaPrecao(playScreen screen, MapObject object, int i) {
        super(screen, object, i);
        fixture.setUserData(this);
        setCategoryFilter(MainGame.PLACA_BIT);
    }

    @Override
    public void onPeHit(Leny leny) {
        setCategoryFilter(MainGame.DESTROI_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
    }
}