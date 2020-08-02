package course.battlegame.gameengine.objects.positionobjects.characters;

import course.battlegame.annotations.XmlIgnore;
import course.battlegame.annotations.XmlName;
import course.battlegame.gameengine.actions.Weapon;
import course.battlegame.gameengine.actions.closestrikes.CloseStrike;
import course.battlegame.gameengine.actions.longrangestrikes.LongRangeStrike;
import course.battlegame.gameengine.objects.Position;
import course.battlegame.gameengine.objects.positionobjects.characters.stuff.Stuff;
import course.battlegame.gameengine.objects.positionobjects.characters.stuff.Shield;
import course.battlegame.gameengine.transactions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Archer extends Character {
    @XmlIgnore
    private static Integer MIN_POWER = 15;
    @XmlIgnore
    private static Integer MAX_POWER = 25;

    @XmlName("Weapon")
    private Weapon weapon;

    public Archer(String name, Integer maxHitPoint, Weapon weapon) {
        this(name, maxHitPoint, ThreadLocalRandom.current().nextInt(MIN_POWER, MAX_POWER), null, weapon);
    }

    public Archer(String name, Integer maxHitPoint, Integer power, Weapon weapon) {
        this(name, maxHitPoint, power, null, weapon);
    }

    public Archer(String name, Integer maxHitPoint, Integer power, Stuff stuff, Weapon weapon) {
        super(name, maxHitPoint, power, stuff);
        this.weapon = weapon;
    }

    @Override
    public ArrayList<Transaction> act(Map<Position, Character> battlefield) {
        if(!(weapon instanceof LongRangeStrike) && !(weapon instanceof CloseStrike)) {
            ArrayList<Transaction> reaction = new ArrayList<>();
            reaction.add(new ReactionTransaction(String.format("I cannot use this weapon \"%s\"",
                    weapon.getDescriber().getName()), null, false));
            return reaction;
        }

        Map<Position, Character> defenderAndAttackerPositions = new HashMap<>();

        Position enemyPosition = null;

        while (enemyPosition == null) {
            Integer enemyPositionID = ThreadLocalRandom.current().nextInt(battlefield.keySet().size());

            for (Position position : battlefield.keySet()) {
                if (enemyPositionID.equals(0) && battlefield.get(position) != this) {
                    enemyPosition = position;
                }
                enemyPositionID--;
            }
        }

        defenderAndAttackerPositions.put(enemyPosition, battlefield.get(enemyPosition));

        for (Position position : battlefield.keySet()) {
            if (battlefield.get(position) == this) {
                defenderAndAttackerPositions.put(position, this);
                break;
            }
        }

        return weapon.attack(defenderAndAttackerPositions, this);
    }

    @Override
    public ArrayList<Transaction> react(ActionTransaction transaction) {
        ArrayList<Transaction> reaction = new ArrayList<>();

        if (transaction instanceof ChangeHPTransaction) {
            Character attacker = transaction.getActionCreator();
            Integer hitPoints = ((ChangeHPTransaction) transaction).getHitPoints();

            if (attacker == null) {
                reaction.add(new ReactionTransaction("Attacker Null Pointer Exception.", transaction, false));
                return reaction;
            }

            reaction.add(new ReactionTransaction("SUCCESS", transaction, true));

            if (getStuff() instanceof Shield) {
                Integer correctedHitPoints = ((Shield) getStuff()).protect(hitPoints);
                setHitPoints(getHitPoints() + correctedHitPoints);

                reaction.add(new InfoTransaction(String.format("Archer \"%s\" protects self by shield.", getName())));
                reaction.add(new InfoTransaction(String.format("Archer \"%s\" got damage on %d hp.", getName(), correctedHitPoints)));
                return reaction;
            }

            setHitPoints(getHitPoints() + hitPoints);
            reaction.add(new InfoTransaction(String.format("Archer \"%s\" got damage on %d hp.", getName(), hitPoints)));
            return reaction;
        }

        reaction.add(new ReactionTransaction("Bad Transaction.", transaction, false));
        return reaction;
    }
}