import java.util.*;

//All of this has been painstakingly translated from Rust

class Game
{
    ArrayList<Integer> deck;

    Game() 
    {
        deck = new ArrayList<>();
        
        for (int i = 2; i < 15; ++i)
        {
            this.deck.add(i);
            this.deck.add(i);
            this.deck.add(i);
            this.deck.add(i);
        }
    }
}

class Player
{
    ArrayList<Integer> hand;
    int score;

    Player()
    {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public void score() //Check for 4 of a kind
    {
        for (int i = 0; i < this.hand.size(); ++i)
        {
            if (Collections.frequency(this.hand, this.hand.get(i)) == 4) //Might not work
            {
                this.score += this.hand.get(i);
                this.hand.removeAll(Collections.singleton(this.hand.get(i)));
            }
        }
    }

    public void set_hand(Game game, boolean player)
    {
        Random rand = new Random();

        for (int i = 0; i < 7; ++i)
        {
            int card = rand.nextInt(game.deck.size());
            this.hand.add(game.deck.get(card));
            game.deck.remove(card); //Might be wrong
        }

        Collections.sort(this.hand);
        this.score();

        if (player)
        {
            System.out.println("Your hand: " + this.hand + "\nYour Score: " + this.score);
        }
    }

    public void play(Player opp, Game game, int card, boolean player) //Only accept if choice is in deck
    {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        if (Collections.frequency(this.hand, card) != 0)
        {
            int len = this.hand.size();
    
            for (int i = 0; i < opp.hand.size(); ++i)
            {
                if (opp.hand.get(i) == card)
                {
                    this.hand.add(opp.hand.get(i));
                    opp.hand.remove(i);
                }
            }
    
            if (len == this.hand.size())
            {
                System.out.println("Go Fish!");
                int index_card = rand.nextInt(game.deck.size());
                this.hand.add(game.deck.get(index_card));
    
                if (game.deck.get(index_card) == card) //fish your wish
                {
                    if (player)
                    {
                        game.deck.remove(index_card);
                        System.out.println("You fished your wish, go again!");
                        System.out.println("\nWhat do you want to fish?");
                        int choice = sc.nextInt();
                        this.play(opp, game, choice, player);
                    }
                    else
                    {
                        game.deck.remove(index_card);
                        System.out.println("You fished your wish, go again!");
                        int choice = rand.nextInt(this.hand.size());
                        System.out.println("\n" + this.hand.get(choice));
                        this.play(opp, game, choice, player);
                    }
                }
    
                game.deck.remove(index_card);
            }
    
            Collections.sort(this.hand);
            this.score();
    
            if (player)
            {
                System.out.println("Your hand: " + this.hand + "\nYour Score: " + this.score);
            }
            else
            {
                System.out.println("Opponent score: " + this.score + "\n");
            }
        }
        else
        {
            System.out.println(card + " not in deck, try again");
            System.out.println("\nWhat do you want to fish?");
            int choice = sc.nextInt();
            this.play(opp, game, choice, player);
        }
    }
}

public class IRP 
{
    public static void main(String[] args) throws Exception 
    {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        Game board = new Game();
        Player player = new Player();
        Player cpu = new Player();
        int choice;

        player.set_hand(board, true);
        cpu.set_hand(board, false);

        while (board.deck.size() != 0)
        {
            System.out.println("What do you want to fish?\nYour hand: " + player.hand);
            choice = sc.nextInt();
            player.play(cpu, board, choice, true);

            choice = rand.nextInt(cpu.hand.size());
            System.out.println("\n" + cpu.hand.get(choice));
            cpu.play(player, board, cpu.hand.get(choice), false);
        }

        if (player.score >= cpu.score)
        {
            System.out.println("You win!!\nPlayer Score: " + player.score + "\nOpponent Score: " + cpu.score);
        }
        else
        {
            System.out.println("You lose :(\nPlayer Score: " + player.score + "\nOpponent Score: " + cpu.score);
        }
    }
}
