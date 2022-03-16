package com.koukoutou.textgame;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assassin", "Warlock", "Archer", "Troll", "Orc",
                "Hellhound", "Sprider", "Wolf" };
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 25;
        int enemyExp = 15;

        int health = 100;
        int playerAttackDamage = 50;
        int numHealthPotions = 3;
        int healAmount = 30;
        int healthPotionDropChance = 50;
        int playerExp = 0;
        int playerLevel = 1;
        int levelCap = 25;

        boolean running = true;

        System.out.println("Welcome to the Dungeon, brave warrior!");

        GAME: while (running) {
            System.out.println("------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");

            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP:" + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    health -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage ");
                    System.out.println("\t> You receive " + damageTaken + " in retaliation ");

                    if (health < 1) {
                        System.out.println("\t> You have been defeated.");
                        break;
                    }

                } else if (input.equals("2")) {
                    if (numHealthPotions > 0) {
                        health += healAmount;
                        numHealthPotions--;
                        System.out.println("\t> You drink a health potion, healing yourself for " + healAmount + "."
                                + "\n\t> You now have " + health + " HP." + "\n\t> You have " + numHealthPotions
                                + " health potions left.\n");
                    } else {
                        System.out.println(
                                "\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
                    }

                } else if (input.equals("3")) {
                    System.out.println("\tYou run away from the " + enemy + "!");
                    continue GAME;
                } else {
                    System.out.println("\tInvalid command");
                }

                if (health < 1) {
                    System.out.println("\tYou have been defeated.");
                    break;
                }
            }

            if (health < 1) {
                break;
            }

            int expGained = rand.nextInt(enemyExp);
            playerExp += expGained;
            if (playerExp > levelCap) {
                playerLevel++;
                levelCap += 25;
            }

            System.out.println("------------------------------------");
            System.out.println(" # You gained " + expGained + " exp #");
            System.out.println(" # You are now level " + playerLevel + "! #");
            System.out.println(" # You have " + health + " HP left. #");
            if (rand.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion!  #");
                System.out.println(" # You have " + numHealthPotions + " health potion(s)  #");
            }

            System.out.println("------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            String input1 = in.nextLine();
            System.out.println(input1);
            while (!input1.equals("1") && !input1.equals("2")) {
                System.out.println("Invalid command!");
                input1 = in.nextLine();
            }

            if (input1.equals("1")) {
                System.out.println("You continue on your adventure!");
            } else if (input1.equals("2")) {
                System.out.println("You exit the dungeon! Congratulations!");
                break;
            }
        }
        System.out.println("###################");
        System.out.println("Thanks for playing!");
        System.out.println("###################");
        in.close();
    }
}
