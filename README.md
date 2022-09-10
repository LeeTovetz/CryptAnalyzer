# CryptAnalyzer

**This is the final project of the first "Java Syntax" module at JavaRush University.**  
##
The program operates in several modes:<br>
1. Text Ecryption using a Caesar cipher.<br>
The Caesar cipher is a substitution cipher. Each character in the text is replaced by a character that is located at some constant positions number (key) to the right of it in the alphabet.<br>
**Example: "Hello world", Key = 1; Result = "Ifmmp xpsme"**<br>
<br>
2. Text Decipherment using a Caesar cipher.<br>
Using the known key, the ciphertext is decrypted using the Caesar cipher.<br>
**Example: "Ifmmp xpsm", Key = 1; Result = "Hello world"**<br>
<br>
3. Text Decipherment using Brute-force searching.<br>
There is a search of all keys. The decoder decrypts the text based on the metric of the most frequently used words.<br>
**Example: After going through all the keys, the decoder analyzed that with the key 11 in the text, words like "they", "are", "am", "an" occured most often.**<br> 
Therefore, 11 is the key you are looking for. The decryptor then decrypts the text using key 11.<br>
<br>
4. Text Decipherment using method of frequency statistical analysis method.<br>
Frequency analysis assumes that the frequency of occurrence of a given letter of the alphabet in sufficiently long texts is the same for different texts of the same language. If there is a symbol in the ciphertext with a similar probability of occurrence, then we can assume that it is the specified encrypted letter.<br>
**Example: In the encrypted text, the letter "c" is found most often. After analyzing the text of another work by the same author, the decoder calculated that the letter "e" is more common in this text. Accordingly, it changes "c" to "e".**<br>
