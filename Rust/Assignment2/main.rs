use std::io;

fn roman_numeral(num : i32) -> String
{
    let mut rome = String::from("");
    let nums = String::from(num.to_string());
    let mut split_numbers : Vec<i32> = Vec::new();
    let mut count : i32 = 0;
    let mut check : i32 = 0;

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
            rome = format!("{}{}", "M".repeat(((check + 5)).try_into().unwrap()), rome);
        }
        
        count += 1;
    }

    String::from(rome)
}

fn main() 
{
    let mut input = String::new();
    println!("1. Roman Numerals");
    io::stdin().read_line(&mut input).expect("Failed to read input");
    let choice : i32 = input.trim().parse().expect("Invalid input");
    input.clear();

    if choice == 1
    {
        println!("Enter a number up to 9999");
        io::stdin().read_line(&mut input).expect("Failed to read input");
        let mut roman :i32 = input.trim().parse().expect("Invalid input");
        input.clear();

        while roman > 9999 || roman < 0 
        {
            println!("Enter a number up to 9999");
            io::stdin().read_line(&mut input).expect("Failed to read input");
            roman = input.trim().parse().expect("Invalid input");
            input.clear();
        }

        println!("{}", roman_numeral(roman));
    }
    else 
    {
        println!("Invalid entry");
    }
}