package com.cyborg.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.cyborg.base.BaseScreen;
import com.cyborg.math.Rect;
import com.cyborg.sprite.Background;
import com.cyborg.sprite.Star;
import com.cyborg.sprite.menu.ExitButton;
import com.cyborg.sprite.menu.PlayButton;

public class MenuScreen extends BaseScreen {

    private Game game;

    private TextureAtlas atlas;
    private Texture bg;
    private Background background;
    private Star star[];
    private ExitButton exitButton;
    private PlayButton playButton;

    private Music music;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/title.mp3"));
        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[256];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        exitButton = new ExitButton(atlas);
        playButton = new PlayButton(atlas, game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        exitButton.draw(batch);
        playButton.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        exitButton.resize(worldBounds);
        playButton.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        playButton.touchDown(touch, pointer);
        exitButton.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        playButton.touchUp(touch, pointer);
        exitButton.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }
}
