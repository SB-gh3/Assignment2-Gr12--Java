use std::io;
use rand::Rng;

struct Game
{
    deck : Vec<u8>
}

impl Default for Game 
{
    fn default() -> Game 
    {
        Game 
        {
            deck : vec![2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14]
        }
    }
}

struct Player
{
    hand : Vec<u8>,
    score : u8
}

impl Player
{
    fn new() -> Player
    {
        Player 
        {
            hand : Vec::new(),
            score : 0
        }
    }

    fn score(&mut self) //Check for 4 of a kind
    {
        for (_i, elem) in self.hand.clone().into_iter().enumerate()
        {
            if self.hand.iter().filter(|&n| *n == elem).count() == 4
            {
                self.score += elem;
                self.hand.retain(|&x| x != elem);
            }
        }
    }

    fn set_hand(&mut self, game : &mut Game, player : bool)
    {
        for _i in 0..7
        {
            let card : usize = rand::thread_rng().gen_range(0..game.deck.len());
            self.hand.push(game.deck[card]);
            game.deck.remove(card);
        }

        self.hand.sort();
        self.score();

        if player
        {
            println!("Your hand: {}\nYour Score: {}", format!("{:?}", self.hand), self.score);
        }
    }

    fn play(&mut self, opp : &mut Player, game : &mut Game, card : u8, player : bool) //Only accept if choice is in deck
    {
        let mut input = String::new();

        if self.hand.iter().filter(|&n| *n == card).count() != 0
        {
            let len : u8 = self.hand.len() as u8;
    
            for (_i, elem) in opp.hand.clone().into_iter().enumerate()
            {
                if elem == card
                {
                    self.hand.push(elem);
                    opp.hand.remove(opp.hand.iter().position(|x| *x == elem).unwrap());
                }
            }
    
            if len == self.hand.len() as u8
            {
                println!("Go Fish!");
                let index_card : usize = rand::thread_rng().gen_range(0..game.deck.len());
                self.hand.push(game.deck[index_card]);
    
                if game.deck[index_card] == card //fish your wish
                {
                    if player
                    {
                        game.deck.remove(index_card);
                        println!("You fished your wish, go again!");
                        println!("\nWhat do you want to fish?");
                        io::stdin().read_line(&mut input).expect("Failed to read input");
                        let choice : u8 = input.trim().parse().expect("Invalid input");
                        input.clear();
                        self.play(opp, game, choice, player);
                    }
                    else
                    {
                        game.deck.remove(index_card);
                        println!("You fished your wish, go again!");
                        let choice = rand::thread_rng().gen_range(0..self.hand.len() as u8);
                        println!("\n{}", self.hand[choice as usize]);
                        self.play(opp, game, choice, player);
                    }
                }
    
                game.deck.remove(index_card);
            }
    
            self.hand.sort();
            self.score();
    
            if player
            {
                println!("Your hand: {}\nYour score: {}", format!("{:?}", self.hand), self.score);
            }
            else
            {
                println!("Opponent score: {}\n", self.score)
            }
        }
        else
        {
            println!("{} not in deck, try again", card);
            println!("\nWhat do you want to fish?");
            io::stdin().read_line(&mut input).expect("Failed to read input");
            let choice : u8 = input.trim().parse().expect("Invalid input");
            input.clear();
            self.play(opp, game, choice, player);
        }
    }
}

fn main() 
{
    let mut board : Game = Game{..Default::default()};
    let mut player = Player::new();
    let mut cpu = Player::new();
    let mut input = String::new();
    let mut choice : u8;

    player.set_hand(&mut board, true);
    cpu.set_hand(&mut board, false);

    while board.deck.len() != 0
    {
        println!("What do you want to fish?\nYour hand: {}", format!("{:?}", player.hand));
        io::stdin().read_line(&mut input).expect("Failed to read input");
        choice = input.trim().parse().expect("Invalid input");
        input.clear();
        player.play(&mut cpu, &mut board, choice, true);

        choice = rand::thread_rng().gen_range(0..cpu.hand.len() as u8);
        println!("\n{}", cpu.hand[choice as usize]);
        cpu.play(&mut player, &mut board, cpu.hand[choice as usize], false);
    }

    if player.score >= cpu.score
    {
        println!("You win!!\nPlayer Score: {}\nOpponent Score: {}", player.score, cpu.score);
    }
    else
    {
        println!("You lose :(\nPlayer Score: {}\nOpponent Score: {}", player.score, cpu.score);
    }
}