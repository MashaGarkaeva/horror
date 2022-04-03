package com.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){//помещает элемент в вершину стека
        states.push(state);
    }

    public void pop(){//извлекает верхних элемент, удаляя его из стека
        states.pop().dispose();
    }

    public void set(State state){//ощищает верхних экран
        states.pop().dispose();
        states.push(state);//помещает следующий экран в вершину стека
    }

    public void update(float dt){
        states.peek().update(dt);//возвращает верхний элемент, не удаляя из стека
    }

    public void render(SpriteBatch sd){
        states.peek().render(sd);
    }
}
