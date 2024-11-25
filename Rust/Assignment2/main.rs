use std::io;
use rand::Rng;
//use std::io::BufReader;

struct ArrayTester;
impl ArrayTester
{
    fn get_column(&self, vec : Vec<Vec<i32>>, col : i32) -> Vec<i32>
    {
        let mut result : Vec<i32> = vec![];

        for (i, _elem) in vec.clone().into_iter().enumerate()
        {
            result.push(vec[i][(col - 1) as usize])
        }

        return result;
    }

    fn is_duplicate(&self, vec: Vec<Vec<i32>>, vec2 : Vec<i32>) -> bool
    {
        for (i, elem) in vec.clone().into_iter().enumerate()
        {
            for j in elem
            {
                for (k, _elem) in vec2.clone().into_iter().enumerate()
                {
                    if vec[i][j as usize] == vec2[k]
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    fn contains_full(&self, vec: Vec<Vec<i32>>, vec2 : Vec<i32>) -> bool
    {
        let mut check : bool = false;

        for (i, elem) in vec.clone().into_iter().enumerate()
        {
            for j in elem
            {
                if vec[i][j as usize] == vec2[j as usize]
                {
                    check = true;
                }
                else
                {
                    break;
                }
            }
        }

        if check
        {
            return true;
        }

        return false;
    }
}

fn roman_numeral(num : i32) -> String
{
    let mut rome = String::from("");
    let nums = String::from(num.to_string());
    let mut split_numbers : Vec<i32> = Vec::new();
    let mut count : i32 = 0;
    let mut check : i32;

    for c in nums.chars().rev()
    {
        split_numbers.push(c as i32 - 0x30)
    }

    for (_i, elem) in split_numbers.into_iter().enumerate()
    {
        check = elem - 5;

        if count == 0
        {
            if check <= -2
            {
                rome = format!("{}{}", "I".repeat((check + 5).try_into().unwrap()), rome);
            }
            else if check <= 0
            {
                rome = format!("{}{}{}", "I".repeat(check.abs().try_into().unwrap()), "V", rome);
            }
            else if check <= 3
            {
                rome = format!("V{}{}", "I".repeat(check.try_into().unwrap()), rome);
            }
            else if check == 4
            {
                rome = format!("{}{}{}", "I".repeat((check - 3).try_into().unwrap()), "X", rome);
            }
        }
        else if count == 1
        {
            if check <= -2
            {
                rome = format!("{}{}", "X".repeat((check + 5).try_into().unwrap()), rome);
            }
            else if check <= 0
            {
                rome = format!("{}{}{}", "X".repeat(check.abs().try_into().unwrap()), "L", rome);
            }
            else if check <= 3
            {
                rome = format!("L{}{}", "X".repeat(check.try_into().unwrap()), rome);
            }
            else if check == 4
            {
                rome = format!("{}{}{}", "X".repeat((check - 3).try_into().unwrap()), "C", rome);
            }
        }
        else if count == 2
        {
            if check <= -2
            {
                rome = format!("{}{}", "C".repeat((check + 5).try_into().unwrap()), rome);
            }
            else if check <= 0
            {
                rome = format!("{}{}{}", "C".repeat(check.abs().try_into().unwrap()), "D", rome);
            }
            else if check <= 3
            {
                rome = format!("D{}{}", "C".repeat(check.try_into().unwrap()), rome);
            }
            else if check == 4
            {
                rome = format!("{}{}{}", "C".repeat((check - 3).try_into().unwrap()), "M", rome);
            }
        }
        else if count == 3
        {
            rome = format!("{}{}", "M".repeat((check + 5).try_into().unwrap()), rome);
        }
        
        count += 1;
    }

    String::from(rome)
}

fn guess(mut player : i32, mut comp : i32) -> bool
{
    let mut input = String::new();
    let num = rand::thread_rng().gen_range(0..10);
    println!("Enter Guess:");
    io::stdin().read_line(&mut input).expect("Failed to read input");
    let pguess : i32 = input.trim().parse().expect("Invalid input");
    input.clear();
    let cguess : i32 = rand::thread_rng().gen_range(0..10);

    player -= (num - pguess).abs();
    comp -= (num - cguess).abs();

    println!("Player Guess: {}\nComputer Guess: {}\nSelected Number: {}\nPlayer Points: {}\nComputer Points: {}", pguess, cguess, num, player, comp);

    if comp <= 0
    {
        return true;
    }
    else if player <= 0
    {
        return false;
    }
    
    guess(player, comp);
    
    return false;
}

fn main() 
{
    let mut input = String::new();
    println!("1. Roman Numerals\n2. Guessing Game\n3. Array Tester");
    io::stdin().read_line(&mut input).expect("Failed to read input");
    let choice : i32 = input.trim().parse().expect("Invalid input");
    input.clear();

    if choice == 1
    {
        println!("Enter a number up to 9999");
        io::stdin().read_line(&mut input).expect("Failed to read input");
        let mut roman :i32 = input.trim().parse().expect("Invalid input");
        input.clear();

        while roman > 9999 || roman < 1 
        {
            println!("Enter a number up to 9999");
            io::stdin().read_line(&mut input).expect("Failed to read input");
            roman = input.trim().parse().expect("Invalid input");
            input.clear();
        }

        println!("{}", roman_numeral(roman));
    }
    if choice == 2
    {
        if guess(7, 7)
        {
            // // Get an output stream handle to the default physical sound device
            // let (_stream, stream_handle) = OutputStream::try_default().unwrap();
            // // Load a sound from a file, using a path relative to Cargo.toml
            // let file = BufReader::new(File::open("./tf.mp3").unwrap());
            // // Decode that sound file into a source
            // let source = Decoder::new(file).unwrap();
            // // Play the sound directly on the device
            // stream_handle.play_raw(source.convert_samples());

            // // The sound plays in a separate audio thread,
            // // so we need to keep the main thread alive while it's playing.
            // std::thread::sleep(std::time::Duration::from_secs(7));

            // let (_stream, handle) = rodio::OutputStream::try_default().unwrap();
            // let sink = rodio::Sink::try_new(&handle).unwrap();

            // let file = std::fs::File::open("./tf.mp3").unwrap();
            // sink.append(rodio::Decoder::new(BufReader::new(file)).unwrap());

            // sink.sleep_until_end();
        }
        else
        {
            // // Get an output stream handle to the default physical sound device
            // let (_stream, stream_handle) = OutputStream::try_default().unwrap();
            // // Load a sound from a file, using a path relative to Cargo.toml
            // let file = BufReader::new(File::open("./victorymale-version-230553.mp3").unwrap());
            // // Decode that sound file into a source
            // let source = Decoder::new(file).unwrap();
            // // Play the sound directly on the device
            // stream_handle.play_raw(source.convert_samples());

            // // The sound plays in a separate audio thread,
            // // so we need to keep the main thread alive while it's playing.
            // std::thread::sleep(std::time::Duration::from_secs(2));
        }
    }
    if choice == 3
    {
        let vec2 : Vec<Vec<i32>> = vec![vec![0,1,2], vec![3,4,5], vec![6,7,8], vec![9,5,3]];
        let vec : Vec<i32> = vec![1,2,0];
        let atest : ArrayTester = ArrayTester;

        println!("Column 3 is {:?}", atest.get_column(vec2.clone(), 3));
        println!("Has duplicate: {:?}", atest.is_duplicate(vec2.clone(), vec.clone()));
        println!("Contains full: {:?}", atest.contains_full(vec2, vec));
    }
    else 
    {
        println!("Invalid entry");
    }
}