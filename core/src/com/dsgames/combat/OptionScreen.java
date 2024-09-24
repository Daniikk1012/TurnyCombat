//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

class OptionScreen extends Stage implements Screen {
    private MyGdxGame game;

    OptionScreen(final MyGdxGame game) {
        super(new StretchViewport(1600.0f, 900.0f), game.batch);
        final TextureRegion[] array = {new TextureRegion(game.optionButtons, 0, 0, 128, 64),
                new TextureRegion(game.optionButtons, 0, 64, 128, 64),
                new TextureRegion(game.optionButtons, 0, 128, 128, 64),
                new TextureRegion(game.optionButtons, 0, 192, 128, 64),
                new TextureRegion(game.optionButtons, 128, 0, 128, 64),
                new TextureRegion(game.optionButtons, 128, 64, 128, 64),
                new TextureRegion(game.optionButtons, 128, 128, 128, 64),
                new TextureRegion(game.optionButtons, 128, 192, 128, 64)};
        this.game = game;
        this.addActor(new ButtonActive(0.0f, 750.0f, 300.0f, 150.0f,
                new TextureRegion[] {new TextureRegion(game.mainBtn, 384, 0, 128, 64),
                        new TextureRegion(game.mainBtn, 384, 64, 128, 64)},
                0, 1) {
            final OptionScreen this_0 = OptionScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.setScreen(new MainMenuScreen(this.val_game));
                this.this_0.dispose();
            }
        });
        int n;
        if (game.pref.getBoolean("sound")) {
            n = 0;
        } else {
            n = 2;
        }
        int n2;
        if (game.pref.getBoolean("sound")) {
            n2 = 1;
        } else {
            n2 = 3;
        }
        this.addActor(new ButtonActive(500.0f, 100.0f, 600.0f, 300.0f, array, n, n2) {
            final MyGdxGame val_game = game;

            @Override
            void click() {
                int cur = 0;
                this.val_game.pref.putBoolean("sound", !this.val_game.pref.getBoolean("sound"));
                this.val_game.pref.flush();
                if (!this.val_game.pref.getBoolean("sound")) {
                    cur = 2;
                }
                this.cur = cur;
                this.curDown = this.cur + 1;
            }
        });
        int n3;
        if (game.pref.getBoolean("music")) {
            n3 = 4;
        } else {
            n3 = 6;
        }
        int n4;
        if (game.pref.getBoolean("music")) {
            n4 = 5;
        } else {
            n4 = 7;
        }
        this.addActor(new ButtonActive(500.0f, 500.0f, 600.0f, 300.0f, array, n3, n4) {
            final MyGdxGame val_game = game;

            @Override
            void click() {
                this.val_game.pref.putBoolean("music", !this.val_game.pref.getBoolean("music"));
                this.val_game.pref.flush();
                int cur;
                if (this.val_game.pref.getBoolean("music")) {
                    cur = 4;
                } else {
                    cur = 6;
                }
                this.cur = cur;
                this.curDown = this.cur + 1;
                if (this.val_game.pref.getBoolean("music")) {
                    this.val_game.backM.play();
                } else {
                    this.val_game.backM.stop();
                }
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
            this.game.setScreen(new MainMenuScreen(this.game));
            this.dispose();
        }
        return false;
    }

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
