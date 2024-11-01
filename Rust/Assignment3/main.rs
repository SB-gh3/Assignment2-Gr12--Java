use std::io;
use primes::is_prime;

struct StarryNight;
impl StarryNight
{
    fn convert(&self, row : i32, col : i32, lighti : &Vec<f32>) -> Vec<Vec<f32>>
    {
        if row * col != lighti.len() as i32
        {
            println!("Invalid");
            return vec![];
        }

        let mut lightf : Vec<Vec<f32>> = vec![];

        for _i in 0..col
        {
            lightf.push(vec![]);
        }

        for (i, elem) in lighti.into_iter().enumerate()
        {
            if ((i as i32 / row) + 1) % 2 != 0
            {
                lightf[i / row as usize].push(*elem);
            }
            else
            {
                lightf[i / row as usize].insert(0, *elem);
            }
        }

        return lightf;
    }
}

fn circular() -> Vec<u32>
{
    let mut primes : Vec<u32> = Vec::new();
    let mut check : bool;
    let mut org : String;

    for i in (100 as u32)..999
    {
        check = false;
        org = i.to_string();

        if is_prime(i as u64) && !org.contains("0")
        {
            for _j in 0..2
            {
                org += &(org.chars().nth(0).unwrap()).to_string();
                org.remove(0);

                if is_prime(org.parse().unwrap())
                {
                    check = true;
                }
                else
                {
                    check = false;
                    break;
                }
            }
        }

        if check
        {
            primes.push(i);
        }
    }

    return primes;
}

fn latin(squares : &Vec<Vec<i32>>) -> bool
{
    let mut col : Vec<i32> = Vec::new();

    for (i, _elem) in squares.into_iter().enumerate()
    {
        for k in 0..squares.len()
        {
            col.push(squares[k][i]);
        }

        for (j, elemj) in squares[i].clone().into_iter().enumerate()
        {
            if squares[0].iter().filter(|&n| *n == elemj).count() != 1 //First row dupe checker
            {
                return false;
            }

            if !squares[i].contains(&squares[j][i]) //Check row
            {
                return false;
            }

            if col.iter().filter(|&n| *n == elemj).count() != 1 //Check column
            {
                return false;
            }
        }

        col.clear();
    }

    return true;
}

fn main() 
{
    let mut input = String::new();
    println!("1. Circular Primes\n2. Latin Squares\n3. Night Sky");
    io::stdin().read_line(&mut input).expect("Failed to read input");
    let choice : u8 = input.trim().parse().expect("Invalid input");
    input.clear();
    let vec = vec![vec![10, 30, 20, 0], vec![0, 20, 30, 10], vec![30, 0, 10, 20], vec![20, 10, 0, 30]];
    let light = vec![0.3, 0.7, 0.8, 0.4, 1.4, 1.1, 0.2, 0.5, 0.1, 1.6, 0.6, 0.9];
    let star: StarryNight = StarryNight;

    match choice
    {
        1_u8 => println!("{}", format!("{:?}", circular())),
        2_u8 => println!("{}: {}", format!("{:?}", vec), latin(&vec)),
        3_u8 => println!("{}", format!("{:?}", star.convert(3, 4, &light))),
        _ => println!("ERROR"),
    }
}