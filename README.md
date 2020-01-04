This Java program is divided into 2 parts.

PART 1
This tool creates an itemized list of all words that appear in a given text (.txt) file that the user defines. There are several rules that the tool follows, such as not counting a given word and the same word with "'s" at the end as 2 separate words. For instnce "Einstein" and "Einstein's" should not be counted as 2 words, so only 1 entry in the list will appear. As a point of interest, the chracters MC^2 should be treated as a word and remain formatted correctly.

The tool also counts the number of entries it writes and displays the number to the user.

It creates a file (.txt) in an "output files" directory. 

All text that you wish to parse should be within the "samples" directory.

Note: keep in mind that windows-1252 (cp1252) is the encoding utilized here. This is adjustable in the "readFile" method. Make sure
your given file is of the correct encoding, or unpredictable results may occur.

What I learned (and techniques I used) on this project:
- File I/O
- Encodings (windows-1252)
- Exception Handling

PART 2
This tool implements a Linked List data structure and tests its features. Data is gathered from the "Cell_Info.txt" file in the "samples" folder. 

What I learned (and techniques I used) on this project:
- File I/O
- Inheritance
- Interfaces
- Exception Handling
- Deep / Shallow Copying
- Linked Lists
- Inner Classes
