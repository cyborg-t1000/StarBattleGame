package com.cyborg;

import com.badlogic.gdx.Game;

import com.cyborg.screen.MenuScreen;

public class StarBattleGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
