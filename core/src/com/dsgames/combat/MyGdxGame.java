//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
    Sound atk;
    Music backM;
    SpriteBatch batch;
    Texture btns;
    private OrthographicCamera camera;
    Texture choose;
    BitmapFont font;
    BitmapFont fontOver;
    Sound hp;
    Texture humans;
    Texture leftRight;
    Texture mainBtn;
    Texture optionButtons;
    Preferences pref;
    ShapeRenderer renderer;
    Texture title;
    Texture types;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        (this.camera = new OrthographicCamera()).setToOrtho(false, 1600.0f, 900.0f);
        Gdx.input.setCatchBackKey(true);
        (this.pref = Gdx.app.getPreferences("pref")).putBoolean("sound",
                this.pref.getBoolean("sound", true));
        this.pref.putBoolean("music", this.pref.getBoolean("music", true));
        this.pref.flush();
        this.btns = new Texture("hi.png");
        this.title = new Texture("turnyCombat.png");
        this.leftRight = new Texture("leftright.png");
        this.types = new Texture("types.png");
        this.humans = new Texture("humans.png");
        this.mainBtn = new Texture("menuBtn.png");
        this.optionButtons = new Texture("optionButtons.png");
        this.choose = new Texture("choose.png");
        final FreeTypeFontGenerator freeTypeFontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal("cooper.ttf"));
        final FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.borderWidth = 2.0f;
        freeTypeFontParameter.borderColor = Color.BLACK;
        freeTypeFontParameter.size = 48;
        this.hp = Gdx.audio.newSound(Gdx.files.internal("hpsound.mp3"));
        this.atk = Gdx.audio.newSound(Gdx.files.internal("attacksound.mp3"));
        this.font = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        freeTypeFontParameter.color = Color.BLUE;
        freeTypeFontParameter.size = 72;
        this.fontOver = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        freeTypeFontGenerator.dispose();
        (this.renderer = new ShapeRenderer()).setProjectionMatrix(this.camera.combined);
        (this.backM = Gdx.audio.newMusic(Gdx.files.internal("backM.mp3"))).setLooping(true);
        if (this.pref.getBoolean("music")) {
            this.backM.play();
        }
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        this.pref.flush();
        this.batch.dispose();
        this.btns.dispose();
        this.choose.dispose();
        this.optionButtons.dispose();
        this.mainBtn.dispose();
        this.title.dispose();
        this.hp.dispose();
        this.backM.dispose();
        this.atk.dispose();
        this.humans.dispose();
        this.renderer.dispose();
        this.font.dispose();
        this.fontOver.dispose();
        super.dispose();
    }

    @Override
    public void render() {
        this.camera.update();
        super.render();
    }
}
