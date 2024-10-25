import google.generativeai as genai
import os
import re
import sys

os.environ["GEMINI_API_KEY"] = ""

genai.configure(api_key=os.environ["GEMINI_API_KEY"])
model = genai.GenerativeModel('gemini-1.5-flash')

subject = "math"
with open("previous_answers", "r") as f:
    string = f.read()
f.close()
print(string)
prompt = f"""""Create a Python dictionary where each key is a letter from "a" to "z" and the value is a list with two items:

A math-related word starting with that letter, avoid the words """"" + string + """"".
A question that could be answered by that word.
For example, for the letter 'a', the value might be ['angle', 'What is the measure of an angle in geometry?']. Each answer should be a one-word math concept related to the letter, and each question should be clear and concise. Please complete this for all letters, A to Z."""""
response = model.generate_content(prompt)
data_as_text = response.text
print(data_as_text)
pattern = r"'\w': \['([^']*)', '([^']*)'\]"
matches = re.findall(pattern, data_as_text)
string = ""

for match in matches:
    answer, question = match
    string = string + answer+","
    print(f"Answer: {answer}, Question: {question}")
print(string)
with open("previous_answers", "w") as previous_answers:
    previous_answers.write(string)
previous_answers.close()


