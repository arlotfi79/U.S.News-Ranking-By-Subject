# U.S.News Ranking By Subject

## Description

As you may know, U.S.News only shows rankings for the top 10 universities in each major for free. 
Other universities with their score are shuffled. Hence, in order to see the ranking for all universities, 
you need to whether buy a premium account or rank them manually.

In this repository, I have provided a semi-automatic way to do it. There are some steps that should be done before 
running the code which are listed as bellow:

1. First, Open the URL you want. For example open the [Best Computer Engineering Programs](https://www.usnews.com/best-graduate-schools/top-engineering-schools/computer-engineering-rankings).
2. Then, scroll down until you reach the end of the page.
3. Then, copy all the page content using ``CTRL+C`` and paste it in .txt file named **USNews.txt** using ``CTRL+V``.

Now, run the program and the results will be available a CVS file named **results.csv**.

## Dependencies
[JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) is used to write the code.