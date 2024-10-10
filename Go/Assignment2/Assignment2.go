package main

import(
	"strconv"
	"fmt"
)

func romanNumeral(num int) string {
	var rome string
	nums := strconv.Itoa(num)
	splitNums := []int{}
	count, check := 0, 0

	for i := 0; i < len(nums); i++ {
		splitNums = append(splitNums, int(nums[i] - '0')) // May not work
	}

	for i := 0; i < len(splitNums); i++ {
		check = splitNums[i] - 5

		if count == 0{
			if check <= -2{

			}
		}

		count++;
	}

	return rome
}

// Go doesn't allow curly braces formatted other than this, why?????
func main() {
	choice := 0
    fmt.Println("1. Roman Numerals\n2. Harmonic Mean\n3. Secret Alphabet\n4. Reaction Time Test\n5. Guessing Game\n6. Array Stuff\n7. Four Patterns")
	fmt.Scan(&choice)
	
	switch choice {
		case 1:
			roman := 0
			fmt.Println("Enter a number (up to 9999)")
			fmt.Scan(&roman)

			for roman > 9999 || roman < 1 { // for is the only loop
				fmt.Println("Enter a number (up to 9999)")
				fmt.Scan(&roman)
			}

			fmt.Println(romanNumeral(roman))
		case 2:

		default:
			fmt.Println("Invalid input")
	}
}