file_name = "17-342.txt"
min_multiple_37 = float('inf')
max_multiple_73 = float('-inf')
pair_count = 0
min_sum = float('inf')

with open(file_name, "r") as file:
    numbers = [int(line.strip()) for line in file]

for number in numbers:
    if number % 37 == 0 and number < min_multiple_37:
        min_multiple_37 = number
    if number % 73 == 0 and number > max_multiple_73:
        max_multiple_73 = number

for i in range(len(numbers) - 1):
    if (numbers[i] == min_multiple_37 and numbers[i+1] != max_multiple_73) or (numbers[i] != min_multiple_37 and numbers[i+1] == max_multiple_73):
        pair_count += 1
        pair_sum = numbers[i] + numbers[i+1]
        if pair_sum < min_sum:
            min_sum = pair_sum

print("Количество найденных пар:", pair_count)
print("Минимальная сумма элементов среди таких пар:", min_sum)
