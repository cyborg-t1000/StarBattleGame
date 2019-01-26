package com.cyborg.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cyborg.math.Rect;

public class Exit extends ScaledTouchUpButton {

    public Exit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void action() {
        System.exit(0);
    }
}
