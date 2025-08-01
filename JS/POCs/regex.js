// Literal Notation:
// const regex1 = /pattern/;
// console.log(typeof regex1); 

// const dynamicPattern = "hello";
// const regex2 = new RegExp(dynamicPattern);
// console.log(regex2);

// const str = "hello world"; 
// console.log(/world/.test(str));
// console.log(/hi/.test(str));

// // RegExp.prototype.test():
// console.log(/cat/.test("The cat sat on the mat.")); 
// console.log(/dog/.test("The cat sat on the mat.")); 

// // String.prototype.search():
// console.log("apple pie".search(/pie/)); // 6
// console.log("apple pie".search(/cake/)); // -1


// Metacharacters
// Dot (.) - Any Character/number:
// console.log(/a.b/.test("afb"));

// * (Zero or More): /a*/ matches "", "a", "aa", "aaa"
// console.log(/a*/.test("fly"));

// + (One or More): /a+/ matches "a", "aa", "aaa" (but not "")
// console.log(/a+/.test("fsf"));

// ? (Zero or One): /colou?r/ matches "color" and "colour"
// console.log(/abs?/.test("ajkls"));

// Write a regex to find all variations of 'gray' or 'grey'." (Show /gr(a|e)y/
// console.log(/gr(a|e)y/.test("gray"));

// // {n} (Exactly n times): /a{3}/ matches "aaa"
// console.log(/a{3}/.test("aa"));

// // {n,} (At least n times): /a{2,}/ matches "aa", "aaa", "aaaa"
// console.log(/a{3}/.test("aa"));

// // {n,m} (Between n and m times): /a{1,3}/ matches "a", "aa", "aaa"
// console.log(/a{3, 5}/.test("aaaaaa"));

// Find words with exactly 5 letters

// Character Sets ([]) - Any Character Within the Brackets:
// /[aeiou]/ matches any vowel.
// console.log(/[aeiou]/.test("fly"))

// /[0-9]/ matches any digit. (Shorthand: \d)
// console.log(/[0-9]/.test("nilesh1"))
// console.log(/\d/.test("fly1"))


// /[a-zA-Z]/ matches any letter (uppercase or lowercase).
// console.log(/[a-zA-Z]/.test("na"))

// /[^aeiou]/ matches any character not a vowel. (The ^ inside [] means negation)
// console.log(/[^aeiou]/.test("b")); // true — 'b' is not a vowel
// console.log(/[^aeiou]/.test("a")); // false — 'a' is a vowel

// Activity: "Find any character that is not a digit."
// console.log(/[^0-9]/.test("nilesh13"))

// Anchors: Position Matters

// ^ (Start of string): /^hello/ matches "hello world" but not "world hello"

// $ (End of string): /world$/ matches "hello world" but not "world hello"

// Activity: "Write a regex that matches only strings that start with 'The' and end with 'end'."


// Alternation (|) - OR Operator:

// /cat|dog/ matches "cat" or "dog"

// Activity: "Find either 'apple' or 'orange'."

// Grouping (()) - Capture and Apply Quantifiers:

// /(ab)+/ matches "ab", "abab", "ababab"

// Activity: "Match 'Ha' repeated 2 or more times, e.g., 'HaHa', 'HaHaHa'." (Show / (Ha){2,}/ )



// let regexp = /patterns/i;
// console.log(regexp.test("Design Patterns"))
//or
// let regexp = new RegExp(/pattern/i);
// console.log(regexp.test("Design Patterns")); 
