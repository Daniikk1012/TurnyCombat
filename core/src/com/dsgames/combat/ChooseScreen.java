//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

class ChooseScreen extends Stage implements Screen {
    ChooseScreen(final MyGdxGame game) {
        super(new StretchViewport(1600.0f, 900.0f), game.batch);
        final TextureRegion[] array = {new TextureRegion(game.choose, 0, 0, 128, 64),
                new TextureRegion(game.choose, 0, 64, 128, 64),
                new TextureRegion(game.choose, 128, 0, 128, 64),
                new TextureRegion(game.choose, 128, 64, 128, 64)};
        this.addActor(new ButtonActive(0.0f, 750.0f, 300.0f, 150.0f,
                new TextureRegion[] {new TextureRegion(game.mainBtn, 384, 0, 128, 64),
                        new TextureRegion(game.mainBtn, 384, 64, 128, 64)},
                0, 1) {
            final ChooseScreen this_0 = ChooseScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new MainMenuScreen(this.val_game));
                this.this_0.dispose();
            }
        });
        this.addActor(new ButtonActive(500.0f, 100.0f, 600.0f, 300.0f, array, 0, 1) {
            final ChooseScreen this_0 = ChooseScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new GameScreen(this.val_game));
                this.this_0.dispose();
            }
        });
        this.addActor(new ButtonActive(500.0f, 500.0f, 600.0f, 300.0f, array, 2, 3) {
            final ChooseScreen this_0 = ChooseScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new BlitzGameScreen(this.val_game));
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
    public void pause() {}

    @Override
    public void render(final float n) {
        Gdx.gl.glClearColor(0.0f, 0.86f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
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
