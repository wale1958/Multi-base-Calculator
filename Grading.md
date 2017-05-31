| | Design (20)| Style (20) | Documentation (20) | Correctness & Efficiency (40) |
---------|  ---------| ---------|---------|---------|
Excellent| IS-A and HAS-A relationships are clear and logical; code is streamlined. (18-20) | Code has impeccable style. (18-20) | All javadoc is properly formatted, clear, concise. In-line comments are used judiciously and contribute to reader's understanding of code. "Works Cited" section is complete. (18-20) | Program works as specified; no crashes or unhelpful output. (35-40)  |
Adequate| Object-orientation is evident, but some relationships are not well thought out  (10-17)| Occasional problems with identifiers, spacing, etcc. (10-17) | Javadocs are present but difficult to read; in-line comments are excessive or underutilized; "Works Cited" section has problems. (10-17) | Program runs, but some behavior is missing or incorrect.  (20-34)          |
Needs Work | Little evidence of design consideration (0-16) | Code is very difficult to read/comprehend (0-9) | Javadoc, in-line comments, and/or "Works Cited" are minimal or missing.  (0-9)          |  Code doesn't run; program substantially fails to meet specs. (0-10)            |

Design =  17. You're doing sophisticated stuff with state--but let the *state* take care of it. All the stuff you're doing with `mode` etc in the Panel (like line 210 and after) should really be done *in* the state--the GUI code shouldn't have to know how the state works. 
Style = 20. `updateButtons()` is a nice method.  
Documentation =  20. Make sure you update javadocs.   
Corr/Eff =  35. Slider should affect all displayed values as well as buttons 
  
Total =  92 

Extra Credit =    
