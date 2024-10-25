import google.generativeai as genai
import os
import re
import sys

os.environ["GEMINI_API_KEY"] = "AIzaSyD6IpV7x85wclV2X87vb-F_VqyEKoohW08"

genai.configure(api_key=os.environ["GEMINI_API_KEY"])
model = genai.GenerativeModel('gemini-1.5-flash')

subject = sys.argv[1]

prompt = f"dictionary = {{'a':['answer','question'], 'b':['answer','question']}} give me a like this python dict a to z. answer should start with the key and be only 1 word. the subject is {subject}"
response = model.generate_content(prompt)
data_as_text = response.text

pattern = r"\{.*?\}"  
match = re.search(pattern, data_as_text, re.DOTALL)

if match:
    cleaned_dict_text = match.group(0)
    try:
        math_dict = eval(cleaned_dict_text)
        
        for key, value in math_dict.items():
            print(f"{key}:{value[0]} - {value[1]}")

    except SyntaxError as e:
        print(f"Error: {e}")
else:
    print("No dictionary found in the response text.")





