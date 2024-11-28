package main

import(
	"fmt"
	"strings"
)

type StringChecker struct{
	str string
	min int
	max int
}

func newCheck(str string) *StringChecker{
	return &StringChecker{str, 6, 20}
}

func (self StringChecker) isValid(in string) bool{
	if len(in) < self.min || len(in) > self.max{
		return false
	}

	if strings.Contains(in, self.str){
		return false
	}

	return true
}

type StudyPractice struct{
	fir int
	sec int
}

func (self StudyPractice) getProblem(){
	fmt.Println(self.fir, "times", self.sec)
}

func (self *StudyPractice) nextProblem(){
	self.sec++
}

func main(){
	choice := 0
	fmt.Println("1. String Checker\n2. Study Practice")
	fmt.Scan(&choice)

	switch choice{
		case 1:
			check := StringChecker{"pass", 5, 8}
			fmt.Println(check.isValid("password"), "\n", check.isValid("grapefruit"))
			checko := newCheck("glub")
			fmt.Println(checko.isValid("flubglub"), "\n", checko.isValid("hello"))

		case 2:
			study := StudyPractice{7, 3}
			study.getProblem()
			study.nextProblem()
			study.nextProblem()
			study.getProblem()

		default:
			fmt.Println("Invalid")
	}
}