//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

class GameOverScreen implements Screen, InputProcessor {
    private MyGdxGame game;
    private boolean right;

    GameOverScreen(final MyGdxGame game, final boolean right) {
        this.game = game;
        this.right = right;
    }

    @Override
    public void dispose() {}

    @Override
    public void hide() {}

    @Override
    public boolean keyDown(final int n) {
        return false;
    }

    @Override
    public boolean keyTyped(final char c) {
        return false;
    }

    @Override
    public boolean keyUp(final int n) {
        return false;
    }

    @Override
    public boolean mouseMoved(final int n, final int n2) {
        return false;
    }

    @Override
    public void pause() {}

    @Override
    public void render(final float n) {
        Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.game.batch.begin();
        final BitmapFont fontOver = this.game.fontOver;
        final SpriteBatch batch = this.game.batch;
        String s;
        if (this.right) {
            s = "Player at the left win!";
        } else {
            s = "Player at the right win!";
        }
        fontOver.draw(batch, s, 250.0f, 485.0f);
        this.game.batch.end();
    }

    @Override
    public void resize(final int n, final int n2) {}

    @Override
    public void resume() {}

    @Override
    public boolean scrolled(final float amountX, final float amountY) {
        return false;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDown(final int n, final int n2, final int n3, final int n4) {
        this.game.setScreen(new MainMenuScreen(this.game));
        this.dispose();
        return false;
    }

    @Override
    public boolean touchDragged(final int n, final int n2, final int n3) {
        return false;
    }

    @Override
    public boolean touchUp(final int n, final int n2, final int n3, final int n4) {
        return false;
    }

    @Override
    public boolean touchCancelled(final int screenX, final int screenY, final int pointer,
            final int button) {
        return false;
    }
}
