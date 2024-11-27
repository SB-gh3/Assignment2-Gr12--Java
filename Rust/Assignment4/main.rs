use std::io;
use rand::Rng;

struct StepTracker
{
    check_active : i32,
    days_active : u8,
    total_steps : i32,
    total_days : u8
}

impl StepTracker
{
    fn new(active : i32) -> StepTracker
    {
        StepTracker
        {
            check_active : active,
            days_active : 0,
            total_steps : 0,
            total_days : 1,
        }
    }

    fn add_daily_steps(&mut self, steps : i32)
    {
        if self.total_steps != 0
        {
            self.total_days += 1;
        }

        self.total_steps += steps;

        if steps >= self.check_active
        {
            self.days_active += 1;
        }
    }

    fn active_days(&self) -> u8
    {
        return self.days_active;
    }

    fn average_steps(&self) -> f32
    {
        return self.total_steps as f32 / self.total_days as f32;
    }
}

struct Phrase
{
    stri : String
}

impl Phrase
{
    fn new(phrase : String) -> Phrase
    {
        Phrase
        {
            stri : phrase,
        }
    }

    fn find_nth(&self, val : String, n : u8) -> i8
    {
        let mut count : u8 = 0;

        for (i, _elem) in self.stri.chars().enumerate()
        {
            //let temp : &str = &self.stri[i..(i + val.len())];

            if i + val.len() - 1 < self.stri.len() && self.stri[i..(i + val.len())].eq(&val)
            {
                count += 1;

                if count == n
                {
                    return i as i8;
                }
            }
        }

        return -1;
    }

    fn find_last(&self, val : String) -> i8
    {
        let mut num : i8 = -1;

        for (i, _elem) in self.stri.chars().enumerate()
        {
            if i + val.len() - 1 < self.stri.len() && self.stri[i..(i + val.len())].eq(&val)
            {
                num = i as i8;
            }
        }

        return num;
    }

    fn replace_nth(&mut self, val : String, n : u8, repl : String)
    {
        let mut count : u8 = 0;

        for (i, _elem) in self.stri.clone().chars().enumerate()
        {
            if i + val.len() - 1 < self.stri.len() && self.stri[i..(i + val.len())].eq(&val)
            {
                count += 1;

                if count == n
                {
                    self.stri.replace_range(i..(i + val.len()), &repl);
                    //self.stri = self.stri.replace(&val, &repl); //Replaces all - FIX
                    return;
                }
            }
        }
    }
}

fn light_board(num_row : i32, num_col : i32, row : i32, col : i32) -> bool
{
    let board : &mut Vec<Vec<bool>> = &mut vec![vec![false; num_col as usize]; num_row as usize];
    let mut column : Vec<bool> = Vec::new();

    for (_i, elem) in board.into_iter().enumerate()
    {
        elem.iter_mut().map(|v| *v = if rand::thread_rng().gen_range(0..10) < 4 {true} else {false}).count();

        column.push(elem[col as usize]); //Unsafe?
    }

    if board[row as usize][col as usize]
    {
        if column.iter().filter(|&n| *n == true).count() % 2 == 0
        {
            return false
        }

        return true;
    }
    else
    {
        if column.iter().filter(|&n| *n == true).count() % 3 == 0
        {
            return true;
        }

        return false;
    }
}

fn main() 
{
    let mut input = String::new();
    println!("1. Light Board\n2. Step Tracker\n3. Phrases");
    io::stdin().read_line(&mut input).expect("Failed to read input");
    let choice : u8 = input.trim().parse().expect("Invalid input");
    input.clear();

    match choice
    {
        1_u8 => println!("{}", light_board(10, 10, 5, 3)),
        2_u8 =>
        {
            let mut track : StepTracker = StepTracker::new(10000);
            track.add_daily_steps(1023);
            track.add_daily_steps(10000);
            track.add_daily_steps(15000);
            println!("{}", track.active_days());
            println!("{}", track.average_steps());
        }
        3_u8 =>
        {
            let mut pl : Phrase = Phrase::new("A cat ate late".to_string());
            println!("{}", pl.find_nth("at".to_string(), 2));
            println!("{}", pl.find_last("at".to_string()));
            pl.replace_nth("at".to_string(), 1, "rane".to_string());
            println!("{}", pl.stri);
        }
        _ => println!("Error"),
    }
}