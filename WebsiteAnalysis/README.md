Note: This project, WebsiteAnalysis, was done with a group of 4 individuals to analyze the static content on each page within a local copy of a website in the Java programming language; this includes an analysis of each page and the site as a whole. All names will be kept anonymous for confidentiality reasons. 

# Project Description

The goal of the “Website Analysis” project is to analyze and classify the links and files contained in a local copy of a website. The system processes websites with 1 to 1000 pages, providing an error message if the site contains fewer than 1 or more than 1000 pages. The project focuses on minimizing the data transfer required and maximizing the speed of analysis, benefiting users by providing efficient and thorough website analysis. The system outputs detailed results in JSON, text, and Excel formats, summarizing the analyzed content.

# Project Design

The project follows design decisions outlined in the following pages
  1. Website Analysis Brainstorming https://www.cs.odu.edu/~tkennedy/cs330/latest/Public/designWebsiteAnalysis00/
  2. Website Analysis Program Flow - https://www.cs.odu.edu/~tkennedy/cs330/latest/Public/designWebsiteAnalysis01/
  3. Website Analysis Integration Test Methodology - https://www.cs.odu.edu/~tkennedy/cs330/latest/Public/designWebsiteAnalysis02/
  4. Website Analysis A Little More Than Pseudocode - https://www.cs.odu.edu/~tkennedy/cs330/sum24/Public/designWebsiteAnalysis03/index.html
  5. Website Analysis Requirements Definitions - https://cstkennedy.github.io/course-site-mirror/cs330/Public/designWebsiteAnalysis00/websiteAnalysis.mmd.html

# Examples Used
1. Simple HTML Parser example from cs350 examples repository: https://github.com/cstkennedy/cs350-examples/tree/master/Office-Hours/OfficeHours-JSoup
2. For testClone function syntax: https://github.com/cstkennedy/cs350-examples/blob/master/JUnit-2/Example-8/src/test/java/edu/odu/cs/cs350/examples/TestRoster.java
3. Review on how to sort local webpages lexiographically: https://www.geeksforgeeks.org/java-program-to-sort-elements-in-lexicographical-order-dictionary-order/
4. Learning how to use assertThrows: https://www.geeksforgeeks.org/junit-5-expected-exception/
5. Learn how to check if a directory is accessbile: https://docs.oracle.com/javase/tutorial/essential/io/check.html
6. Learn how to check if a directory is accessible: https://www.baeldung.com/java-file-directory-exists#:~:text=assertFalse(new%20File(%22invalid,Path%20tempFilePath%20%3D%20Files.
7. Learn how to convert a path to a file for a filereader: https://blog.marcnuri.com/how-to-convert-file-to-path-in-java
8. Learn how to convert a path to a file for a filereader: https://www.geeksforgeeks.org/path-tofile-method-in-java-with-examples/
9. To check the file extensions of a path: https://www.geeksforgeeks.org/path-getfilename-method-in-java-with-examples/
