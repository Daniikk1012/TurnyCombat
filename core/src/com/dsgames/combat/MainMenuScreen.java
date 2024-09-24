//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

class MainMenuScreen extends Stage implements Screen {
    private MyGdxGame game;

    MainMenuScreen(final MyGdxGame game) {
        super(new StretchViewport(1600.0f, 900.0f), game.batch);
        this.game = game;
        final TextureRegion[] array = {new TextureRegion(game.mainBtn, 0, 0, 128, 64),
                new TextureRegion(game.mainBtn, 0, 64, 128, 64),
                new TextureRegion(game.mainBtn, 128, 0, 128, 64),
                new TextureRegion(game.mainBtn, 128, 64, 128, 64),
                new TextureRegion(game.mainBtn, 256, 0, 128, 64),
                new TextureRegion(game.mainBtn, 256, 64, 128, 64)};
        this.addActor(new ButtonActive(600.0f, 505.0f, 400.0f, 200.0f, array, 0, 1) {
            final MainMenuScreen this_0 = MainMenuScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new ChooseScreen(this.val_game));
                this.this_0.dispose();
            }
        });
        this.addActor(new ButtonActive(600.0f, 35.0f, 400.0f, 200.0f, array, 2, 3) {
            @Override
            void click() {
                Gdx.app.exit();
            }
        });
        this.addActor(new ButtonActive(600.0f, 270.0f, 400.0f, 200.0f, array, 4, 5) {
            final MainMenuScreen this_0 = MainMenuScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new OptionScreen(this.val_game));
                this.this_0.dispose();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void hide() {}

    @Override
    public boolean keyDown(final int n) {
        if (n == 4 || n == 131) {
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public void pause() {}

    @Override
    public void render(final float n) {
        Gdx.gl.glClearColor(0.0f, 86.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.game.batch.begin();
        this.game.batch.draw(this.game.title, 480.0f, 740.0f, 640.0f, 160.0f);
        this.game.batch.end();
        super.act();
        super.draw();
    }

    @Override
    public void resize(final int n, final int n2) {}

    @Override
    public void resume() {}

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
