package com.aor.tetris.controller.game;

import com.aor.tetris.Game;
import com.aor.tetris.gui.GUI;
import com.aor.tetris.model.Position;
import com.aor.tetris.model.game.arena.Arena;
import com.aor.tetris.model.game.elements.Monster;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastMovement;

    public MonsterController(Arena arena) {
        super(arena);

        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 500) {
            for (Monster monster : getModel().getMonsters())
                moveMonster(monster, monster.getPosition().getRandomNeighbour());
            this.lastMovement = time;
        }
    }

    private void moveMonster(Monster monster, Position position) {
        if (getModel().isEmpty(position)) {
            monster.setPosition(position);
            if (getModel().getHero().getPosition().equals(position))
                getModel().getHero().decreaseEnergy();
        }
    }
}
