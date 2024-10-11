package main

import(
	"strconv"
	"fmt"
	"strings"
	"math"
	"math/rand/v2"
	// "log"
	// "os"
	// "time"

	// "github.com/faiface/beep"
	// "github.com/faiface/beep/mp3"
	// "github.com/faiface/beep/speaker"
)

type ArrayTester struct{
	vec2 [][]int
	vec []int
	col int
}

func (arrTest *ArrayTester) setStruct(vec2 [][]int, vec []int, col int) {
	arrTest.vec2 = vec2
	arrTest.vec = vec
	arrTest.col = col
}

func (arrTest ArrayTester) getColumn() []int{
	result := []int{}

	for i := 0; i < len(arrTest.vec2); i++{
		result = append(result, arrTest.vec2[i][arrTest.col - 1]);
	}

	return result;
}

func (arrTest ArrayTester) isDuplicate() bool{
	for i := 0; i < len(arrTest.vec2); i++{
		for j := 0; j < len(arrTest.vec2[i]); j++{
			for k := 0; k < len(arrTest.vec); k++{
				if arrTest.vec2[i][j] == arrTest.vec[k]{
					return true;
				}
			}
		}
	}

	return false;
}

func (arrTest ArrayTester) constainsFull() bool{
	check := false

        for i := 0; i < len(arrTest.vec2); i++{
            for j := 0; j < len(arrTest.vec2[i]); j++{
                if arrTest.vec2[i][j] == arrTest.vec[j]{
                    check = true;
                }else{
                    break;
                }
            }
        }

        if check{
            return true;
        }else{
			return false;
		}
}

func reverse(s string) string { 
    rns := []rune(s) // convert to rune 
    for i, j := 0, len(rns)-1; i < j; i, j = i+1, j-1 { 
  
        // swap the letters of the string, 
        // like first with last and so on. 
        rns[i], rns[j] = rns[j], rns[i] 
    } 
  
    // return the reversed string. 
    return string(rns) 
} 

func romanNumeral(num int) string {
	var rome string
	nums := strconv.Itoa(num)
	splitNums := []int{}
	count, check := 0, 0.0
	nums = reverse(nums)

	for i := 0; i < len(nums); i++ {
		splitNums = append(splitNums, int(nums[i] - '0')) // May not work
	}

	for i := 0; i < len(splitNums); i++ {
		check = float64(splitNums[i]) - 5.0

		if count == 0{
			if check <= -2{
				rome = strings.Repeat("I", int(check + 5.0)) + rome;
			} else if check <= 0{
				rome = strings.Repeat("I", int(math.Abs(check))) + "V" + rome;
			} else if check <= 3{
				rome = "V" + strings.Repeat("I", int(check)) + rome;
			} else if check == 4{
				rome = strings.Repeat("I", int(check - 3.0)) + "X" + rome;
			}
		}else if count == 1{
			if check <= -2{
				rome = strings.Repeat("X", int(check + 5.0)) + rome;
			} else if check <= 0{
				rome = strings.Repeat("X", int(math.Abs(check))) + "L" + rome;
			} else if check <= 3{
				rome = "L" + strings.Repeat("X", int(check)) + rome;
			} else if check == 4{
				rome = strings.Repeat("X", int(check - 3.0)) + "C" + rome;
			}
		}else if count == 2{
			if check <= -2{
				rome = strings.Repeat("C", int(check + 5.0)) + rome;
			} else if check <= 0{
				rome = strings.Repeat("C", int(math.Abs(check))) + "D" + rome;
			} else if check <= 3{
				rome = "D" + strings.Repeat("C", int(check)) + rome;
			} else if check == 4{
				rome = strings.Repeat("C", int(check - 3.0)) + "M" + rome;
			}
		}else if count == 3{
			rome = strings.Repeat("M", int(check + 5.0)) + rome;
		}

		count++;
	}

	return rome
}

func guessingGame(player int, comp int) bool{
	num := rand.IntN(10)
	cguess := rand.IntN(10)
	pguess := 0
	fmt.Println("Enter your guess: ")
	fmt.Scan(&pguess)

	player -= int(math.Abs(float64(num - pguess)));
    comp -= int(math.Abs(float64(num - cguess)));

	fmt.Println("Player Guess: ", pguess, "\nComputer Guess: ", cguess, "\nActual Number: ", num, "\n\nPlayer points: ", player, "\nComputer points: ", comp)

	if comp <= 0{
		return true;
	}else if player <= 0{
		return false;
	}

    guessingGame(player, comp);

	return true;
}

// Go doesn't allow curly braces formatted other than this, why?????
func main() {
	choice := 0
    fmt.Println("1. Roman Numerals\n2. Guessing Game\n3. Array Tester")
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
			if guessingGame(7, 7){
				// Enable later
				// f, err := os.Open("tf.mp3")
				// if err != nil {
				// 	log.Fatal(err)
				// }

				// streamer, format, err := mp3.Decode(f)
				// if err != nil {
				// 	log.Fatal(err)
				// }
				// defer streamer.Close()

				// speaker.Init(format.SampleRate, format.SampleRate.N(time.Second/10))

				// done := make(chan bool)
				// speaker.Play(beep.Seq(streamer, beep.Callback(func() {
				// 	done <- true
				// })))

				// <-done
			} else{
				// f, err := os.Open("victorymale-version-230553.mp3")
				// if err != nil {
				// 	log.Fatal(err)
				// }

				// streamer, format, err := mp3.Decode(f)
				// if err != nil {
				// 	log.Fatal(err)
				// }
				// defer streamer.Close()

				// speaker.Init(format.SampleRate, format.SampleRate.N(time.Second/10))

				// done := make(chan bool)
				// speaker.Play(beep.Seq(streamer, beep.Callback(func() {
				// 	done <- true
				// })))

				// <-done
			} 

		case 3:
			var arrTest ArrayTester
			vec := []int{1, 2, 0}
			vec2 := [][]int{{0,1,2}, {3,4,5}, {6,7,8}, {9,5,3}}
			arrTest.setStruct(vec2, vec, 3)

			fmt.Println(arrTest.getColumn())
			fmt.Println(arrTest.isDuplicate())
			fmt.Println(arrTest.constainsFull())

		default:
			fmt.Println("Invalid input")
	}
}