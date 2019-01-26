package com.cyborg.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PlayButton extends ScaledTouchUpButton {

    public PlayButton(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay"));
        pos.set(0.2f,0f);
        setHeightProportion(0.1f);
    }

    @Override
    public void action() {
        System.exit(0);
    }


}
