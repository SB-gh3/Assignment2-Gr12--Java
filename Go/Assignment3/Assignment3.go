package main

import(
	"fmt"
)

type Appointment struct{
	avail []bool
}

func (app *Appointment) setStruct(){
	app.avail = make([]bool, 62)

    for i := range app.avail {
        app.avail[i] = true
    }
}

func (app Appointment) reserve(start int, time int) bool{
	if start + time - 1 > 60{
		return false
	}

	for i := start; i < start + time; i++{
		if !app.avail[i]{
			return false
		}
	}

	for i := start; i < start + time; i++{
		app.avail[i] = false

		if i == start + time - 1{
			return true
		}
	}

	return false
}

func (app Appointment) book(start int, time int){
	check := app.avail[0]
	last := 0

	if app.reserve(start, time){
		fmt.Println("\n", start, "to", start + time - 1, "booked.\n\n---------------------")
	}else{
		fmt.Println("\n", start, "to", start + time - 1, "not availible.\n\n---------------------")
	}

	for i := range app.avail{
		if app.avail[i] != check || i == len(app.avail) - 1{
			if app.avail[i - 1]{
				fmt.Println("\nAvailible:", last, "-", i - 1)
				last = i
			}else{
				fmt.Println("\nUnavailible:", last, "-", i - 1)
				last = i
			}
			check = app.avail[i]
		}
	}
}

var loc []int = make([]int, 2)

func scramble(str string) string{
	var letr rune
	runeArr := []rune(str)

	for i := 0; i < len(runeArr); i++{
		if runeArr[i] == 'A' && i < len(runeArr) - 1 && runeArr[i + 1] != 'A'{
			letr = runeArr[i]
			runeArr[i] = runeArr[i + 1]
			runeArr[i + 1] = letr
			i++
		}
	}

	str = string(runeArr)
	return str;
}

func scrambleOrRemove(slice []string) []string{
	var arr []string

	for i := 0; i < len(slice); i++{
		str := scramble(slice[i])
		if slice[i] != str{
			arr = append(arr, str)
		}
	}

	return arr;
}

func getNext(grid [][]int, row int, col int) int{
	if col + 1 >= len(grid){
		loc[0] = loc[0] + 1
		return grid[row + 1][col]
	}else if row + 1 >= len(grid[row]){
		loc[1] = loc[1] + 1
		return grid[row][col + 1]
	}

	if grid[row + 1][col] < grid [row][col + 1]{
		loc[0] = loc[0] + 1
		return grid[row + 1][col]
	}else{
		loc[1] = loc[1] + 1
		return grid[row][col + 1]
	}
}

func getLoc(grid [][]int, val int) []int{
	for i := 0; i < len(grid); i++{
		for j := 0; j < len(grid[i]); j++{
			if grid[i][j] ==  val{
				loc[0] = i
				loc[1] = j
				return loc
			}
		}
	}

	return nil
}

func sumPath(grid [][]int, row int, col int) int{
	sum := grid[row][col]
	loc = getLoc(grid, grid[row][col])

	for i := 0; (loc[0] + 1) * (loc[1] + 1) < len(grid) * len(grid[row]); i++{
		sum += getNext(grid, loc[0], loc[1])
	}

	return sum;
}

func main(){
	choice := 0
	fmt.Println("1. Word Scramble\n2. Array Locations\n3. Appointment Booking")
	fmt.Scan(&choice)

	switch choice{
		case 1:
			word := ""
			var slice []string

			for word != "0"{
				fmt.Println("Enter word to scramble (capital letters only, 0 to stop)")
				fmt.Scan(&word)
				slice = append(slice, word)
			}

			fmt.Println(scrambleOrRemove(slice))

		case 2:
			grid := [][]int{{12, 30, 40, 25, 5}, {11, 3, 22, 15, 43}, {7, 2, 9, 20, 0}, {10, 17, 4, 19, 9}, {8, 33, 18, 6, 1}}
			fmt.Println(sumPath(grid, 1, 1))

		case 3:
			var app Appointment
			app.setStruct()
			app.book(30,12)
			app.book(10, 5)
			app.book(40,20)


		default:
			fmt.Println("Invalid")
	}
}