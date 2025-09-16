def roman_to_int(numeral):
  
  roman_map = {
    "M": 1000, "D": 500, "C": 100, "L": 50, 
    "X": 10, "V": 5, "I": 1
  }

  subtraction_map = {
    "CM": 900, "CD": 400, "XC": 90,
    "XL": 40, "IX": 9, "IV": 4  
  }
  i = 0
  result = 0
  while i < len(numeral):
    if i + 1 < len(numeral) and numeral[i: i+2] in subtraction_map:
      result += subtraction_map[numeral[i:i+2]]
      i += 2
    else:
      result += roman_map[numeral[i]]
      i += 1
  return result


def int_to_roman(number):
  roman_map = [
    (1000, "M"), (900, "CM"), (500, "D"),
    (400, "CD"), (100, "C"), (90, "XC"),
    (50, "L"), (40, "XL"), (10, "X"), (9, "IX"),
    (5, "V"), (4, "IV"), (1, "I")
  ]

  result = ""
  for value, symbol in roman_map:
    while number >= value:
      result += symbol
      number -=value

  return result

def main():
  mode = input("Enter R to convert Roman to Integer, or 'I' to convert Integer to Roman").strip().upper()
  if mode == 'R':
    roman = input("Enter a Roman number: ").strip().upper()
    try:
      print("Integer value: ", roman_to_int(roman))
    except KeyError:
      print("Invalid Roman numeral.")
  elif mode == 'I':
    try:
      num = int(input("Enter an integer (1-3999): ").strip())
      if 1 <= num < 3999:
        print("Roman numeral:", int_to_roman(num))
      else:
        print("Number must be between 1 and 3999.")
    except ValueError:
      print("Invalid integer input.")
  else:
    print("Invalid mode selected.")

if __name__ == "__main__":
  main()