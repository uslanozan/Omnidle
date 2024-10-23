import google.generativeai as genai
import os
import re
import json

os.environ["GEMINI_API_KEY"] = ""

genai.configure(api_key=os.environ["GEMINI_API_KEY"])
model = genai.GenerativeModel('gemini-1.5-flash')

response = model.generate_content("dictionary = {"'a'":[answer,question], "'b'":[answer,question]} give me a like this python dict a to z . answer should start with the key and be only 1 word. the subject is math")
data_as_text = response.text

pattern = r"\{.*?\}"  
match = re.search(pattern, data_as_text, re.DOTALL)

if match:
    cleaned_dict_text = match.group(0)
    try:
        math_dict = eval(cleaned_dict_text) 
    except SyntaxError as e:
        print(f"Error: {e}")
else:
    print("No dictionary found in the response text.")

with open("data.json", "w") as outfile: 
    json.dump(math_dict, outfile)




