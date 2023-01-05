package sdf;

public class Zelda {

    public static void main(String[] args) {
        Monster monster = new Monster();
        RockMonster rockMonster = new RockMonster();
        Tree tree = new Tree();
        Link link = new Link();

        link.attack(monster);
        link.attack(rockMonster);
        link.attack(tree);

        if (monster.isDead())
            System.out.println("You win");
    }
    
}
