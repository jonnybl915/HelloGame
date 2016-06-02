package com.theironyard;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    float x, y, xv, yv; //xv = x velocity...

    static final float MAX_VELOCITY = 100;
    static final float DECELERATOR = 0.99f;
	
	@Override
	public void create () {
		batch = new SpriteBatch(); //responsible for drawing on the screen
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
        move();

		Gdx.gl.glClearColor(1, 0, 0, 1);                //this is wiping the frame clean
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);       //"     "
		batch.begin();
	//	batch.draw(img, 0, 0); //all draw calls are here, between begin and end . image does not move
        batch.draw(img, x, y); //now we're not hard coding but instead using our float values
        batch.end();


	}

    public void move(){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            yv = MAX_VELOCITY;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            yv = -MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            xv = MAX_VELOCITY;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            xv = -MAX_VELOCITY;
        }

        float delta = Gdx.graphics.getDeltaTime(); //amount of seconds which have passed since the last frame
        y+= yv * delta;
        x+= xv * delta;

        yv = decelerate(yv);
        xv = decelerate(xv);
    }
    public float decelerate(float velocity){
        velocity *= DECELERATOR;
        if (Math.abs(velocity) <1) {
            velocity = 0;
        }
        return velocity;
    }
}
