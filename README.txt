Copyright (C) 2015 Jeremy Cosel & James Fix

This directory contains a scanner and parser for the Python programming language.

You need JFlex, the lexical analyzer, and CUP, the parser generator, for this to compile.

I set it up on my machine by typing:

cd ~/Documents/MiniPython/src
cd /Scanner
jflex minipython.flex
cd ..
cd /Parser
java java_cup.Main -interface minipython.cup
cd ..
javac *.java

The parser can be tested with:
java TestParser test.py