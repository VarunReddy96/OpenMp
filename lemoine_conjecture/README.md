<h2>Lemoine's Conjecture</h2>
<p><a name="system"></a></p>
<p>In 1894, French mathematician &Eacute;mile Lemoine (1840&ndash;1912) made this conjecture:</p>
<p></p>
<blockquote><strong>Every odd integer greater than 5 is the sum of a prime and twice a prime.</strong></blockquote>
<p>In other words, if&nbsp;<em>n</em>&nbsp;is odd and&nbsp;<em>n</em>&nbsp;&gt; 5, then&nbsp;<em>n</em>&nbsp;=&nbsp;<em>p</em>&nbsp;+ 2<em>q</em>&nbsp;for some primes&nbsp;<em>p</em>&nbsp;and&nbsp;<em>q</em>. The primes&nbsp;<em>p</em>&nbsp;and&nbsp;<em>q</em>&nbsp;might or might not be the same. Note that&nbsp;<em>p</em>&nbsp;must be odd;&nbsp;<em>q</em>&nbsp;might be odd or even. There might be more than one solution to the formula. Here are some examples:</p>
<p></p>
<blockquote>7 = 3 + 2&sdot;2&nbsp;<br />9 = 3 + 2&sdot;3 = 5 + 2&sdot;2&nbsp;<br />11 = 5 + 2&sdot;3 = 7 + 2&sdot;2&nbsp;<br />13 = 3 + 2&sdot;5 = 7 + 2&sdot;3&nbsp;<br />15 = 5 + 2&sdot;5 = 11 + 2&sdot;2&nbsp;<br />17 = 3 + 2&sdot;7 = 7 + 2&sdot;5 = 13 + 2&sdot;2&nbsp;<br />19 = 5 + 2&sdot;7 = 13 + 2&sdot;3&nbsp;<br />99 = 5 + 2&sdot;47 = 13 + 2&sdot;43 = 17 + 2&sdot;41 = 37 + 2&sdot;31 = 41 + 2&sdot;29 = 53 + 2&sdot;23 = 61 + 2&sdot;19 = 73 + 2&sdot;13 = 89 + 2&sdot;5&nbsp;<br />199 = 5 + 2&sdot;97 = 41 + 2&sdot;79 = 53 + 2&sdot;73 = 113 + 2&sdot;43 = 137 + 2&sdot;31 = 173 + 2&sdot;13 = 193 + 2&sdot;3</blockquote>
<p>The conjecture has been verified for&nbsp;<em>n</em>&nbsp;up to 10<sup>9</sup>. However, no one has been able to prove or disprove it in general.</p>
<p>You will write sequential and multicore parallel versions of a program that verifies Lemoine's Conjecture for all odd integers in a given range. For purposes of this project, we will assume that Lemoine's Conjecture is true.</p>

<p>You may use the provided class Prime in your programs. Class Prime contains an iterator over odd primes and a method to test whether an int is prime. You are not allowed to change class Prime in any way.</p>